package com.rxjavastudy.RxJava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class StudyFlowable {
    public void initFlow() {
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
                        Logger.i(integer + "");
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

    Subscription mSubscription;

    public void request() {
        mSubscription.request(20);
    }

    public void latest() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                for (int i = 0; i < 10000; i++) {
                    e.onNext(i + "");
                }
            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(128);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("kklv", s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void drop() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                for (int i = 0; i < 10000; i++) {
                    e.onNext(i + "");
                }
            }
        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(128);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("kklv", s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loop() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 1; i <= 128; i++) {
                    e.onNext(i);
                }
                boolean flag=true;
                int j=1;
                while (flag){
                    if(e.requested() > 0){
                        Log.i("kklv","send:"+j);
                        e.onNext(j++);
                    }
                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(128);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("kklv","receive:"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
