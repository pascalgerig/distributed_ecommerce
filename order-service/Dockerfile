FROM maven:3.8.1-openjdk-11
COPY . .
RUN mvn clean install
CMD ["java", "-jar", "target/order-service-0.1.jar"]