package com.example.suleman_pc.assignment4_services;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        textView = findViewById(R.id.DisplayTime);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                switch (view.getId()) {
                    case R.id.start_service:
                        startService(intent);
                        break;
                    case R.id.end_service:
                        stopService(intent);
                        break;
                }
            }
        };
        findViewById(R.id.start_service).setOnClickListener(listener);
        findViewById(R.id.end_service).setOnClickListener(listener);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MyEvent event) {
        Integer i = event.getTime();
        textView.setText(i.toString());

    }
}





