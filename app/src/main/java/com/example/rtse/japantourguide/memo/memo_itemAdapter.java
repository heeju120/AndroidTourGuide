package com.example.rtse.japantourguide.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rtse.japantourguide.R;

import java.util.ArrayList;

/**
 * Created by RTSE on 2015-12-11.
 */
public class memo_itemAdapter extends ArrayAdapter<memo_item>{


    private Context context;
    private final ArrayList<memo_item> itemList;
    private int resource;

    public memo_itemAdapter(Context context, int id, ArrayList<memo_item> i_list) {
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
            convertView = inflater.inflate(R.layout.memo_item, parent,false);
        }

        memo_item item = itemList.get(position);
        if( item != null ){
            TextView title = (TextView) convertView.findViewById(R.id.memo_title);

            title.setText(item.getTitle());


        }


        return convertView;
    }

    public Object getMemo(int pos){
        return itemList.get(pos);
    }

    public long getItemId(int position) {
        return position;
    }
    public int getCount() {
        return itemList.size();
    }



    // 외부에서 아이템 추가 요청 시 사용
    public void add(memo_item item) {
        itemList.add(item);
    }






}
