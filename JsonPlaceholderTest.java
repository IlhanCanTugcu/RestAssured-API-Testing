package com.testproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // TEST 1: Tek bir gönderi getir (GET)
    @Test
    @DisplayName("GET /posts/1 - Gönderi başarıyla getirilmeli")
    public void testGetSinglePost() {
        given()
            .log().all()                                          // Giden isteği (Request) konsola yazdırır
        .when()
            .get("/posts/1")
        .then()
            .log().all()                                          // Dönen cevabı (Response) konsola yazdırır
            .statusCode(200)                                      // HTTP 200 mi?
            .time(lessThan(3000L))                                // 3 saniyeden hızlı mı?
            .body("id", equalTo(1))                               // id = 1 mi?
            .body("userId", equalTo(1))                           // userId = 1 mi?
            .body("title", notNullValue())                        // title boş değil mi?
            .body("body", notNullValue());                        // body boş değil mi?
    }

    // TEST 2: Tüm gönderileri getir (GET)
    @Test
    @DisplayName("GET /posts - Tüm gönderiler getirilmeli")
    public void testGetAllPosts() {
        given()
            .log().all()                                          // Giden isteği (Request) konsola yazdırır
        .when()
            .get("/posts")
        .then()
            .log().all()                                          // Dönen cevabı (Response) konsola yazdırır
            .statusCode(200)                                      // HTTP 200 mi?
            .time(lessThan(3000L))                                // 3 saniyeden hızlı mı?
            .body("size()", equalTo(100));                        // 100 gönderi var mı?
    }

    // TEST 3: Yeni gönderi oluştur (POST)
    @Test
    @DisplayName("POST /posts - Yeni gönderi oluşturulmalı")
    public void testCreatePost() {
        // Göndereceğimiz JSON verisi
        String requestBody = """
                {
                    "title": "Test Başlığı",
                    "body": "Bu bir test gönderisidir.",
                    "userId": 1
                }
                """;

        given()
            .log().all()                                          // Giden isteği (Request) konsola yazdırır
            .header("Content-Type", "application/json")           // JSON gönderiyoruz
            .body(requestBody)                                    // İçerik
        .when()
            .post("/posts")
        .then()
            .log().all()                                          // Dönen cevabı (Response) konsola yazdırır
            .statusCode(201)                                      // HTTP 201 Created mi?
            .time(lessThan(3000L))                                // 3 saniyeden hızlı mı?
            .body("title", equalTo("Test Başlığı"))               // title doğru mu?
            .body("userId", equalTo(1))                           // userId doğru mu?
            .body("id", notNullValue());                          // id atandı mı?
    }
}