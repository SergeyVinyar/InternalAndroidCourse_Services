package ru.vinyarsky.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    private volatile boolean mShouldInterrupt;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        new Thread(() -> {
            int step = 0;
            while (true) {
                Log.d(TAG, "We're doing something: " + step++);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Do nothing
                }

                if (mShouldInterrupt) {
                    Log.d(TAG, "Interrupted");
                    break;
                }
            }
        }).start();

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mShouldInterrupt = true;
    }
}
