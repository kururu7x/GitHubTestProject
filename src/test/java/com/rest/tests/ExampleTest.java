package com.rest.tests;

import com.rest.actions.branches.BranchesGitHub;
import com.rest.actions.gitdatabase.Blobs;
import com.rest.actions.gitdatabase.Commits;
import com.rest.actions.gitdatabase.References;
import com.rest.actions.gitdatabase.Trees;
import com.rest.actions.repositories.Contents;
import com.rest.actions.repositories.RepositoriesGitHub;
import com.rest.pojo.blob.RootBlob;
import com.rest.pojo.commit.blob.RootCommit;
import com.rest.pojo.tree.RootTree;
import com.rest.pojo.tree.Tree;
import com.rest.utils.ConfigLoader;
import com.rest.utils.RandomUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.Matchers.*;

public class ExampleTest {

    private static String owner = ConfigLoader.getInstance().getLogin();

    @Test
    public void createAndDeleteRepository(){
        var repoName = RandomUtils.generateString("Repo");

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("name", repoName);

        RepositoriesGitHub repositories = new RepositoriesGitHub();

    //create new repo
        repositories
                .createSimpleRepositoryForAuthenticatedUser(repoName)
            .then()
                .assertThat()
                .statusCode(201)
                .body("name", is(equalTo(repoName)),
                        "full_name", is(equalTo(owner + "/" + repoName)),
                        "owner.login", is(equalTo(owner)),
                        "visibility", is(equalTo("public")));

    //get created repo
        repositories.getRepository(repoName)
                .then()
                .assertThat()
                .statusCode(200)
                .body("owner.login", is(equalTo(owner)));

    //get user repo list
        var getReposResponse = repositories
                .getListRepositoriesForTheAuthenticatedUser();

        Assert.assertEquals(getReposResponse.getStatusCode(), 200);

        ArrayList<String> reposNamesList  = getReposResponse.path("name");
        Assert.assertTrue(reposNamesList.contains(repoName));

    //delete repo
        repositories.deleteRepository(repoName)
                .then()
                .assertThat()
                .statusCode(204);

    //get user repo list after delete
        var getReposResponseAfterDelete = repositories
                .getListRepositoriesForTheAuthenticatedUser();

        ArrayList<String> reposNamesListAfterDelete  = getReposResponseAfterDelete.path("name");
        Assert.assertFalse(reposNamesListAfterDelete.contains(repoName));
    }


    @Test
    public void downloadRepository(){
        var repoName = RandomUtils.generateString("Repo");
        var path = "usefulDocuments/doc1.txt";
        var fileContent = RandomUtils.generateString();
        var message = "I added doc1 document";
        var branch = "main";

        RepositoriesGitHub repositories = new RepositoriesGitHub();
        repositories.createSimpleRepositoryForAuthenticatedUser(repoName);

        Contents contents = new Contents();
        contents.createOrUpdateFileContent(repoName, path, fileContent, message);

        contents.downloadRepositoryArchiveZip(repoName, branch)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/zip");
    }

    @Test
    public void createFileContent(){
        var repoName = "Repo_" + RandomUtils.generateString();
        var fileContent = "mainfilecontent_" + RandomUtils.generateString();
        var pathInMain = "notatki/hello.txt";
        var message = "my commit message";
        var branchName = "branch" + RandomUtils.generateString(5);

    //create repository
        RepositoriesGitHub repositories = new RepositoriesGitHub();
        repositories.createSimpleRepositoryForAuthenticatedUser(repoName)
                .then()
                .assertThat()
                .statusCode(201);

    // create file content
        Contents contents = new Contents();
        contents.createOrUpdateFileContent(repoName, pathInMain, fileContent, message)
            .then()
                .assertThat()
                .statusCode(201);

    // create new branch
        var sha = new References()
                .createReferenceAndGetSha(repoName);

        BranchesGitHub branches = new BranchesGitHub();
        branches.createBranch(repoName, branchName, sha)
                .then()
                .assertThat()
                .statusCode(201);

    // get branch list
        List<String> expectedBranches = Arrays.asList("main", branchName);
        Response repoListResponse = branches.getBranchesList(repoName);
        Assert.assertEqualsNoOrder(repoListResponse.path("name"), expectedBranches);
    }

