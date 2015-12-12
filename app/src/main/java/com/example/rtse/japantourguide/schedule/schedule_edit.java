package com.example.rtse.japantourguide.schedule;

import android.os.Bundle;

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

import com.example.rtse.japantourguide.R;

/**
 * Created by RTSE on 2015-12-10.
 */
public class schedule_edit extends Activity {

    DateFormat fmDateAndTime = DateFormat.getDateTimeInstance();
    TextView dateAndtimeLabel;
    Calendar dateAndtime = Calendar.getInstance();

    schedule_item newItem = new schedule_item();

    static final int return_ok = 222;
    static final int return_cancle = 223;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_edit);

        Intent intent = getIntent();
        final schedule_item item = (schedule_item) intent.getSerializableExtra("edited_item");



        //라디오박스

        RadioGroup rg1 = (RadioGroup)findViewById(R.id.edit_rg);
        newItem.setCategory(item.getCategory());

        if(item.getCategory() == 1){         rg1.check(R.id.schedule_edit_c1);  }
        else if(item.getCategory() == 2){     rg1.check(R.id.schedule_edit_c2);}
        else if(item.getCategory() == 3){    rg1.check(R.id.schedule_edit_c3);}
        else {     rg1.check(R.id.schedule_edit_c4);  }

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton c1 = (RadioButton) findViewById(R.id.schedule_edit_c1);
                RadioButton c2 = (RadioButton) findViewById(R.id.schedule_edit_c2);
                RadioButton c3 = (RadioButton) findViewById(R.id.schedule_edit_c3);
                RadioButton c4 = (RadioButton) findViewById(R.id.schedule_edit_c4);

                if (checkedId == R.id.schedule_edit_c1) {
                    newItem.setCategory(1);
                } else if (checkedId == R.id.schedule_edit_c2) {
                    newItem.setCategory(2);
                } else if (checkedId == R.id.schedule_edit_c3) {
                    newItem.setCategory(3);
                } else {
                    newItem.setCategory(4);
                }

            }
        });


        //dateAndTime

        Button dateBtn = (Button)findViewById(R.id.dateBtn_edit);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(schedule_edit.this, dateSetListener, dateAndtime.get(Calendar.YEAR),
                        dateAndtime.get(Calendar.MONTH), dateAndtime.get(Calendar.DAY_OF_MONTH)).show();

                updateLabel();

            }

        });

        Button timeBtn = (Button)findViewById(R.id.timeBtn_edit);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(schedule_edit.this, timeSetListener,
                        dateAndtime.get(Calendar.HOUR_OF_DAY), dateAndtime.get(Calendar.MINUTE), false).show();

                updateLabel();
            }
        });



        //dateAndTime TextView
        dateAndtimeLabel = (TextView) findViewById(R.id.dateAndTime_edit);
        dateAndtimeLabel.setText(item.getYear() + "." + item.getMonth() + "." + item.getDay() + "." + item.getHour() + "." + item. getMinute() );


        newItem.setDate(item.getYear(),item.getMonth(),item.getDay() );
        newItem.setTime(item.getHour(),item.getMinute());


        // 위치, 내용 텍스트뷰
        final EditText editLocation = (EditText)findViewById(R.id.edit_Location);
        final EditText editContext = (EditText)findViewById(R.id.edit_Context);

        editLocation.setText(item.getLocation());
        editContext.setText(item.getContent());



        Button cancleBtn=(Button)findViewById(R.id.edit_cancleBtn);
        Button addBtn = (Button)findViewById(R.id.edit_addBtn);

        //취소버튼
        cancleBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent data = new Intent();
                setResult(return_cancle,data);
                finish();
            }
        });

        //확인 버튼
        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String loc = editLocation.getText().toString();
                String con = editContext.getText().toString();

               if(loc.equals("")){
                    Toast.makeText(schedule_edit.this,"장소를 입력해 주세요" ,Toast.LENGTH_SHORT).show();
                }else{
                    //schedule item 추가

                    newItem.setLocation(loc);
                    newItem.setContent(con);
                    newItem.setId( item.getId() );

                    Intent data = new Intent();
                    data.putExtra("edited_schedule", newItem);
                    setResult(return_ok,data);


//                    Toast.makeText(schedule_new.this, ("일정 추가버튼 누름"+ data )  ,Toast.LENGTH_SHORT).show();


                    finish();
                }


            }
        });




    }



    //DatePicker, TimePicker
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view,int year, int month, int day){
            dateAndtime.set(Calendar.YEAR, year);
            dateAndtime.set(Calendar.MONTH, month);
            dateAndtime.set(Calendar.DAY_OF_MONTH, day);

            //           newItem.setDate(year, month + 1, day);
            String tmp = year + " / " + (month+1)+ " / " + day;
            Toast.makeText(schedule_edit.this, tmp, Toast.LENGTH_SHORT ).show();


            updateLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view,int hour, int minute){
            dateAndtime.set(Calendar.HOUR_OF_DAY, hour);
            dateAndtime.set(Calendar.MINUTE, minute);

            //           newItem.setTime(hour, minute);
            String tmp = hour + " : " + minute;
            Toast.makeText(schedule_edit.this, tmp, Toast.LENGTH_SHORT ).show();

            updateLabel();
        }

    };

    private void updateLabel() {
        dateAndtimeLabel.setText(fmDateAndTime.format(dateAndtime.getTime()));
        //      newItem.setDate(year  , month+1, day);

        newItem.setDate(dateAndtime.get(Calendar.YEAR), dateAndtime.get(Calendar.MONTH) + 1, dateAndtime.get(Calendar.DAY_OF_MONTH));
        newItem.setTime(dateAndtime.get(Calendar.HOUR_OF_DAY),dateAndtime.get(Calendar.MINUTE));

    }




}
