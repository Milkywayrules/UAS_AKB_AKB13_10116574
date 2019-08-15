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
//    Tanggal Pengerjaan:13/08/2019
public class TambahTemanFragment extends Fragment implements  View.OnClickListener {

    View view;
    Button btnSimpan, btnTampil;
    EditText nim, nama,kelas,email,noHp, instagram;
    String sNama,sKelas,sEmail,sNHp, sInstagram;;
    Integer sNim;
    Realm realm;
    RealmHelper realmHelper;
    FriendsterModel friendsterModel;

    public TambahTemanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tambah_teman, container, false);
        //Inisialisasi
        btnSimpan = view.findViewById(R.id.btnSimpan);
        btnTampil = view.findViewById(R.id.btnTampil);
        nim = view.findViewById(R.id.nim);
        nama = view.findViewById(R.id.nama);
        kelas = view.findViewById(R.id.kelas);
        email = view.findViewById(R.id.email);
        noHp = view.findViewById(R.id.noHp);
        instagram = view.findViewById(R.id.instgaram);

        //Set up Realm
        Realm.init(getActivity());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            sNim = Integer.parseInt(nim.getText().toString());
            sNama = nama.getText().toString().trim();
            sKelas = kelas.getText().toString().trim();
            sEmail = email.getText().toString().trim();
            sNHp    = noHp.getText().toString().trim();
            sInstagram = instagram.getText().toString().trim();
            if (!sNim.equals("") && !sNama.isEmpty()){
                friendsterModel = new FriendsterModel();
                friendsterModel.setNim(sNim);
                friendsterModel.setNama(sNama);
                friendsterModel.setKelas(sKelas);
                friendsterModel.setEmail(sEmail);
                friendsterModel.setNoHp(sNHp);;
                friendsterModel.setInstagram(sInstagram);
                realmHelper = new RealmHelper(realm);
                realmHelper.save(friendsterModel);

                Toast.makeText(getActivity(), "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                nim.setText("");
                nama.setText("");
                kelas.setText("");
                email.setText("");
                noHp.setText("");
                instagram.setText("");
            }else {
                Toast.makeText(getActivity(), "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnTampil){
            ListTemanFragment listTemanFragment = new ListTemanFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, listTemanFragment);
            fragmentTransaction.commit();
        }
    }
}
