package com.rxjavastudy.RxJava;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 操作符
 *
 * @author LvZhenDong
 *         created on 2017/11/23 11:04
 */
public class Operator {
    Integer[] datas=new Integer[]{0,1,2,3,4};
    int[] data1=new int[]{0,1,2,3,4};

    public void fromArray(){
        Observable.fromArray(datas).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("kklv",integer+"");
            }
        });
    }

    public void fromArray2(){
        Observable.fromArray(data1).subscribe(new Consumer<int[]>() {
            @Override
            public void accept(int[] ints) throws Exception {
                Log.i("kklv",ints.length+"");
            }
        });
    }

    public void just(){
        Observable.just(1,2,3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("kklv","just:"+integer);
            }
        });
    }

    List<Integer> list= Arrays.asList(datas);
    public void fromIterable(){
        Observable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("kklv","iterable:"+integer);
            }
        });
    }
    Observable deferObservable;
    public void defer(){
        deferObservable=Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return Observable.just(1);
            }
        });
    }

    public void clickDefer(Consumer<Integer> Observable){
        deferObservable.subscribe(Observable);
    }

    public void repeat(){
        Observable.just(1)
                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("kklv","repeat:"+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("kklv","repeat:complete");
                    }
                });
    }
}