    @Test
    public void pushFewFilesInOneCommit(){
        var repoName = "Repo_" + RandomUtils.generateString();
        var fileContent = "mainfilecontent_" + RandomUtils.generateString();
        var pathInMain = "notatki/hello.txt";
        var message = "Some commit message";
        var branchName = "branch" + RandomUtils.generateString(5);

    //create repository
        RepositoriesGitHub repositories = new RepositoriesGitHub();
        repositories.createSimpleRepositoryForAuthenticatedUser(repoName);
//                .then().statusCode(201);

    // create file content
        Contents contents = new Contents();
        contents.createOrUpdateFileContent(repoName, pathInMain, fileContent, message);
//                .then()
//                .statusCode(201);

    // create new branch
        var sha = new References().createReferenceAndGetSha(repoName);

        BranchesGitHub branches = new BranchesGitHub();
        branches.createBranch(repoName, branchName, sha)
                .then()
                .assertThat()
                .statusCode(201);

    // get branch list
        List<String> expectedBranches = Arrays.asList("main", branchName);
        Response repoListResponse = branches.getBranchesList(repoName);
        Assert.assertEqualsNoOrder(repoListResponse.path("name"), expectedBranches);

    //Create blobs
        RootBlob rootBlobA = new RootBlob("print(\\\"Hello !\\\")", "utf-8");
        RootBlob rootBlobB = new RootBlob("print(\\\"World !\\\")", "utf-8");

        Blobs blobs = new Blobs();
        var blobShaA = blobs.createBlob(repoName, rootBlobA).path("sha").toString();
        var blobShaB = blobs.createBlob(repoName, rootBlobB).path("sha").toString();

    // Create a tree
        Trees baseTree = new Trees();
        var baseTreeResponse = baseTree.getTree(repoName, branchName);
        String baseTreeSha = baseTreeResponse.path("sha").toString();

        var type = "blob";
        var mode = "100644";

        Tree treeA = new Tree();
        treeA.setPath("helloworld/hello.py");
        treeA.setMode(mode);
        treeA.setType(type);
        treeA.setSha(blobShaA);

        Tree treeB = new Tree();
        treeB.setPath("helloworld/world.py");
        treeB.setMode(mode);
        treeB.setType(type);
        treeB.setSha(blobShaB);

        List<Tree> treeList = Arrays.asList(treeA, treeB);

        RootTree rootTree = new RootTree(treeList, baseTreeSha);

        Trees trees = new Trees();
        var createdTreeResponse = trees.createTree(repoName, rootTree);
        var createdTreeSha = createdTreeResponse.path("sha").toString();

        References references = new References();
        Response referenceResponse = references.getReference(repoName, branchName);
        var branchSha = referenceResponse.path("object.sha").toString();

    // add commit
        RootCommit rootCommit = new RootCommit();
        rootCommit.setTree(createdTreeSha);
        rootCommit.setMessage("my commit message");
        rootCommit.setParents(Collections.singletonList(branchSha));

        Commits commits = new Commits();
        Response commitResponse = commits.createCommit(repoName, rootCommit);
        Assert.assertEquals(commitResponse.statusCode(), 201);
        var commitSha = commitResponse.path("sha").toString();

    //update reference
        HashMap<String, String> payloadToUpdateResponse = new HashMap<>();
        payloadToUpdateResponse.put("sha", commitSha);

        References newReferences = new References();
        Response newReferenceResponse = newReferences.updateReference(repoName, branchName, payloadToUpdateResponse);
        Assert.assertEquals(newReferenceResponse.statusCode(), 200);
    }

}
