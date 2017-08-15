package com.rxjavastudy;

import android.app.Activity;
import android.os.Bundle;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;
import com.rxjavastudy.api.API;
import com.rxjavastudy.bean.response.Branch;
import com.rxjavastudy.bean.response.UserFollower;

import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUserFollowers();

    }

    void listUserFollowers() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL_GIT_HUB)
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        final API api = retrofit.create(API.class);

        api.listUserFollowers("lvzhendong")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<UserFollower>>() {
                    @Override
                    public void accept(@NonNull List<UserFollower> userFollowers) throws Exception {
                        Logger.i(userFollowers.get(0).getLogin());
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<UserFollower>, ObservableSource<List<Branch>>>() {
                    @Override
                    public ObservableSource<List<Branch>> apply(@NonNull List<UserFollower> userFollowers) throws Exception {
                        return api.listBranches("LvZhenDong", "Monitor");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Branch>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Branch> branches) {
                        Logger.i(branches.get(0).getName()+"");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("onComplete");
                    }
                });

    }

}
