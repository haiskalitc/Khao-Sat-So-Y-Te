package com.example.cscom_pc.phanmemkhaosat;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CSCOM-PC on 4/25/2017.
 */

public class DanhSachAdapter extends ArrayAdapter<DataListView>
{
    Activity context ;
    int resource ;
    List<DataListView> objects;
    public DanhSachAdapter(Activity context, int resource, List<DataListView> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View dataRowView = layoutInflater.inflate(this.resource,null);
        TextView txtTenDotKhaoSat = (TextView) dataRowView.findViewById(R.id.txtTenDotKhaoSat);
        TextView txtNgayDen = (TextView) dataRowView.findViewById(R.id.txtNgayDenNgayDi);
        TextView txtTenBenhVien = (TextView) dataRowView.findViewById(R.id.txtBenhVien);
        txtTenDotKhaoSat.setText(this.objects.get(position).getTen());
        txtNgayDen.setText(this.objects.get(position).getNgay());
        txtTenBenhVien.setText(this.objects.get(position).getTenbv());
        return  dataRowView;
    }
}
