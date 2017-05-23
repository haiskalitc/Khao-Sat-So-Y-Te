package com.syt.phanmemkhaosat;

import Fragment.EditTaiKhoan;
import Fragment.SearchDanhSach;
import Fragment.ThongTinDanhSach;
import Fragment.ThongTinTaiKhoan;

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
