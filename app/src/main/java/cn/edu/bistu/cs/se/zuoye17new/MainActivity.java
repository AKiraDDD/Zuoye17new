package cn.edu.bistu.cs.se.zuoye17new;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //SharedPreferences文件名
    private final static String SharedPreferencesFileName="config";

    //键
    private final static String Key_Name="name";
    private final static String Key_Id="id";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得SharedPreferences实例
        preferences=getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor=preferences.edit();

        Button btnWrite=(Button)findViewById(R.id.ButtonWrite);
        Button btnRead=(Button)findViewById(R.id.ButtonRead);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1 = (EditText) findViewById(R.id.name);
                String name = editText1.getText().toString();
                EditText editText2 = (EditText) findViewById(R.id.id);
                String id = editText2.getText().toString();

                //写入键值对
                editor.putString(Key_Name,name);
                editor.putString(Key_Id, id);

                //提交修改，此处换成commit()也可以
                editor.apply();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = preferences.getString(Key_Name, null);
                String strId = preferences.getString(Key_Id, null);
                if (strName != null && strId != null)
                    Toast.makeText(MainActivity.this, "姓名:" + strName + "学号:" + strId, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
            }
        });
    }
}