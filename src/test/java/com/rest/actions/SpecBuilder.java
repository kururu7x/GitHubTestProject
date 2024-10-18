package com.rest.actions;

import com.rest.utils.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    static String owner = ConfigLoader.getInstance().getLogin();

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .addHeader("Authorization","Bearer " + ConfigLoader.getInstance().getToken())
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public RequestSpecification getRequestSpecForRepos(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl() + String.format("/repos/%s", owner))
                .addHeader("Authorization","Bearer " + ConfigLoader.getInstance().getToken())
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }



    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
//                .log(LogDetail.STATUS)
//                .log(LogDetail.METHOD)
//                .log(LogDetail.URI)
////                .log(LogDetail.PARAMS)
////                .log(LogDetail.HEADERS)
////                .log(LogDetail.COOKIES)
//                .log(LogDetail.BODY)
                .log(LogDetail.ALL)
                .build();
    }
}
