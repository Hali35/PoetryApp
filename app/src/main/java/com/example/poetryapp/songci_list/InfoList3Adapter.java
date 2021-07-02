package com.example.poetryapp.songci_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryapp.R;
import com.example.poetryapp.bean_songci.SongciBean;

import java.util.List;

public class InfoList3Adapter extends BaseAdapter {
    Context context;
    List<SongciBean> mDatas;

    public InfoList3Adapter(Context context, List<SongciBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }
    //决定了ListView列表展示的行数
    @Override
    public int getCount() {
        return mDatas.size();
    }

    //返回指定位置对应的数据
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    //返回指定位置对应的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.songci_infobtn3_lv,null);//将布局转换成view对象的方法
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //加载控件当中的数据
        //获取集合当中的数据
        SongciBean songciBean = mDatas.get(position);
        holder.titleTv.setText(songciBean.getTitle());
        holder.authorTv.setText(songciBean.getAuthor());
//        holder.iv.setImageResource(poetryBean.getPicId());
        return convertView;
    }


    class ViewHolder{
        ImageView iv;
        TextView titleTv,authorTv;
        public ViewHolder (View view){
            iv = view.findViewById(R.id.songci_info_iv);
            titleTv = view.findViewById(R.id.songci_info_title);
            authorTv = view.findViewById(R.id.songci_info_author);
        }
    }
}
