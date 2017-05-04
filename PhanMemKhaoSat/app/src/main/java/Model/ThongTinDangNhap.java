package Model;

import java.io.Serializable;

/**
 * Created by hung-pc on 5/4/2017.
 */

public class ThongTinDangNhap implements Serializable {
    String hoten;
    String email;
    String soDienThoai;
    String donVi;

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public ThongTinDangNhap(String hoten, String email, String soDienThoai, String donVi) {
        this.hoten = hoten;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.donVi = donVi;
    }
}
