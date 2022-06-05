package com.example.task_71p;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class createAdvert extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText nameIn, phoneIn, descIn, dateIn, locationIn;

    private String mParam1;
    private String mParam2;

    public createAdvert() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.create_advert, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        nameIn = view.findViewById(R.id.name);
        phoneIn = view.findViewById(R.id.phone);
        descIn = view.findViewById(R.id.desc);
        dateIn = view.findViewById(R.id.date);
        locationIn = view.findViewById(R.id.loc);
    }
}