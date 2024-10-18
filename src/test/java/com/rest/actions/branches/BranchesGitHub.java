package com.rest.actions.branches;

import com.rest.actions.SpecBuilder;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BranchesGitHub extends SpecBuilder implements Branches{

    @Override
    public Response createBranch(String repoName, String branchName, String baseBranchHash){

        HashMap<String, String> payload = new HashMap<>();
        payload.put("ref", "refs/heads/" + branchName);
        payload.put("sha", baseBranchHash);

        return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .body(payload)
            .when()
                .post("/{repo}/git/refs")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    @Override
    public Response getBranchesList(String repoName){
        return given(getRequestSpecForRepos())
                .pathParams("repo", repoName)
            .when()
                .get("/{repo}/branches")
            .then().spec(getResponseSpec())
                .extract().response();
    }

    @Override
    public Response getBranch(String branchName, String repoName){
         return given(getRequestSpecForRepos())
                .pathParam("repo", repoName)
                .pathParam("branch", branchName)
            .when()
                .get("/{repo}/branches/{branch}")
            .then().spec(getResponseSpec())
                .extract().response();

    }

    @Override
    public Response checkoutBranch(String owner, String repoName, String branchName){
        return given(getRequestSpec())
                .pathParam("owner", owner)
                .pathParam("repo", repoName)
                .pathParam("branch", branchName)
            .when()
                .get("/{owner}/{repo}/tree/{branch)")
            .then()
                .extract().response();
    }

}
