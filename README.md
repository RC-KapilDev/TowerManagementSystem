Certainly! Here’s the updated README file reflecting the changes from `technician` to `users` and `user_id`:

---

# Telecom Tower Management System

## Overview

The Telecom Tower Management System is designed to streamline the management of telecom towers, including work orders, users, maintenance reports, and equipment. This system is built using a microservices architecture to ensure scalability, maintainability, and flexibility.

## Architecture

The system is composed of the following microservices:

1. **Work Order Service**
2. **User Service**
3. **Maintenance Report Service**
4. **Equipment Service**
5. **Tower Service**

Each microservice is responsible for managing its specific domain and communicates with other services as needed.

### Microservices

#### 1. Work Order Service

- **Responsibilities**:
  - Manage work orders including creation, updates, and status changes.
- **Endpoints**:
  - `GET /workorders` - Retrieve work orders.
  - `POST /workorders` - Create a new work order.
  - `PUT /workorders/{id}` - Update work order details.
  - `PATCH /workorders/{id}/status` - Update work order status.
  - **Search Endpoints**:
    - `GET /workorders/status/{status}` - Retrieve work orders by status.
    - `GET /workorders/user/{userId}` - Retrieve work orders by user ID.

#### 2. User Service

- **Responsibilities**:
  - Manage user information and equipment claims.
- **Endpoints**:
  - `GET /users` - Retrieve user information.
  - `POST /users` - Create a new user.
  - `PUT /users/{id}` - Update user details.
  - `PATCH /users/{id}/claim` - Claim equipment.
  - **Search Endpoints**:
    - `GET /users/role/{role}` - Retrieve users by role.
    - `GET /users/location/{location}` - Retrieve users by location.

#### 3. Maintenance Report Service

- **Responsibilities**:
  - Handle maintenance reports including creation and updates.
- **Endpoints**:
  - `GET /maintenance-reports` - Retrieve maintenance reports.
  - `POST /maintenance-reports` - Submit a new maintenance report.
  - `PUT /maintenance-reports/{id}` - Update a maintenance report.
  - **Search Endpoints**:
    - `GET /maintenance-reports/tower/{towerId}` - Retrieve reports by tower ID.
    - `GET /maintenance-reports/priority/{priority}` - Retrieve reports by priority.

#### 4. Equipment Service

- **Responsibilities**:
  - Manage equipment details and claims.
- **Endpoints**:
  - `GET /equipments` - Retrieve equipment details.
  - `POST /equipments` - Add new equipment.
  - `PUT /equipments/{id}` - Update equipment details.
  - `PATCH /equipments/{id}/claim` - Claim equipment.
  - **Search Endpoints**:
    - `GET /equipments/name/{name}` - Retrieve equipment by name.
    - `GET /equipments/manufacture/{manufacture}` - Retrieve equipment by manufacture.

#### 5. Tower Service

- **Responsibilities**:
  - Manage tower information including creation, updates, and retrieval.
- **Endpoints**:
  - `GET /towers` - Retrieve tower details.
  - `POST /towers` - Add a new tower.
  - `PUT /towers/{id}` - Update tower details.
  - **Search Endpoints**:
    - `GET /towers/location/{location}` - Retrieve towers by location.
    - `GET /towers/status/{status}` - Retrieve towers by status.

### Database Schema

The system uses PostgreSQL as the database. Here’s a summary of the tables and their primary keys:

- **Work Order Table**:
  - `workorder_id` (INTEGER, starts from 200 to 400000)
  - Foreign Keys: `tower_id`, `user_id`
  
- **User Table**:
  - `user_id` (INTEGER, starts from 500001 to 599999)
  - Other columns include `role`, `active_status`, `name`, `email`, `specialisation`, `location`, `pincode`, `deleted_status`, `username`, `password`

- **Maintenance Report Table**:
  - `maintenance_id` (INTEGER, starts and ends with 7 digits)
  - Foreign Keys: `user_id`, `workorder_id`, `tower_id`

- **Equipment Table**:
  - `equipment_id` (INTEGER, starts with 8 digits)
  - Foreign Keys: `workorder_id`, `tower_id`

- **Tower Table**:
  - `tower_id` (SERIAL, starting from a specified number)

### App Flow

1. **User Operations**:
   - Users log in and view assigned work orders.
   - Users update work order status and submit maintenance reports.
   - Users claim equipment assigned to them.

2. **Admin Operations**:
   - Admins log in and manage work orders, equipment, and user details.
   - Admins add new equipment and update work orders after equipment claims.

### Access Control

- **Admin**:
  - Full access to all microservices.
  - Can add equipment, update work orders, manage user details.

- **User**:
  - Can view work orders assigned to them.
  - Can update work order status and submit maintenance reports.
  - Can claim equipment.

### Communication Between Services

- **REST APIs**: Services communicate using RESTful APIs.
- **Asynchronous Messaging**: For decoupled operations and handling notifications.
- **Service Discovery**: Using a service registry for dynamic service lookups.

### Setup and Running

1. **Build and Run Microservices**:
   - Ensure you have Java and Maven/Gradle installed.
   - Build each microservice and run it using your preferred method.

2. **Database Setup**:
   - Set up PostgreSQL and create the required schemas and tables as defined.

3. **Configuration**:
   - Configure application properties and endpoints for inter-service communication.

4. **Testing**:
   - Test each microservice independently and ensure they integrate correctly.

### Conclusion

This Telecom Tower Management System is designed to be modular and scalable. Each microservice handles a specific aspect of the system, making it easier to manage and extend. Follow the architecture and flow outlined here to build and deploy your system effectively.

---
