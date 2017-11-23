package com.rxjavastudy.RxJava;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/22.
 */

public class StudyObservable {
    public void delay() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Logger.i("start");
                e.onNext("delay");
            }
        })
                .delay(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.i(s);
                    }
                });
    }

    public void subscribeOnNewThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Logger.i("subscribe");
                e.onNext("haha");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.i("onNext 1");
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.i("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError");
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("onComplete");
                    }
                });
    }

    public void countDownTimer(final Context context) {
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
                        Toast.makeText(context, "计时开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.i(aLong + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "计时出错", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(context, "倒计时结束", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
