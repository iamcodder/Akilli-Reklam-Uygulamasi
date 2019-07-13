package com.patronusstudio.akillireklam.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patronusstudio.akillireklam.R;
import com.patronusstudio.akillireklam.kampanyaAdapter;
import com.patronusstudio.akillireklam.magazaModel;

import java.util.List;

public class KampanyaActivity extends AppCompatActivity {

    private Intent intent;
    private List<magazaModel> kampanyali_magazalar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampanya);

        intent=getIntent();

        if(intent.getExtras()!=null){
            kampanyali_magazalar=intent.getParcelableArrayListExtra("kampanyalimagazalar");
        }

        if(kampanyali_magazalar!=null){
            RecyclerView recyclerView=findViewById(R.id.recyclerView);
            kampanyaAdapter adapter=new kampanyaAdapter(this,kampanyali_magazalar);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(adapter);
        }

    }
}
