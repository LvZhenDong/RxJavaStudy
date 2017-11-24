package com.rxjavastudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.rxjavastudy.RxJava.Operator;
import com.rxjavastudy.RxJava.StudyFlowable;
import com.rxjavastudy.RxJava.StudyObservable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;
    @BindView(R.id.tv)
    TextView mTv;
    StudyFlowable studyFlowable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder=ButterKnife.bind(this);

        StudyObservable studyObservable=new StudyObservable();

        operator=new Operator();
//        operator.defer();
//        operator.fromArray();
//        operator.just();
//        operator.fromIterable();
        operator.repeat();
    }
    Operator operator;


    @OnClick(R.id.tv)
    public void onViewClicked() {
//        studyFlowable.request();
        operator.clickDefer(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("kklv",integer+"");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
