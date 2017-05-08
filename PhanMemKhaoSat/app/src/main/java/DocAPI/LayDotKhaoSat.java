package DocAPI;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

/**
 * Created by CSCOM-PC on 4/25/2017.
 */

public class LayDotKhaoSat {
    public static JSONArray getJSONObjectFromURL(String urlString,long yearBatDau,long yearKetThuc) {
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


            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            JSONObject jsonObject = new JSONObject();
            // {"MaDonVi": "588029e323c9091574dd6471", "TuNgay": 0, "DenNgay": 1491358040}
            jsonObject.put("MaDonVi","588029e323c9091574dd6471");
            jsonObject.put("DenNgay",yearKetThuc);
            jsonObject.put("TuNgay",yearBatDau);
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            return new JSONArray(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

