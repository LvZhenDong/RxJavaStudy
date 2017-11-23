package com.rxjavastudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rxjavastudy.RxJava.StudyFlowable;
import com.rxjavastudy.RxJava.StudyObservable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

        studyFlowable=new StudyFlowable();
        studyFlowable.loop();
    }



    @OnClick(R.id.tv)
    public void onViewClicked() {
        studyFlowable.request();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
