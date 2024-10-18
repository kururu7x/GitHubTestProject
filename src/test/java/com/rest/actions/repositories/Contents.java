package com.rest.actions.repositories;

import com.rest.actions.SpecBuilder;
import com.rest.utils.EncodeUtils;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Contents extends SpecBuilder {

    public Response createOrUpdateFileContent(String repoName, String path, String fileContent, String message){
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("message", message);
        payload.put("content", EncodeUtils.ToBase64(fileContent));

        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .pathParam("path", path)
                .body(payload)
            .when()
                .put("/{repo}/contents/{path}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    public Response downloadRepositoryArchiveZip(String repoName, String branch){
        return given(getRequestSpecForRepos())
                .header("Accept", "application/vnd.github+json")
                .pathParam("repo", repoName)
                .pathParam("branch", branch)
            .when()
                .get("/{repo}/zipball/refs/heads/{branch}")
            .then().spec(getResponseSpec())
                .extract().response();
    }

}
