🧠 Online Quiz Application

An interactive full-stack quiz application built with React (frontend) and Spring Boot (backend). Users can select quizzes, attempt questions, see their score, and review correct/wrong answers.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🌟 Features
Frontend

Start Page: Select any available quiz to start.

Quiz Page:

View one question at a time.

Navigate using Next and Previous buttons.

Timer for each question.

Results Page: Shows the final score and highlights correct and wrong answers.

Responsive and modern UI with animated gradient background.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Backend

Quiz Management: Create, delete quizzes.

Question Management: Add, delete questions with options.

Score Calculation: Automatically calculates the score upon submission.

Endpoints exposed via REST API.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Tech Stack

Frontend: React 19, CSS3, React Router

Backend: Spring Boot 3.5, Java 17, PostgreSQL

Other Tools: Maven, JPA/Hibernate, Lombok (optional)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



🔧 Backend API Endpoints

| Method | Endpoint                  | Description                       | Request Body                                                                    | Response                                                               |
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
| GET     |  `/`                     | Get all quizzes                   | -                                                                               | List of `Quiz` objects                                                 
|         |
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

| GET    | `/{quizId}/questions`     | Get all questions of a quiz       | -                                                                               | List of `QuestionDTO` objects (without correct answers)            |                                   |                                                                                 |
 
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------              |
| POST   | `/{quizId}/submit`        | Submit answers for a quiz         | `SubmitRequestDTO` `{ answers: [{questionId, selectedOptionId}] }`              | `{ score, total, questions: [{id, text, options, selectedOptionId}] }` |                             |                                                                                 |

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

| POST   | `/`                       | Create a new quiz                 | `QuizRequestDTO` `{ title, questions: [{text, options: [{text, isCorrect}]}] }` | Newly created `Quiz` object                                            |        |                           |                                   |                                                                                 |

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

| POST   | `/{quizId}/add-questions` | Add questions to an existing quiz | List of `QuestionDTO`                                                           | List of added `Question` objects  |                           |                                   |                                                                                 |           
         |           
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         |
| DELETE | `/{quizId}`               | Delete a quiz                     | -                                                                               | Success message                                                        |        |                           |                                   |                                                                                 |

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

| DELETE | `/questions/{questionId}` | Delete a question                 | -                                                                               | Success message                                                        |
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



📦 Project Structure
Backend
src/main/java/com/example/quizapp/
├─ controller/      # REST controllers (QuizController)
├─ dto/             # Data transfer objects (QuestionDTO, OptionDTO, SubmitRequestDTO)
├─ models/          # Entities (Quiz, Question, Option)
├─ repo/            # Spring Data JPA repositories
├─ service/         # Business logic (QuizService)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Frontend
src/
├─ components/
│  ├─ StartPage.js
│  ├─ QuizPage.js
│  ├─ ResultPage.js
│  └─ QuestionCard.js
├─ services/
│  └─ quizService.js
├─ App.js
├─ index.js
└─ styles/
   ├─ StartPage.css
   ├─ QuizPage.css
   └─ ResultPage.css

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


⚡ How to Run Locally
1️⃣ Backend

Clone the repository:
git clone <repo-url>
cd Online_Quiz_Application_Backend

Configure PostgreSQL in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/Online_Quiz_Application_Database
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Build and run:
mvn clean install
mvn spring-boot:run

The backend runs on http://localhost:8080.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2️⃣ Frontend

Navigate to frontend folder:
cd frontend

Install dependencies:
npm install


Start the development server:
npm start


The frontend runs on http://localhost:3000.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🔗 Example Workflow

Open http://localhost:3000/.

Select a quiz from the dropdown.

Attempt each question, navigating with Next/Previous.

Submit answers before the timer runs out.

View the final score and correct/wrong answers.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🎯 Data Structures
Quiz
{
  "id": 1,
  "title": "JavaScript Basics",
  "questions": [...]
}

QuestionDTO
{
  "id": 101,
  "text": "What is closure in JS?",
  "options": [
    {"id": 1, "text": "Option A"},
    {"id": 2, "text": "Option B"}
  ]
}

SubmitRequestDTO
{
  "answers": [
    {"questionId": 101, "selectedOptionId": 1}
  ]
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

💡 Notes

The backend ensures that correct answers are never exposed via the /questions endpoint.

Timer auto-submits quiz when time runs out.

The results page clearly indicates correct (green) and wrong (red) options.

Adding/deleting quizzes or questions is available via API (can be used for admin panel in future).

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🎨 Frontend UI Features

Gradient animated background.

Responsive design (mobile-first).

Smooth navigation between questions.

Highlights correct/incorrect answers after submission.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

👨‍💻 Author

Aditya Malode
💼 Full Stack Developer
📧 adityamalode4464@gmail.com
🔗 [LinkedIn Profile - https://www.linkedin.com/in/aditya-malode-471545255] | [GitHub Profile - https://github.com/AdityaMalode05]