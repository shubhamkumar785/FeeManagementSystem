# 🎓 Fee Management System (Java Swing + MySQL)

A desktop-based **Fee Management System** developed using **Java Swing** and **MySQL**, designed to manage student fees efficiently with a clean and user-friendly interface.

---

## 📌 Project Overview

This application helps institutes manage:
- Student registration
- Secure login
- Fee entry with GST calculation
- Course & record management
- Reports and fee receipts

Built using **Core Java (Swing GUI)** with **JDBC connectivity**.

---

## 🛠️ Technologies Used

- **Java (JDK 17+)**
- **Java Swing** (GUI)
- **JDBC**
- **MySQL Database**
- **NetBeans IDE**
- **Maven**
- **JCalendar (DateChooser)**

---

## 📂 Project Structure
```
FeeManagementSystem
│
├── src/main/java
│ └── com/mycompany/feemanagementsystem
│ ├── SignUpPage.java
│ ├── Login.java
│ └── HomePage.java
| └── AddFees.java
│
├── pom.xml
└── README.md
```
---
## 🖥️ Application Screens

### 🔐 Login Page
- Username & password authentication
- Input validation
- Navigation to Signup page

### 📝 Signup Page
- New user registration
- Fields:
  - Name
  - Email
  - Username
  - Password & Confirm Password
  - Date of Birth (Date Picker)
  - Contact Number
- Validation before database insertion

### 🏠 Home Page (Dashboard)
- Central dashboard with options:
  - Add Fees
  - Search Record
  - View Course
  - Edit Course
  - View Report
  - View Record
  - About
  - Logout

### 💰 Add Fees Page
- Receipt number generation
- Mode of payment selection
- Course selection
- Automatic GST calculation:
  - CGST 7%
  - SGST 7%
- Total amount calculation
- Amount in words
- Print-ready layout

---

## 🗄️ Database Setup

### 1️⃣ Create Database
```sql
CREATE DATABASE feemanagementdb;

CREATE TABLE signup (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    emailid VARCHAR(100),
    username VARCHAR(100),
    password VARCHAR(100),
    dob DATE,
    contact_number VARCHAR(15)
);

```
🔔 Tip: You can use AUTO_INCREMENT for id in real projects.

---

🔐 Security Note

- Passwords are currently stored as plain text.
- For production-level projects, use password hashing (BCrypt).
---
