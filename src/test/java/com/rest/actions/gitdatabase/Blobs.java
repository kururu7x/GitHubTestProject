package com.rest.actions.gitdatabase;

import com.rest.actions.SpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Blobs extends SpecBuilder {

    public Response createBlob(String repoName, Object payload){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .body(payload)
            .when()
                .post("/{repo}/git/blobs")
            .then()
                .extract().response();
    }
}
