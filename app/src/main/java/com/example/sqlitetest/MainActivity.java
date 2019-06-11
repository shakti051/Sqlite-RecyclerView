package com.example.sqlitetest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitetest.DatabaseClasses.MyDatabaseClass;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    MyDatabaseClass objMyDatabaseClass;
    EditText userNameET,emailET,phoneET;
    TextView showValuesTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameET = findViewById(R.id.userNameET);
        emailET = findViewById(R.id.emailET);
        phoneET = findViewById(R.id.phoneET);
     //   showValuesTV = findViewById(R.id.valuesTV);
        objMyDatabaseClass = new MyDatabaseClass(this);
            /**Code For Online Database**/
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }

    public void createDatabase(View view){

        try {
                objMyDatabaseClass.getReadableDatabase();
        }catch (Exception e){
            Toast.makeText(this,"Exception while creating database "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void insertInToDatabase(View view){

        try {
            SQLiteDatabase objSqliteDatabase = objMyDatabaseClass.getReadableDatabase();

            if (objSqliteDatabase!=null){
                if (!userNameET.getText().toString().isEmpty() &&
                !emailET.getText().toString().isEmpty() && !phoneET.getText().toString().isEmpty()
                ){
                    ContentValues objContentValues = new ContentValues();
                    objContentValues.put("Name",userNameET.getText().toString());
                    objContentValues.put("Email",emailET.getText().toString());
                    objContentValues.put("Phone",phoneET.getText().toString());
                    long checkIfQueryRuns = objSqliteDatabase.insert("UniStudent",null,objContentValues);
                if (checkIfQueryRuns!= -1){
                    Toast.makeText(this,"Values inserted successfully ",Toast.LENGTH_SHORT).show();
                  userNameET.setText(null);
                  emailET.setText(null);
                  phoneET.setText(null);
                  }
                }
                else {
                  Toast.makeText(this,"Please fill all fields.. ",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"Database is null.. ",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this,"Error inserting values "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

   /* public void showValuesFromDatabase(View view){
        try {
            SQLiteDatabase objSqliteDatabase = objMyDatabaseClass.getReadableDatabase();
            if (objSqliteDatabase!=null){
                Cursor objCursor = objSqliteDatabase.rawQuery("select * from UniStudent",null);
                StringBuffer stringBuffer = new StringBuffer();
                if (objCursor.getCount()==0){
                    Toast.makeText(this,"No data returned.. ",Toast.LENGTH_LONG).show();
                }else {
                    while (objCursor.moveToNext()){
                        stringBuffer.append("ID:- "+objCursor.getInt(0)+"\n");
                        stringBuffer.append("Name:- "+objCursor.getString(1)+"\n");
                        stringBuffer.append("Email "+objCursor.getString(2)+"\n");
                        stringBuffer.append("Phone:- "+objCursor.getInt(3)+"\n");
                    }
                    showValuesTV.setText(stringBuffer);
                }
            } else {
                Toast.makeText(this,"Database is null..",Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(this,"Show values from DB"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }*/

    public void moveToShowValuesAct(View view){
        startActivity(new Intent(this,ShowValuesAct.class));
    }
}
