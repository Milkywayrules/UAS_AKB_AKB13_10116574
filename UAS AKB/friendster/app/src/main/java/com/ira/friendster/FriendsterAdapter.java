package com.ira.friendster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
//    NIM:10116574
//    Nama:Ira Yuti
//    Kelas:If-13
//    Tanggal Pengerjaan:13/08/2019
public class FriendsterAdapter extends RecyclerView.Adapter<FriendsterAdapter.MyViewHolder> {
    private List<FriendsterModel> friendsterModel;
    Context context;
   ListTemanFragment  LTTeman;

    public FriendsterAdapter(ListTemanFragment v,Context context, List<FriendsterModel> friendsterModel){
        this.LTTeman = v;
        this.context = context;
        this.friendsterModel = friendsterModel;
    }
    public FriendsterAdapter(Context context, List<FriendsterModel> friendsterModel){
        this.context = context;
        this.friendsterModel = friendsterModel;
    }

    @Override
    public FriendsterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teman, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(FriendsterAdapter.MyViewHolder holder, int position) {
        final FriendsterModel model = friendsterModel.get(position);
        holder.nim.setText(model.getNim().toString());
        holder.nama.setText(model.getNama());
        holder.kelas.setText(model.getKelas());
        holder.email.setText(model.getEmail());
        holder.noHp.setText(model.getEmail());
        holder.instagram.setText(model.getInstagram());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LTTeman.update(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendsterModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nim, nama,kelas,email,noHp,instagram;

        public MyViewHolder(View itemView){
            super(itemView);
            nim = itemView.findViewById(R.id.tvNim);
            nama = itemView.findViewById(R.id.tvNama);
            kelas = itemView.findViewById(R.id.tvKelas);
            email = itemView.findViewById(R.id.tvEmail);
            noHp = itemView.findViewById(R.id.tvHp);
            instagram = itemView.findViewById(R.id.tvInstagram);
        }
    }
}
