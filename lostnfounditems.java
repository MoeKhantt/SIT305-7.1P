package com.example.task_71p;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class lostnfounditems extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ListView listview;

    public lostnfounditems() {
        // Required empty public constructor
    }

    public static lostnfounditems newInstance(String param1, String param2) {
        lostnfounditems fragment = new lostnfounditems();
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

        return inflater.inflate(R.layout.lostnfounditems, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listview = view.findViewById(R.id.listview);
        listViewManage();
        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                ((MainActivity)getActivity()).selectArticleFragment(position+1);
            }
        });
    }

    public void listViewManage(){

        List<lostArticle> table = ((MainActivity)getActivity()).dbGet();
        List<String> articleNames = new ArrayList<>();
        for (int i =0 ; i < table.size(); i++){
            lostArticle x = table.get(i);
            articleNames.add(x.Name);
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, articleNames);
        listview.setAdapter(listViewAdapter);
    }
}