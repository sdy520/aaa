package com.example.aaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends CheckPermissionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Button btnPlay = (Button)findViewById(R.id.btnPlay);
//        final TextView textView = (TextView)findViewById(R.id.textView);
//
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(btnPlay.getText().equals("Start")) {
        Intent myService = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(myService);
//                    btnPlay.setText("Stop");
//                    textView.setTextColor(Color.parseColor("#FF0000"));
//                }else {
//                    Intent myService = new Intent(MainActivity.this, BackgroundSoundService.class);
//                    stopService(myService);
//                    btnPlay.setText("Start");
//                    textView.setTextColor(Color.parseColor("#0000FF"));
//                }
//            }
        //      });


    }
}
