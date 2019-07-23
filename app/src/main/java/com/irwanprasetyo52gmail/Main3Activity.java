package com.irwanprasetyo52gmail;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main3Activity extends AppCompatActivity {

    MySQLHelper dbHelper;
    private EditText ed_num;
    private EditText ed_name;
    protected Cursor cursor;
    private int id=-1;
    protected ListAdapter adapter;
    protected ListView numberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        this.ed_num = (EditText) this.findViewById(R.id.editText_num);
        this.ed_name = (EditText) this.findViewById(R.id.editText_name);
        this.numberList = (ListView) this.findViewById(R.id.ListView01);
        dbHelper = new MySQLHelper(this);
        numberList.setSelected(true);
        numberList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM data",null);
                cursor.moveToPosition(arg2);
                ed_num.setText(cursor.getString(1));
                ed_name.setText(cursor.getString(2));
                id=cursor.getInt(0);             }});
        view();     }

    private void addData(String num,String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            db.execSQL("insert into "+ MySQLHelper.TABLE +     " values(null,'"+num+"','"+name+"')");         }
        catch(Exception e)         {             ed_num.setText(e.toString());         }     }
    public void but_inClick(View v)     {
        addData(ed_num.getText().toString(),ed_name.getText().toString());
        view();
        ed_num.setText("");
        ed_name.setText("");
    }
    public void but_DelClick(View v)     {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{             if(id!=-1)             {
            db.execSQL("delete from "+ MySQLHelper.TABLE +" where number='" +     ed_num.getText().toString()+ "'");
            view();
            ed_num.setText("");
            ed_name.setText("");
        }         }
        catch(Exception e)         {
            ed_num.setText(e.toString());
        }     }
    public void but_EditClick(View v)     {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            if(id!=-1)             {
                db.execSQL("update data set number='"+   ed_num.getText().toString()+"',name='"+ed_name.getText().toString()+    "' where _id="+id);
                view();
                ed_num.setText("");
                ed_name.setText("");
            }         }         catch(Exception e)
        {             ed_num.setText(e.toString());         }     }
    private void view()     {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            cursor = db.rawQuery("SELECT * FROM data",null);
            adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.view2,
                    cursor,
                    new String[] {"number","name"},
                    new int[] {R.id.number,R.id.name});

            numberList.setAdapter(adapter);}
        catch(Exception e)
        {             ed_num.setText(e.toString());
        }     }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {             return true;         }

        return super.onOptionsItemSelected(item);     }
}

