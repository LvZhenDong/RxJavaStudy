package com.rxjavastudy;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.rxjavastudy.api.API;
import com.rxjavastudy.bean.request.LoginRequest;
import com.rxjavastudy.bean.response.BaseResponseBean;
import com.rxjavastudy.bean.response.LoginResponseBean;
import com.rxjavastudy.bean.response.UserFollowers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login();
        listUserFollowers();
    }

    void listUserFollowers() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL_GIT_HUB)
                .addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<List<UserFollowers>> call = api.listUserFollowers("lvzhendong");

        call.enqueue(new Callback<List<UserFollowers>>() {
            @Override
            public void onResponse(Call<List<UserFollowers>> call, Response<List<UserFollowers>>
                    response) {
                Logger.i(response.body().get(0).getLogin());
            }

            @Override
            public void onFailure(Call<List<UserFollowers>> call, Throwable t) {
                Logger.i(t.getMessage());
            }
        });

    }

    void login() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL_EHUU).addConverterFactory
                (GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);

        LoginRequest request = new LoginRequest();
        request.setLoginName("111865");
        request.setPassword("123456");

        Call<BaseResponseBean<LoginResponseBean>> call = api.login(request);
        call.enqueue(new Callback<BaseResponseBean<LoginResponseBean>>() {
            @Override
            public void onResponse(Call<BaseResponseBean<LoginResponseBean>> call,
                                   Response<BaseResponseBean<LoginResponseBean>> response) {

                Logger.i(response.toString());
            }

            @Override
            public void onFailure(Call<BaseResponseBean<LoginResponseBean>> call, Throwable t) {
                Logger.i(t.getMessage());
            }
        });
    }

}
