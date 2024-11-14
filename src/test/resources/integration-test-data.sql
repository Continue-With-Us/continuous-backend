TRUNCATE TABLE course;
TRUNCATE TABLE tag;
TRUNCATE TABLE problem;
TRUNCATE TABLE problem_tag;
TRUNCATE TABLE problem_course;

-- Create and insert data for course table (if applicable)
INSERT INTO course (id, name)
VALUES (1, 'FRONTEND');
INSERT INTO course (id, name)
VALUES (2, 'BACKEND');

-- Create and insert data for tag table
INSERT INTO tag (id, name)
VALUES (1, 'JAVASCRIPT');
INSERT INTO tag (id, name)
VALUES (2, 'JAVA');

-- Create and insert data for problem table
INSERT INTO problem (id, title)
VALUES (1, '클로저에 대해서 설명해주세요');
INSERT INTO problem (id, title)
VALUES (2, '함수형 프로그래밍에 대해서 설명해주세요');
INSERT INTO problem (id, title)
VALUES (3, '자바 Enum에 대해서 설명해주세요');

-- Create and insert data for problem_tag table
INSERT INTO problem_tag (id, problem_id, tag_id)
VALUES (1, 1, 1); -- problem 1 has JAVASCRIPT tag
INSERT INTO problem_tag (id, problem_id, tag_id)
VALUES (2, 2, 1); -- problem 2 has JAVASCRIPT tag
INSERT INTO problem_tag (id, problem_id, tag_id)
VALUES (3, 3, 2); -- problem 3 has JAVA tag

INSERT INTO problem_course (id, problem_id, course_id)
VALUES (1, 1, 1);
INSERT INTO problem_course (id, problem_id, course_id)
VALUES (2, 2, 1);
INSERT INTO problem_course (id, problem_id, course_id)
VALUES (3, 3, 2);
