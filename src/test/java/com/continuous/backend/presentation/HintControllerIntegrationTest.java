package com.continuous.backend.presentation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
class HintControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public void setUp() throws Exception {
        RestAssured.port = port;

        executeSqlScript("integration-test-data.sql");
    }

    private void executeSqlScript(String scriptPath) throws Exception {
        String sql = new String(getClass().getClassLoader().getResourceAsStream(scriptPath).readAllBytes());
        jdbcTemplate.execute(sql);
    }

    @DisplayName("GET /v1/problems/{id}/hints - 문제에 대한 힌트를 조회한다.")
    @Test
    @Sql("/hint-test-data.sql")
    void getHint() {
        long problemId = 1L;

        given()
            .log().all()
            .contentType(ContentType.JSON)
            .when()
            .get("/v1/problems/{id}/hints", problemId)
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("success"))
            .body("data", notNullValue())
            .body("data.exampleCode", notNullValue())
            .body("data.resourceUrl", notNullValue());
    }

    @DisplayName("GET /v1/problems/{id}/hints - 존재하지 않는 문제 ID로 조회하면 404를 반환한다.")
    @Test
    void getHintByNonExistentProblemId() {
        long problemId = 9999L;

        given()
            .log().all()
            .contentType(ContentType.JSON)
            .when()
            .get("/v1/problems/{id}/hints", problemId)
            .then()
            .statusCode(404)
            .contentType(ContentType.JSON)
            .body("status", equalTo("error"))
            .body("message", notNullValue());
    }
}
