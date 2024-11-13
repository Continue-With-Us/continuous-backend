-- Create and insert data for tag table
INSERT INTO tag (id, name)
VALUES (1, 'JAVA');
INSERT INTO tag (id, name)
VALUES (2, 'JAVASCRIPT');

-- Create and insert data for problem_tag table
INSERT INTO problem_tag (id, problem_id, tag_id)
VALUES (1, 1, 1);
INSERT INTO problem_tag (id, problem_id, tag_id)
VALUES (2, 1, 2);
