[![Twitter](https://img.shields.io/twitter/follow/shreyamahalle?style=social)](https://x.com/shreyamahalle)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)
![GitHub language count](https://img.shields.io/github/languages/count/shreyamahalle/spring-boot-foodease-rest-api-filter-interceptor)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/shreyamahalle/spring-boot-foodease-rest-api-filter-interceptor)
![GitHub issues](https://img.shields.io/github/issues/shreyamahalle/spring-boot-foodease-rest-api-filter-interceptor)
![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/shreyamahalle/spring-boot-foodease-rest-api-filter-interceptor)
![GitHub followers](https://img.shields.io/github/followers/shreyamahalle?style=social)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.6-brightgreen.svg)
[![Developed with IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-Supported-blue.svg)](https://www.jetbrains.com/idea/)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://linkedin.com/in/shreyamahalle) &nbsp;&nbsp;
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/shreyamahalle) &nbsp;&nbsp;

<table align="center">
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/771cf93e-c2fd-4f65-a141-5e54b74a14ae" alt="FoodEase Logo" width="400" />
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/51d95660-90d0-42cf-bbda-2232c0d5419f" alt="Project Screenshot" width="300" />
    </td>
  </tr>
</table>

## Foodease - Food Order Booking System

## Table of Contents

- [Introduction](#introduction)
- [Advanced_Features](#Advanced-Features)
- [Features](#features)
- [Technology Stack](#Technology-Stack)
- [Getting Started](#Getting-Started)
- [Installation and Setup](#Installation-and-Setup)
- [API Endpoints (CRUD)](#api-endpoints-crud)
- [Postman Collection](#Postman-Collection)
- [Module Responsibilities](#Module-Responsibilities)
- [Spring Annotations](#Spring-Annotations)
- [System Functionality](#System-Functionality)
- [Code Structure ](#Code-Structure)
- [Database Schema (POJO Classes)](#Database-Schema-(POJO-Classes))
- [Class Diagram](#Class-Diagram)
- [API Endpoints (CURL Commands)](#API-Endpoints-(CURL-Commands))
- [Contact](#contact)
- [Best Practices Followed](#Best-Practices-Followed)
- [Future Scope](#Future-Scope)
- [License](#license)
- [Author](#Author)

# **foodease-food-order-booking-system**

**Foodease** is a Java Spring-based online food ordering and booking system. It allows customers to register, browse restaurants, place orders, and receive deliveries from nearby agents. The system is modular, scalable, and designed for real-world business needs.

> **Use case**: A food ordering platform where customers can register, explore nearby restaurants, place orders, and get deliveries from available agents based on location and serviceability.

## Advanced Features

### Filters & Interceptors
1. **Request Logging Filter**: Logs all incoming requests
2. **Authentication Filter**: JWT validation for secure endpoints
3. **Response Time Interceptor**: Measures API response times
4. **Request Validation Interceptor**: Validates payloads before controller processing

### Scheduled Tasks

1. **Daily Sales Report**: Generated at midnight
2. **Order Status Updates**: Hourly checks for pending orders
3. **Promotion Engine**: Weekly coupon generation

### Email Service

- Order confirmation emails
- Delivery status updates
- Password reset functionality
- Promotional campaigns

## Introduction

## Project Overview

The Spring Food Order & Booking Management System provides a modular approach to managing:
**FoodEase** is a comprehensive Spring Boot-based solution that digitalizes end-to-end food ordering operations, serving three key stakeholders:

- Customers
- Restaurants
- Delivery Agents
- Orders
- CartItem
- Coupon
- MenuItem
- Feedback
- Review
- BookingTable
- Payment
- OrderStatus
- Request Filtering
- **API Interception**
- **Scheduled Jobs**
- **Email Notifications**

It mimics real-world operations like customer registration, restaurant assignment, order placing, and delivery
tracking — all managed using a clean layered architecture 
```bash
Controller → Service → Repository → Model → DB

```
```
graph LR
    A[FoodEase] --> B(POS Systems)
    A --> C(Payment Gateways)
    A --> D(Logistics Providers)
    A --> E(Marketing Platforms)

```

## Features

## Key Features

-  Customer and Restaurant Booking Management: Effortlessly handle customer bookings and restaurant data.
-  Delivery Agent Simulation: Assign delivery agents based on location and availability.
-  Dynamic Order Handling: Add, track, and manage orders through their entire lifecycle.
-  Seamless Java Integration: Clean integration of Java models for each entity (Customer, Delivery Agent, Order,
   Restaurant).
-  Easy-to-Follow Structure: Well-documented, clean code for learning and demonstration.
-  Layered Architecture: Clean separation of concerns.
-  REST API: Full CRUD functionality exposed via REST.

## Technology Stack

This project is built with the following technologies:


| Technology        | Purpose                                   |
|------------------|-------------------------------------------|
| Java 21           | Core language                            |
| Spring Boot       | Application framework                    |
| Spring Web        | RESTful APIs                             |
| Spring Data JPA   | Database operations                      |
| Hibernate         | ORM mapping                              |
| JSP/Servlets      | Web interface                            |
| MySQL             | Relational database                      |
| Maven             | Project management and build             |
| Lombok            | Boilerplate reduction                    |
| HTML5/CSS3        | Frontend structure                       |
| Git               | Version control                          |
| Postman           | API Testing                              |
---

## Getting Started

## Installation and Setup

### Prerequisites

Before you begin, ensure you have the following:

- **Java Development Kit (JDK) 21**: Install the latest JDK for optimal performance.
- **Maven**: Make sure Maven is installed to handle the project build.
- **MySQL Database**: You'll need to have MySQL installed and set up to manage your data.
- **Apache Tomcat**: A servlet container for running your web application.
- **IDE**: Use IntelliJ IDEA, Eclipse, or any Java IDE you're comfortable with.
- **Git (optional)**: For version control and easier collaboration.

  ## 🛠️ Prerequisites

Before deploying FoodEase, ensure your development environment meets these requirements:

### Core Requirements
| Component           | Version       | Installation Guide                      | Verification Command          |
|---------------------|---------------|-----------------------------------------|-------------------------------|
| Java JDK            | 21+           | [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/install/) | `java -version` |
| Apache Maven        | 3.9.x         | [Maven Install Guide](https://maven.apache.org/install.html) | `mvn -v` |
| MySQL Server        | 8.0+          | [MySQL Downloads](https://dev.mysql.com/downloads/) | `mysql --version` |
| Docker Engine       | 24.0+         | [Docker Installation](https://docs.docker.com/engine/install/) | `docker --version` |

### Optional Tools
| Tool                | Recommended For                      | Installation Link                      |
|---------------------|--------------------------------------|----------------------------------------|
| IntelliJ IDEA       | Advanced Development                | [Download IDEA](https://www.jetbrains.com/idea/download/) |
| Postman            | API Testing                         | [Get Postman](https://www.postman.com/downloads/) |
| Kubernetes CLI     | Production Deployment               | [Install kubectl](https://kubernetes.io/docs/tasks/tools/) |

### Environment Verification
```bash
Verify Java installation
java -version
Should show: java version "21.x.x"

# Verify Maven
mvn --version
# Should show: Apache Maven 3.9.x

# Verify Docker
docker compose version
# Should show: Docker Compose version v2.x.x

# Verify MySQL
mysql --version
# Should show: mysql Ver 8.0.x for Linux/macOS

## Steps to Get Started
```

## 1. **Clone the Repository:**

```bash
   git clone (https://github.com/shreyamahalle/foodease-food-order-booking-system.git)
```

## 2. **Navigate to the Project Directory:**

```bash
 cd spring-boot-foodease-rest-api-filter-interceptor
```

## 3. **Open the Project in Your Preferred IDE:**

```bash
 Launch your IDE (IntelliJ, Eclipse, etc.), and open the cloned repository.
```

## 4. **Set up the MySQL Database:**

```bash
  CREATE DATABASE booking_management;
  USE booking_management;
```

## 5. **MySQL Database Setup**
- To set up the database and create the necessary tables, run the following SQL queries:
```bash
 -Insert sample data into the Customer table
  INSERT INTO Customer (name, username, mobileNo, city, area)
  
 -VALUES ('John Doe', 'johndoe123', '123-456-7890', 'New York', 'Manhattan');

```

## 6. **Configure Your MySQL Connection:**
```
- In your project configuration (application.properties), set the MySQL database connection details.
- Example application.properties:
- spring.datasource.url=jdbc:mysql://localhost:3306/booking_management
- spring.datasource.username=root
- spring.datasource.password=your_password
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
## 7. **Compile and Run the Application:**

- Locate Main.java in your IDE.
- Run the file to launch the application.

## 8. **Build the project:**

```bash
   mvn clean install
 ```
## API Endpoints (CRUD)


## API Endpoints (CURL Commands)

## Get All Customers
```bash
      curl location 'http://localhost:8080/api/customerManagement/customer' \
      header 'Content-Type: application/json'
```
## Get Customer by ID
```bash
     curl --location 'http://localhost:8080/api/customerManagement/customer/6' \
     header 'Content-Type: application/json'
```

## Update Customer
```bash
curl -X PUT 'http://localhost:8080/api/customer/6' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer YOUR_ACCESS_TOKEN' \
-d '{
  "name": "John Doe Updated",
  "area": "Bandra"
}'
```
## Delete Customer
```bash
curl --location --request DELETE 'http://localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data '{
  "id": 1,
  "name": "Shreya",
  "city": "Pune",
  "mobileNo": 9876543210,
  "age": 25
}
'
```
## Restaurant Management
## Create Restaurant
```bash
curl -X POST 'http://localhost:8080/api/restaurant' \
-H 'Content-Type: application/json' \
-d '{
  "name": "Pizza Palace",
  "city": "Mumbai",
  "area": "Bandra",
  "cuisineType": "Italian"
}'
```
## Get Restaurant Menu
```bash
curl -X GET 'http://localhost:8080/api/restaurant/1/menu' \
-H 'Accept: application/json'
```
## Order Management
## Place Order
```bash
curl -X POST 'http://localhost:8080/api/order' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer YOUR_ACCESS_TOKEN' \
-d '{
  "customerId": 1,
  "restaurantId": 2,
  "items": [
    {
      "menuItemId": 5,
      "quantity": 2,
      "specialInstructions": "No onions"
    }
  ],
  "couponCode": "SAVE10"
}' 
```
## Postman Collection

You can test the REST APIs using Postman:

### 🌐 Sample JSON for POST requests:

**POST /api/customers**

```json
{
  "name": "John Doe",
  "username": "john123",
  "mobileNo": "1234567890",
  "city": "Mumbai",
  "area": "Andheri"
}
``` 
## POST /api/restaurants
```json
{
  "name": "Pizza Palace",
  "city": "Mumbai",
  "area": "Bandra"
}
```
POST /api/orders
```json
{
  "customerId": 1,
  "restaurantId": 2,
  "deliveryAgentId": 3
}
```

## Module Responsibilities
```properties
This project includes full CRUD and business logic support for the following modules:

1 Customer – Registration, login, update profile

2 DeliveryAgent – Assign orders, update status

3 Order – Place, view, update, cancel orders

4 Restaurant – Add/view restaurant info and menus

5 Feedback – Customer feedback on orders or service

6 Review – Star ratings and reviews for restaurants

7 BookingTable – Reserve tables at restaurants

8 Payment – Payment processing and tracking

9 OrderStatus – Track real-time status of orders

10 MenuItem – Add/update menu items

11 Notification – Real-time or scheduled alerts

12 Coupon – Apply discount codes to orders

13 Cart Item – Add/remove items to cart before checkout.


```
## Spring Annotations

- Overview of Spring Annotations
  In this project, Spring annotations replace XML-based configuration for defining beans, enabling easier configuration
  and management of the application. The following annotations are used:

1. @Configuration
   Description: Marks the class as a source of bean definitions for the application context. This is used in place of
   XML configuration files.

2. @ComponentScan
   Description: Tells Spring to scan the specified base package(s) for annotated components (like @Service, @Controller,
   @Repository, etc.) and register them as beans in the Spring context.

3. @Service
   Description: Indicates that a class is a service, which typically holds business logic. It's a specialization of
   @Component.

4. @Repository
   Description: Marks a class as a Data Access Object (DAO), typically used for database-related operations. It also
   provides exception translation.

5. @Controller
   Description: Marks a class as a Spring MVC controller, handling incoming HTTP requests.
6. @Autowired
   Description: Automatically injects the dependencies into the Spring beans. It can be used on fields, constructors, or
   setter methods.
7. @Bean
   Description: Used to define a bean within a @Configuration annotated class. It's a method-level annotation.


## Contact

For any questions or suggestions, feel free to open an issue or contact me directly:

- **GitHub:** [Shreya Mahalle](https://github.com/shreyamahalle)

---

*This README was generated by [Shreya Mahalle](https://github.com/shreyamahalle).*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Code Structure

```
src/
└── main/
    ├── java/
    │   └── com.booking
    │       ├── config/             # App configuration
    │       ├── controller/         # REST controllers
    │       ├── model/              # Entity classes
    │       ├── repository/         # Data access interfaces
    │       ├── service/            # Business logic
    │       └── BookingApplication  # Main class
    └── resources/
        ├── application.properties
        └── static/templates

```

## **Functionality**

1. **Customer**
2. **DeliveryAgent**
3. **Order**
4. **Restaurant**
5. **Feedback**
6. **Review**
7. **BookingTable**
8. **Payment**
9. **OrderStatus**
10. **Review**
11. **MenuItem**
12. **Notification**
13. **Coupon**


## System Functionality

### Core Modules

| Module               | Key Features                                                                 | Technical Implementation                          |
|----------------------|-----------------------------------------------------------------------------|---------------------------------------------------|
| **Customer**         | - JWT Authentication<br>- Profile Management<br>- Order History            | Spring Security, OAuth2, Redis Cache              |
| **DeliveryAgent**    | - Real-time Tracking<br>- Capacity Management<br>- Route Optimization      | WebSocket, Google Maps API, Kafka Events          |
| **Order**           | - State Machine Workflow<br>- Payment Integration<br>- SAGA Pattern        | Spring State Machine, Stripe API, Axon Framework  |
| **Restaurant**      | - Menu Management<br>- Inventory Control<br>- Service Area Configuration   | Spring Data JPA, Geospatial Queries               |

### Supporting Modules

| Module               | Functionality                                                             | Technologies Used                              |
|----------------------|---------------------------------------------------------------------------|-----------------------------------------------|
| **Payment**         | - Multi-gateway Support<br>- Idempotent Transactions<br>- Refund Handling | Stripe SDK, Idempotency Keys, Circuit Breaker |
| **MenuItem**        | - Dynamic Pricing<br>- Dietary Tagging<br>- Availability Scheduling       | Elasticsearch, Time-based Cache               |
| **Notification**    | - Multi-channel (SMS/Email/Push)<br>- Template Engine<br>- Read Receipts | Twilio, SendGrid, Firebase Cloud Messaging    |

### Advanced Features

```mermaid
sequenceDiagram
    Customer->>+OrderService: Place Order
    OrderService->>+PaymentService: Process Payment
    PaymentService-->>-OrderService: Confirmation
    OrderService->>+RestaurantService: Validate Order
    RestaurantService-->>-OrderService: Acknowledgement
    OrderService->>+DeliveryService: Assign Agent
    DeliveryService-->>-Customer: Tracking Link

```




    

## **🔧 Core Functionality**

1. Customer Management
   Allows you to create and manage customer information such as name, username, contact details, and address.

2. Delivery Agent Management
   Adds delivery agents, assigns them to orders, and tracks their delivery status.

3. Order Management
   Manages the creation, display, and tracking of customer orders from placement to delivery.

4. Restaurant Management
   Registers new restaurants, displays restaurant details, and associates them with orders.

5. Feedback
   Allows customers to leave feedback on restaurants and delivery services.

6. Review
   Allows customers to leave ratings and reviews for restaurants and orders.

7. BookingTable
   Manages bookings for customers at restaurants, including tracking the booking status and details.

8. Payment
   Handles payment processing for orders and bookings, ensuring successful transactions.

9. OrderStatus
   Tracks and updates the status of customer orders, such as pending, delivered, or cancelled.
## Database Schema (POJO Classes)

| **Entity**        | **Attributes**                              |
|-------------------|---------------------------------------------|
| **Customer**      | user id, name, username,monbileNo,city,area |
| **DeliveryAgent** | id, name, city, mobileNo                    |
| **Order**         | id, name, city, mobileNo                    |
| **Restaurant**    | registerNo, name, City, Area                |

---


# Class Diagram

```mermaid
---
title: foodease-food-order-booking-system (Extended)
---
classDiagram

 note " food order booking management "

class Customer {
  +int customerId
  +String firstName
  +String lastName
  +String email
  +String phone
  +String address
  +createCustomer()
  +updateCustomer()
  +displayCustomer()
}

class Restaurant {
  +int restaurantId
  +String name
  +String cuisineType
  +String address
  +String contact
  +String openingHours
  +createRestaurant()
  +updateRestaurant()
  +displayRestaurant()
}

class Order {
  +int orderId
  +Date orderDate
  +String deliveryAddress
  +String status
  +createOrder()
  +updateOrder()
  +displayOrder()
}

class Payment {
  +int paymentId
  +String paymentMethod
  +double amount
  +String status
  +Date paymentDate
  +createPayment()
  +updatePayment()
  +displayPayment()
}

class DeliveryAgent {
  +int agentId
  +String name
  +String vehicleNumber
  +String contact
  +String status
  +createDeliveryAgent()
  +updateDeliveryAgent()
  +displayDeliveryAgent()
}

class Feedback {
  +int feedbackId
  +int rating
  +String comments
  +Date feedbackDate
  +createFeedback()
  +displayFeedback()
}

class Review {
  +int reviewId
  +int rating
  +String comments
  +Date reviewDate
  +createReview()
  +displayReview()
}

class OrderStatus {
  +int statusId
  +String status
  +Date statusDate
  +createOrderStatus()
  +displayOrderStatus()
}

class BookingTable {
  +int bookingId
  +Date bookingDate
  +int numberOfPeople
  +String specialRequests
  +createBooking()
  +updateBooking()
  +displayBooking()
}

%% New Classes
class MenuItem {
  +int menuItemId
  +String name
  +String description
  +double price
  +boolean isAvailable
  +createMenuItem()
  +updateMenuItem()
  +displayMenuItem()
}

class Notification {
  +int notificationId
  +String message
  +Date sentDate
  +boolean isRead
  +sendNotification()
  +markAsRead()
  +displayNotification()
}

class Coupon {
  +int couponId
  +String code
  +double discount
  +Date expiryDate
  +boolean isActive
  +applyCoupon()
  +validateCoupon()
  +displayCoupon()
}

Customer "1" --> "*" Order : places
Customer "1" --> "*" Feedback : provides
Customer "1" --> "*" Review : writes
Customer "1" --> "*" BookingTable : makes

Restaurant "1" --> "*" Order : receives
Restaurant "1" --> "*" Review : has
Restaurant "1" --> "*" BookingTable : accepts

Order "1" --> "1" Payment : has
Order "1" --> "1" DeliveryAgent : assigned to
Order "1" --> "*" OrderStatus : has

Payment --> Customer : paid by
DeliveryAgent --> Order : delivers

%% New Relationships
Restaurant "1" --> "*" MenuItem : offers
Order "*" --> "*" MenuItem : contains
Customer "1" --> "*" Notification : receives
Coupon "*" --> "*" Order : applied to

class CustomerController {
  +createCustomer()
  +getCustomer()
  +updateCustomer()
  +deleteCustomer()
}

class RestaurantController {
  +createRestaurant()
  +getRestaurant()
  +updateRestaurant()
  +deleteRestaurant()
}

class OrderController {
  +createOrder()
  +getOrder()
  +updateOrder()
  +cancelOrder()
}

CustomerController --> CustomerService
RestaurantController --> RestaurantService
OrderController --> OrderService

class CustomerService {
  +registerCustomer()
  +authenticateCustomer()
  +updateCustomerProfile()
}

class RestaurantService {
  +registerRestaurant()
  +updateRestaurantInfo()
  +manageMenu()
}

class OrderService {
  +placeOrder()
  +trackOrder()
  +cancelOrder()
}

CustomerService --> CustomerRepository
RestaurantService --> RestaurantRepository
OrderService --> OrderRepository

class CustomerRepository {
  +save()
  +findById()
  +findAll()
  +delete()
}

class RestaurantRepository {
  +save()
  +findById()
  +findAll()
  +delete()
}

class OrderRepository {
  +save()
  +findById()
  +findAll()
  +delete()
}
```


## Best Practices Followed
- MVC Layering: Each layer has a single responsibility and adheres to open/closed principle.
- Exception Handling: Extendable @ControllerAdvice class can be added for custom error management.
- Validation: javax.validation annotations (future improvement) to enforce domain constraints.
- DTO Mapping: DTOs can be introduced for request/response abstraction (optional enhancement).
- Lombok: Reduces boilerplate while maintaining readability.

## Future Scope
- Add unit tests using JUnit + Mockito
- API authentication using Spring Security or JWT
- Integration with Kafka or RabbitMQ for event-driven order tracking
- CI/CD with GitHub Actions
- Dockerization and deployment to Kubernetes or AWS ECS

---


## 🌐 Connect With Me

<div align="center" style="margin: 20px 0;">
  <!-- GitHub -->
  <a href="https://github.com/shreyamahalle" style="margin: 0 10px;">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub" height="28"/>
  </a>
  
  <!-- LinkedIn (with your corrected link) -->
  <a href="https://www.linkedin.com/in/shreya-mahalle-254657176/?originalSubdomain=in" style="margin: 0 10px;">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" height="28"/>
  </a>
  
  <!-- Twitter -->
  <a href="https://twitter.com/shreyamahalle" style="margin: 0 10px;">
    <img src="https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white" alt="Twitter" height="28"/>
  </a>
  
  <!-- Email -->
  <a href="mailto:shreyamahalle8@example.com" style="margin: 0 10px;">
    <img src="https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white" alt="Email" height="28"/>
  </a>
</div>

<div align="center" style="margin-top: 15px;">
  <a href="https://shreyamahalle.github.io" style="text-decoration: none;">
    <img src="https://img.shields.io/badge/Portfolio-4285F4?style=for-the-badge&logo=google-chrome&logoColor=white" alt="Portfolio" height="28"/>
  </a>
</div>

<div align="center"> <sub>Built with ❤︎ by Shreya Mahalle</sub> </div> 


<div align="center">
  <sub>Made with ❤︎ by <strong>Shreya Mahalle</strong> | Powered by Java & Spring Boot ☕🌱</sub>  
  <br/>
  <sub>Let's connect on 
    <a href="https://github.com/shreyamahalle">GitHub</a> &bull; 
    <a href="https://linkedin.com/in/shreyamahalle">LinkedIn</a>
  </sub>
</div>


