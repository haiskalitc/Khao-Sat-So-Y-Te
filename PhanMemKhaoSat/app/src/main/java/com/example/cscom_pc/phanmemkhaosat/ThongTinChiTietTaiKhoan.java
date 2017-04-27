package com.example.cscom_pc.phanmemkhaosat;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ThongTinChiTietTaiKhoan extends AppCompatActivity {

    EditText txtTaiKhoan ;
    EditText txtSoDienThoai;
    EditText txtEmail;
    EditText txtVaiTro;
    EditText txtDonVi;
    ImageButton btnDoiMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_chi_tiet_tai_khoan);
        callFragment(DachSachFragment.getInstance().thongTinTaiKhoan);
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiMatKhau();
            }
        });
    }
    //Gọi fragment
    public void callFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frmThongTinTaiKhoan, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void setEdit(boolean bo)
    {
    }
    public void DoiMatKhau()
    {
        Intent intent = new Intent(ThongTinChiTietTaiKhoan.this,DoiMatKhau.class);
        startActivity(intent);
    }
    public void KhoiTao()
    {
        txtTaiKhoan = (EditText) findViewById(R.id.txtTenNguoiDung);
        txtSoDienThoai = (EditText) findViewById(R.id.txtSoDienThoaiNguoiDung);
        txtEmail = (EditText) findViewById(R.id.txtEmailNguoiDung);
        txtVaiTro = (EditText) findViewById(R.id.txtVaiTroNguoiDung);
        txtDonVi = (EditText) findViewById(R.id.txtDonViNguoiDugn);
        btnDoiMatKhau = (ImageButton) findViewById(R.id.btnDoiMatKhau);
    }
}
