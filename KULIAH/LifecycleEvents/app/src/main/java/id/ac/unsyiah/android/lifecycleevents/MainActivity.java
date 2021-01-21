package id.ac.unsyiah.android.lifecycleevents;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(MAIN_ACTIVITY,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MAIN_ACTIVITY,"onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(MAIN_ACTIVITY,"onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MAIN_ACTIVITY,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MAIN_ACTIVITY,"onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(MAIN_ACTIVITY,"onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MAIN_ACTIVITY,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MAIN_ACTIVITY,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MAIN_ACTIVITY,"onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Log.d(MAIN_ACTIVITY,"Dalam kondisi portrait");
        }else{
            Log.d(MAIN_ACTIVITY,"Dalam kondisi landscape");
        }
    }
}
