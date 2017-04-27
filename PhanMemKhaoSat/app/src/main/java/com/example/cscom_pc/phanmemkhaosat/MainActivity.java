package com.example.cscom_pc.phanmemkhaosat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// MÀN HÌNH ĐĂNG NHẬP

public class MainActivity extends AppCompatActivity {

    EditText txtTaiKhoan ;
    EditText txtMatKhau;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KhoiTaoControl();
        DangNhapClick();
    }

    // Khởi tạo control
    public void KhoiTaoControl()
    {
        txtTaiKhoan = (EditText) findViewById(R.id.txtTenDangNhap);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
    }
    //-----------------------------------
    // Click đăng nhập
    public void DangNhapClick()
    {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(txtTaiKhoan.length()==0)
                {
                    txtTaiKhoan.setError("Không thể trống !!!",getResources().getDrawable(R.drawable.eror));
                }
                if(txtMatKhau.length()==0)
                {
                    txtTaiKhoan.setError("Không thể trống !!!",getResources().getDrawable(R.drawable.eror));

                }
                if (txtTaiKhoan.length()!=0 && txtMatKhau.length()!=0)
                {
                    if (KiemTraDangNhap(txtTaiKhoan.getText().toString(), txtMatKhau.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, DanhSachDotKhaoSat.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    //--------------------------------------
    //Kiểm tra đang nhập
    public boolean KiemTraDangNhap(String taiKhoan,String matKhau)
    {
        if(taiKhoan.equals("admin")&&matKhau.equals("123"))
        {
            //Đăng nhập thành công
            return true;
        }
        else
        {
            //Đăng nhập thất bại
            return  false;
        }
    }
}
