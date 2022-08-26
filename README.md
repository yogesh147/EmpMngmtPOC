# EmpMngmtPOC

Project of Spring Boot + MYSQL + Spring JPA 

echo "# EmpMngmtPOC" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/yogesh147/EmpMngmtPOC.git
git push -u origin main

git remote add origin https://github.com/yogesh147/EmpMngmtPOC.git
git branch -M main
git push -u origin main

curl -i -X GET http://localhost:6868/api/employee/

curl -i -X GET http://localhost:8080/api/employee/
curl -i -X DELETE http://localhost:8080/api/employee/task/7
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"Emp2","tasks":[{"taskName":"Handling5"},{"taskName":"Pluging5"}],"department":{"name":"Water Supply5"},"role":{"name":"Technician"},"users":[{"name":"A"},{"name":"B"}]}' http://localhost:8080/api/employee/
curl -i -X PUT -H 'Content-Type: application/json' -d '{"id":5,"name":"Emp1","tasks":[{"taskName":"Handling2"},{"taskName":"Pluging2"},{"taskName":"setter"}],"departmentName":"Water"}' http://localhost:8080/api/employee/

FROM openjdk:8
ADD target/*.jar /home/poc.jar
CMD ["java","-jar","/home/poc.jar"]

docker build -t java-app .
docker run java-app

https://www.bezkoder.com/docker-compose-spring-boot-mysql/

docker-compose up -d
docker ps
docker images
docker-compose down
docker-compose down --rmi all