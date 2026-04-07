package com.hayaisoftware.launcher.activities;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.hayaisoftware.launcher.BuildConfig;
import com.hayaisoftware.launcher.R;
public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView aboutTextView=((TextView) findViewById(R.id.about_textview));
        aboutTextView.setText(aboutTextView.getText().toString().replace("[APP_VERSION]",
                BuildConfig.VERSION_NAME));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
