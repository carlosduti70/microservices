CREATE TABLE IF NOT EXISTS grades (
    id SERIAL PRIMARY KEY,
    student_name VARCHAR(50) NOT NULL,
    teacher VARCHAR(50) NOT NULL,
    calification INT NOT NULL
);