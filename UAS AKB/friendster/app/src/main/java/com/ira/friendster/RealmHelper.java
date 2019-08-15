package com.ira.friendster;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
//    NIM:10116574
//    Nama:Ira Yuti
//    Kelas:If-13
//    Tanggal Pengerjaan:13/08/2019
public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final FriendsterModel friendsterModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(FriendsterModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    friendsterModel.setId(nextId);
                    FriendsterModel model = realm.copyToRealm(friendsterModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<FriendsterModel> getAllTeman(){
        RealmResults<FriendsterModel> results = realm.where(FriendsterModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void update(final Integer id, final FriendsterModel friendsterModel){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FriendsterModel model = realm.where(FriendsterModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNim(friendsterModel.getNim());
                model.setNama(friendsterModel.getNama());
                model.setKelas(friendsterModel.getKelas());
                model.setEmail(friendsterModel.getEmail());
                model.setNoHp(friendsterModel.getNoHp());
                model.setInstagram(friendsterModel.getInstagram());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<FriendsterModel> model = realm.where(FriendsterModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
