package com.example.rtse.japantourguide.memo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rtse.japantourguide.R;
import com.example.rtse.japantourguide.databases.MYSQLiteOpenHelper_memo;
import com.example.rtse.japantourguide.schedule.schedule_check;
import com.example.rtse.japantourguide.schedule.schedule_item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainMemoActivity extends Activity {

    final MYSQLiteOpenHelper_memo helper_memo = new MYSQLiteOpenHelper_memo(MainMemoActivity.this, "Memo.db",null, 1);
    SQLiteDatabase db_memo;


    private memo_itemAdapter m_adapter;
    private ArrayList<memo_item> m_items = new ArrayList<>();

    static final int code_memo_new =111;
    static final int code_memo_delete = 112;
    static final int code_memo_edit= 113;
    static final int code_memo_check = 114;


    static final int memo_return_ok = 222;
    static final int memo_return_cancle = 223;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_main);


        String fileChk = "/data/data/com.example.rtse.japantourguide/databases/Memo.db";
        File file = new File(fileChk);

        if (!file.exists()) {

            memo_item m1 = new memo_item("a", "aa");
            memo_item m2 = new memo_item("b", "aa");
            memo_item m3 = new memo_item("c", "aa");


            insertMemo(m1);
            insertMemo(m2);
            insertMemo(m3);

        }else{

        }


        m_items.clear();
        dbToArray();




        m_adapter = new memo_itemAdapter(this, R.layout.memo_item, m_items);
        ListView m_ListView = (ListView) findViewById(R.id.memoList);
        m_ListView.setAdapter(m_adapter);
        m_ListView.setOnItemClickListener(mItemClickListner);



    }

    public void onClick(View view){

        Intent intent;


        switch (view.getId()){
            case R.id.memo_newBtn:
                intent = new Intent(this,memo_new.class);
                startActivityForResult(intent, code_memo_new);

                break;
            case R.id.memo_finish:
                finish();
                break;


        }

    }





    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case code_memo_new:
                if(resultCode == memo_return_ok){

                    memo_item newMemo = (memo_item)data.getSerializableExtra("memo");
                    long newId = insertMemo(newMemo);
                    newMemo.setId(newId);

                    m_items.add(newMemo);




                }else if(resultCode == memo_return_cancle){}
                break;

            case code_memo_check:

                if(resultCode == code_memo_delete){  // 그 id 가진 데이터 삭제

                    String s_i = data.getExtras().getString("Cancle_id");
                    long id = Long.parseLong(s_i);

                    if(id==-1){
                        Toast.makeText(this, "삭제 에러", Toast.LENGTH_SHORT).show();
                    }else{  deleteMemo(id);}


                }else if(resultCode == code_memo_edit){  // schedule_edit으로 넘어감. 데이터 넘겨줌

                    memo_item foredit = (memo_item)data.getSerializableExtra("edit_item");

                    Intent intent;
                    intent = new Intent(MainMemoActivity.this,memo_edit.class);
                    intent.putExtra("edited_item",foredit);

                    startActivityForResult(intent,code_memo_edit);


                }

                break;

            case code_memo_edit:

                if(resultCode == memo_return_ok){

                    memo_item editedItem = (memo_item)data.getSerializableExtra("edited_memo");

                    if(editedItem.getId() == -1){
                        Toast.makeText(this, "수정 에러" , Toast.LENGTH_SHORT).show();
                    }else{
                        editMemo(editedItem.getId(),editedItem);
                    }

                }else if (resultCode == memo_return_cancle){}

                break;




        }

        dbToArray();
        m_adapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener mItemClickListner = new AdapterView.OnItemClickListener(){

        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position){


            memo_item clickedItem = (memo_item)parent.getAdapter().getItem(position);

            //   Toast.makeText( MainScheduleActivity.this , position, Toast.LENGTH_SHORT).show();

            Intent intent;
            intent = new Intent(MainMemoActivity.this, memo_check.class);
            intent.putExtra("clicked_item",clickedItem);
//            startActivity(intent);
            startActivityForResult(intent,code_memo_check);
//


        }

    };



    public long insertMemo(memo_item item){

        db_memo = helper_memo.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("Key_title",item.getTitle());
        values.put("Key_context",item.getContext());


        long result = db_memo.insert("Memo", null, values);
        item.setId(result);

        return result;
    }

    public void deleteMemo(long i){
        db_memo = helper_memo.getWritableDatabase();

        String str = "DELETE FROM Memo WHERE id = '"+i+"';" ;

        db_memo.execSQL(str);
        db_memo.close();
    }
    public void editMemo(long i, memo_item item){
        db_memo = helper_memo.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("Key_title",item.getTitle());
        values.put("Key_context",item.getContext());

        db_memo.update("Memo", values, "id = '" + item.getId() + "'", null);
        db_memo.close();

    }

    public  void dbToArray(){
        db_memo = helper_memo.getReadableDatabase();
        Cursor c = db_memo.query("Memo",null,null,null,null, null,null);

        m_items.clear();

        while (c.moveToNext()){

            long id = c.getLong(c.getColumnIndex("id"));

            String title = c.getString(c.getColumnIndex("Key_title"));
            String context = c.getString(c.getColumnIndex("Key_context"));

            memo_item mi = new memo_item(id,title,context);
            m_items.add(mi);

        }




    }

    public void deleteAllDB(){
        db_memo = helper_memo.getWritableDatabase();
        String sql = "DELETE FROM Memo";
        db_memo.execSQL(sql);
        db_memo.close();
    }







}
