package com.example.rtse.japantourguide.schedule;

        import android.app.ListActivity;
        import android.app.TabActivity;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TabHost;
        import android.widget.Toast;

        import com.example.rtse.japantourguide.R;
        import com.example.rtse.japantourguide.databases.MYSQLiteOpenHelper_schedule;

        import java.io.File;
        import java.text.Collator;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;

/**
 * Created by RTSE on 2015-11-16.
 */
public class MainScheduleActivity extends TabActivity {

    TabHost mTab;
    private ListView m_ListView = null;
    private ListView m_ListView_c1 = null;
    private ListView m_ListView_c2 = null;
    private ListView m_ListView_c3 = null;
    private ListView m_ListView_c4 = null;

//    private static final String TAG = "MainScheduleActivity";
 //   private schedule_dbHelper mDbOpenHelper;
//    private Cursor mCursor;

    final MYSQLiteOpenHelper_schedule helper_schedule = new MYSQLiteOpenHelper_schedule(MainScheduleActivity.this, "Schedule.db",null, 1);

    SQLiteDatabase db_schedule;

    static final int code_new =111;
    static final int code_delete = 112;
    static final int code_edit= 113;
    static final int code_check = 114;


    static final int return_ok = 222;
    static final int return_cancle = 223;


    private ArrayList<schedule_item> m_items = new ArrayList<schedule_item>();
    private schedule_itemAdapter m_adapter;

    private ArrayList<schedule_item> m_items_c1 = new ArrayList<schedule_item>();
    private ArrayList<schedule_item> m_items_c2 = new ArrayList<schedule_item>();
    private ArrayList<schedule_item> m_items_c3 = new ArrayList<schedule_item>();
    private ArrayList<schedule_item> m_items_c4 = new ArrayList<schedule_item>();

    private schedule_itemAdapter m_adapter_c1;
    private schedule_itemAdapter m_adapter_c2;
    private schedule_itemAdapter m_adapter_c3;
    private schedule_itemAdapter m_adapter_c4;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        TabHost mTab = getTabHost();
        TabHost.TabSpec spec;

        LayoutInflater.from(this).inflate(R.layout.activity_schedule_main, mTab.getTabContentView(), true);

        spec = mTab.newTabSpec("tab1").setIndicator("전체").setContent(R.id.tv1);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("tab2").setIndicator("여행지").setContent(R.id.tv2);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("tab3").setIndicator("음식점").setContent(R.id.tv3);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("tab4").setIndicator("숙소").setContent(R.id.tv4);
        mTab.addTab(spec);
        spec = mTab.newTabSpec("tab5").setIndicator("공항").setContent(R.id.tv5);
        mTab.addTab(spec);


//        helper_schedule = new MYSQLiteOpenHelper_schedule(MainScheduleActivity.this, "Schedule.db",null, 1);


//        String fileChk = "/data/data/com.example.test054sqlite/databases/Schedule.db";
        String fileChk = "/data/data/com.example.rtse.japantourguide/databases/Schedule.db";

        File file = new File(fileChk);
        if (!file.exists()) {
  //          deleteAllDB();

             schedule_item newItem1 = new schedule_item(1,2016,2,30,3,15,"간사이 공항","AA");
             schedule_item newItem2 = new schedule_item(2,2016,1,13,10,15,"오사카역","b");
             schedule_item newItem3 = new schedule_item(3,2015,12,15,7,15,"유니버셜 스튜디오","c");
 //           schedule_item newItem4 = new schedule_item(4,2015,12,14,7,15,"긴자역","d");
 //           schedule_item newItem5 = new schedule_item(4,2015,12,14,5,15,"긴자역","d");
 //           schedule_item newItem6 = new schedule_item(4,2015,12,14,5,10,"긴자역","d");

            insertSchedule(newItem1);
            insertSchedule(newItem2);
            insertSchedule(newItem3);
   //         insertSchedule(newItem4);
   //         insertSchedule(newItem5);
   //         insertSchedule(newItem6);

        }else{

   //         deleteAllDB();

        }

