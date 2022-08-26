FROM maven:3.6.3-jdk-8
WORKDIR /EmployeeManagement
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run