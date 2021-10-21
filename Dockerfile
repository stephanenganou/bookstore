ARG JDK_IMAGE=11
ARG JRE_IMAGE=11

FROM openjdk:${JDK_IMAGE} as builder
WORKDIR application
ARG JAR_FILE=target/tracker-0.0.1-SNAPSHOT*.jar
COPY ${JAR_FILE} web_book_tracker_apps.jar
RUN java -Djarmode=layertools -jar web_book_tracker_apps.jar extract

FROM openjdk:${JRE_IMAGE}

# add a user for security aspects
RUN addgroup --system springboot
RUN adduser --system --group springboot

RUN mkdir -p /application
RUN chown springboot /application

USER springboot

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]