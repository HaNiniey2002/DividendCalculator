package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button btnCalculate;
    EditText ifAmount, adRate, noMonths;
    TextView result, monthlyDividend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set title of the toolbar
        getSupportActionBar().setTitle("Dividend Calculator");

        // Initialize the EditText, Button, and TextView
        ifAmount = findViewById(R.id.ifAmount);
        adRate = findViewById(R.id.adRate);
        noMonths = findViewById(R.id.noMonths);
        btnCalculate = findViewById(R.id.btncalculate);
        result = findViewById(R.id.result);
        monthlyDividend = findViewById(R.id.monthlyDividend);

        // Set a click listener for the "Calculate" button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values from the EditText fields
                String ifAmountStr = ifAmount.getText().toString();
                String adRateStr = adRate.getText().toString();
                String noMonthsStr = noMonths.getText().toString();

                // Check if any of the fields are empty
                if (!ifAmountStr.isEmpty() && !adRateStr.isEmpty() && !noMonthsStr.isEmpty()) {

                    // Convert the input Strings to numeric values
                    double ifAmount = Double.parseDouble(ifAmountStr);
                    int noMonths = Integer.parseInt(noMonthsStr);

                    //Format the annual dividend rate to 2 decimal places
                    double adRate = Double.parseDouble(adRateStr);
                    adRate = Math.round(adRate * 100.0) / 100.0;

                    //Limit the number of months to 12
                    if (noMonths > 12) {
                        monthlyDividend.setText("");
                        result.setText("Number of months cannot exceed 12.");
                        return;
                    }

                    //Limit the annual dividend rate below 100
                    if (adRate > 100) {
                        monthlyDividend.setText("");
                        result.setText("Annual dividend rate cannot exceed 100%.");
                        return;
                    }

                    // Perform the calculation
                    double monthlyDividendValue = (adRate / 100) /12 * ifAmount;
                    double totalDividend = monthlyDividendValue * noMonths;

                    //Display monthly dividend
                    monthlyDividend.setText("Monthly:\nRM " + String.format("%.2f", monthlyDividendValue));

                    // Display the result
                    result.setText("Year-end total:\nRM " + String.format("%.2f", totalDividend));
                } else {
                    // Display an error message if any field is empty
                    result.setText("Please fill in all fields.");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu resource file into the toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handle item clicks in the toobar
        int id = item.getItemId();

        if(id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
