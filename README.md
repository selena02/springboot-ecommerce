# E-Commerce Platform

An e-commerce platform where users can browse products, add items to a cart, place orders, and leave reviews for purchased products.

---

## **I. System Definition**

### **Business Domain**
The platform provides an online shopping experience, enabling users to manage products, place orders, and interact with the system based on specific roles (e.g., ADMIN, USER).

---

## **II. Business Requirements**

1. **User Registration and Authentication**
   - Users must be able to register and log in securely using their credentials.

2. **Role Management**
   - Support for different roles (e.g., ADMIN, USER) with appropriate access control mechanisms.

3. **Product Management**
   - Admins can add, update, and delete products, including details such as name, description, category, and price.

4. **Product Browsing**
   - Users can view a catalog of products.

5. **Cart Functionality**
   - Users can add items to their cart, adjust quantities, and remove items as needed.

6. **Order Placement**
   - Users can place orders based on the items in their cart.

7. **Order History**
   - Users can access their order history, with detailed information about each order.

8. **Review System**
   - Users can leave reviews and ratings for products.

9. **Product Details and Review Displayd**
   - Users can view detailed information about a product, including its name, description, price, and category
   - Users can also view reviews and ratings left by other users for the product.

10. **JWT-Based Security**
    - JSON Web Tokens (JWT) are used for secure authentication and authorization.

---

## **III. MVP Document: Main Features**

### **Feature 1: User Registration and Authentication**
- **Description**:
  - Users can create accounts and log in securely.
  - Roles (USER, ADMIN) are assigned upon registration or by an admin.
  - JWT tokens are generated for secure sessions.
- **Priority**: High
- **Technical Requirements**:
  - Hashing for password storage.
  - JWT integration for token-based authentication.

### **Feature 2: Product Management**
- **Description**:
  - Admins can add, update, and delete products.
  - Each product includes a name, description, price, and category.
- **Priority**: High
- **Technical Requirements**:
  - A database schema for product storage.
  - REST APIs for CRUD operations on products.

### **Feature 3: Cart and Order System**
- **Description**:
  - Users can add products to a cart, adjust quantities, and place orders.
  - Orders include details like items, total price, and timestamps.
  - Users can view their order history.
- **Priority**: High
- **Technical Requirements**:
  - Cart and order database tables.
  - APIs for adding to cart, viewing cart, and placing orders.

### **Feature 4: Review System**
- **Description**:
  - Users can leave reviews and ratings for purchased products.
  - Reviews include a rating (1-5) and a text description.
- **Priority**: Medium
- **Technical Requirements**:
  - A relationship between users, products, and reviews in the database.
  - APIs for adding and viewing reviews.

### **Feature 5: Product Details and Review Display**
- **Description**:
  - Users can view detailed information about each product, including its name, description, price, and category.
  - Users can also view reviews and ratings left by other users for the product.
- **Priority**: Medium
- **Technical Requirements**:
  - APIs for retrieving product details and associated reviews.
  - Database relationships to connect products with their reviews.

---

## **IV. Technologies Used**
- **Backend**: Spring Boot
- **Authentication**: JWT (JSON Web Tokens)
- **Database**: MySQL 
- **API Documentation**: Swagger / OpenAPI
- **Testing**: JUnit, Mockito

---

