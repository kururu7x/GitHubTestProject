package com.rest.actions.repositories;

import com.rest.actions.SpecBuilder;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RepositoriesGitHub extends SpecBuilder implements Repositories{

    @Override
    public Response createSimpleRepositoryForAuthenticatedUser(String repoName){
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("name", repoName);

        return given(getRequestSpec())
                .body(payload)
            .when()
                .post("/user/repos")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    @Override
    public Response getRepository(String repoName){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
            .when()
                .get("/{repo}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    @Override
    public Response getListRepositoriesForTheAuthenticatedUser(){
        return given(getRequestSpec())
            .when()
                .get("/user/repos")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    @Override
    public Response deleteRepository(String repoName){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
            .when()
                .delete("/{repo}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

}
