package com.example.tourmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    int resultValue = -1;

    TextView title, details;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findAllViews();
        getIntentValue();
        setData();
    }

    private void setData() {
    }

    private void findAllViews() {
        title = findViewById(R.id.txt_title);
        details = findViewById(R.id.txt_details);
        imageView = findViewById(R.id.img_main);
    }

    private void getIntentValue() {
        Intent i = getIntent();
        resultValue = i.getExtras().getInt("place", -1);
        serData(resultValue);
    }

    private void serData(int resultValue) {

        if(resultValue==0){
            title.setText("Bandarban");
            details.setText(R.string.txt_bandarban);
            imageView.setImageResource(R.drawable.bandarban);
        }
        else if(resultValue==1){
            title.setText("Cox's Bazar");
            details.setText(R.string.txt_cox_bazar);
            imageView.setImageResource(R.drawable.coxsbazar);
        }
        else if(resultValue==2){
            title.setText("Khagrachari");
            details.setText(R.string.txt_khagrachari);
            imageView.setImageResource(R.drawable.khagrachari);
        }
        else if(resultValue==3){
            title.setText("Kuakata");
            details.setText(R.string.txt_kuakata);
            imageView.setImageResource(R.drawable.kuakata);
        }
        else if(resultValue==4){
            title.setText("Rangamati");
            details.setText(R.string.txt_rangamati);
            imageView.setImageResource(R.drawable.rangamati);
        }
        else if(resultValue==5){
            title.setText("Saint Martin");
            details.setText(R.string.txt_saint_martin);
            imageView.setImageResource(R.drawable.saint_martin);
        }
        else if(resultValue==6){
            title.setText("Sajek Valley");
            details.setText(R.string.txt_sajek);
            imageView.setImageResource(R.drawable.sajek);
        }
        else if(resultValue==7){
            title.setText("Sitakunda");
            details.setText(R.string.txt_sitakunda);
            imageView.setImageResource(R.drawable.sitakunda);
        }
        else if(resultValue==8){
            title.setText("Sundarban");
            details.setText(R.string.txt_sundarban);
            imageView.setImageResource(R.drawable.sundarban);
        }
        else if(resultValue==9){
            title.setText("Sylhet");
            details.setText(R.string.txt_sylhet);
            imageView.setImageResource(R.drawable.sylhet);
        }
        else if(resultValue==10){
            title.setText("Tanguar Haor");
            details.setText(R.string.txt_tanguar_haor);
            imageView.setImageResource(R.drawable.tanguar_hoar);
        }

    }
}