package com.example.tourmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourmate.ml.TourPlan;
import com.google.android.material.button.MaterialButton;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

public class welcome extends AppCompatActivity {
    EditText memberEditTxt, budgetEditTxt;
    Spinner month, transport;
    MaterialButton findTourBtn;
    TextView result;
    Float monthValue = 0.0f;
    Float transportValue = 0.0f;
    Float costValue = 0.0f;
    Float memberValue = 0.0f;
    int resultValue = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findAllViews();
        setSpinners();

        findTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()) {
                    int max = 10;
                    int min = 0;
                    resultValue = new Random().nextInt(max - min + 1) + min;
                    setResultText(resultValue);
                }

              /*  Toast.makeText(welcome.this, "Tour suggestions are on the way!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(welcome.this, result_recview.class);
                startActivity(intent);*/
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcome.this, DetailsActivity.class);
                intent.putExtra("place",resultValue);
                startActivity(intent);
            }
        });

    }

    private void setResultText(int resultValue) {
        if(resultValue==0){
            result.setText("Bandarban");
        }
        else if(resultValue==1){
            result.setText("Cox's Bazar");
        }
        else if(resultValue==2){
            result.setText("Khagrachari");
        }
        else if(resultValue==3){
            result.setText("Kuakata");
        }
        else if(resultValue==4){
            result.setText("Rangamati");
        }
        else if(resultValue==5){
            result.setText("Saint Martin");
        }
        else if(resultValue==6){
            result.setText("Sajek Valley");
        }
        else if(resultValue==7){
            result.setText("Sitakunda");
        }
        else if(resultValue==8){
            result.setText("Sundarban");
        }
        else if(resultValue==9){
            result.setText("Sylhet");
        }
        else if(resultValue==10){
            result.setText("Tanguar Haor");
        }
    }

    private void setSpinners() {

        // set month
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ArrayAdapter monthAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, monthNames);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(monthAdapter);
        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedMonth = adapterView.getItemAtPosition(position).toString();
                monthValue = (float) getMonthIndex(selectedMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // set Transport
        String[] transportNames = {"Bus", "Train", "Car", "Launch", "Biman"};
        ArrayAdapter transportAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, transportNames);
        transportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transport.setAdapter(transportAdapter);
        transport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedTransport = adapterView.getItemAtPosition(position).toString();
                transportValue = (float) getTransportIndex(selectedTransport);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private int getMonthIndex(String monthName) {
        switch (monthName) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        return 0;
    }

    private int getTransportIndex(String transportName) {
        switch (transportName) {
            case "Bus":
                return 1;
            case "Train":
                return 2;
            case "Car":
                return 3;
            case "Launch":
                return 4;
            case "Biman":
                return 5;
        }
        return 0;
    }

    private Boolean isValid() {

        if (monthValue == 0.0f) {
            Toast.makeText(welcome.this, "Select a month", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (transportValue == 0.0f) {
            Toast.makeText(welcome.this, "Select a transport", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (memberEditTxt.getText().toString().equals("")) {
            Toast.makeText(welcome.this, "Enter tour member", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (budgetEditTxt.getText().toString().equals("")) {
            Toast.makeText(welcome.this, "Enter tour budget", Toast.LENGTH_SHORT).show();
            return false;
        }
        memberValue= Float.valueOf(memberEditTxt.getText().toString());
        costValue= Float.valueOf(budgetEditTxt.getText().toString());
        return true;
    }

    private void findAllViews() {
        memberEditTxt = findViewById(R.id.edt_member);
        budgetEditTxt = findViewById(R.id.edt_budget);
        month = findViewById(R.id.month_spiner);
        transport = findViewById(R.id.transport_spiner);
        findTourBtn = findViewById(R.id.findTourBtn);
        result = findViewById(R.id.txt_result);
    }

    private void predictTour() {
        try {

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 4);

            byteBuffer.putFloat(monthValue);
            byteBuffer.putFloat(transportValue);
            byteBuffer.putFloat(memberValue);
            byteBuffer.putFloat(costValue);

            TourPlan model = TourPlan.newInstance(this);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 4}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            TourPlan.Outputs outputs = model.process(inputFeature0);
            float[] outputFeature0 = outputs.getOutputFeature0AsTensorBuffer().getFloatArray();
            String resultValue = String.valueOf(outputFeature0[0]);
            result.setText(resultValue);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
}