package com.example.tmcfc.thanhxuanfc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tmcfc.thanhxuanfc.R;
import com.example.tmcfc.thanhxuanfc.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    ArrayList<Menu> arrayListmenu;
    Context context;

    public MenuAdapter(ArrayList<Menu> arrayListmenu, Context context) {
        this.arrayListmenu = arrayListmenu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListmenu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListmenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txttenmenu;
        ImageView imgmenu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_listview_menu, null);
            viewHolder.txttenmenu = convertView.findViewById(R.id.textviewmenu);
            viewHolder.imgmenu = convertView.findViewById(R.id.imageviewmenu);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Menu menu = (Menu) getItem(position);
        viewHolder.txttenmenu.setText(menu.getTenmenu());
        Picasso.get().load(menu.getHinhanhmenu())
                .placeholder(R.drawable.avatar)
                .error(R.drawable.common_google_signin_btn_text_dark_normal)
                .into(viewHolder.imgmenu);
        return convertView;
    }
}
