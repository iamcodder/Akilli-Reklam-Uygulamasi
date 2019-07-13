package com.patronusstudio.akillireklam;

import android.net.Uri;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class IFirebaseDatabase {


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private DatabaseReference firebase_magazalar = firebaseDatabase.getReference().child(Sabitler.AKILLI_REKLAM)
            .child(Sabitler.MAGAZALAR);

    private DatabaseReference firebase_magaza_sayisi = firebaseDatabase.getReference().child(Sabitler.AKILLI_REKLAM)
            .child(Sabitler.MAGAZA_SAYISI);

    private ArrayList<magazaModel> tumMagazalarinListesi = new ArrayList<>();

    private IFirebase.Database interface_firebase;

    private int child_sayisi = 0;

    public IFirebaseDatabase(IFirebase.Database interface_firebase) {
        this.interface_firebase = interface_firebase;
        tumMagazalariAl();
    }

    void tumMagazalariAl() {

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                child_sayisi = (int) dataSnapshot.getChildrenCount();
                magazaSayisiniYaz(child_sayisi);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    magazaModel magaza = ds.getValue(magazaModel.class);

                    if (magaza != null) {
                        tumMagazalarinListesi.add(magaza);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        firebase_magazalar.addValueEventListener(postListener);

    }

    void IDyeGoreMagazalariCek(final List<magazaModel> magaza_listesi) {

        final ArrayList<magazaModel> kampanyali_magazalar = new ArrayList<>();
        kampanyali_magazalar.clear();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                child_sayisi = (int) dataSnapshot.getChildrenCount();
                magazaSayisiniYaz(child_sayisi);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    magazaModel magaza = ds.getValue(magazaModel.class);


                    if (magaza != null && magaza.getKampanyaIcerik() != null ) {

                        for(int i=0;i<magaza_listesi.size();i++){
                            if(magaza.getFirmaID().equals(magaza_listesi.get(i).getFirmaID())){
                                kampanyali_magazalar.add(magaza);
                            }
                        }
                    }
                }

                interface_firebase.cevredeki_magazalar(kampanyali_magazalar);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        firebase_magazalar.addValueEventListener(postListener);


    }

    void magazalariKarsilastir(List<magazaModel> cevredeki_magazalar) {

        int cevredeki_magaza_sayisi = cevredeki_magazalar.size();
        int tum_magaza_sayisi = tumMagazalarinListesi.size();


        for (int i = 0; i < cevredeki_magaza_sayisi; i++) {
            boolean bolundu_mu = false;

            for (int j = 0; j < tum_magaza_sayisi; j++) {

                String cevre_id = cevredeki_magazalar.get(i).getFirmaID();
                String tum_id = tumMagazalarinListesi.get(j).getFirmaID();


                if (cevre_id.equals(tum_id)) {
                    bolundu_mu = true;
                }

            }

            if (!bolundu_mu) {
                magazayiDbyeYaz(cevredeki_magazalar.get(i));
            }

        }


    }

    void magazayiDbyeYaz(magazaModel magaza) {
        child_sayisi++;
        firebase_magazalar.child(String.valueOf(child_sayisi)).child("FirmaID").setValue(magaza.getFirmaID());
        firebase_magazalar.child(String.valueOf(child_sayisi)).child("FirmaAdi").setValue(magaza.getFirmaAdi());
        firebase_magazalar.child(String.valueOf(child_sayisi)).child("Lokasyon").setValue(magaza.getLokasyon());
        firebase_magazalar.child(String.valueOf(child_sayisi)).child("KampanyaIcerik").setValue(magaza.getKampanyaIcerik());
        firebase_magazalar.child(String.valueOf(child_sayisi)).child("KampanyaSuresi").setValue(magaza.getKampanyaSuresi());

        magazaSayisiniYaz(child_sayisi);
    }

    void magazaSayisiniYaz(int magaza_sayisi) {

        String str_magaza=String.valueOf(magaza_sayisi);

        firebase_magaza_sayisi.child(Sabitler.MAGAZA_SAYISI).setValue(str_magaza);
    }

}
