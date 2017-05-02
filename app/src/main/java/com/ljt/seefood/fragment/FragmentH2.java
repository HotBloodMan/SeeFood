package com.ljt.seefood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljt.seefood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentH2 extends Fragment {

    private static FragmentH2 instanceFH2;

    public FragmentH2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_h2, container, false);
    }

    public static FragmentH2 getInstance(){
        if(instanceFH2==null){
            instanceFH2=new FragmentH2();
        }
        return instanceFH2;
    }

}
