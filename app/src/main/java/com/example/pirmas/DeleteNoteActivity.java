package com.example.pirmas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        final Spinner noteSelector = (Spinner) findViewById(R.id.noteSelector);
        final Button buttonDeleteNote = (Button) findViewById(R.id.deleteNoteButton);
        final List<Note> notes = (List<Note>) getIntent().getSerializableExtra("notes");
        List<String> selections = new ArrayList<>();
        for (Note note : notes)
            selections.add(note.Title);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteSelector.setAdapter(adapter);


        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("removedNote", noteSelector.getSelectedItemId());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