        m_items.clear();
 //       selectByCategory(0);

        dbToArrList();

        SortingByDateAndTime(m_items);

        CategoryTabSetting();





        m_adapter = new schedule_itemAdapter(this,R.layout.schedule_item,m_items);

        m_ListView = (ListView) findViewById(R.id.tv1);
        m_ListView.setAdapter(m_adapter);
        m_ListView.setOnItemClickListener(mItemClickListner);


        m_ListView_c1 = (ListView) findViewById(R.id.tv2);
        m_adapter_c1 = new schedule_itemAdapter(this,R.layout.schedule_item,m_items_c1);
        m_ListView_c1.setAdapter(m_adapter_c1);
        m_ListView_c1.setOnItemClickListener(mItemClickListner);

        m_ListView_c2 = (ListView) findViewById(R.id.tv3);
        m_adapter_c2 = new schedule_itemAdapter(this,R.layout.schedule_item,m_items_c2);
        m_ListView_c2.setAdapter(m_adapter_c2);
        m_ListView_c2.setOnItemClickListener(mItemClickListner);

        m_ListView_c3 = (ListView) findViewById(R.id.tv4);
        m_adapter_c3 = new schedule_itemAdapter(this,R.layout.schedule_item,m_items_c3);
        m_ListView_c3.setAdapter(m_adapter_c3);
        m_ListView_c3.setOnItemClickListener(mItemClickListner);

        m_ListView_c4 = (ListView) findViewById(R.id.tv5);
        m_adapter_c4 = new schedule_itemAdapter(this,R.layout.schedule_item,m_items_c4);
        m_ListView_c4.setAdapter(m_adapter_c4);
        m_ListView_c4.setOnItemClickListener(mItemClickListner);




