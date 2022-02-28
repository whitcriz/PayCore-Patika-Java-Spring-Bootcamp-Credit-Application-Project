# PayCore & Patika Java Spring Bootcamp Credit Application System as Capstone Project

---

This is a credit application management project that uses credit score service project in order to get the customer's updated credit score whenever a customer applies for credit. Java is the projects's programming language and Spring Boot is the main framework for creating the projects. RabbitMQ as a message broker is used to provide communication between the projects during applications. I used RabbitMQ because an asynchronous communication would be more effective for the sake of interaction with the customers. Used Hibernate ORM to map the database and application objects properly.Postman is used to test endpoints and added a Postman collection here :

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/19503180-e91316ee-d7bb-415c-86bb-9d3aaf529047?action=collection%2Ffork&collection-url=entityId%3D19503180-e91316ee-d7bb-415c-86bb-9d3aaf529047%26entityType%3Dcollection%26workspaceId%3Dfdc1d7fa-9dbf-483f-81df-3e505e7d6208)

Used PostgreSQL as a relational database. Because I wanted to keep separate the business logic code from others, which is presentation layer with end points and the database ORM layer. In this way, anyone can both easily read and understand my code organization, also noone can not reach my business logic and this removes several vulnerabilities onto application. I also seperated the credit score service and credit application because even if they are dependent and share the same entity, credit score serviceis need to be away from customer interaction, it just can be used as an inner service, as a helpful service.

Credit application project runs at `8080` port, credit score service runs at `8085` port and RabbitMQ management port is `15672`. 