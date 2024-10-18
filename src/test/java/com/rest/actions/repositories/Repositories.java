package com.rest.actions.repositories;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public interface Repositories {

    Response createSimpleRepositoryForAuthenticatedUser(String repoName);

    Response getRepository(String repoName);

    Response getListRepositoriesForTheAuthenticatedUser();

    Response deleteRepository(String repoName);

}
