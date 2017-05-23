package Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.syt.phanmemkhaosat.R;

import java.util.List;

import Model.DataListView;
import Model.DataProvider;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View dataRowView = layoutInflater.inflate(this.resource,null);
        TextView txtTenDotKhaoSat = (TextView) dataRowView.findViewById(R.id.txtTenDotKhaoSat);
        TextView txtNgayDen = (TextView) dataRowView.findViewById(R.id.txtNgayDenNgayDi);
        TextView txtTenBenhVien = (TextView) dataRowView.findViewById(R.id.txtBenhVien);
        txtTenDotKhaoSat.setText(this.objects.get(position).getTen());
        txtNgayDen.setText(this.objects.get(position).getNgay());
        txtTenBenhVien.setText(this.objects.get(position).getTenbv());
        ImageButton btnNextItem = (ImageButton) dataRowView.findViewById(R.id.btnItemNext);
        btnNextItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),DataProvider.arrDanhSachDotKhaoSat.get(position).getTen(),Toast.LENGTH_SHORT).show();
            }
        });
        return  dataRowView;
    }
}
