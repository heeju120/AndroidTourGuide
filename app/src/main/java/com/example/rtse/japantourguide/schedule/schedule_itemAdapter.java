package com.example.rtse.japantourguide.schedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtse.japantourguide.R;

import org.w3c.dom.Text;

/**
 * Created by RTSE on 2015-11-17.
 */
public class schedule_itemAdapter extends ArrayAdapter<schedule_item> {

        private Context context;
        private final ArrayList<schedule_item> itemList;
        private int resource;

        public schedule_itemAdapter(Context context, int id, ArrayList<schedule_item> i_list) {
            super(context,id,i_list);
            this.context = context;
            this.resource = id;
            this.itemList = i_list;

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final int pos = position;

            if ( convertView == null ) {
               LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               convertView = inflater.inflate(R.layout.schedule_item, parent,false);
            }

            schedule_item item = itemList.get(position);
            if( item != null ){
                TextView location = (TextView) convertView.findViewById(R.id.item_location);
                TextView dt = (TextView) convertView.findViewById(R.id.item_dayAndtime);

                if(location != null){  location.setText( "장소 : " + item.getLocation()); }
               if(dt != null){  dt.setText( "날짜 : " + item.getYear()  + "/" + item.getMonth() + "/" + item.getDay() +
                                                "\t시간 : " + item.getHour() + " : " + item.getMinute()       ); }


            }



/*
            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : "+itemList.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
*/

        return convertView;
    }

    public Object getSchedule(int pos){
        return itemList.get(pos);
    }

    public long getItemId(int position) {
        return position;
    }
    public int getCount() {
        return itemList.size();
    }



    // 외부에서 아이템 추가 요청 시 사용
    public void add(schedule_item item) {
        itemList.add(item);
    }




}
