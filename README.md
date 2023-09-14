# Shop Voucher Web Application Design

## 1. Requirements

### System Name
**Shop Voucher Portal**

### Purpose
The Shop Voucher Portal aims to provide an online platform where users can browse and purchase vouchers for various products and services offered by the shop. Users can conveniently select, pay for, and redeem vouchers directly through the portal.

### Users
- **Customers**: Individuals interested in purchasing vouchers for themselves or as gifts.
- **Shop Administrators**: Manage voucher listings, inventory, and order processing.

### Features
1. **User Registration and Login**
2. **Browse Voucher Categories and Listings**
3. **Search and Filter Vouchers**
4. **View Voucher Details and Terms**
5. **Add Vouchers to Cart**
6. **Online Payment Integration**
7. **Order History and Tracking**
8. **Voucher Redemption and Tracking**
9. **Admin Dashboard for Voucher Management**

## 2. Wireframes and UI Screenshots

### Wireframe Example
- **Home Page**
  - Featured Vouchers
  - Search Bar
- **Voucher Listings Page**
  - Voucher Cards
  - Filter Options
- **Voucher Details Page**
  - Voucher Image
  - Description and Terms
  - Add to Cart Button
- **Cart Page**
  - Selected Vouchers
  - Total Price
  - Checkout Button

### Site Map
- Home
- Browse Vouchers
- Voucher Details
- Cart
- Checkout
- Order History
- Admin Dashboard

## 3. Database Design

### Entity Relationship Diagram (ERD)
- **User**
  - UserID (PK)
  - Username
  - Email
  - Password
  - RoleID (FK, references Role)
- **Voucher**
  - VoucherID (PK)
  - Title
  - Description
  - Price
  - CategoryID (FK)
- **Category**
  - CategoryID (PK)
  - Name
- **Order**
  - OrderID (PK)
  - UserID (FK)
  - Date
  - TotalAmount
- **OrderItem**
  - OrderItemID (PK)
  - OrderID (FK)
  - VoucherID (FK)
  - Quantity
- **Role**
  - RoleID (PK)
  - RoleName ("User", "Admin")

### ERD Diagram
![ERD Diagram](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/c12ea1b4-0aa0-474d-b886-6fd36d94e204)

## 4. System Design

### MVC2 Architecture
- **Model**: Represents business logic and data management.
- **View**: Displays user interface and interacts with users.
- **Controller**: Handles user requests, processes data, and coordinates between Model and View.

### Technology Stack
- Java Servlets and JSP for server-side processing.
- JavaBeans for business logic and data handling.
- MySQL Database for data storage.
- HTML, CSS, and JavaScript for the user interface.
- Payment Gateway Integration for online payments.

### Diagrams
- **Class Diagram**: Shows classes and their relationships.
- **Sequence Diagram**: Illustrates interaction between components during user actions.
- **Deployment Diagram**: Depicts the distribution of components across servers.

### GUI Screenshots
![Home Page](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/21045e20-cf89-4411-9473-7f4501746e67)
![Voucher Listings](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/dc52f39a-32aa-4144-bcd8-0794ecb259e6)
![Voucher Details](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/b5bec9e8-f260-4502-9420-ccee95866f43)
![Cart](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/14a35790-b1ed-4613-a8d3-e4dda62171c1)
![Checkout](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/75d51dcb-3f94-4ea1-9aae-056dcae63321)
![Admin Dashboard](https://github.com/dunghuynh-teaching/prj301-se23su3w-g13/assets/120037416/61b1a54b-5169-4908-b0d2-77da8bebcb54)

## 5. Conclusion and Discussion

### Advantages
- Enhanced shopping experience for customers.
- Streamlined order processing and tracking.
- Efficient voucher management for administrators.

### Disadvantages
- Initial development complexity.
- Potential security concerns related to online transactions.

### Learning Experience
- Gained a deeper understanding of MVC2 architecture.
- Improved skills in Java web development and database design.

### Future Enhancements
- Strengthen user authentication and security measures.
- Implement a recommendation system based on user preferences.
- Incorporate responsive design for improved mobile usability.
- Extend admin functionality and access control for efficient management.
