-- 샘플 데이터 삽입
-- Course 테이블 데이터
INSERT INTO course (name) VALUES ('BACKEND');
INSERT INTO course (name) VALUES ('FRONTEND');

-- Problem 테이블 데이터
INSERT INTO problem (title) VALUES ('자바 Enum에 대해서 설명해주세요.');
INSERT INTO problem (title) VALUES ('클로저에 대해서 설명해주세요.');

-- Hint 테이블 데이터
INSERT INTO hint (problem_id, example_code, resource_url) VALUES (1, 'public enum Course {\n    BACKEND, FRONTEND;\n}', 'https://youtu.be/qACxvjrb-xk?feature=shared');
INSERT INTO hint (problem_id, example_code, resource_url) VALUES (2, 'function outerFunction() {\n    let count = 0; // 외부 함수의 변수\n\n    function innerFunction() {\n        count++; // 외부 함수의 변수에 접근\n        console.log(`현재 카운트: ${count}`);\n    }\n\n    return innerFunction;\n}\n\nconst myClosure = outerFunction(); // outerFunction 호출 후 innerFunction 반환\nmyClosure(); // 출력: 현재 카운트: 1\nmyClosure(); // 출력: 현재 카운트: 2\nmyClosure(); // 출력: 현재 카운트: 3','https://youtu.be/LfwRDFrhXA4?feature=shared');

-- Problem_Course 테이블 데이터 (연결 테이블)
INSERT INTO problem_course (course_id, problem_id) VALUES (1, 1);
INSERT INTO problem_course (course_id, problem_id) VALUES (2, 2);

-- Tag 테이블 데이터
INSERT INTO tag (name) VALUES ('JAVA');
INSERT INTO tag (name) VALUES ('JAVASCRIPT');

-- Problem_Tag 테이블 데이터 (연결 테이블)
INSERT INTO problem_tag (problem_id, tag_id) VALUES (1, 1);
INSERT INTO problem_tag (problem_id, tag_id) VALUES (2, 2);

-- Solution 테이블 데이터
INSERT INTO solution (problem_id, content) VALUES (1, '열거형(Enum)은 관련된 상수들의 집합을 정의하여, 이름이 있는 값의 고정된 집합을 표현하는 특별한 클래스입니다.');
INSERT INTO solution (problem_id, content) VALUES (2, '클로저는 함수와 해당 함수가 선언될 때의 렉시컬 환경을 함께 기억하여, 외부 함수의 변수에 접근할 수 있는 기능을 제공합니다.');
INSERT INTO solution (problem_id, content) VALUES (1, '특정 값 집합을 열거형으로 정의하여 코드의 가독성과 안전성을 높이는 데 사용됩니다.');
INSERT INTO solution (problem_id, content) VALUES (2, '외부 함수의 변수를 기억하고 접근할 수 있는 내부 함수를 통해 데이터 은닉과 상태 유지가 가능합니다.');
