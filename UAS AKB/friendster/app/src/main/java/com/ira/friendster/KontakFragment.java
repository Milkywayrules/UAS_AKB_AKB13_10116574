package com.ira.friendster;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class KontakFragment extends Fragment {
    //    NIM:10116574
    //    Nama:Ira Yuti
    //    Kelas:If-13
    //    Tanggal Pengerjaan:14/08/2019

    public KontakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kontak, container, false);
        Button btnPangil = view.findViewById(R.id.btnHubungi);
        btnPangil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:083126358162"));
                startActivity(callIntent);
            }
        });
        return  view;
    }

}
