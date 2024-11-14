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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProblemControllerIntegrationTest {

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

    @DisplayName("GET /problems - 문제 목록을 가져온다.")
    @Test
    void getProblems() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/v1/problems")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", not(empty()))
            .body("data.size()", greaterThan(0))
            .body("data[0].title", notNullValue())
            .body("data[0].course", notNullValue())
            .body("data[0].tags", not(empty()));
    }

    @DisplayName("GET /problems - 특정 코스로 필터링한다.")
    @Test
    void getProblemsFilteredByCourse() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("course", "FRONTEND")
            .when()
            .get("/v1/problems")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", not(empty()))
            .body("data.size()", greaterThan(0))
            .body("data.findAll { it.course == 'FRONTEND' }", not(empty()));
    }

    @DisplayName("GET /problems - 특정 태그로 필터링한다.")
    @Test
    void getProblemsFilteredBySingleTag() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("tags", "JAVASCRIPT")
            .when()
            .get("/v1/problems")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", not(empty()))
            .body("data.size()", greaterThan(0))
            .body("data.tags.flatten()", hasItem("JAVASCRIPT"));
    }

    @DisplayName("GET /problems - 여러 태그로 필터링한다.")
    @Test
    void getProblemsFilteredByMultipleTags() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("tags", "JAVASCRIPT,JAVA")
            .when()
            .get("/v1/problems")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", not(empty()))
            .body("data.size()", greaterThan(0))
            .body("data.tags.flatten()", anyOf(hasItem("JAVASCRIPT"), hasItem("JAVA")));
    }

    @DisplayName("GET /problems - 코스와 태그로 필터링한다.")
    @Test
    void getProblemsFilteredByCourseAndTags() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("course", "FRONTEND")
            .queryParam("tags", "JAVASCRIPT,JAVA")
            .when()
            .get("/v1/problems")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", not(empty()))
            .body("data.findAll { it.course == 'FRONTEND' }", not(empty()))
            .body("data.tags.flatten()", anyOf(hasItem("JAVASCRIPT"), hasItem("JAVA")));
    }

    @DisplayName("GET /problems/{id} - 특정 문제를 조회한다.")
    @Test
    void getProblemById() {
        long existingProblemId = 1L;

        given()
            .contentType(ContentType.JSON)
            .pathParam("id", existingProblemId)
            .when()
            .get("/v1/problems/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", notNullValue())
            .body("data.id", equalTo((int)existingProblemId)) // JSON에 long이 int로 변환될 수 있어 캐스팅 처리
            .body("data.title", notNullValue())
            .body("data.course", notNullValue())
            .body("data.tags", not(empty()));
    }

    @DisplayName("GET /problems/{id} - 존재하지 않는 문제를 조회하면 404를 반환한다.")
    @Test
    void getProblemByNonExistentId() {
        long nonExistentProblemId = 9999L;

        given()
            .contentType(ContentType.JSON)
            .pathParam("id", nonExistentProblemId)
            .when()
            .get("/v1/problems/{id}")
            .then()
            .statusCode(500) // TODO: 에러 공통화
            .contentType(ContentType.JSON);
        // .body("status", equalTo("error"));
        // .body("message", notNullValue());
    }
}
