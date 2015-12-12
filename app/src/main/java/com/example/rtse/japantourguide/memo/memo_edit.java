package com.example.rtse.japantourguide.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rtse.japantourguide.R;

/**
 * Created by RTSE on 2015-12-11.
 */
public class memo_edit extends Activity {


    static final int return_ok = 222;
    static final int return_cancle = 223;

    memo_item newItem = new memo_item();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_edit);

        Intent intent = getIntent();
        final memo_item item = (memo_item) intent.getSerializableExtra("edited_item");


        final EditText editTitle = (EditText)findViewById(R.id.memo_edit_title);
        final EditText editContext = (EditText)findViewById(R.id.memo_edit_context);

        editTitle.setText(item.getTitle());
        editContext.setText(item.getContext());

        Button cancleBtn=(Button)findViewById(R.id.memo_edit_cancleBtn);
        Button addBtn = (Button)findViewById(R.id.memo_edit_addBtn);


        //취소버튼
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent data = new Intent();
                setResult(return_cancle, data);
                finish();
            }
        });

        //확인 버튼
        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String con = editContext.getText().toString();


                if (title.equals("")) {
                    Toast.makeText(memo_edit.this, "장소를 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    //memo item 추가

                    newItem.setTitle(title);
                    newItem.setContext(con);
                    newItem.setId(item.getId());

                    Intent data = new Intent();
                    data.putExtra("edited_memo", newItem);
                    setResult(return_ok, data);


//                    Toast.makeText(schedule_new.this, ("일정 추가버튼 누름"+ data )  ,Toast.LENGTH_SHORT).show();


                    finish();
                }


            }
        });


    }
}
