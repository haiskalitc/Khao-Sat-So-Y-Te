package com.syt.phanmemkhaosat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

import org.json.JSONException;
import org.json.JSONObject;

import Model.DataListView;
import Model.DataProvider;
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
    JSONObject jsonTaiKhoan = null;
    private Handler handler;
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
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    if(jsonTaiKhoan!=null) {
                        txtTaiKhoan.setText(jsonTaiKhoan.getString("Ten").toString());
                        txtSoDienThoai.setText(jsonTaiKhoan.getString("DienThoai").toString());
                        txtEmail.setText(jsonTaiKhoan.getString("Email").toString());
                        backToDanhSach();
                    }
                    else
                    {
                        Toast.makeText(ThongTinChiTietTaiKhoan.this,"Cập nhật thông tin thất bại !!",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonTaiKhoan = null;
                Toast.makeText(ThongTinChiTietTaiKhoan.this,"Cập nhật thông tin thành công !!",Toast.LENGTH_SHORT).show();

            }
        };
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
    public void backToDanhSach()
    {
        Intent inten = new Intent();
          ThongTinDangNhap thongTinDangNhap = new ThongTinDangNhap(txtTaiKhoan.getText().toString(),
                txtEmail.getText().toString(),
                txtSoDienThoai.getText().toString(),
                txtDonVi.getText().toString());
        inten.putExtra("tk",thongTinDangNhap);
        setResult(1,inten);
    }
    public void DoiMatKhau()
    {
        Intent intent = new Intent(ThongTinChiTietTaiKhoan.this,DoiMatKhau.class);
        ThongTinDangNhap tinDangNhap = new ThongTinDangNhap(txtTaiKhoan.getText().toString(),
                txtEmail.getText().toString(),
                txtSoDienThoai.getText().toString(),
                txtDonVi.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("tkdmk",tinDangNhap);
        intent.putExtra("tkm",bundle);
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
        txtDonVi.setEnabled(false);
        txtVaiTro.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        if (DachSachFragment.getInstance().thongTinTaiKhoan.isVisible())
        {
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
        txtSoDienThoai.setEnabled(fa);
    }
    public void ThayDoiThongTin()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                    jsonTaiKhoan = ChucNang.getInstance().CapNhat(
                        txtTaiKhoan,
                        DataProvider.arrTaiKhoan.get(0).getTaiKhoan(),
                        DataProvider.arrTaiKhoan.get(0).getMatKhau(),
                        txtEmail,txtSoDienThoai);
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        }).start();
    }
}
