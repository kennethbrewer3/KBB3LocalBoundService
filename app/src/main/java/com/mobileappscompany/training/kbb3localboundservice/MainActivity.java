package com.mobileappscompany.training.kbb3localboundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private Context context;
    private BoundService kbb3Service;
    private boolean isBound = false;

    private TextView textviewTimeDate;
    private Button buttonShowTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        textviewTimeDate = (TextView)findViewById(R.id.textviewTimeDate);
        buttonShowTime = (Button)findViewById(R.id.buttonShowTime);

        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, kbb3Connection, Context.BIND_AUTO_CREATE);

        buttonShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(v);
            }
        });
    }

    private void showTime(View view) {
        String currentTime = kbb3Service.getCurrentTime();
        textviewTimeDate.setText(currentTime);
    }

    private ServiceConnection kbb3Connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            BoundService.KBB3Binder binder = (BoundService.KBB3Binder)service;
            kbb3Service = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            isBound = false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
