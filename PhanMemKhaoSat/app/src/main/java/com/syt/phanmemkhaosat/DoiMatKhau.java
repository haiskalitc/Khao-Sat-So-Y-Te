package com.syt.phanmemkhaosat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import Model.DataProvider;
import Model.ThongTinDangNhap;

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
                        if(txtMatKhauCu.getText().toString().length() == 0  ||
                                txtMatKhauMoi.getText().toString().length() == 0 ||
                                txtXacNhan.getText().toString().length() == 0)
                        {
                            if(txtMatKhauCu.getText().toString().trim().length() == 0 )
                            {
                                txtMatKhauCu.setError("Không thể trống !! ",getResources().getDrawable(R.drawable.eror));
                                txtMatKhauCu.requestFocus();

                            }
                            if(txtMatKhauMoi.getText().toString().trim().length() == 0)
                            {
                                txtMatKhauMoi.setError("Không thể trống !! ",getResources().getDrawable(R.drawable.eror));
                                txtMatKhauMoi.requestFocus();
                            }
                            if(txtXacNhan.getText().toString().trim().length() == 0)
                            {
                                txtXacNhan.setError("Không thể trống !! ",getResources().getDrawable(R.drawable.eror));
                                txtXacNhan.requestFocus();
                            }
                        }

                        if(txtMatKhauCu.getText().toString().length() !=0 &&
                                txtMatKhauMoi.getText().toString().length() !=0 &&
                                txtXacNhan.getText().toString().length() !=0 &&
                                !txtMatKhauMoi.getText().toString().equals(txtXacNhan.getText().toString()))
                        {
                            Toast.makeText(DoiMatKhau.this,"Mật khẩu không trùng khớp !! ",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(txtMatKhauMoi.getText().toString().length() < 6 &&
                                    txtXacNhan.getText().toString().length() < 6 )
                            {
                                Toast.makeText(DoiMatKhau.this,"Mật khẩu phải lớn hơn 6 kí tự !! ",Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(txtMatKhauCu.getText().toString().length() !=0 &&
                                txtMatKhauMoi.getText().toString().length() !=0 &&
                                txtXacNhan.getText().toString().length() !=0 &&
                                txtMatKhauMoi.getText().toString().equals(txtXacNhan.getText().toString())
                                &&
                                (txtMatKhauMoi.getText().toString().length() >= 6 &&
                                        txtXacNhan.getText().toString().length() >= 6)
                                )
                        {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(DoiMatKhau.this);
                            dialog.setTitle("Thông báo!!");
                            dialog.setMessage("Lưu thay đổi ?");
                            dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Lưu
                                    if(txtMatKhauCu.getText().toString().equals(DataProvider.arrTaiKhoan.get(0).getMatKhau()))
                                    {
                                        Intent intent = getIntent();
                                        ThongTinDangNhap thongTinDangNhap = (ThongTinDangNhap)
                                                intent.getBundleExtra("tkm").getSerializable("tkdmk");
                                        if(
                                                ChucNang.getInstance().DoiMatKhau(thongTinDangNhap.getHoten().toString(),
                                                DataProvider.arrTaiKhoan.get(0).getTaiKhoan(),
                                                DataProvider.arrTaiKhoan.get(0).getMatKhau(),
                                                txtMatKhauMoi.getText().toString(),
                                                thongTinDangNhap.getEmail(),thongTinDangNhap.getSoDienThoai()
                                                ) == null)
                                        {
                                            Toast.makeText(DoiMatKhau.this,"Đổi mật khẩu thất bại !!",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(DoiMatKhau.this,"Đổi mật khẩu thành công !!",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(DoiMatKhau.this,"Mật khẩu cũ không chính xác !!",Toast.LENGTH_SHORT).show();
                                    }
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
