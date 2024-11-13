-- Create and insert data for course table
INSERT INTO course (id, name)
VALUES (1, 'BACKEND');
INSERT INTO course (id, name)
VALUES (2, 'FRONTEND');

-- Create and insert data for problem_course table
INSERT INTO problem_course (id, problem_id, course_id)
VALUES (1, 1, 1);
INSERT INTO problem_course (id, problem_id, course_id)
VALUES (2, 2, 2);
