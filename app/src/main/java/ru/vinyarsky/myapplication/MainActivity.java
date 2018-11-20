package ru.vinyarsky.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyService.class);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(v -> {
            Log.d(TAG, "startService");
            startService(intent);
        });

        Button stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(v -> {
            Log.d(TAG, "stopService");
            stopService(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
