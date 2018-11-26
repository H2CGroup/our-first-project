package com.example.tmcfc.thanhxuanfc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tmcfc.thanhxuanfc.R;
import com.example.tmcfc.thanhxuanfc.model.InfoCauThu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoPlayerAdapter extends BaseAdapter {

    ArrayList<InfoCauThu> mangInfocauthu;
    Context context;

    public InfoPlayerAdapter(ArrayList<InfoCauThu> mangInfocauthu, Context context) {
        this.mangInfocauthu = mangInfocauthu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mangInfocauthu.size();
    }

    @Override
    public Object getItem(int position) {
        return mangInfocauthu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtTencauthu, txtNamsinh, txtChieucao, txtCannang, txtVitri, txtSoao, txtDiachi;
        ImageView imgCauthu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_listview_info_cauthu, null);
            viewHolder.txtTencauthu = convertView.findViewById(R.id.textviewtencauthu);
            viewHolder.imgCauthu = convertView.findViewById(R.id.imageviewinfocauthu);
            viewHolder.txtNamsinh = convertView.findViewById(R.id.textviewnamsinh);
            viewHolder.txtChieucao = convertView.findViewById(R.id.textviewchieucao);
            viewHolder.txtCannang = convertView.findViewById(R.id.textviewcannang);
            viewHolder.txtVitri = convertView.findViewById(R.id.textviewvitri);
            viewHolder.txtSoao = convertView.findViewById(R.id.textviewsoao);
            viewHolder.txtDiachi = convertView.findViewById(R.id.textviewdiachi);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        InfoCauThu infoCauThu = (InfoCauThu) getItem(position);
        viewHolder.txtTencauthu.setText(infoCauThu.getTencauthu());
        viewHolder.txtNamsinh.setText(infoCauThu.getNamsinh());
        viewHolder.txtChieucao.setText(infoCauThu.getChieucao());
        viewHolder.txtCannang.setText(infoCauThu.getCannang());
        viewHolder.txtVitri.setText(infoCauThu.getVitri());
        viewHolder.txtSoao.setText(infoCauThu.getSoao());
        viewHolder.txtDiachi.setText(infoCauThu.getDiachi());
        Picasso.get().load(infoCauThu.getHinhanhcauthu())
                .placeholder(R.drawable.avatar)
                .error(R.drawable.common_google_signin_btn_text_dark_normal)
                .into(viewHolder.imgCauthu);
        return convertView;
    }
}
