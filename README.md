# Fee Management System 💼💻

A **Java Swing–based Fee Management System** that allows users to **sign up, log in, and manage user data** with MySQL database integration.  
This project is built using **Core Java, Swing GUI, JDBC, and MySQL** and is suitable for **college / academic projects**.

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
│
├── pom.xml
└── README.md
```
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

Passwords are currently stored as plain text.
For production-level projects, use password hashing (BCrypt).
---
