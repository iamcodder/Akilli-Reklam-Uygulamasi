package com.patronusstudio.akillireklam.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.patronusstudio.akillireklam.IFirebase;
import com.patronusstudio.akillireklam.IFirebaseAuth;
import com.patronusstudio.akillireklam.IFirebaseDatabase;
import com.patronusstudio.akillireklam.R;
import com.patronusstudio.akillireklam.magazaModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements IFirebase.Database,IFirebase.Authenticate {

    private CircleImageView circleImageView;
    private TextView txt_email;
    private FirebaseAuth mAuth;
    private IFirebaseDatabase firebaseDatabase;
    private IFirebaseAuth mAuthObject;
    private EditText edx_sifre, edx_sifre_tekrar;
    private Button btn_sifre_degistir,btn_cikis_yap;
    private String sifre,sifre2;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        atama();
        tiklama();
    }

    public void atama(){
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=new IFirebaseDatabase(this);
        circleImageView=findViewById(R.id.profil_resmi);
        txt_email=findViewById(R.id.profil_email);
        mAuthObject=new IFirebaseAuth(mAuth,this);
        edx_sifre =findViewById(R.id.profile_sifre);
        edx_sifre_tekrar =findViewById(R.id.profile_sifre_tekrar);
        btn_sifre_degistir=findViewById(R.id.profil_sifre_degistir_butonu);
        btn_cikis_yap=findViewById(R.id.profil_cikis_yap);
        lottieAnimationView=findViewById(R.id.profile_animation);
    }

    public void tiklama(){

        btn_sifre_degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sifre=edx_sifre.getText().toString();
                sifre2=edx_sifre_tekrar.getText().toString();

                if(sifre.equals(sifre2)){
                    mAuthObject.sifreDegistir(sifre);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(),"İki şifre birbirinden farklı", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cikis_yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthObject.cikisYap();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void sifreDegistirme(String mesaj) {
        Toast.makeText(this,mesaj,Toast.LENGTH_SHORT).show();
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void cevredeki_magazalar(List<magazaModel> cevredeki_magazalar) {

    }

    @Override
    public void mesajDondur(String mesaj, boolean sonuc) {

    }
}
