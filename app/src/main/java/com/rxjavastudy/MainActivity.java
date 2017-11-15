package com.rxjavastudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;
import com.rxjavastudy.api.API;
import com.rxjavastudy.bean.response.Branch;
import com.rxjavastudy.bean.response.UserFollower;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        listUserFollowers();
//        countDownTimer();
        initFlow();
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
                        Logger.i(branches.get(0).getName() + "");
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

    @OnClick(R.id.tv)
    public void onViewClicked() {
    }

    private void countDownTimer() {
        final int timerLong = 10;
        Observable.interval(1, TimeUnit.SECONDS)
                .take(timerLong + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return timerLong - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.i("onSubscribe");
                        Toast.makeText(MainActivity.this, "计时开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.i(aLong + "");
                        mTv.setText(aLong + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "计时出错", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initFlow() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(0);
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.i(integer+"");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.i("onError");
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("onComplete");
                    }
                });
    }
}
