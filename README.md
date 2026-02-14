## 📦 Product Service – SmartCommerce
This module manages the complete product catalog lifecycle within the SmartCommerce ecosystem. It operates as an independent microservice, secured with JWT and registered in Eureka for service discovery.

## 🎯 Why This Module Exists
- Catalog Isolation
  Keeps product domain logic independent from authentication, users, and orders.

- Public Product Browsing
  Allows customers to view, search, and filter products without authentication.

- Role-Based Management
  Restricts creation, updates, and deletion to ADMIN and SELLER roles using method-level security.

- Soft Delete Strategy
  Products are deactivated instead of immediately removed, preserving business integrity.

- Stateless Security
  Validates JWT tokens and extracts roles to enforce fine-grained access control.

- Scalable Architecture
  Designed to scale independently from other services in the ecosystem.

## 🔑 Core Capabilities
Public Access
- List all products
- Get product by ID
- Search by name
- Filter by category
- Filter by category + active status
- Restricted Access
- View inactive products (ADMIN, SELLER)
- Create product (ADMIN, SELLER)
- Update product (ADMIN, SELLER)
- Soft delete product (ADMIN only)
- Permanent delete (ADMIN only)
- Reactivate product (ADMIN, SELLER)

## 🛡️ Security Design
- Stateless session policy
- JWT validation on every request
- Role extracted from token claim
- Method-level authorization via @PreAuthorize
- Global exception handling for validation and access control

## 🗄️ Persistence
- Database: PostgreSQL (smartcommerce_products)
- JPA with Hibernate (ddl-auto: update)
- Automatic timestamps (createdAt, updatedAt)
- Preloaded sample catalog (Electronics, Clothing, Books, Sports, Home & Kitchen)

## ⚙️ Configuration
- Port: 8083
- Registered in Eureka
- JWT secret shared with Auth Service
- Actuator endpoints enabled (health, info)
- Debug logging enabled
