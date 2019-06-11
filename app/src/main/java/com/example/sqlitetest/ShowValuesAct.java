package com.example.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sqlitetest.DatabaseClasses.DatabaseRecyclerAdapter;
import com.example.sqlitetest.DatabaseClasses.MyDatabaseClass;
import com.example.sqlitetest.RecyclerClasses.ModelClass;

import java.util.ArrayList;

public class ShowValuesAct extends AppCompatActivity {
    RecyclerView objRecyclerView;
    ArrayList<ModelClass> objModelClassArrayList;
    DatabaseRecyclerAdapter objDatabaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_values);
        objRecyclerView = findViewById(R.id.dataRV);
        objModelClassArrayList = new ArrayList<>();
    }

    public void showValuesFromDatabase(View view){
        try {
            MyDatabaseClass objMyDatabaseClass = new MyDatabaseClass(this);
            SQLiteDatabase objSqliteDatabase = objMyDatabaseClass.getReadableDatabase();
            if (objSqliteDatabase!=null){
                Cursor objCursor = objSqliteDatabase.rawQuery("select * from UniStudent",null);

                if (objCursor.getCount()==0){
                    Toast.makeText(this,"No data returned.. ",Toast.LENGTH_LONG).show();
                }else {
                    while (objCursor.moveToNext()){
                        objModelClassArrayList.add(new ModelClass(objCursor.getInt(0),
                                objCursor.getInt(3),
                                objCursor.getString(1),objCursor.getString(2)
                                ));
                    }
                    objDatabaseRecyclerAdapter = new DatabaseRecyclerAdapter(objModelClassArrayList);
                    objRecyclerView.hasFixedSize();
                    objRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    objRecyclerView.setAdapter(objDatabaseRecyclerAdapter);

                }
            } else {
                Toast.makeText(this,"Database is null..",Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(this,"Show values from DB"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
