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
public class memo_new extends Activity{


    memo_item newItem = new memo_item();

    static final int return_memo_ok = 222;
    static final int return_memo_cancle = 223;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_new);



        //위치, 내용 설정
        final EditText editTitle = (EditText)findViewById(R.id.memo_new_title);
        final EditText editContext = (EditText)findViewById(R.id.memo_new_context);

        Button cancleBtn=(Button)findViewById(R.id.memo_new_cancleBtn);
        Button addBtn = (Button)findViewById(R.id.memo_new_addBtn);


        //취소버튼
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //               Toast.makeText(schedule_new.this,("setResult 전") ,Toast.LENGTH_SHORT).show();


                Intent data = new Intent();
                setResult(return_memo_cancle, data);
                //            Toast.makeText(schedule_new.this,(" 취소버튼 눌림") ,Toast.LENGTH_SHORT).show();


                finish();
            }
        });

        //추가버튼
        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String con = editContext.getText().toString();


                if (title.equals("")) {
                    Toast.makeText(memo_new.this, "장소를 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    //memo item 추가

                    newItem.setTitle(title);
                    newItem.setContext(con);

                    Intent data = new Intent();
                    data.putExtra("memo", newItem);

                    setResult(return_memo_ok, data);


                    finish();
                }


            }
        });




    }


}
