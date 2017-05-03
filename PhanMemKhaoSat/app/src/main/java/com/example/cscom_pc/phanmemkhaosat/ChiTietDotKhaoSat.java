package com.example.cscom_pc.phanmemkhaosat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ChiTietDotKhaoSat extends AppCompatActivity {

    ImageButton btnBack ;
    ImageButton btnThongKeTheoCauHoi ;
    ImageButton btnThongKeTheoCauTraLoi;
    ImageButton btnThongKeNguuoiDongY;
    TextView txtTenDotKhaoSat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dot_khao_sat);
        KhoiTao();
    }

    public void KhoiTao()
    {
        btnBack = (ImageButton) findViewById(R.id.btnBackChiTiet);
        txtTenDotKhaoSat = (TextView) findViewById(R.id.txtTenDotKhaoSat);
        btnThongKeTheoCauHoi = (ImageButton) findViewById(R.id.btnThongKeTheoCauHoi);
        btnThongKeTheoCauTraLoi = (ImageButton) findViewById(R.id.btnThongKeTheoCauTraLoi);
        btnThongKeNguuoiDongY = (ImageButton) findViewById(R.id.btnThongKeTheoSoNguoiDongY);
    }
}
