package com.rest.actions.gitdatabase;

import com.rest.actions.SpecBuilder;
import com.rest.pojo.commit.blob.RootCommit;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Commits extends SpecBuilder {

    public Response createCommit(String repoName, RootCommit payload){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .body(payload)
            .when()
                .post("/{repo}/git/commits")
            .then().spec(getResponseSpec())
                .extract().response();
    }

}
