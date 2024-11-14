package com.continuous.backend.presentation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolutionControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public void setUp() throws IOException {
        RestAssured.port = port;

        executeSqlScript("integration-test-data.sql");
    }

    private void executeSqlScript(String scriptPath) throws IOException {
        String sql = new String(getClass().getClassLoader().getResourceAsStream(scriptPath).readAllBytes());
        jdbcTemplate.execute(sql);
    }

    @DisplayName("POST /v1/problems/{id}/solutions - 솔루션을 제출한다.")
    @Test
    void submitSolution() {
        long problemId = 1L;

        String requestBody = """
            {
                "content": "나의 답변"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/v1/problems/{id}/solutions", problemId)
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", notNullValue())
            .body("data.content", equalTo("나의 답변"))
            .body("data.problemId", equalTo((int)problemId)); // JSON에서 long을 int로 변환될 수 있음
    }

    @DisplayName("POST /v1/problems/{id}/solutions - 존재하지 않는 문제 ID로 제출하면 400을 반환한다.")
    @Test
    void submitSolutionByNonExistentProblemId() {
        long problemId = 9999L;

        String requestBody = """
            {
                "content": "나의 답변"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/v1/problems/{id}/solutions", problemId)
            .then()
            .statusCode(404)
            .contentType(ContentType.JSON);
    }

    @DisplayName("GET /v1/problems/{id}/solutions - 문제에 대한 솔루션 목록을 조회한다.")
    @Test
    @Sql("/solution-test-data.sql")
    void getSolutions() {
        long problemId = 1L;

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/v1/problems/{id}/solutions", problemId)
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", not(empty()))
            .body("data.size()", greaterThan(0))
            .body("data[0].problemId", equalTo((int)problemId))
            .body("data[0].content", notNullValue());
    }

    @DisplayName("GET /v1/problems/{id}/solutions - 존재하지 않는 문제 ID로 조회하면 404를 반환한다.")
    @Test
    void getSolutionsByNonExistentProblemId() {
        long problemId = 9999L;

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/v1/problems/{id}/solutions", problemId)
            .then()
            .statusCode(404)
            .contentType(ContentType.JSON);
    }
}
