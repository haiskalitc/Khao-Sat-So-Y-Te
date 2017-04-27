package com.example.cscom_pc.phanmemkhaosat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.kosalgeek.android.json.JsonConverter;


public class DanhSachDotKhaoSat extends AppCompatActivity {
    ArrayList<ItemMenu> arrMenu;
    MenuAdapter menuAdapter;
    //-------------------
    ArrayList<DataListView> arrItem;
    DanhSachAdapter listAdapter;
    //-------------------
    public static int YearSystem = Calendar.getInstance().get(Calendar.YEAR);
    ImageButton btnNext;
    ImageButton btnPrev;
    TextView tsvNam;
    ListView lsvItem;
    ListView lsvMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    JSONArray jsonArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dot_khao_sat);
        callFragment(DachSachFragment.getInstance().thongTinDanhSach);
        KhoiTao();
        EventThayDoiNam();
        EventMenuClick();
        LayDanhSachDotKhaoSat();
    }

    public void LayDanhSachDotKhaoSat() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonArray = LayDuLieu.getJSONObjectFromURL("http://172.29.14.66:9999/api/DotKhaoSat/DocDanhSachDotKhaoSat");
                if(jsonArray!=null) {
                    arrItem = new ArrayList<DataListView>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONArray arr = jsonArray.getJSONObject(i).getJSONArray("DanhSachNhomKhaoSat");
                            for (int j = 0; j < arr.length(); j++) {
                                arrItem.add(new DataListView(arr.getJSONObject(j).getString("Ten"),
                                        arr.getJSONObject(j).getString("Ten"),
                                        arr.getJSONObject(j).getString("Ten")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    listAdapter = new DanhSachAdapter(DanhSachDotKhaoSat.this, R.layout.item_list_danhsach, arrItem);
                    lsvItem.setAdapter(listAdapter);
                }
                else
                {
                }

            }
        }).start();
    }

    //---------------------
    //Khởi tạo MENU item
    public void KhoiTaoMenu() {
        arrMenu = new ArrayList<ItemMenu>();
        arrMenu.add(new ItemMenu(1, R.drawable.menuhome, "Trang chủ"));
        arrMenu.add(new ItemMenu(2, R.drawable.icon_accountt, "Tài khoản"));
        arrMenu.add(new ItemMenu(3, R.drawable.menuout, "Đăng xuất"));
        menuAdapter = new MenuAdapter(DanhSachDotKhaoSat.this, R.layout.item_list_menu, arrMenu);
        lsvMenu.setAdapter(menuAdapter);
    }

    public void EventMenuClick() {
        lsvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrMenu.get(i).getId()) {
                    case 1: {
                        //Click trang chủ
                        break;
                    }
                    case 2: {
                        //Click tài khoản
                        Intent intent = new Intent(DanhSachDotKhaoSat.this, ThongTinChiTietTaiKhoan.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        finish();
                        break;
                    }
                }
            }
        });
    }

    //Khởi tạo LIST Item
    public void KhoiTaoItem()
    {

    }

    //------------------------------------
    //Khởi tạo control
    public void KhoiTao() {
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

    //Nếu không có năm tiếp theo thì ân nút next
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

    //Gọi fragment
    public void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.tittle, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
