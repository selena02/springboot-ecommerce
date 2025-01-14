I. System Definition: E-Commerce Platform
Business Domain: An e-commerce platform where users can browse products, add items to a cart, place orders, and leave reviews for purchased products.

1. Business Requirements
User Registration and Authentication: Users must be able to register and log in using secure credentials.
Role Management: The platform must support different user roles (e.g., ADMIN, USER) with appropriate access controls.
Product Management: Admins must be able to add, update, and delete products, including their details such as name, description, category, and price.
Product Browsing: Users should be able to view a catalog of products and search by name or category.
Cart Functionality: Users must be able to add items to their cart, adjust quantities, and remove items.
Order Placement: Users should be able to place orders based on the items in their cart and receive a confirmation.
Order History: Users must have access to their order history, with details of each order.
Review System: Users should be able to leave reviews and ratings for products they have purchased.
Admin Dashboard: Admins should be able to view and manage user accounts, orders, and reviews.
JWT-Based Security: The platform must use JSON Web Tokens (JWT) for secure authentication and authorization.
2. MVP Document: Main Features
Feature 1: User Registration and Authentication
Description:
Users can create accounts and log in securely.
Roles (USER, ADMIN) are assigned upon registration or by an admin.
JWT tokens are generated for secure sessions.
Priority: High
Technical Requirements:
Hashing for password storage.
JWT integration for token-based authentication.
Feature 2: Product Management
Description:
Admins can add, update, and delete products.
Each product includes a name, description, price, and category.
Priority: High
Technical Requirements:
A database schema for product storage.
REST APIs for CRUD operations on products.
Feature 3: Cart and Order System
Description:
Users can add products to a cart, adjust quantities, and place orders.
Orders include details like items, total price, and timestamps.
Users can view their order history.
Priority: High
Technical Requirements:
Cart and order database tables.
APIs for adding to cart, viewing cart, and placing orders.
Feature 4: Review System
Description:
Users can leave reviews and ratings for purchased products.
Reviews include a rating (1-5) and a text description.
Priority: Medium
Technical Requirements:
A relationship between users, products, and reviews in the database.
APIs for adding and viewing reviews.
Feature 5: Admin Dashboard
Description:
Admins can view and manage users, orders, and reviews.
They can block/unblock users and moderate reviews.
Priority: Medium
Technical Requirements:
Role-based access control for admin functionalities.
APIs for admin-specific operations.
