package com.example.mock.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mock.Pojo.Clauses;
import com.example.mock.Pojo.MyBean;
import com.example.mock.R;
import com.example.mock.Utill.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Myadapter extends BaseAdapter {
    private final int  TYPEONE=0;
    private final int TYPETWO=1;
    private final int TYPETHREE=2;
    private List<Clauses.DataBean> list;
    private Context context;

    public Myadapter(List<Clauses.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public int getCount() {
        return  list.size();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        String s = list.get(position).getImage0();
        String column_logo = list.get(position).getImage1();
        String column_background = list.get(position).getImage2();
        if(s!=null&&column_logo!=null&&column_background!=null){
            return TYPETHREE;
        }else if(column_logo!=null&&column_background!=null&&s==null){
  return TYPETWO;
        }else if(column_logo==null&&column_background!=null&&s==null){
            return TYPEONE;
        }else {
            return TYPEONE;
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
   if(itemViewType==TYPEONE){
       ViewHodler hodler;
       if(view==null){
           hodler=new ViewHodler();
           view=View.inflate(context,R.layout.fill,null);
           hodler.text=view.findViewById(R.id.text);
           hodler.image=view.findViewById(R.id.image);
           view.setTag(hodler);
       }else {
           hodler= (ViewHodler) view.getTag();
       }
       hodler.text.setText(list.get(i).getText());
       String column_background = list.get(i).getImage0();
       ImageLoader.getInstance().displayImage(column_background,hodler.image, MyApp.shape());
       return view;
   }else if(itemViewType==TYPETWO){
  ViewHodler1 hodler1;
  if(view==null){
      hodler1=new ViewHodler1();
      view=View.inflate(context,R.layout.fill1,null);
      hodler1.text1=view.findViewById(R.id.text1);
      hodler1.image=view.findViewById(R.id.image1);
      hodler1.image1=view.findViewById(R.id.image2);
      view.setTag(hodler1);
  }else {
   hodler1= (ViewHodler1) view.getTag();
  }
        hodler1.text1.setText(list.get(i).getText());
       String column_background = list.get(i).getImage0();
       String column_logo = list.get(i).getImage1();
       ImageLoader.getInstance().displayImage(column_background,hodler1.image,MyApp.shape());
       ImageLoader.getInstance().displayImage(column_logo,hodler1.image1,MyApp.shape());
       return view;
   }else if(itemViewType==TYPETHREE){
      ViewHodler2  hodler2;
      if(view==null){
        hodler2=new ViewHodler2();
        view=View.inflate(context,R.layout.fill2,null);
         hodler2.image=view.findViewById(R.id.image);
          hodler2.image1=view.findViewById(R.id.image1);
          hodler2.image2=view.findViewById(R.id.image2);
      hodler2.text2=view.findViewById(R.id.text2);
      view.setTag(hodler2);
      }else {
  hodler2= (ViewHodler2) view.getTag();
      }
  hodler2.text2.setText(list.get(i).getText());
       String column_background = list.get(i).getImage0();
       String column_logo = list.get(i).getImage1();
       String s = list.get(i).getImage2();
       ImageLoader.getInstance().displayImage(column_background,hodler2.image,MyApp.shape());
       ImageLoader.getInstance().displayImage(column_logo,hodler2.image1,MyApp.shape());
       ImageLoader.getInstance().displayImage(s,hodler2.image2,MyApp.shape());

return  view;
   }

        return null;
    }
 class ViewHodler{
 ImageView image;
 TextView text;
 }
    class ViewHodler1{
        ImageView image;
        ImageView image1;
        TextView text1;
    }

    class ViewHodler2{
        ImageView image;
        ImageView image1;
        ImageView image2;
        TextView text2;
    }

}
