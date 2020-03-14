package com.example.wei09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;//Editor 為 sp 的內部類別
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=getSharedPreferences("conifg",MODE_PRIVATE);
        editor=sp.edit();
    }

    public void test1(View view){//存特定keyValue在沙箱中，跟著app共存亡
        //user 清除資料就會刪除
        editor.putInt("stage",(int)(Math.random()*45+1));
        editor.putBoolean("sound",true);
        editor.putString("username","wei");
        editor.commit();//寫入
    }
    public void test2(View view){
         boolean sound=sp.getBoolean("sound",true);
         int stage=sp.getInt("stage",1);
         String username=sp.getString("username","nobody");
        Toast.makeText(this,username+":"+stage+":"+sound+":"+sound,Toast.LENGTH_SHORT).show();
    }
    public void test3(View view){
        try {
            FileOutputStream fout=openFileOutput("test3.txt",MODE_PRIVATE);
            fout.write("hi".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save Succeed!",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.v("bug",e.toString());
            Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show();
        }
    }
    public void test4(View view) {//存到file資料夾裡
        try {
            FileInputStream fin = openFileInput("test3.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            fin.close();
            Toast.makeText(this, sb, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.v("bug", e.toString());
            Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
        }
    }
}
