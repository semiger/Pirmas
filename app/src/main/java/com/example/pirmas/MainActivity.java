package com.example.pirmas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultText = (TextView) findViewById(R.id.resultText);
        final Button buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        final EditText inputText = (EditText) findViewById(R.id.inputText);
        final Spinner typeSelection = (Spinner) findViewById(R.id.typeSelection);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultText.setText("");
                String text = inputText.getText().toString();
                if (text.isEmpty()) {
                    Toast.makeText(getBaseContext(),getString(R.string.inputEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(typeSelection.getSelectedItem().equals(getResources().getStringArray(R.array.calculationSelection)[0]))
                    resultText.setText(String.valueOf(StringCountCalculator.GetWordCount(text)));
                else
                    resultText.setText(String.valueOf(StringCountCalculator.GetSymbolCount(text)));
            }
        });
    }
}
