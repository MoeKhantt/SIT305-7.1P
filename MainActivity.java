package com.example.task_71p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button toCreateButton, toLFButton;
    FragmentContainerView fragmentContainerView;
    EditText nameIn, phoneIn, descIn, dateIn, locationIn;
    RadioGroup radioGroup;
    database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defaultfragment);
        dbHelper = new database(getApplicationContext());

        toCreateButton = findViewById(R.id.toCreateButton);
        toLFButton = findViewById(R.id.toLFButton);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        nameIn = findViewById(R.id.name);
        phoneIn = findViewById(R.id.phone);
        descIn = findViewById(R.id.desc);
        dateIn = findViewById(R.id.date);
        locationIn = findViewById(R.id.loc);

        setContentView(R.layout.activity_main);
    }
    public void selectArticleFragment(int position){
        Bundle bundle = new Bundle();
        bundle.putInt("key", position);
        Fragment removeAdvert = new removeAdvert();
        removeAdvert.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, removeAdvert).commit();
    }

    public void selectFragment(View view){
        Fragment fragment = null;
        Fragment fCreateAdvert = new createAdvert();
        Fragment fLostnFound = new lostnfounditems();
        Fragment fDefaultFragment = new defaultFragment();

        switch (view.getId()){
            case R.id.toCreateButton:
                fragment = fCreateAdvert;
                break;
            case R.id.toLFButton:
                fragment = fLostnFound;
                break;
            default:
                fragment = fDefaultFragment;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit();

    }

    public List<lostArticle> dbGet() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + data.TABLE_NAME, null);

        ArrayList<lostArticle> table = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                table.add(new lostArticle(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return table;
    }

    public lostArticle dbGetArticle(Integer findID){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = {findID.toString()};
        Cursor cursor = db.rawQuery("select * from " + data.TABLE_NAME + " where " + data.ID + "= ?", args, null);

        ArrayList<lostArticle> table = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                table.add(new lostArticle(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return table.get(0);
    }

    public void fullUpdateID(){
        List<lostArticle> table = new ArrayList<>();
        table = dbGet();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(data.TABLE_NAME, null, null);
        int len = table.size();

        for(int i = 0; i<len; i++){
            lostArticle newEntry = table.get(i);
            newEntry.ID=i+1;
            dbHelper.insertNew(newEntry);
        }
        db.close();
    }

    public void dbDelete(Integer delID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(data.TABLE_NAME, data.ID + "=?", new String[]{delID.toString()});
        db.close();
        fullUpdateID();
    }

    public void deleteButton(View view){
        int pass = (int) view.getTag();
        dbDelete(pass);
        selectFragment(view);
    }

    public void dbInsert(View view){
        nameIn = findViewById(R.id.name);
        phoneIn = findViewById(R.id.phone);
        descIn = findViewById(R.id.desc);
        dateIn = findViewById(R.id.date);
        locationIn = findViewById(R.id.loc);
        String condition = "Lost";

        radioGroup = findViewById(R.id.radioGroup);
        if(radioGroup.getCheckedRadioButtonId() == R.id.foundIn){
            condition = "Found";
        }

        String na, ph, de, da, lo;

        na = nameIn.getText().toString();
        ph = phoneIn.getText().toString();
        de = descIn.getText().toString();
        da = dateIn.getText().toString();
        lo = locationIn.getText().toString();

        Integer len = dbGet().size();


        if(na.length()>0 && ph.length()>0 && de.length()>0 && da.length()>0 && lo.length()>0){
            lostArticle newLostArticle = new lostArticle(len, condition, na, ph, de, da, lo);
            dbHelper.insertNew(newLostArticle);
            selectFragment(view);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_LONG).show();
        }
    }
}