package com.example.rtse.japantourguide.schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rtse.japantourguide.R;

/**
 * Created by RTSE on 2015-11-19.
 */
public class MapFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.schedule_check,container,false);
    }


}
