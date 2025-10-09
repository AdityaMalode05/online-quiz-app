🧠 Online Quiz Application
📋 Description

The Online Quiz Application is a full-stack web app built using React (Frontend), Spring Boot (Backend), and PostgreSQL (Database).
It provides a secure and interactive platform for users to register, log in, and attempt quizzes.
The system supports dynamic question loading, real-time scoring, and user result tracking via RESTful APIs.




🚀 Tech Stack

Frontend: React, Axios, React Router, CSS
Backend: Spring Boot 3+, Java 17, Hibernate, Spring Security, JPA
Database: PostgreSQL
APIs: RESTful APIs for frontend-backend communication




✨ Features

✅ User Registration & Login with Authentication
✅ Secure Password Encryption
✅ Quiz Management (create, view, attempt quizzes)
✅ Dynamic Question Loading from Database
✅ Real-time Score Calculation
✅ Result History Page
✅ Fully Responsive UI
✅ Logout Functionality




⚙️ Project Structure

online-quiz-app/
│
├── frontend/                 # React app (UI)
│   ├── src/
│   │   ├── components/       # Reusable React components
│   │   ├── pages/            # Login, Register, Quiz, Results, etc.
│   │   └── App.js
│   └── package.json
│
└── backend/                  # Spring Boot app (API)
    ├── src/main/java/com/example/quizapp/
    │   ├── controller/       # REST Controllers
    │   ├── model/            # Entities
    │   ├── repository/       # JPA Repositories
    │   ├── service/          # Business Logic
    │   └── QuizAppApplication.java
    └── pom.xml





🧑‍💻 Setup Instructions

🔹 1. Clone the Repository

git clone https://github.com/<your-username>/online-quiz-app.git


🔹 2. Backend Setup

cd backend

Open the project in IntelliJ IDEA / VS Code / Eclipse

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8080


Run the Spring Boot application:

mvn spring-boot:run

🔹 3. Frontend Setup

cd frontend
npm install
npm start


The app will run on http://localhost:3000





📦 API Endpoints (Examples)

| Method | Endpoint                | Description        |
| ------ | ----------------------- | ------------------ |
| POST   | `/api/auth/register`    | Register new user  |
| POST   | `/api/auth/login`       | User login         |
| GET    | `/api/quizzes`          | Get all quizzes    |
| GET    | `/api/quiz/{id}`        | Get quiz by ID     |
| POST   | `/api/results`          | Submit quiz result |
| GET    | `/api/results/{userId}` | Get user results   |





🖼️ Screens Included

Login Page :
<img width="1871" height="845" alt="Screenshot 2025-10-10 011805" src="https://github.com/user-attachments/assets/02eed6f2-1377-4d1c-8ba7-49410616502f" />


Registration Page :
<img width="1866" height="870" alt="Screenshot 2025-10-10 011852" src="https://github.com/user-attachments/assets/0284b78e-305e-4ccb-8cdf-f4eac0c01822" />


Quiz Selection Page :
<img width="1875" height="868" alt="Screenshot 2025-10-10 012210" src="https://github.com/user-attachments/assets/ca13004a-5cfb-4cc1-9da2-e9b72db3223c" />

<img width="1874" height="867" alt="Screenshot 2025-10-10 012346" src="https://github.com/user-attachments/assets/90df1251-d60b-4c1a-97c5-7116990a4bd2" />


Quiz Attempt Page :

<img width="1873" height="869" alt="Screenshot 2025-10-10 012428" src="https://github.com/user-attachments/assets/bc80953c-ebb2-486d-b09a-fdeaf1dfb4a6" />

Results Page :
<img width="1874" height="867" alt="Screenshot 2025-10-10 012549" src="https://github.com/user-attachments/assets/00214067-2eb4-433d-8e24-bc438167d4af" />
<img width="1866" height="864" alt="Screenshot 2025-10-10 012612" src="https://github.com/user-attachments/assets/dfbf7414-7d44-4d7e-91e4-88f4f523e095" />


previous quiz attempted history : 

<img width="1870" height="862" alt="Screenshot 2025-10-10 012254" src="https://github.com/user-attachments/assets/51016ea0-9ff5-4cad-89c1-64d8ab2a88d8" />


Responsive Header with Logout :
<img width="1877" height="88" alt="Screenshot 2025-10-10 012638" src="https://github.com/user-attachments/assets/744c8fc2-aefd-4644-8a41-2c5cfa9d2143" />





🧩 Future Enhancements

Admin Panel for quiz creation and result management

Timer-based quizzes

Leaderboard & analytics dashboard





👨‍💻 Developed By

Aditya Malode
💼 Full-Stack Developer (Spring Boot + React)







