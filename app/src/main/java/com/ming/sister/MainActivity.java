package com.ming.sister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ming.sister.video.VideoActivity;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{


    private ListView lv_people;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data = new ArrayList<>();
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);


        setFile();

        lv_people = (ListView)findViewById(R.id.lv_people);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv_people.setAdapter(adapter);

        getFileName();

        lv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                Toast toast = Toast.makeText(MainActivity.this,"已接通",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,600);
                toast.show();
            }
        });
    }



    public void setFile(){
        file = new File("/storage/emulated/0/sister");
        if (!file.exists()){
            boolean isSuccess = file.mkdirs();
            //Toast.makeText(MainActivity.this,"文件夹创建成功",Toast.LENGTH_LONG).show();
        }else{
            //Toast.makeText(MainActivity.this,"文件夹已存在",Toast.LENGTH_LONG).show();
        }
    }


    //读取指定目录下的所有TXT文件的文件名
    private void getFileName() {
        String[] video = file.list();
        for(String s : video){
            s = s.replace(".mp4","");
            data.add(s);
        }
        adapter.notifyDataSetChanged();
    }



}
