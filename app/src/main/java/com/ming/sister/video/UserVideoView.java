package com.ming.sister.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @Author : 张
 * @Email : manitozhang@foxmail.com
 * @Date : 2018/5/22
 */

public class UserVideoView extends VideoView{

    private int mWidth;
    private int mHeight;

    public UserVideoView(Context context) {
        super(context, null);
    }

    public UserVideoView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public UserVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setMeasure(int width,int height){
        mWidth = width;
        mHeight = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
        if(mWidth > 0){
            float scaleW = (float) width / (float) mWidth;
            float scaleH = (float) height / (float) mHeight;
            if(scaleH < scaleW){
                mHeight = (int)(mHeight * scaleW);
                mWidth = (int)(mWidth * scaleW);
            }else{
                mHeight = (int)(mHeight * scaleH);
                mWidth = (int)(mWidth * scaleH);
            }

            setMeasuredDimension(mWidth, mHeight);
        }

    }

}

