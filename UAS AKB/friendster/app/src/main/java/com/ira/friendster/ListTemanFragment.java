package com.ira.friendster;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * A simple {@link Fragment} subclass.
 */
//    NIM:10116574
//    Nama:Ira Yuti
//    Kelas:If-13
//    Tanggal Pengerjaan:14/08/2019
public class ListTemanFragment extends Fragment {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
   FriendsterAdapter friendsterAdapter;
    List<FriendsterModel> friendsterModels;
    View view;

    public ListTemanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_teman, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        friendsterModels = new ArrayList<>();

        friendsterModels = realmHelper.getAllTeman();
        show();
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahTemanFragment tambahTemanFragment = new TambahTemanFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, tambahTemanFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    public void show(){
        friendsterAdapter= new FriendsterAdapter(this,getActivity(), friendsterModels);
        recyclerView.setAdapter(friendsterAdapter);
    }

    public void  update(FriendsterModel friendsterModel){
        Bundle bundle = new Bundle();
        bundle.putString("id",String.valueOf(friendsterModel.getId()));
        bundle.putString("nim",String.valueOf(friendsterModel.getNim()));
        bundle.putString("nama",String.valueOf(friendsterModel.getNama()));
        bundle.putString("kelas",String.valueOf(friendsterModel.getKelas()));
        bundle.putString("email",String.valueOf(friendsterModel.getEmail()));
        bundle.putString("nohp",String.valueOf(friendsterModel.getNoHp()));
        bundle.putString("ig",String.valueOf(friendsterModel.getInstagram()));
        DetailTemanFragment detailTemanFragment = new DetailTemanFragment();
        detailTemanFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, detailTemanFragment);
        fragmentTransaction.commit();
    }

}
