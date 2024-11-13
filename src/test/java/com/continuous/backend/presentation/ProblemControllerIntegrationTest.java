package com.continuous.backend.presentation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/integration-test-data.sql")
class ProblemControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
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
}
