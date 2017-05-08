package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cscom_pc.phanmemkhaosat.DachSachFragment;
import com.example.cscom_pc.phanmemkhaosat.R;

/**
 * Created by CSCOM-PC on 4/24/2017.
 */

public class SearchDanhSach extends Fragment
{
    public android.support.v7.widget.SearchView txtTiemKiem;
    public Button btnCancel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_search,container,false);
        txtTiemKiem = (android.support.v7.widget.SearchView) view.findViewById(R.id.txtTimKiem);
        btnCancel = (Button) view.findViewById(R.id.txvCancel);
        return  view;
    }
    public  void Event()
    {
        //Hủy tìm kiếm
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(DachSachFragment.getInstance().thongTinDanhSach);
            }
        });

        //Tìm kiếm
    }
    public void replaceFragment(Fragment someFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.tittle, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Event();
    }
}
