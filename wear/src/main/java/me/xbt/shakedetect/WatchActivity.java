package me.xbt.shakedetect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import me.xbt.shakedetect.lib.ShakeDetectActivity;
import me.xbt.shakedetect.lib.ShakeDetectActivityListener;

public class WatchActivity extends Activity {

    private TextView mTextView;

    ShakeDetectActivity shakeDetectActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
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

    public void triggerShakeDetected() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
        //nextPicture();
    }
}
