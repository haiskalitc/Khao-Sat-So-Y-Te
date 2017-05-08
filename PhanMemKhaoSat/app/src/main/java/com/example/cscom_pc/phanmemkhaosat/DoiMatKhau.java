package com.example.cscom_pc.phanmemkhaosat;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class DoiMatKhau extends AppCompatActivity {

    EditText txtMatKhauCu ;
    EditText txtMatKhauMoi;
    EditText txtXacNhan;
    ImageButton btnHuy;
    ImageButton btnXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        KhoiTao();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.btnHuyDoiMatKhau :
                    {
                        if(txtMatKhauCu.getText().toString().length() > 0 ||
                                txtMatKhauMoi.getText().toString().length() > 0 ||
                                txtXacNhan.getText().toString().length()>0) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(DoiMatKhau.this);
                            dialog.setTitle("Thông báo!!");
                            dialog.setMessage("Hủy đổi mật khẩu?");
                            dialog.setPositiveButton("Quay Lại", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Quay lại
                                    dialog.cancel();

                                }
                            });
                            dialog.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    finish();
                                }
                            });
                            dialog.show();
                        }
                        else
                        {
                            finish();
                        }
                        break;
                    }
                    case R.id.btnLuuThayDoi :
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(DoiMatKhau.this);
                        dialog.setTitle("Thông báo!!");
                        dialog.setMessage("Lưu thay đổi ?");
                        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Lưu
                                finish();
                            }
                        });
                        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        dialog.show();
                        break;
                    }
                }
            }
        };
        btnXacNhan.setOnClickListener(clickListener);
        btnHuy.setOnClickListener(clickListener);
    }
    public void KhoiTao()
    {
        txtMatKhauCu = (EditText) findViewById(R.id.txtMatKhauCu);
        txtMatKhauMoi = (EditText) findViewById(R.id.txtMatKhauMoi);
        txtXacNhan = (EditText) findViewById(R.id.txtXacNhanMatKhauMoi);
        btnHuy = (ImageButton) findViewById(R.id.btnHuyDoiMatKhau);
        btnXacNhan = (ImageButton) findViewById(R.id.btnLuuThayDoi);
    }
}
