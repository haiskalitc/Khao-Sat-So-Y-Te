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

import java.util.List;

/**
 * Created by CSCOM-PC on 4/24/2017.
 */

public class MenuAdapter extends ArrayAdapter<ItemMenu>
{
    Activity context ;
    int resource ;
    List<ItemMenu> objects ;

    public MenuAdapter(Activity context, int resource, List<ItemMenu> objects) {
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
        TextView txtName = (TextView) dataRowView.findViewById(R.id.txtTenMenu);
        ImageView imageView = (ImageView) dataRowView.findViewById(R.id.hinhMenu);
        txtName.setText(this.objects.get(position).getName().toString());
        imageView.setImageResource(Integer.valueOf(this.objects.get(position).getImage()));
        return  dataRowView;
    }
}
