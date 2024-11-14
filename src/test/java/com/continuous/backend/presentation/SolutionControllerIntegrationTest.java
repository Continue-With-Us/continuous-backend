package com.continuous.backend.presentation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SolutionControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public void setUp() {
        RestAssured.port = port;
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
            .body("data.content", equalTo("This is a test solution"))
            .body("data.problemId", equalTo((int)problemId)); // JSON에서 long을 int로 변환될 수 있음
    }
}
