package com.ming.sister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ming.sister.video.MyAdapter;
import com.ming.sister.video.PeopleItem;
import com.ming.sister.video.VideoActivity;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{


    private ListView lv_people;
    private MyAdapter adapter;
    private ArrayList<PeopleItem> data = new ArrayList<>();
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
        adapter = new MyAdapter(this,R.layout.item_people,data);
        lv_people.setAdapter(adapter);

        getFileName();

        lv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PeopleItem peopleItem = (PeopleItem) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                intent.putExtra("name",peopleItem.getName());
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



    private int[] headsId = {R.mipmap.head01,	R.mipmap.head02,	R.mipmap.head03,
            R.mipmap.head04,	R.mipmap.head05,	R.mipmap.head06,	R.mipmap.head07,
            R.mipmap.head08,	R.mipmap.head09,	R.mipmap.head010,	R.mipmap.head011,
            R.mipmap.head012,	R.mipmap.head013,	R.mipmap.head014,	R.mipmap.head015,
            R.mipmap.head016,	R.mipmap.head017,	R.mipmap.head018};
    private int headNumber = 0;

    //读取指定目录下的所有文件的文件名
    private void getFileName() {

        String[] video = file.list();
        for(String s : video){

            if(headNumber == headsId.length){
                headNumber = 0;
            }

            s = s.replace(".mp4","");
            PeopleItem peopleItem = new PeopleItem();
            peopleItem.setName(s);
            peopleItem.setPictureId(headsId[headNumber]);
            headNumber++;
            data.add(peopleItem);
        }
        adapter.notifyDataSetChanged();
    }



}
