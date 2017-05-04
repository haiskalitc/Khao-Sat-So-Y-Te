package com.example.cscom_pc.phanmemkhaosat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Adapter.DanhSachAdapter;
import Adapter.MenuAdapter;
import DocAPI.LayDotKhaoSat;
import Model.DataListView;
import Model.DataProvider;
import Model.ItemMenu;
import Model.ThongTinDangNhap;


public class DanhSachDotKhaoSat extends AppCompatActivity {
    ArrayList<ItemMenu> arrMenu;
    MenuAdapter menuAdapter;
    //-------------------
    JSONArray jarrDanhSach = null;
    DanhSachAdapter listAdapter;
    //-------------------
    public static int YearSystem = Calendar.getInstance().get(Calendar.YEAR);
    ImageButton btnNext;
    ImageButton btnPrev;
    TextView txtMenuName ;
    TextView txtMenuNameBV;
    TextView tsvNam;
    ListView lsvItem;
    ListView lsvMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Handler handler;
    ThongTinDangNhap thongTinDangNhap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dot_khao_sat);
        callFragment(DachSachFragment.getInstance().thongTinDanhSach);
        KhoiTao();
        EventThayDoiNam();
        EventMenuClick();
        DataProvider.arrDanhSachDotKhaoSat.clear();
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listAdapter = new DanhSachAdapter(DanhSachDotKhaoSat.this,R.layout.item_list_danhsach, DataProvider.arrDanhSachDotKhaoSat);
                lsvItem.setAdapter(listAdapter);
            }
        };
        LayDanhSachDotKhaoSat();
    }
    public void LayDanhSachDotKhaoSat()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                jarrDanhSach = ChucNang.getInstance().LayDotKhaoSat();
                for(int i = 0 ; i < jarrDanhSach.length(); i ++)
                {
                    try {
                        JSONObject obj = jarrDanhSach.getJSONObject(i);
                        String tenDotKhaoSat = obj.getString("TenDotKhaoSat");
                        String ngayBatDAu = getDate(obj.getLong("NgayBatDau"),"dd/MM/yyyy");
                        String ngayketThuc = getDate(obj.getLong("NgayKetThuc"),"dd/MM/yyyy");
                        String tenBenhVien = obj.getJSONObject("DonVi").getString("Ten").toString();
                        DataProvider.arrDanhSachDotKhaoSat.add(new DataListView(tenDotKhaoSat,ngayBatDAu + " đến " + ngayketThuc,tenBenhVien));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        }).start();
    }
    public void KhoiTaoMenu() {
        arrMenu = new ArrayList<ItemMenu>();
        arrMenu.add(new ItemMenu(1, R.drawable.menuhome, "Trang chủ"));
        arrMenu.add(new ItemMenu(2, R.drawable.thongke, "Thống kê"));
        arrMenu.add(new ItemMenu(3, R.drawable.icon_accountt, "Tài khoản"));
        arrMenu.add(new ItemMenu(4, R.drawable.menuout, "Đăng xuất"));
        menuAdapter = new MenuAdapter(DanhSachDotKhaoSat.this, R.layout.item_list_menu, arrMenu);
        lsvMenu.setAdapter(menuAdapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("key");
        thongTinDangNhap = (ThongTinDangNhap) bundle.getSerializable("thongtin");
        txtMenuNameBV.setText(thongTinDangNhap.getDonVi());
        txtMenuName.setText(thongTinDangNhap.getHoten());
    }
    public void EventMenuClick() {
        lsvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrMenu.get(i).getId()) {
                    case 1:
                    {
                        drawerLayout.closeDrawers();
                        break;
                    }
                    case 2 :
                    {
                        break;
                    }
                    case 3: {
                        //Click tài khoản
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(DanhSachDotKhaoSat.this, ThongTinChiTietTaiKhoan.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("thongtin",thongTinDangNhap);
                        intent.putExtra("key",bundle);
                        startActivity(intent);
                        break;
                    }
                    case 4: {
                        finish();
                        break;
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if(DachSachFragment.getInstance().thongTinDanhSach.isVisible()) {
            finish();
        }
        else
        {
            super.onBackPressed();
        }
    }
    public void KhoiTao() {
        txtMenuName = (TextView) findViewById(R.id.txtMenuName);
        txtMenuNameBV = (TextView) findViewById(R.id.txtMenuTenBV);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        tsvNam = (TextView) findViewById(R.id.txvNam);
        lsvItem = (ListView) findViewById(R.id.lsvItem);
        lsvMenu = (ListView) findViewById(R.id.lsvMenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        setNam();
        setButton();
        KhoiTaoMenu();
    }
    public void setNam() {
        tsvNam.setText(String.valueOf(YearSystem));
    }
    public void setButton() {
        if (isNextYear(tsvNam)) {
            btnNext.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.INVISIBLE);
        }
    }
    public void EventThayDoiNam() {
        //Click next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNextYear(tsvNam)) {
                    YearSystem++;
                    setNam();
                    setButton();
                }
            }
        });

        //Click Prev
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YearSystem--;
                setNam();
                setButton();
            }
        });
    }
    public boolean isNextYear(TextView textView) {
        if (Integer.valueOf(textView.getText().toString()) + 1 > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }
        return true;
    }
    public void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.tittle, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public static String getDate(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);return formatter.format(calendar.getTime());
    }
}
