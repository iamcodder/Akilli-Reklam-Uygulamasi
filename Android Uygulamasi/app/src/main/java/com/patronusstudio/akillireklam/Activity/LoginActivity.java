package com.patronusstudio.akillireklam.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.patronusstudio.akillireklam.IFirebaseAuth;
import com.patronusstudio.akillireklam.IFirebase;
import com.patronusstudio.akillireklam.MapsActivity;
import com.patronusstudio.akillireklam.R;

public class LoginActivity extends AppCompatActivity implements IFirebase.Authenticate {

    private FirebaseAuth mAuth;
    private IFirebaseAuth fireObject;
    private Button btn_kayit,btn_giris,btn_sifre_unuttum;
    private EditText edx_email,edx_sifre;
    private String email,sifre;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        atama();
        tiklama();

    }

    void atama(){
        mAuth=FirebaseAuth.getInstance();

//        if(mAuth.getCurrentUser()!=null){
//            startActivity(new Intent(this,MapsActivity.class));
//            finish();
//        }

        fireObject=new IFirebaseAuth(mAuth,this);
        btn_kayit=findViewById(R.id.login_activity_register_button);
        btn_giris=findViewById(R.id.login_activity_login_button);
        btn_sifre_unuttum=findViewById(R.id.login_activity_sifremi_unuttum);
        edx_email=findViewById(R.id.login_email);
        edx_sifre=findViewById(R.id.login_password);

        edx_email.setText("me.iamcodder@gmail.com");
        edx_sifre.setText("1234321");

        lottieAnimationView=findViewById(R.id.login_animation);


    }

    void tiklama(){
        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=edx_email.getText().toString();
                sifre=edx_sifre.getText().toString();
                if(!email.equals("")){
                    fireObject.kayitOl(email,sifre);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Mail/şifre kontrol edin",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=edx_email.getText().toString();
                sifre=edx_sifre.getText().toString();
                if(!email.equals("") && !sifre.equals("")){
                    fireObject.girisYap(email,sifre);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Mail/şifre kontrol edin",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_sifre_unuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=edx_email.getText().toString();
                if(!email.equals("")){
                    fireObject.sifreSifirla(email);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Email giriniz",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void mesajDondur(String mesaj, boolean sonuc) {
        if(sonuc){
            startActivity(new Intent(this,MapsActivity.class));
            Toast.makeText(this,mesaj,Toast.LENGTH_SHORT).show();
            lottieAnimationView.setVisibility(View.GONE);
        }
        else{
            Toast.makeText(this,mesaj,Toast.LENGTH_SHORT).show();
            lottieAnimationView.setVisibility(View.GONE);
        }
    }

    @Override
    public void sifreDegistirme(String mesaj) {

    }
}
