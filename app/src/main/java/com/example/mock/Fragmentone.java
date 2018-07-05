package com.example.mock;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.mock.Adapter.Myadapter;
import com.example.mock.Adapter.Viewpager;
import com.example.mock.Pojo.Clauses;
import com.example.mock.Pojo.MyBean;
import com.example.mock.Utill.HttpUtil;
import com.example.mock.Utill.JsonBack;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class Fragmentone extends Fragment {
    private ViewPager viewPager;
    private int page=1;
    private String path="http://www.wanandroid.com/tools/mockapi/6523/article_channel_list";
    private ViewPager Adapter;
    private String mroute="https://www.apiopen.top/satinApi?type=&page=";
    private RadioGroup group;
    private PullToRefreshListView  pullToRefreshListView;
    private List<String>  list=new ArrayList<>();
    private Myadapter myadapter;
    private  List<Clauses.DataBean> datas=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = viewPager.getCurrentItem();
            viewPager.setCurrentItem(currentItem+1);
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };
    public static   Fragment getfragment(String title){
        Fragmentone fragments=new Fragmentone();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
       fragments.setArguments(bundle);
       return  fragments;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmenyone,container,false);
        viewPager= view.findViewById(R.id.viewpager);
        group=view.findViewById(R.id.grouop);
        pullToRefreshListView=view.findViewById(R.id.pulltorefresh);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        String title = arguments.getString("title");
        if(title.equals("经济")) {

            HttpUtil.getdata(path, getActivity(), new JsonBack() {
                @Override
                public void getdata(String s) {
                    Gson gson = new Gson();
                    MyBean myBean = gson.fromJson(s, MyBean.class);
                    List<MyBean.DataBean.FocusListBean> focus_list = myBean.getData().getFocus_list();
                    for (int i = 0; i < focus_list.size(); i++) {
                        list.add(focus_list.get(i).getImage_url());
                    }
                    viewPager.setAdapter(new Viewpager(list, getActivity(), handler));
                    handler.sendEmptyMessageDelayed(1, 1000);
                    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int i) {
                            switch (i) {
                                case 0:
                                    group.check(R.id.but1);//选中对应的Button
                                    break;
                                case 1:
                                    group.check(R.id.but2);//选中对应的Button
                                    break;
                                case 2:
                                    group.check(R.id.but3);
                                    break;
                                case 3:
                                    group.check(R.id.but4);
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {
                        }
                    });

                }
            });
            initview();
            getDatas();
        }else if(title.equals("视频")){





        }
    }

    private void initview() {
        pullToRefreshListView.setMode(PullToRefreshListView.Mode.BOTH);
        ILoadingLayout startloading = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        startloading.setPullLabel("下拉刷新");
        startloading.setRefreshingLabel("正在刷新");
        startloading.setPullLabel("刷新完成");
        ILoadingLayout  endLayoutProxy = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        endLayoutProxy.setPullLabel("上拉加载");
        endLayoutProxy.setRefreshingLabel("正在加载");
        endLayoutProxy.setPullLabel("加载完成");
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> pullToRefreshBase) {
                page=1;
                getDatas();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullToRefreshListView.onRefreshComplete();
                }
            },2000);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshListView.onRefreshComplete();
                    }
                },2000);
                getDatas();
            }
        });
 myadapter=new Myadapter(datas,getActivity());
 pullToRefreshListView.setAdapter(myadapter);

    }
    public void getDatas() {
        String url=mroute+page;
HttpUtil.getdata(url, getActivity(), new JsonBack() {
     @Override
     public void getdata(String s) {
         Gson gson = new Gson();
         Clauses clauses = gson.fromJson(s, Clauses.class);
         List<Clauses.DataBean> data = clauses.getData();
         if (page==1){
             datas.clear();
         }
         datas.addAll(data);
         myadapter.notifyDataSetChanged();
     };
 });







    }
}
