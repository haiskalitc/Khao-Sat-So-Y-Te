package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class ThongTinTaiKhoan extends Fragment
{
    ImageButton btnEdit;
    ImageButton btnBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chitiet_taikhoan,container,false);
        btnEdit = (ImageButton) view.findViewById(R.id.btnEdit);
        btnBack = (ImageButton) view.findViewById(R.id.btnBack);
        return  view;
    }
    public void Event()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                getActivity().finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //click edit
                replaceFragment(DachSachFragment.getInstance().editTaiKhoan);
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Event();
        ((ThongTinChiTietTaiKhoan)getActivity()).SetEdit(false);
    }
    public void replaceFragment(Fragment someFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frmThongTinTaiKhoan, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
