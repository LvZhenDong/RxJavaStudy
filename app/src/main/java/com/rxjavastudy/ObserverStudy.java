package com.rxjavastudy;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ObserverStudy {
    public void start(){
        Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
            }
        });

        Observer<Integer> observer =new Observer<Integer>(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.i("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Logger.i("onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.i("onError");
            }

            @Override
            public void onComplete() {
                Logger.i("onComplete");
            }
        };

        observable.subscribe(observer);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {

            }
        });
    }
}
