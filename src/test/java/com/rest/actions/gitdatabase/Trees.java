package com.rest.actions.gitdatabase;

import com.rest.actions.SpecBuilder;
import com.rest.pojo.tree.RootTree;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Trees extends SpecBuilder {

    public Response getTree(String repoName, String branch){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .pathParam("branch", branch)
            .when()
                .get("/{repo}/git/trees/{branch}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    public Response createTree(String repoName, RootTree payload){
        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .body(payload)
            .when()
                .post("/{repo}/git/trees")
            .then().spec(getResponseSpec())
                .extract().response();

    }
}
