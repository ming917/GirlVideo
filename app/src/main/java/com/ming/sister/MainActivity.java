package com.ming.sister;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ming.sister.video.GlideCircleWithBorder;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_pause;
    private ImageView iv_shrink;
    private ImageView iv_stop;
    private ImageView iv_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initData();

    }

    private void init() {
        iv_shrink = (ImageView)findViewById(R.id.iv_shrink);
        iv_pause = (ImageView)findViewById(R.id.iv_pause);
        iv_stop = (ImageView)findViewById(R.id.iv_stop);
        iv_change = (ImageView)findViewById(R.id.iv_change);
    }

    private void initData() {
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
}
