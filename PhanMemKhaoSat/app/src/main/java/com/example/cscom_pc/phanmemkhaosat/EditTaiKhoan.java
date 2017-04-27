package com.example.cscom_pc.phanmemkhaosat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by CSCOM-PC on 4/24/2017.
 */

public class EditTaiKhoan extends Fragment
{

    ImageButton btnDelete;
    ImageButton btnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_taikhoan,container,false);
        btnDelete = (ImageButton) view.findViewById(R.id.btnDelete);
        btnSave = (ImageButton) view.findViewById(R.id.btnSave);
        return  view;
    }
    public void Event()
    {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save edit
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Event();
    }
}
