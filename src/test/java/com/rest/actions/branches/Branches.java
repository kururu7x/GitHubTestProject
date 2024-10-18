package com.rest.actions.branches;

import io.restassured.response.Response;

public interface Branches {

    Response createBranch(String repoName, String branchName, String baseBranchHash);

    Response getBranchesList(String repoName);

    Response getBranch(String branchName, String repoName);

    Response checkoutBranch(String owner, String repoName, String branchName);

}
