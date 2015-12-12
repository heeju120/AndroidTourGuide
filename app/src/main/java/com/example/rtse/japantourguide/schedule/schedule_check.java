package com.example.rtse.japantourguide.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtse.japantourguide.R;

/**
 * Created by RTSE on 2015-11-18.
 */

public class schedule_check  extends Activity {

    static final int code_delete = 112;
    static final int code_edit= 113;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_check);

        Intent intent = getIntent();
        final schedule_item item = (schedule_item) intent.getSerializableExtra("clicked_item");


        TextView locView = (TextView)findViewById(R.id.checkLocation);
        TextView dateView = (TextView)findViewById(R.id.checkDate);
        TextView timeView = (TextView)findViewById(R.id.checkTime);
        TextView contextView = (TextView)findViewById(R.id.checkContext);

        locView.setText(item.getLocation());
        dateView.setText(item.getYear() + "/" + item.getMonth() + "/" + item.getDay());
        timeView.setText(item.getHour() + " : " + item.getMinute() );
        contextView.setText(item.getContent());


        Button deleteBtn = (Button)findViewById(R.id.schedule_delete);
        Button editBtn = (Button)findViewById(R.id.schedule_edit);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

    //          Toast.makeText(schedule_check.this,  "삭제 눌림  "+ item.getId(), Toast.LENGTH_SHORT).show();

                Intent data = new Intent();
                data.putExtra("Cancle_id", Long.toString(item.getId()) );

                setResult(code_delete,data);

                finish();

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

     //           Toast.makeText(schedule_check.this,  "수정 눌림" , Toast.LENGTH_SHORT).show();

                Intent data = new Intent();
    //            data.putExtra("Edit_id", Long.toString(item.getId()) );
                data.putExtra("edit_item", item);

                setResult(code_edit, data);

                finish();



            }
        });




    }
}
