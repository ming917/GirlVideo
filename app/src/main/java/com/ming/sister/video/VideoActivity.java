package com.ming.sister.video;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ming.sister.R;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private ImageView iv_pause;
    private ImageView iv_shrink;
    private ImageView iv_stop;
    private ImageView iv_change;

    private Camera camera;
    private SurfaceView sfv_camera;
    private SurfaceHolder holder;
    private UserVideoView vv_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO
        getCamera();


        setContentView(R.layout.activity_video);

        init();
        initData();

    }

    private void init() {

        iv_shrink = (ImageView)findViewById(R.id.iv_shrink);
        iv_pause = (ImageView)findViewById(R.id.iv_pause);
        iv_stop = (ImageView)findViewById(R.id.iv_stop);
        iv_change = (ImageView)findViewById(R.id.iv_change);

        vv_video = (UserVideoView)findViewById(R.id.vv_video);

        //TODO
        sfv_camera = (SurfaceView)findViewById(R.id.sfv_camera);
        holder = sfv_camera.getHolder();
        holder.addCallback(this);
    }

    private void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        vv_video.setVideoPath("/storage/emulated/0/sister/" + name);

        vv_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int width = mp.getVideoWidth();
                int height = mp.getVideoHeight();

                //TODO 回调函数
                vv_video.setMeasure(width,height);
                vv_video.requestLayout();

                vv_video.start();

            }
        });
        vv_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });


        iv_shrink.setImageResource(R.drawable.shrink);

        Glide.with(this).load(R.drawable.pause)
                .apply(new RequestOptions().error(this.getResources().getDrawable(R.drawable.pause))
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleWithBorder(this, 3, Color.parseColor("#ccffffff"))))
                .into(iv_pause);

        Glide.with(this).load(R.drawable.stop)
                .apply(new RequestOptions().error(this.getResources().getDrawable(R.drawable.pause))
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleWithBorder(this, 3, Color.parseColor("#ccff0000"))))
                .into(iv_stop);

        Glide.with(this).load(R.drawable.change)
                .apply(new RequestOptions().error(this.getResources().getDrawable(R.drawable.pause))
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleWithBorder(this, 3, Color.parseColor("#ccffffff"))))
                .into(iv_change);
    }





    //TODO
    private void getCamera(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    camera= Camera.open(1);
                }catch (Exception e){
                    camera = null;
                    e.printStackTrace();
                }
                if(holder != null){
                    setStartPreView(camera,holder);
                }
            }
        }).start();
    }

    private void releaseCamera(){
        camera.release();
    }


    private void setStartPreView(Camera camera, SurfaceHolder holder) {
        if(camera == null){
            setStartPreView(this.camera,holder);
            return;
        }
        try {
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreView(camera,holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        camera.stopPreview();
        setStartPreView(camera,holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }


}
