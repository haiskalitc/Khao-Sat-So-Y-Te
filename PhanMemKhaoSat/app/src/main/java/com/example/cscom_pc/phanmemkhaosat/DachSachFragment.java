package com.example.cscom_pc.phanmemkhaosat;

/**
 * Created by CSCOM-PC on 4/24/2017.
 */

public class DachSachFragment
{
    private static DachSachFragment ourInstance = new DachSachFragment();

    public static DachSachFragment getInstance() {
        return ourInstance;
    }

    public SearchDanhSach searchDanhSach = new SearchDanhSach();

    public ThongTinDanhSach thongTinDanhSach = new ThongTinDanhSach();

    public EditTaiKhoan editTaiKhoan = new EditTaiKhoan();

    public ThongTinTaiKhoan thongTinTaiKhoan = new ThongTinTaiKhoan();

}
