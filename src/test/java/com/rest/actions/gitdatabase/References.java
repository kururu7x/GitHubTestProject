package com.rest.actions.gitdatabase;

import com.rest.actions.SpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class References extends SpecBuilder {

    public Response createReference(String repoName){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
            .when()
                .get("/{repo}/git/refs")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    public Response getReference(String repoName, String branch){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .pathParam("branch", branch)
            .when()
                .get("/{repo}/git/refs/heads/{branch}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    public Response updateReference(String repoName, String branch, Object payload){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .pathParam("branch", branch)
                .body(payload)
            .when()
                .patch("/{repo}/git/refs/heads/{branch}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    public String createReferenceAndGetSha(String repoName){
        return createReference(repoName)
                .path("object.sha")
                .toString()
                .replace("[","")
                .replace("]", "");
    }

}
