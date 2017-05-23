package com.syt.phanmemkhaosat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Adapter.DanhSachAdapter;
import Adapter.MenuAdapter;
import Model.DataListView;
import Model.DataProvider;
import Model.ItemMenu;
import Model.ThongTinDangNhap;


public class DanhSachDotKhaoSat extends AppCompatActivity {
    ArrayList<ItemMenu> arrMenu;
    MenuAdapter menuAdapter;
    //-------------------
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
    LinearLayout linearLayout ;
    ActionBarDrawerToggle drawerToggle;
    ProgressDialog progressDialog ;
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
                if(listAdapter == null) {
                    listAdapter = new DanhSachAdapter(DanhSachDotKhaoSat.this, R.layout.item_list_danhsach,
                            DataProvider.arrDanhSachDotKhaoSat);
                    lsvItem.setAdapter(listAdapter);
                    progressDialog.dismiss();
                }
                else
                {
                    listAdapter.notifyDataSetChanged();
                    if(DataProvider.arrDanhSachDotKhaoSat.size()==0)
                    {
                        Toast.makeText(DanhSachDotKhaoSat.this,"Không có dữ liệu !! ",Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            }
        };
        LayDanhSachDotKhaoSat();
        ItemClick();
        drawerToggle = new ActionBarDrawerToggle(DanhSachDotKhaoSat.this,drawerLayout,R.string.app_name,R.string.app_name)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
                linearLayout.setTranslationX(slideOffset*(linearLayout.getWidth()-getResources().getInteger(R.integer.strans)));
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }
    public void ItemClick()
    {
        lsvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            }
        });
    }
    public void OpenNavigation()
    {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void LayDanhSachDotKhaoSat()
    {
        progressDialog = ProgressDialog.show(DanhSachDotKhaoSat.this,"Vui lòng đợi...","Đang tải dữ liệu ...",true);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                 JSONArray jarrDanhSach = ChucNang.getInstance().LayDotKhaoSat(getMili(tsvNam.getText().toString(),1,1),
                         getMili(tsvNam.getText().toString(),30,12));
                for(int i = 0 ; i < jarrDanhSach.length(); i ++)
                {
                    try {
                        JSONObject obj = jarrDanhSach.getJSONObject(i);
                        String tenDotKhaoSat = obj.getString("TenDotKhaoSat");
                        String ngayBatDAu = getDate(obj.getLong("NgayBatDau"),"dd/MM/yyyy");
                        String ngayKetThuc = getDate(obj.getLong("NgayKetThuc"),"dd/MM/yyyy");
                        String tenBenhVien = obj.getJSONObject("DonVi").getString("Ten").toString();
                        DataProvider.arrDanhSachDotKhaoSat.add(new DataListView(tenDotKhaoSat,ngayBatDAu + " đến "+ngayKetThuc,tenBenhVien));
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
        arrMenu.add(new ItemMenu(2, R.drawable.icothongke, "Thống kê"));
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
                        startActivityForResult(intent,1);
                        break;
                    }
                    case 4:
                        {
                            DangXuat();
                        break;
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if(DachSachFragment.getInstance().thongTinDanhSach.isVisible())
        {
            DangXuat();
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
        linearLayout = (LinearLayout) findViewById(R.id.baonien);
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
                if (isNextYear(tsvNam))
                {
                    YearSystem++;
                    setNam();
                    setButton();
                    DataProvider.arrDanhSachDotKhaoSat.clear();
                    LayDanhSachDotKhaoSat();
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
                DataProvider.arrDanhSachDotKhaoSat.clear();
                LayDanhSachDotKhaoSat();
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
        milliSeconds*=1000;
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
    public static  long getMili(String nam,int ngay , int thang)
    {
        int mi = Integer.valueOf(nam);
        Calendar calendar = Calendar.getInstance();
        calendar.set(mi,thang,ngay);
        Date date = new Date();
        return calendar.getTimeInMillis()/ 1000;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==1)
            {
                thongTinDangNhap = (ThongTinDangNhap) data.getSerializableExtra("tk");
                txtMenuName.setText(thongTinDangNhap.getHoten());
            }
        }
    }
    public void DangXuat()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DanhSachDotKhaoSat.this);
        dialog.setTitle("Thông báo!!");
        dialog.setMessage("Bạn có muốn đang xuất ?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        dialog.show();
    }
}
