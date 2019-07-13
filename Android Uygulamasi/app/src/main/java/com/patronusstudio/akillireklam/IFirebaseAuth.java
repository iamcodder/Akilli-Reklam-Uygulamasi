package com.patronusstudio.akillireklam;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IFirebaseAuth {

    private FirebaseAuth mAuth;
    private IFirebase.Authenticate fireMesaj;

    public IFirebaseAuth(FirebaseAuth mAuth, IFirebase.Authenticate fireMesaj) {
        this.mAuth = mAuth;
        this.fireMesaj = fireMesaj;
    }



    public void kayitOl(String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        fireMesaj.mesajDondur("Kayıt Yapıldı",true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        fireMesaj.mesajDondur(e.getLocalizedMessage(),false);
                    }
                });
    }

    public void girisYap(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        fireMesaj.mesajDondur("Giriş Yapıldı",true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        fireMesaj.mesajDondur(e.getLocalizedMessage(),false);
                    }
                });
    }

    public void cikisYap(){
        mAuth.signOut();
    }

    public void sifreSifirla(String email){
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        fireMesaj.sifreDegistirme("Şifre değiştirme emaile gönderildi");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        fireMesaj.sifreDegistirme(e.getLocalizedMessage());
                    }
                });
    }

    public void sifreDegistir(String yeniSifre){

        if(mAuth.getCurrentUser()!=null){
            mAuth.getCurrentUser().updatePassword(yeniSifre)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            fireMesaj.sifreDegistirme("Şifre değiştirildi");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            fireMesaj.sifreDegistirme(e.getLocalizedMessage());
                        }
                    });
        }
    }

}
