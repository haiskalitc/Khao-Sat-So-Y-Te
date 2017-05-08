package Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cscom_pc.phanmemkhaosat.DachSachFragment;
import com.example.cscom_pc.phanmemkhaosat.R;
import com.example.cscom_pc.phanmemkhaosat.ThongTinChiTietTaiKhoan;

/**
 * Created by CSCOM-PC on 4/24/2017.
 */

public class EditTaiKhoan extends Fragment
{

    ImageButton btnDelete;
    ImageButton btnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_taikhoan,container,false);
        btnDelete = (ImageButton) view.findViewById(R.id.btnDelete);
        btnSave = (ImageButton) view.findViewById(R.id.btnSave);
        return  view;
    }
    public void Event()
    {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete
                showDialogExit();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save edit
                showDialogSave();
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Event();
        ((ThongTinChiTietTaiKhoan)getActivity()).SetEdit(true);
    }
    public void showDialogExit()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Thông báo!!");
        dialog.setMessage("Bạn chưa lưu thay đổi ?");
        dialog.setPositiveButton("Quay Lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Quay lại
                dialog.cancel();
            }
        });
        dialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                replaceFragment(DachSachFragment.getInstance().thongTinTaiKhoan);
            }
        });
        dialog.show();
    }
    public void showDialogSave()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Thông báo!!");
        dialog.setMessage("Bạn có muốn lưu thay đổi không ?");

        dialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Quay lại
                replaceFragment(DachSachFragment.getInstance().thongTinTaiKhoan);
            }
        });
        dialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                replaceFragment(DachSachFragment.getInstance().thongTinTaiKhoan);
            }
        });
        dialog.show();
    }
    public void replaceFragment(Fragment someFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frmThongTinTaiKhoan, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
