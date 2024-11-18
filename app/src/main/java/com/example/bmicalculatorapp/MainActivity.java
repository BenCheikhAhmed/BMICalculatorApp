package com.example.bmicalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editName, editWeight, editHeight;
    private RadioGroup radioGroupGender;
    private Button buttonCalculate, buttonReset;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI elements
        editName = findViewById(R.id.editName);
        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonReset = findViewById(R.id.buttonReset);
        textResult = findViewById(R.id.textResult);

        // Set Calculate button click listener
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        // Set Reset button click listener
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    private void calculateBMI() {
        String name = editName.getText().toString().trim();
        String weightStr = editWeight.getText().toString().trim();
        String heightStr = editHeight.getText().toString().trim();

        if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty() || radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        if (height <= 0 || weight <= 0) {
            Toast.makeText(this, "Invalid weight or height!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate BMI
        double bmi = weight / (height * height);

        // Determine gender
        String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        // Generate result message
        String message = name + ", your BMI is " + String.format("%.2f", bmi) + ".\n";
        if (bmi < 18.5) {
            message += "You are underweight.";
        } else if (bmi < 24.9) {
            message += "You have a normal weight.";
        } else if (bmi < 29.9) {
            message += "You are overweight.";
        } else {
            message += "You are obese.";
        }

        message += "\nGender: " + gender;

        // Display the result
        textResult.setText(message);
    }

    private void resetFields() {
        // Clear all input fields
        editName.setText("");
        editWeight.setText("");
        editHeight.setText("");
        radioGroupGender.clearCheck();

        // Reset result text
        textResult.setText("Your BMI result will appear here.");
    }
}
