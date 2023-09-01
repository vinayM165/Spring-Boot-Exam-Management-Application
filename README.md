# Spring-Boot-Exam-Management-Application
Springboot Monolithic Restful App for Exam/Quiz management

This is a Spring Boot monolithic application for Quiz/Exam management system. The application allows users with admin/teacher roles to create quizzes/exams and add questions based on topics and difficulties. Users with student identity can appear for the quiz/exam and will get results based on the quiz they appeared for. The application includes Java Spring Security for authentication and authorization, where authentication is implemented with JPA and PostgreSQL cloud database. JUnit tests have been implemented for all three layers i.e. Controller, Service, and Repository layers where Mockito has been used to mock the database for testing purposes.

## Prerequisites
```
Java 8 or higher
Maven
PostgreSQL
```

## Installation
```
Clone the repository.
Create a PostgreSQL database.(In my case I have used elephantSQL cloud database service)
Update the application.properties file with your database credentials.
Run the following command to build the project:
mvn clean install
```
Run the following command to start the application:
```
java -jar target/quizspringboot.jar
```
## Usage
To create a quiz/exam, log in as a user with admin/teacher roles.
Click on the “/quiz” end point and add questions based on topics and difficulties at "/question" endpoints.
To appear for a quiz, log in as a user with student identity.
After appearing for the quiz, you will get results based on the quiz you appeared for.

## Testing
To run the JUnit tests that have been implemented in your application, run the following command:
```
mvn test
```

## Contributing
Contributions are welcome! Please feel free to submit a pull request.


## Contact
If you have any questions or suggestions about this project, please feel free to contact me at mandgevinay16@gmail.com.
