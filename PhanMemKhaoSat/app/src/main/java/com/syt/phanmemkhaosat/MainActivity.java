package com.syt.phanmemkhaosat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.DataProvider;
import Model.TaiKhoan;
import Model.ThongTinDangNhap;

// MÀN HÌNH ĐĂNG NHẬP

public class MainActivity extends AppCompatActivity
{
    List<String> listTaiKhoan ;
    String countTemp ;
    public int  delay = 0 ;
    JSONObject taiKhoan  = null;
    AutoCompleteTextView txtTaiKhoan ;
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
        listTaiKhoan= new ArrayList<String>();
        txtTaiKhoan = (AutoCompleteTextView) findViewById(R.id.txtTenDangNhap);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        loadArray(this);
        txtTaiKhoan.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,listTaiKhoan));
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
                listTaiKhoan.add(txtTaiKhoan.getText().toString());
                saveArray();
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
    public  boolean saveArray()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEdit1 = sp.edit();
    /* sKey is an array */
        mEdit1.putInt("Status_size", listTaiKhoan.size());

        for(int i=0;i<listTaiKhoan.size();i++)
        {
                mEdit1.remove("Status_" + i);
                mEdit1.putString("Status_" + i, listTaiKhoan.get(i));
        }

        return mEdit1.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadArray(this);
        txtTaiKhoan.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,listTaiKhoan));
    }

    public  void loadArray(Context mContext)
    {
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(listTaiKhoan!=null) {
            listTaiKhoan.clear();
        }
        int size = mSharedPreference1.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            if(ThemTaiKhoanAuto(mSharedPreference1.getString("Status_" + i, null),listTaiKhoan))
            {
                listTaiKhoan.add(mSharedPreference1.getString("Status_" + i, null));
            }
        }

    }
    public boolean ThemTaiKhoanAuto(String taiKhoan ,List<String> list)
    {
        for (String item:list
             )
        {
            if(taiKhoan.equals(item))
            {
                return  false;
            }
        }
        return  true;
    }
    //-----------------------------------
    // Click đăng nhập
    public void DangNhapClick()
    {
        if (txtTaiKhoan.getText().toString().isEmpty()) {
            txtTaiKhoan.setError("Không thể trống !!!", getResources().getDrawable(R.drawable.eror));
            txtTaiKhoan.requestFocus();
        }
        else
        if (txtMatKhau.getText().toString().isEmpty())
        {
            txtMatKhau.setError("Không thể trống !!!", getResources().getDrawable(R.drawable.eror));
            txtMatKhau.requestFocus();
        }
        if (txtTaiKhoan.getText().toString().length() != 0 && txtMatKhau.getText().toString().length() != 0)
            {
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DataProvider.arrTaiKhoan.clear();
                        taiKhoan = ChucNang.getInstance().DangNhap(txtTaiKhoan,txtMatKhau,getMac(),getDeviceName(),"");
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
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }
    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }
    public String getMac()
    {
        WifiManager manager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }
}