        //추가 버튼
        Button newSchedule = (Button) findViewById(R.id.schedule_nb);
        newSchedule.setOnClickListener(   new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent;
                    intent = new Intent(MainScheduleActivity.this, schedule_new.class);

                    startActivityForResult(intent, code_new);


                }



            });



    }


    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case code_new:
                if(resultCode == return_ok){

                    schedule_item newSchedule = (schedule_item)data.getSerializableExtra("schedule");
                    long newId = insertSchedule(newSchedule);
                    newSchedule.setId(newId);

                    m_items.add(newSchedule);

           //         Toast.makeText(this, "code_new 들어옴" , Toast.LENGTH_SHORT).show();

                }else if(resultCode ==return_cancle){}
                break;

            case code_check:

                if(resultCode == code_delete){  // 그 id 가진 데이터 삭제

                    String s_i = data.getExtras().getString("Cancle_id");
                    long id = Long.parseLong(s_i);


                    if(id == -1 ){
                        Toast.makeText(this, "삭제 에러" , Toast.LENGTH_SHORT).show();
                    }else{   deleteSchedule(id);    }


                }else if(resultCode == code_edit){  // schedule_edit으로 넘어감. 데이터 넘겨줌


                    schedule_item foredit = (schedule_item)data.getSerializableExtra("edit_item");

                    Intent intent;
                    intent = new Intent(MainScheduleActivity.this, schedule_edit.class);
                    intent.putExtra("edited_item",  foredit );

                    startActivityForResult(intent, code_edit);


                }

                break;

            case code_edit:

         //       Toast.makeText(this,"수정 들어옴",Toast.LENGTH_SHORT).show();

                if(resultCode == return_ok){

                    schedule_item editedItem = (schedule_item)data.getSerializableExtra("edited_schedule");

                    if( editedItem.getId() == -1 ){
                        Toast.makeText(this, "수정 에러" , Toast.LENGTH_SHORT).show();
                    }else {
      //                  Toast.makeText(this,"수정 들어옴",Toast.LENGTH_SHORT).show();
                        editSchedule(editedItem.getId(), editedItem);

                    }




                }else if (resultCode == return_cancle){}

                break;




        }





        dbToArrList();
        SortingByDateAndTime(m_items);

        CategoryTabSetting();
        m_adapter.notifyDataSetChanged();
        m_adapter_c1.notifyDataSetChanged();
        m_adapter_c2.notifyDataSetChanged();
        m_adapter_c3.notifyDataSetChanged();
        m_adapter_c4.notifyDataSetChanged();

    }


    private AdapterView.OnItemClickListener mItemClickListner = new AdapterView.OnItemClickListener(){

        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position){


            schedule_item clickedItem = (schedule_item)parent.getAdapter().getItem(position);

            //   Toast.makeText( MainScheduleActivity.this , position, Toast.LENGTH_SHORT).show();

            Intent intent;
            intent = new Intent(MainScheduleActivity.this, schedule_check.class);
            intent.putExtra("clicked_item",clickedItem);
//            startActivity(intent);
            startActivityForResult(intent,code_check);
//


        }

    };


    private final static void SortingByDateAndTime(ArrayList<schedule_item> items){
        Collections.sort(items,minComparator);
        Collections.sort(items,hourComparator);
        Collections.sort(items,dayComparator);
        Collections.sort(items,monthComparator);
        Collections.sort(items,yearComparator);
    }

    //Comparator들
    private final static Comparator<schedule_item> yearComparator= new Comparator<schedule_item>() {
        //연도별 sort
        private final Collator y_collator = Collator.getInstance();
        @Override
        public int compare(schedule_item object1,schedule_item object2) {

            int temp1 = object1.getYear();
            int temp2 = object2.getYear();

            String year1 = Integer.toString(temp1);
            String year2 = Integer.toString(temp2);

            return y_collator.compare(year1,year2);

        }

    };

    private final static Comparator<schedule_item> monthComparator= new Comparator<schedule_item>() {
        //월별 sort
        private final Collator m_collator = Collator.getInstance();
        @Override
        public int compare(schedule_item object1,schedule_item object2) {

            int temp1 = object1.getMonth();
            int temp2 = object2.getMonth();

            String month1;
            String month2;

            if(temp1 < 10){month1 =  "0" + Integer.toString(temp1);}
            else{   month1 = Integer.toString(temp1);   }

            if(temp2 < 10){month2 =  "0" + Integer.toString(temp2);}
            else{   month2 = Integer.toString(temp2);   }

            return m_collator.compare(month1,month2);

        }

    };

    private final static Comparator<schedule_item> dayComparator= new Comparator<schedule_item>() {

        private final Collator d_collator = Collator.getInstance();
        @Override
        public int compare(schedule_item object1,schedule_item object2) {

            int temp1 = object1.getDay();
            int temp2 = object2.getDay();

            String s1;
            String s2;

            if(temp1 < 10){s1 =  "0" + Integer.toString(temp1);}
            else{   s1 = Integer.toString(temp1);   }

            if(temp2 < 10){s2 =  "0" + Integer.toString(temp2);}
            else{   s2 = Integer.toString(temp2);   }

            return d_collator.compare(s1,s2);

        }

    };

    private final static Comparator<schedule_item> hourComparator= new Comparator<schedule_item>() {

        private final Collator h_collator = Collator.getInstance();
        @Override
        public int compare(schedule_item object1,schedule_item object2) {

            int temp1 = object1.getHour();
            int temp2 = object2.getHour();

            String s1;
            String s2;

            if(temp1 < 10){s1 =  "0" + Integer.toString(temp1);}
            else{   s1 = Integer.toString(temp1);   }

            if(temp2 < 10){s2 =  "0" + Integer.toString(temp2);}
            else{   s2 = Integer.toString(temp2);   }

            return h_collator.compare(s1,s2);

        }

    };

    private final static Comparator<schedule_item> minComparator= new Comparator<schedule_item>() {

        private final Collator min_collator = Collator.getInstance();
        @Override
        public int compare(schedule_item object1,schedule_item object2) {

            int temp1 = object1.getMinute();
            int temp2 = object2.getMinute();

            String s1;
            String s2;

            if(temp1 < 10){s1 =  "0" + Integer.toString(temp1);}
            else{   s1 = Integer.toString(temp1);   }

            if(temp2 < 10){s2 =  "0" + Integer.toString(temp2);} else{   s2 = Integer.toString(temp2);
            }

            return min_collator.compare(s1, s2);

        }

    };



    public long insertSchedule(schedule_item item){


      db_schedule = helper_schedule.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Key_category", item.getCategory());
        values.put("Key_year", item.getYear());
        values.put("Key_month", item.getMonth());
        values.put("Key_day", item.getDay());
        values.put("Key_hour", item.getHour());
        values.put("Key_minute", item.getMinute());

        values.put("Key_location", item.getLocation());
        values.put("Key_context", item.getContent());


        long result = db_schedule.insert("Schedule",null,values);

        item.setId((int)result);


        return result;
     }

    public void deleteSchedule(long i){
        db_schedule = helper_schedule.getWritableDatabase();

        String str = "DELETE FROM Schedule WHERE id = '"+i+"';" ;

        db_schedule.execSQL(str);
        db_schedule.close();

    }

    public void editSchedule(long i, schedule_item item){
        db_schedule = helper_schedule.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Key_category",item.getCategory());
        values.put("Key_Year",item.getYear());
        values.put("Key_month",item.getMonth());
        values.put("Key_day",item.getDay());
        values.put("Key_hour",item.getHour());
        values.put("Key_minute",item.getMinute());
        values.put("Key_location",item.getLocation());
        values.put("Key_context",item.getContent());


        db_schedule.update("Schedule",values,"id = '"+item.getId()+"'",null);

//        db_schedule.execSQL(str);
        db_schedule.close();
    }


    public void  dbToArrList(){
        db_schedule = helper_schedule.getReadableDatabase();
        Cursor c = db_schedule.query("Schedule", null,null,null,null,null,null);

        m_items.clear();

        while (c.moveToNext()){

            int id = c.getInt(c.getColumnIndex("id"));

            int category = c.getInt(c.getColumnIndex("Key_category"));
            int year= c.getInt(c.getColumnIndex("Key_year"));
            int month= c.getInt(c.getColumnIndex("Key_month"));
            int day= c.getInt(c.getColumnIndex("Key_day"));
            int hour= c.getInt(c.getColumnIndex("Key_hour"));
            int min= c.getInt(c.getColumnIndex("Key_minute"));

            String location = c.getString(c.getColumnIndex("Key_location"));
            String context= c.getString(c.getColumnIndex("Key_context"));

            schedule_item si = new schedule_item(id,category,year,month,day,hour,min,location,context);
            m_items.add(si);

         }



    }



    public void deleteAllDB(){
        db_schedule = helper_schedule.getWritableDatabase();

        String sql = "DELETE FROM Schedule;";
        db_schedule.execSQL(sql);
        db_schedule.close();

    }

    private void refresh(){

    }

    public  void baseArrayFromDB(){
        db_schedule = helper_schedule.getReadableDatabase();
        Cursor c = db_schedule.query("Schedule", null,null,null,null,null,null);

        int i;




    }

    public void CategoryTabSetting(){

        m_items_c1.clear();
        m_items_c2.clear();
        m_items_c3.clear();
        m_items_c4.clear();

        for (int i=0;i<m_items.size();i++){

            if(m_items.get(i).getCategory() == 1){    m_items_c1.add(m_items.get(i));           }
            if(m_items.get(i).getCategory() == 2){    m_items_c2.add(m_items.get(i));           }
            if(m_items.get(i).getCategory() == 3){    m_items_c3.add(m_items.get(i));           }
            if(m_items.get(i).getCategory() == 4){    m_items_c4.add(m_items.get(i));           }

        }

    }


}
