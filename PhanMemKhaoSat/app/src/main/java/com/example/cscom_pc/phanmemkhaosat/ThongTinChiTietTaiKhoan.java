package com.example.cscom_pc.phanmemkhaosat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import Model.ThongTinDangNhap;

public class ThongTinChiTietTaiKhoan extends AppCompatActivity {

    RelativeLayout reDoiMatKhau;
    EditText txtTaiKhoan ;
    EditText txtSoDienThoai;
    EditText txtEmail;
    EditText txtVaiTro;
    EditText txtDonVi;
    ImageButton btnDoiMatKhau;
    ThongTinDangNhap thongTinDangNhap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_chi_tiet_tai_khoan);
        callFragment(DachSachFragment.getInstance().thongTinTaiKhoan);
        KhoiTao();
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DoiMatKhau();
            }
        });
        SetEdit(false);
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
    public void DoiMatKhau()
    {
        Intent intent = new Intent(ThongTinChiTietTaiKhoan.this,DoiMatKhau.class);
        startActivity(intent);
    }
    public void KhoiTao()
    {
        reDoiMatKhau = (RelativeLayout) findViewById(R.id.reDoiMatKhau);
        txtTaiKhoan = (EditText) findViewById(R.id.txtTenNguoiDung);
        txtSoDienThoai = (EditText) findViewById(R.id.txtSoDienThoaiNguoiDung);
        txtEmail = (EditText) findViewById(R.id.txtEmailNguoiDung);
        txtVaiTro = (EditText) findViewById(R.id.txtVaiTroNguoiDung);
        txtDonVi = (EditText) findViewById(R.id.txtDonViNguoiDugn);
        btnDoiMatKhau = (ImageButton) findViewById(R.id.btnDoiMatKhau);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("key");
        thongTinDangNhap = (ThongTinDangNhap) bundle.getSerializable("thongtin");
        txtTaiKhoan.setText(thongTinDangNhap.getHoten());
        txtSoDienThoai.setText(thongTinDangNhap.getSoDienThoai());
        txtEmail.setText(thongTinDangNhap.getEmail());
        txtDonVi.setText(thongTinDangNhap.getDonVi());
    }

    @Override
    public void onBackPressed() {
        if (DachSachFragment.getInstance().thongTinTaiKhoan.isVisible()) {
            finish();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ThongTinChiTietTaiKhoan.this);
            dialog.setTitle("Thông báo!!");
            dialog.setMessage("Bạn chưa lưu thay đổi ?");
            dialog.setPositiveButton("Quay Lại", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Quay lại
                    dialog.cancel();
                }
            });
            dialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }
    }
    public void SetEdit(boolean fa)
    {
        if(fa) {
            reDoiMatKhau.setVisibility(View.INVISIBLE);
        }
        else
        {
            reDoiMatKhau.setVisibility(View.VISIBLE);
        }
        txtTaiKhoan.setEnabled(fa);
        txtEmail.setEnabled(fa);
        txtDonVi.setEnabled(fa);
        txtSoDienThoai.setEnabled(fa);
        txtVaiTro.setEnabled(fa);
    }
}
