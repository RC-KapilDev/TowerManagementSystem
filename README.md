# Telecom Tower Management System

## Overview

The Telecom Tower Management System is designed to streamline the management of telecom towers, including work orders, technicians, maintenance reports, and equipment. This system is built using a microservices architecture to ensure scalability, maintainability, and flexibility.

## Architecture

The system is composed of the following microservices:

1. **Work Order Service**
2. **Technician Service**
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

#### 2. Technician Service

- **Responsibilities**:
  - Manage technician information and equipment claims.
- **Endpoints**:
  - `GET /technicians` - Retrieve technician information.
  - `POST /technicians` - Create a new technician.
  - `PUT /technicians/{id}` - Update technician details.
  - `PATCH /technicians/{id}/claim` - Claim equipment.

#### 3. Maintenance Report Service

- **Responsibilities**:
  - Handle maintenance reports including creation and updates.
- **Endpoints**:
  - `GET /maintenance-reports` - Retrieve maintenance reports.
  - `POST /maintenance-reports` - Submit a new maintenance report.
  - `PUT /maintenance-reports/{id}` - Update a maintenance report.

#### 4. Equipment Service

- **Responsibilities**:
  - Manage equipment details and claims.
- **Endpoints**:
  - `GET /equipments` - Retrieve equipment details.
  - `POST /equipments` - Add new equipment.
  - `PUT /equipments/{id}` - Update equipment details.
  - `PATCH /equipments/{id}/claim` - Claim equipment.

#### 5. Tower Service

- **Responsibilities**:
  - Manage tower information including creation, updates, and retrieval.
- **Endpoints**:
  - `GET /towers` - Retrieve tower details.
  - `POST /towers` - Add a new tower.
  - `PUT /towers/{id}` - Update tower details.

### Database Schema

The system uses PostgreSQL as the database. Here’s a summary of the tables and their primary keys:

- **Work Order Table**:
  - `workorder_id` (INTEGER, starts from 200 to 400000)
  - Foreign Keys: `tower_id`, `technician_id`
  
- **Technician Table**:
  - `technician_id` (INTEGER, starts from 500001 to 599999)
  - Other columns include `role`, `active_status`, `name`, `email`, `specialisation`, `location`, `pincode`, `deleted_status`, `username`, `password`

- **Maintenance Report Table**:
  - `maintenance_id` (INTEGER, starts and ends with 7 digits)
  - Foreign Keys: `technician_id`, `workorder_id`, `tower_id`

- **Equipment Table**:
  - `equipment_id` (INTEGER, starts with 8 digits)
  - Foreign Keys: `workorder_id`, `tower_id`

- **Tower Table**:
  - `tower_id` (SERIAL, starting from a specified number)

### App Flow

1. **Technician Operations**:
   - Technicians log in and view assigned work orders.
   - Technicians update work order status and submit maintenance reports.
   - Technicians claim equipment assigned to them.

2. **Admin Operations**:
   - Admins log in and manage work orders, equipment, and technician details.
   - Admins add new equipment and update work orders after equipment claims.

### Access Control

- **Admin**:
  - Full access to all microservices.
  - Can add equipment, update work orders, manage technician details.

- **Technician**:
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

