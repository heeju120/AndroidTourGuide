package com.example.rtse.japantourguide.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtse.japantourguide.R;

/**
 * Created by RTSE on 2015-12-11.
 */
public class memo_check extends Activity {


    static final int code_memo_delete = 112;
    static final int code_memo_edit= 113;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_check);
        Intent intent = getIntent();

        final memo_item item = (memo_item) intent.getSerializableExtra("clicked_item");


        TextView titleView = (TextView)findViewById(R.id.memo_check_title);
        TextView conView = (TextView)findViewById(R.id.memo_check_context);

        titleView.setText(item.getTitle());
        conView.setText(item.getContext());


        Button deleteBtn = (Button)findViewById(R.id.memo_delete);
        Button editBtn = (Button)findViewById(R.id.memo_edit);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

   //             Toast.makeText(memo_check.this, "삭제 눌림  " + item.getId(), Toast.LENGTH_SHORT).show();

                Intent data = new Intent();
                data.putExtra("Cancle_id", Long.toString(item.getId()) );

                setResult(code_memo_delete,data);

                finish();

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

 //               Toast.makeText(memo_check.this,  "수정 눌림" , Toast.LENGTH_SHORT).show();

                Intent data = new Intent();
                //            data.putExtra("Edit_id", Long.toString(item.getId()) );
                data.putExtra("edit_item", item);

                setResult(code_memo_edit, data);

                finish();



            }
        });




    }





}
