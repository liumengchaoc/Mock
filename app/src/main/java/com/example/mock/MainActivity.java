package com.example.mock;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout line;
    private ViewPager pager;
    private String[] titile={"头条","读报","视频","激情","经济","人文","生态","社会","图片","旅游","宜居","时间","观点","健康","法制","生活","教育","活动"};
  private List<TextView> list=new ArrayList<>();
  private List<ChannelBean> channelBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   initview();
   initdata();
    }
    private void initdata() {
        channelBeans.add( new ChannelBean("头条",true));
        channelBeans.add( new ChannelBean("读报",true));
        channelBeans.add(new ChannelBean("视频",true));
        channelBeans.add( new ChannelBean("激情",true));
        channelBeans.add(new ChannelBean("经济",true));
        channelBeans.add( new ChannelBean("人文",true));
        channelBeans.add(new ChannelBean("生态",true));
        channelBeans.add( new ChannelBean("社会",true));
        channelBeans.add(new ChannelBean("图片",true));
        channelBeans.add( new ChannelBean("旅游",true));
        channelBeans.add( new ChannelBean("宜居",true));
        channelBeans.add(new ChannelBean("时间",false));
        channelBeans.add(new ChannelBean("观点",false));
        channelBeans.add( new ChannelBean("健康",false));
        channelBeans.add( new ChannelBean("法制",false));
        channelBeans.add( new ChannelBean("生活",false));
        channelBeans.add( new ChannelBean("活动",false));
        for (int i = 0; i < channelBeans.size(); i++) {
   final TextView textVie=new TextView(this);
   textVie.setText(channelBeans.get(i).getName().toString());
   textVie.setId(i+1000);
   textVie.setTextSize(23);
   list.add(textVie);
            textVie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = textVie.getId();
                    pager.setCurrentItem(id-1000);
                }
            });
   LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
   layoutParams.setMargins(50,10,50,10);
   line.addView(textVie,layoutParams);
  if(i==0){
   textVie.setTextColor(Color.RED);
  }else {
      textVie.setTextColor(Color.BLACK);
  }
  if(channelBeans.get(i).isSelect()){
   textVie.setVisibility(View.VISIBLE);
  }else {

      textVie.setVisibility(View.GONE);
  }




        }
        pager.setAdapter(new Myadapter(getSupportFragmentManager()));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                for (int j = 0; j <list.size() ; j++) {
                    if(j==i){
                   list.get(j).setTextColor(Color.RED);
                    }else {
                 list.get(j).setTextColor(Color.BLACK);
                    }

                }
                int width =  list.get(i).getWidth();
                int totalWidth = (width +20)*i;
                horizontalScrollView.scrollTo(totalWidth,0);
                }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ChannelActivity.startChannelActivity(MainActivity.this,channelBeans);
        }
    });
    }
    private void initview() {
        button=findViewById(R.id.button);
        horizontalScrollView=findViewById(R.id.horizontalScrollview);
        line=findViewById(R.id.line);
        pager=findViewById(R.id.pager);
    }
    private class Myadapter extends FragmentPagerAdapter {
        public Myadapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return  Fragmentone.getfragment(list.get(i).getText().toString());
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }
}
