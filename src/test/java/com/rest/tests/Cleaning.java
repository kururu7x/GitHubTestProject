package com.rest.tests;

import com.rest.actions.repositories.RepositoriesGitHub;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Cleaning {

//    @Test
    @AfterSuite
    public void CreateAndDeleteRepository(){
        RepositoriesGitHub repositories = new RepositoriesGitHub();

    //get repos
        var getReposResponse = repositories
                .getListRepositoriesForTheAuthenticatedUser();
        List<String> reposNamesList  = getReposResponse.path("name");

    //select repos to delete
        List<String> reposToDelete = new ArrayList<>();
        reposNamesList.forEach(r -> {
            if (r.startsWith("Repo_")) {
                reposToDelete.add(r);
            }
        });

    //delete repos
        reposToDelete.forEach(repo -> repositories.deleteRepository(repo));
    }
}
