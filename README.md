# 🚗 Ride-Sharing Application

A Spring Boot-based ride-sharing platform designed to connect riders with drivers efficiently, securely, and in real time.

## 🚀 Features

- **Core Ride Services**
  - Implemented core functionalities: **Request Ride**, **Accept Ride**, and **Ride Strategy Manager**.
  - Ensures optimal and efficient ride allocation between users.

- **Authentication & Security**
  - Integrated **JWT** and **OAuth** based authentication.
  - Secure user login and role-based access management.

- **Notifications**
  - Developed an **email-based notification system**.
  - Real-time updates on ride status and transactions to users.

- **Persistence Layer**
  - Database connectivity using **Hibernate**, **Spring Data JPA**, and **MySQL**.
  - Enabled **transaction management** for data consistency and integrity.

## 🧩 System Design (UML)

The following UML diagram outlines the core entities and their relationships within the application:

[📄 View UML Diagram (PDF)](./src/main/resources/static/Uber_UML_Diagram.pdf)

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- OAuth2, JWT
- JavaMail
- Maven

## 🧪 Future Improvements

- Real-time driver tracking with WebSockets
- In-app chat between riders and drivers
- Integration with third-party payment gateways (e.g., Razorpay, Stripe)

---

> Feel free to contribute, fork, or suggest improvements to this ride-sharing application.
