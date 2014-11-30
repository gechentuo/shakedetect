package me.xbt.shakedetect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;

import me.xbt.shakedetect.lib.ShakeDetectActivity;
import me.xbt.shakedetect.lib.ShakeDetectActivityListener;


public class PhoneActivity extends Activity {

    ShakeDetectActivity shakeDetectActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        shakeDetectActivity = new ShakeDetectActivity(this);
        shakeDetectActivity.addListener(new ShakeDetectActivityListener() {
            @Override
            public void shakeDetected() {
                PhoneActivity.this.triggerShakeDetected();

            }
        });
    }

    public void triggerShakeDetected() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
        //nextPicture();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

}
