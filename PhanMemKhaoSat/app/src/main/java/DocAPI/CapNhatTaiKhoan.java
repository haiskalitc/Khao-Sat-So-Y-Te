package DocAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hung-pc on 5/4/2017.
 */

public class CapNhatTaiKhoan
{
    public void CapNhatDuLieu(String urlString,String ten ,String tenDangNhap,String matKhau,String email,String sdt) {
        try {
            HttpURLConnection urlConnection = null;

            URL url = null;

            url = new URL(urlString);

            urlConnection = (HttpURLConnection) url.openConnection();
            // Thiet lap loai phuong thuc
            urlConnection.setRequestMethod("POST");
            // thiet lap dang du lieu gui len va nhan ve
            urlConnection.setRequestProperty("Content-Type", "application/json");
            // tong thoi gian doc du lieu
            urlConnection.setReadTimeout(40000);
            // tong thoi gian ket noi
            urlConnection.setConnectTimeout(45000);
            // cho phep gui dulieu len server: mac dinh la false
            urlConnection.setDoOutput(true);
            // cho phep doc du lieu tu server: mac dinh la true
            //urlConnection.setDoInput(true);
            // thuc hien goi den phuong thuc theo yeu cau
            urlConnection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
