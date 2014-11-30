package me.xbt.shakedetect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.WatchViewStub;
import android.view.WindowManager;
import android.widget.TextView;

import me.xbt.shakedetect.lib.ShakeDetectActivity;
import me.xbt.shakedetect.lib.ShakeDetectActivityListener;

public class WatchActivity extends Activity {

    private TextView mTextView;
    private TextView mTextView2;

    ShakeDetectActivity shakeDetectActivity;
    int shakeCount = 0; // number of times shake is detected.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mTextView2 = (TextView) stub.findViewById(R.id.text2);
            }
        });

        shakeDetectActivity = new ShakeDetectActivity(this);
        shakeDetectActivity.addListener(new ShakeDetectActivityListener() {
            @Override
            public void shakeDetected() {
                WatchActivity.this.triggerShakeDetected();

            }
        });
    }

    /**
     * this will keep the screen on while the activity is active
     */
    @Override
    protected void onResume() {
        super.onResume();
        shakeDetectActivity.onResume();
        getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        shakeDetectActivity.onPause();
        super.onPause();
    }

    public void triggerShakeDetected() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
        shakeCount++;
        mTextView2.setText("shakes detected: " + shakeCount);
        //nextPicture();
    }
}
