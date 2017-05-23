package com.syt.phanmemkhaosat;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import DocAPI.CapNhatThongTin;
import DocAPI.LayDotKhaoSat;
import DocAPI.LayTaiKhoan;
import Model.DataListView;
import Model.DataProvider;

import static DocAPI.CapNhatThongTin.CapNhatTaiKhoan;

/**
 * Created by hung-pc on 5/4/2017.
 */

public class ChucNang
{
    private static ChucNang ourInstance = new ChucNang();

    public static ChucNang getInstance() {
        return ourInstance;
    }

    synchronized public JSONObject DangNhap(EditText txtTaiKhoan, EditText txtMauKhau,String maThietBi,String tenThietBi,String token)
    {
        return LayTaiKhoan.getJSONObjectFromURL("http://172.29.14.66:9999/api/TaiKhoan/KiemTraTaiKhoan",
                txtTaiKhoan.getText().toString()
                ,txtMauKhau.getText().toString(),maThietBi,tenThietBi,token);
    }
    synchronized public JSONArray LayDotKhaoSat(long nambd,long namkt)
    {
        return LayDotKhaoSat.getJSONObjectFromURL("http://172.29.14.66:9999/api/DotKhaoSat/DocDanhSachDotKhaoSatTheoNgay",nambd,namkt);
    }
    synchronized public JSONObject CapNhat(EditText txtName,String taiKhoan,String matKhau,EditText txtEmail,EditText txtSDT)
    {
        return CapNhatThongTin.CapNhatTaiKhoan("http://172.29.14.66:9999/api/TaiKhoan/CapNhatTaiKhoan",
                txtName.getText().toString(),
                txtEmail.getText().toString(),
                txtSDT.getText().toString(),
                taiKhoan,matKhau);
    }
    synchronized public JSONObject DoiMatKhau(String txtName,String taiKhoan,String matKhau,String matKhauMoi,String txtEmail,String txtSDT)
    {
        return CapNhatThongTin.DoiMatKhau("http://172.29.14.66:9999/api/TaiKhoan/CapNhatTaiKhoan",
                txtName.toString(),
                txtEmail.toString(),
                txtSDT.toString(),
                taiKhoan,matKhau,matKhauMoi);
    }
}
