package com.rxjavastudy.api;

import com.rxjavastudy.bean.request.LoginRequest;
import com.rxjavastudy.bean.response.BaseResponseBean;
import com.rxjavastudy.bean.response.LoginResponseBean;
import com.rxjavastudy.bean.response.UserFollowers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface API {

    String BASE_URL_GIT_HUB = "https://api.github.com/";
    String BASE_URL_EHUU = "http://api-qa.ehuu.com/site-api/";

    @POST("userlogin")
    Call<BaseResponseBean<LoginResponseBean>> login(@Body LoginRequest body);

    @GET("users/{user}/followers")
    Call<List<UserFollowers>> listUserFollowers(@Path("user") String user);
}
