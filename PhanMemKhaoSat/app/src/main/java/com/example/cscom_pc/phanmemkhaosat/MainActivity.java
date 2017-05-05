package com.example.cscom_pc.phanmemkhaosat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import Model.DataProvider;
import Model.TaiKhoan;
import Model.ThongTinDangNhap;

// MÀN HÌNH ĐĂNG NHẬP

public class MainActivity extends AppCompatActivity
{
    public int  delay = 0 ;
    JSONObject taiKhoan  = null;
    EditText txtTaiKhoan ;
    EditText txtMatKhau;
    Button btnDangNhap;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KhoiTaoControl();
        DataProvider.arrTaiKhoan.clear();
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ValidateDangNhap();
            }

        };
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DangNhapClick();
            }
        });
    }
    // Khởi tạo control
    public void KhoiTaoControl()
    {
        txtTaiKhoan = (EditText) findViewById(R.id.txtTenDangNhap);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
    }
    public void ValidateDangNhap()
    {
        if(taiKhoan==null)
        {
            Toast.makeText(MainActivity.this, "Tài khoản mật khẩu không chính xác !! ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try {
                Intent intent = new Intent(MainActivity.this,DanhSachDotKhaoSat.class);
                ThongTinDangNhap thongTinDangNhap = new ThongTinDangNhap(taiKhoan.getString("Ten").toString(),
                        taiKhoan.getString("Email").toString(),
                        taiKhoan.getString("DienThoai").toString(),
                        taiKhoan.getJSONObject("DonVi").getString("Ten"));
                Bundle bundle = new Bundle();
                bundle.putSerializable("thongtin",thongTinDangNhap);
                intent.putExtra("key",bundle);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    //-----------------------------------
    // Click đăng nhập
    public void DangNhapClick()
    {
        if (txtTaiKhoan.getText().toString().length() == 0) {
            txtTaiKhoan.setError("Không thể trống !!!", getResources().getDrawable(R.drawable.eror));
        }
        if (txtMatKhau.getText().toString().length() == 0) {
            txtTaiKhoan.setError("Không thể trống !!!", getResources().getDrawable(R.drawable.eror));
            }
        if (txtTaiKhoan.getText().toString().length() != 0 && txtMatKhau.getText().toString().length() != 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        taiKhoan = ChucNang.getInstance().DangNhap(txtTaiKhoan,txtMatKhau);
                        DataProvider.arrTaiKhoan.add(new TaiKhoan(txtTaiKhoan.getText().toString(),txtMatKhau.getText().toString()));
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }
                }).start();
            }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(txtTaiKhoan.getText().length()>0 && txtMatKhau.getText().length() > 0)
        {
            txtTaiKhoan.setText("");
            txtMatKhau.setText("");
            txtTaiKhoan.setFocusable(true);
        }
    }
}
