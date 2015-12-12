package com.example.rtse.japantourguide;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.rtse.japantourguide.memo.MainMemoActivity;
import com.example.rtse.japantourguide.schedule.MainScheduleActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void onClick(View view) {

        Intent intent;


        switch (view.getId()){
            case R.id.main_b1:

                intent = new Intent(this, MainScheduleActivity.class);
                startActivity(intent);
                break;

            case R.id.main_b2:
                intent = new Intent(this, MainMemoActivity.class);
                startActivity(intent);
                break;

        }
    }
}
