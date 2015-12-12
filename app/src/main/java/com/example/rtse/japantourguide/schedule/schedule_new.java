package com.example.rtse.japantourguide.schedule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rtse.japantourguide.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by RTSE on 2015-11-18.
 */
public class schedule_new extends Activity implements RadioGroup.OnCheckedChangeListener{

    DateFormat fmDateAndTime = DateFormat.getDateTimeInstance();
    TextView dateAndtimeLabel;
    Calendar dateAndtime = Calendar.getInstance();

    schedule_item newItem = new schedule_item();

    static final int return_ok = 222;
    static final int return_cancle = 223;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_new);

        //카테고리 설정
        RadioGroup rg1 = (RadioGroup)findViewById(R.id.schedule_rg);
        rg1.check(R.id.schedule_c1);
        newItem.setCategory(1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton c1 = (RadioButton)findViewById(R.id.schedule_c1);
                RadioButton c2 = (RadioButton)findViewById(R.id.schedule_c2);
                RadioButton c3 = (RadioButton)findViewById(R.id.schedule_c3);
                RadioButton c4 = (RadioButton)findViewById(R.id.schedule_c4);

                if(checkedId == R.id.schedule_c1){
                    newItem.setCategory(1);
                }else if(checkedId == R.id.schedule_c2){
                    newItem.setCategory(2);
                }else if(checkedId == R.id.schedule_c3){
                    newItem.setCategory(3);
                }else{
                    newItem.setCategory(4);
                }

            }
        });




        //날짜, 시간 설정
        Button dateBtn = (Button)findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(schedule_new.this, dateSetListener, dateAndtime.get(Calendar.YEAR),
                        dateAndtime.get(Calendar.MONTH), dateAndtime.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        Button timeBtn = (Button)findViewById(R.id.timeBtn);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(schedule_new.this, timeSetListener,
                        dateAndtime.get(Calendar.HOUR_OF_DAY), dateAndtime.get(Calendar.MINUTE),false).show();
            }
        });


        dateAndtimeLabel = (TextView) findViewById(R.id.dateAndTime);
        updateLabel();

        //위치, 내용 설정
        final EditText editLocation = (EditText)findViewById(R.id.editLocation);
        final EditText editContext = (EditText)findViewById(R.id.editContext);

        Button cancleBtn=(Button)findViewById(R.id.schedule_cancleBtn);
        Button addBtn = (Button)findViewById(R.id.schedule_addBtn);


        //취소버튼
        cancleBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent data = new Intent();
                setResult(return_cancle,data);

                finish();
            }
        });

        //추가버튼
        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String loc = editLocation.getText().toString();
                String con = editContext.getText().toString();


                if(loc.equals("")){
                    Toast.makeText(schedule_new.this,"장소를 입력해 주세요" ,Toast.LENGTH_SHORT).show();
                }else{
                    //schedule item 추가

                    newItem.setLocation(loc);
                    newItem.setContent(con);


                    newItem.setLocation(loc);
                    newItem.setContent(con);

                    Intent data = new Intent();
                    data.putExtra("schedule", newItem);

                    setResult(return_ok,data);


                    finish();
                }


            }
        });


    }

    //Category
    //라디오버튼 이벤트 처리
    public void onCheckedChanged(RadioGroup arg0, int arg1){

        switch (arg1) {
            case R.id.schedule_c1:
                Toast.makeText(this,"1000000",Toast.LENGTH_SHORT).show();
                newItem.setCategory(1);
                break;

            case R.id.schedule_c2:
                Toast.makeText(this,"20000",Toast.LENGTH_SHORT).show();
                newItem.setCategory(2);
                break;

            case R.id.schedule_c3:
                Toast.makeText(this,"30000",Toast.LENGTH_SHORT).show();
                newItem.setCategory(3);
                break;

            case R.id.schedule_c4:
                Toast.makeText(this,"40000",Toast.LENGTH_SHORT).show();
                newItem.setCategory(4);
                break;

        }
    }

    //날짜선택, 시간선택 처리
    //DatePicker, TimePicker
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view,int year, int month, int day){
            dateAndtime.set(Calendar.YEAR, year);
            dateAndtime.set(Calendar.MONTH, month);
            dateAndtime.set(Calendar.DAY_OF_MONTH, day);

 //           newItem.setDate(year, month + 1, day);
            String tmp = year + " / " + (month+1)+ " / " + day;
   //         Toast.makeText(schedule_new.this, tmp, Toast.LENGTH_SHORT ).show();


            updateLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view,int hour, int minute){
            dateAndtime.set(Calendar.HOUR_OF_DAY, hour);
            dateAndtime.set(Calendar.MINUTE, minute);

 //           newItem.setTime(hour, minute);
            String tmp = hour + " : " + minute;
   //         Toast.makeText(schedule_new.this, tmp, Toast.LENGTH_SHORT ).show();

            updateLabel();
        }

    };

    private void updateLabel(){
        dateAndtimeLabel.setText(fmDateAndTime.format(dateAndtime.getTime()));
  //      newItem.setDate(year  , month+1, day);

        newItem.setDate(dateAndtime.get(Calendar.YEAR),dateAndtime.get(Calendar.MONTH)+1,dateAndtime.get(Calendar.DAY_OF_MONTH)  );
        newItem.setTime(dateAndtime.get(Calendar.HOUR_OF_DAY),dateAndtime.get(Calendar.MINUTE));

    }






}
