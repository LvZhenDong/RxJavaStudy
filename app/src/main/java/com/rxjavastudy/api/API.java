package com.rxjavastudy.api;

import com.rxjavastudy.bean.response.Branch;
import com.rxjavastudy.bean.response.UserFollower;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface API {

    String BASE_URL_GIT_HUB = "https://api.github.com/";

    @GET("users/{user}/followers")
    Observable<List<UserFollower>> listUserFollowers(@Path("user") String user);

    @GET("repos/{owner}/{repo}/branches")
    Observable<List<Branch>> listBranches(@Path("owner") String owner,@Path("repo") String repo);
}
