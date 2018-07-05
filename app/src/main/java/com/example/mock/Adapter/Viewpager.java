package com.example.mock.Adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mock.Utill.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Viewpager extends PagerAdapter {
    private List<String> image;
    private Context context;
    private Handler handler;

    public Viewpager(List<String> image, Context context, Handler handler) {
        this.image = image;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view== o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


            ImageView  imageView=new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        String s = image.get(position);
        ImageLoader.getInstance().displayImage(s,imageView, MyApp.shape());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(1,1000);
                        break;
                    case  MotionEvent.ACTION_CANCEL:
                        handler.sendEmptyMessageDelayed(1,1000);


                }
                return false;
            }
        });




        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView((View) object)

         ;
    }
}
