package com.example.cscom_pc.phanmemkhaosat;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import DocAPI.LayDotKhaoSat;
import DocAPI.LayTaiKhoan;
import Model.DataListView;
import Model.DataProvider;

/**
 * Created by hung-pc on 5/4/2017.
 */

public class ChucNang
{
    private static ChucNang ourInstance = new ChucNang();

    public static ChucNang getInstance() {
        return ourInstance;
    }

    synchronized public JSONObject DangNhap(EditText txtTaiKhoan, EditText txtMauKhau)
    {
        return LayTaiKhoan.getJSONObjectFromURL("http://172.29.14.66:9999/api/TaiKhoan/KiemTraTaiKhoan",
                txtTaiKhoan.getText().toString()
                ,txtMauKhau.getText().toString());
    }
    synchronized public JSONArray LayDotKhaoSat()
    {
        return LayDotKhaoSat.getJSONObjectFromURL("http://172.29.14.66:9999/api/DotKhaoSat/DocDanhSachDotKhaoSatTheoNgay");
    }
}
