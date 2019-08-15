package com.ira.friendster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * A simple {@link Fragment} subclass.
 */
//    NIM:10116574
//    Nama:Ira Yuti
//    Kelas:If-13
//    Tanggal Pengerjaan:14/08/2019
public class DetailTemanFragment extends Fragment implements  View.OnClickListener {
 View view;
    Button btnUbah, btnHapus,btnKembali;
    EditText nim, nama,kelas,email,noHp, instagram;
    String sNama,sKelas,sEmail,sNHp, sInstagram;;
    Integer sNim,sId;
    Realm realm;
    RealmHelper realmHelper;
    FriendsterModel friendsterModel;
    public DetailTemanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detail_teman, container, false);
        // Set up
        Realm.init(getActivity());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
     realmHelper = new RealmHelper(realm);
//
        btnUbah = view.findViewById(R.id.btnUbahD);
        btnHapus = view.findViewById(R.id.btnHapusD);
        btnKembali = view.findViewById(R.id.btnKembaliD);
        nim = view.findViewById(R.id.nimD);
        nama = view.findViewById(R.id.namaD);
        kelas = view.findViewById(R.id.kelasD);
        email = view.findViewById(R.id.emailD);
        noHp = view.findViewById(R.id.noHpD);
        instagram = view.findViewById(R.id.instgaramD);
//
        sId = Integer.parseInt(getArguments().getString("id"));
        sNim = Integer.parseInt(getArguments().getString("nim"));
        sNama = getArguments().getString("nama");
        sKelas = getArguments().getString("kelas");
        sEmail = getArguments().getString("email");
        sNHp = getArguments().getString("nohp");
        sInstagram = getArguments().getString("ig");

        nim.setText(String.valueOf(sNim));
        nama.setText(sNama);
        kelas.setText(sKelas);
        email.setText(sEmail);
        noHp.setText(sNHp);
        instagram.setText(sInstagram);
        btnKembali.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
        btnUbah.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUbahD:
                sNim = Integer.parseInt(nim.getText().toString());
                sNama = nama.getText().toString().trim();
                sKelas = kelas.getText().toString().trim();
                sEmail = email.getText().toString().trim();
                sNHp    = noHp.getText().toString().trim();
                sInstagram = instagram.getText().toString().trim();
                if (!sNim.equals("") && !sNama.isEmpty()) {
                    friendsterModel = new FriendsterModel();
                    friendsterModel.setNim(sNim);
                    friendsterModel.setNama(sNama);
                    friendsterModel.setKelas(sKelas);
                    friendsterModel.setEmail(sEmail);
                    friendsterModel.setNoHp(sNHp);
                    friendsterModel.setInstagram(sInstagram);
                    realmHelper.update(sId,friendsterModel);

                    Toast.makeText(getActivity(), "Berhasil Diubah!", Toast.LENGTH_SHORT).show();
                } else {
            Toast.makeText(getActivity(), "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
        }
                break;
            case R.id.btnHapusD:
                realmHelper.delete(sId);
              Toast.makeText(getActivity(), "Delete Success"+String.valueOf(sId), Toast.LENGTH_SHORT).show();
                ListTemanFragment listTemanFragment = new ListTemanFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, listTemanFragment);
                fragmentTransaction.commit();
                break;
            case R.id.btnKembaliD:
                ListTemanFragment listTemanFragment1 = new ListTemanFragment();
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.content_frame, listTemanFragment1);
                fragmentTransaction1.commit();
                break;
        }
    }
}
