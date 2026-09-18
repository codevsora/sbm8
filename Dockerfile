# syntax=docker/dockerfile:1.7
#
# Multi-stage build for KursusePunkt.
#
#   Stage 1 (deps)  — resolve Maven dependencies, cached separately from source
#   Stage 2 (build) — compile and package, then extract the layered jar
#   Stage 3 (run)   — minimal JRE image, non-root, no build tools
#
# Build:  docker build -t kursusepunkt:local .
# Run:    docker run --rm -p 8080:8080 kursusepunkt:local

# ---------------------------------------------------------------- dependencies
FROM maven:3.9-eclipse-temurin-26 AS deps
WORKDIR /build

# Copy only what affects dependency resolution, so this layer is reused
# whenever source changes but the pom does not.
COPY pom.xml ./
RUN mvn -B dependency:go-offline

# ---------------------------------------------------------------------- build
FROM deps AS build
WORKDIR /build

COPY src/ src/
RUN mvn -B clean package -DskipTests

# Spring Boot layered jars split dependencies from application code, so a code
# change does not invalidate the (much larger) dependency layer in the registry.
RUN java -Djarmode=tools -jar target/*.jar extract --layers --launcher --destination extracted

# ---------------------------------------------------------------------- runtime
FROM eclipse-temurin:26-jre AS runtime

# Never run as root. UID 1001 is arbitrary but fixed, so volume permissions
# stay predictable across rebuilds.
RUN groupadd --system --gid 1001 spring \
 && useradd  --system --uid 1001 --gid spring --create-home spring

WORKDIR /app

# Copy in layer order: least likely to change first.
COPY --from=build --chown=spring:spring /build/extracted/dependencies/          ./
COPY --from=build --chown=spring:spring /build/extracted/spring-boot-loader/    ./
COPY --from=build --chown=spring:spring /build/extracted/snapshot-dependencies/ ./
COPY --from=build --chown=spring:spring /build/extracted/application/           ./

USER spring

EXPOSE 8080

# Container-aware defaults. MaxRAMPercentage matters: without it the JVM sizes
# the heap from the host's memory, not the container limit, and gets OOM-killed.
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"
ENV SPRING_PROFILES_ACTIVE=docker

HEALTHCHECK --interval=15s --timeout=3s --start-period=40s --retries=5 \
  CMD ["java", "-version"]
# A real HTTP healthcheck is defined in compose.yaml, where curl is available
# via the actuator endpoint; the JRE image deliberately has no curl or wget.

ENTRYPOINT ["java", "-jar", "app.jar"]
