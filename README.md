# Hostel Management System Overview
## Introduction
The Hostel Management System is an advanced, event-driven microservices architecture designed to streamline hostel operations. Built using Java Spring Boot and adhering to SOLID principles, this system ensures maintainability, scalability, and robustness.

### Microservices Architecture
#### Config-Server: 
Centralized configuration management ensuring consistent environment configurations across all microservices.
#### Hostel Service: 
Manages hostel-specific operations, including room allocations, availability status, and maintenance schedules.
#### Student Service: 
Handles student-related functionalities such as registrations, hostel bookings, and profile management.

#### Key Technologies and Features
##### Java Spring Boot: 
Provides a robust framework for building and deploying microservices with ease.
##### SOLID Principles: 
Ensures the system is modular, scalable, and maintainable by adhering to best practices in software design.
##### Apache Kafka: 
Facilitates real-time event streaming, ensuring efficient and reliable communication between microservices.
##### PostgreSQL & Oracle: 
Utilizes PostgreSQL for general database operations and Oracle for advanced use cases.
##### Okta for SSO: 
Implements secure Single Sign-On (SSO) using Okta, enhancing user authentication and authorization.
##### Event-Driven Architecture: 
Employs an event-driven approach for seamless integration and real-time data processing.
##### Postman & Rest Assured: 
Ensures thorough API testing and validation, maintaining high standards of reliability and performance.
##### Virus Era Feature: 
BMI-Based Auto-Rejection
##### BMI Monitoring: 
Integrates with a free BMI API on RapidAPI to monitor students' health metrics.
##### Virus Detection: 
If a student's BMI score is higher than normal and indicative of a viral condition, the system automatically rejects their room booking application.
##### Real-Time Health Check: 
Ensures that only healthy students are approved for room bookings, promoting a safer living environment.

### Event-Driven Workflow
#### Room Booking: 
When a student books a room, an event is generated. The Student Service updates the student's profile and triggers an event to the Hostel Service.
#### Room Status Update: 
The Hostel Service listens for booking events and updates the room status accordingly. If a room's isEmpty status changes, it broadcasts an event to notify relevant services.
#### Capacity Management: 
Ensures that no more than two students can be assigned to a room when it is available. Once full, the system prevents further bookings for that room.
#### Health Check: 
Integrates with the BMI API to check students' health metrics before approving their booking. If a student's health status is not within the acceptable range, the booking is auto-rejected.

### Security and Authentication
#### Okta Integration: 
Provides robust security mechanisms, ensuring secure authentication and authorization through SSO.
#### Role-Based Access Control: 
Implements fine-grained access control, ensuring users have appropriate permissions based on their roles.

### Testing and Quality Assurance
#### Postman: 
Used for manual and automated API testing, ensuring all endpoints function correctly.
#### Rest Assured: 
Facilitates automated testing of REST APIs, validating the integrity and reliability of the system.

### Conclusion
The Hostel Management System is a state-of-the-art solution designed to revolutionize hostel operations through efficient microservices, robust security, and real-time data processing. With a focus on maintainability and scalability, it is poised to meet the evolving needs of modern hostel management, especially in ensuring the health and safety of its residents during viral outbreaks.