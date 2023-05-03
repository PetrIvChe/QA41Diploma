##Запуск
1. gitclone  https://github.com/PetrIvChe/QA41Diploma.git
2. docker-compose up
3. For SQL
* java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
4. For PostgresSQL 
* java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
5. For SQL 
* ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
6. For PostgresSQL
* ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"

//Запуск в режиме дебага
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" --debug-jvm
when see  in terminal << Listening for transport dt_socket at address: 5005 >>

Run -> Attach to proces -> Java