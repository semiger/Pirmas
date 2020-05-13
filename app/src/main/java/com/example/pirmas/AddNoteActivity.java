package com.example.pirmas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final TextView noteLabelText = (TextView) findViewById(R.id.noteLabelText);
        final TextView noteContentText = (TextView) findViewById(R.id.noteContentText);
        final Button buttonAddNote = (Button) findViewById(R.id.addNoteButton);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title = noteLabelText.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(getBaseContext(),getString(R.string.titleEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                String content = noteContentText.getText().toString();
                if (content.isEmpty()) {
                    Toast.makeText(getBaseContext(),getString(R.string.contentEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newNote", new Note(title, content));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
