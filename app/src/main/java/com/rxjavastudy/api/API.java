package com.rxjavastudy.api;

import com.rxjavastudy.bean.response.UserFollowers;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface API {

    String BASE_URL_GIT_HUB = "https://api.github.com/";

    @GET("users/{user}/followers")
    Observable<List<UserFollowers>> listUserFollowers(@Path("user") String user);
}
