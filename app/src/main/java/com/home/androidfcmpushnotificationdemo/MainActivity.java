package com.home.androidfcmpushnotificationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.home.androidfcmpushnotificationdemo.eventbus.TokenEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tokenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenTextView = findViewById(R.id.tokenTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = FirebaseInstanceId.getInstance().getToken(); // 取得FirebaseInstanceId的token
        if (token != null) {
            tokenTextView.setText(token);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this); // 註冊EventBus
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this); // 解除註冊EventBus
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTokenEvent(TokenEvent event) { // 接收TokenEvent的事件
        String token = event.getToken();
        tokenTextView.setText(token);
    }
}
