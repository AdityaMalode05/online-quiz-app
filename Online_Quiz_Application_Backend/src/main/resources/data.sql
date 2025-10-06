-- Quiz
INSERT INTO quiz (id, title) VALUES (1, 'General Knowledge - Sample Quiz');

-- Questions
INSERT INTO question (id, text, quiz_id) VALUES (1, 'What is the capital of France?', 1);
INSERT INTO question (id, text, quiz_id) VALUES (2, 'Which planet is known as the Red Planet?', 1);
INSERT INTO question (id, text, quiz_id) VALUES (3, 'Which language is primarily used for Android app development?', 1);

-- Options for Q1
INSERT INTO option (id, text, is_correct, question_id) VALUES (1, 'Paris', true, 1);
INSERT INTO option (id, text, is_correct, question_id) VALUES (2, 'Berlin', false, 1);
INSERT INTO option (id, text, is_correct, question_id) VALUES (3, 'London', false, 1);

-- Options for Q2
INSERT INTO option (id, text, is_correct, question_id) VALUES (4, 'Mars', true, 2);
INSERT INTO option (id, text, is_correct, question_id) VALUES (5, 'Jupiter', false, 2);
INSERT INTO option (id, text, is_correct, question_id) VALUES (6, 'Venus', false, 2);

-- Options for Q3
INSERT INTO option (id, text, is_correct, question_id) VALUES (7, 'Kotlin', true, 3);
INSERT INTO option (id, text, is_correct, question_id) VALUES (8, 'Python', false, 3);
INSERT INTO option (id, text, is_correct, question_id) VALUES (9, 'Swift', false, 3);
