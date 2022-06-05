package com.example.task_71p;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class removeAdvert extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Integer advertPos;

    public removeAdvert() {
    }

    public static removeAdvert newInstance(String param1, String param2) {
        removeAdvert fragment = new removeAdvert();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        return inflater.inflate(R.layout.remove_advert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textItem = view.findViewById(R.id.textItem);
        TextView textTime = view.findViewById(R.id.textTime);
        TextView textLocation = view.findViewById(R.id.textLocation);
        Button removeButton = view.findViewById(R.id.removeButton);
        Bundle args = getArguments();
        this.advertPos = args.getInt("key",0);
        lostArticle thisAdvert = ((MainActivity)getActivity()).dbGetArticle(this.advertPos);
        textItem.setText(thisAdvert.Condition + " " + thisAdvert.Name);
        textTime.setText(thisAdvert.Date);
        textLocation.setText(thisAdvert.Loc);
        removeButton.setTag(this.advertPos);
    }


}