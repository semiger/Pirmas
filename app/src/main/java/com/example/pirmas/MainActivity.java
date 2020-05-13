package com.example.pirmas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_NOTES_KEY = "Notes";
    private static final int ADD_NOTE_ACTIVITY = 1;
    private static final int DELETE_NOTE_ACTIVITY = 2;
    private static SharedPreferences _sharedPreferences;
    private static List<Note> _notes;
    private static ArrayAdapter<String> _arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultText = (TextView) findViewById(R.id.resultText);
        final Button buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        final EditText inputText = (EditText) findViewById(R.id.inputText);
        final Spinner typeSelection = (Spinner) findViewById(R.id.typeSelection);
        final Button buttonAddNote = (Button) findViewById(R.id.addNoteButton);
        final Button buttonDeleteNote = (Button) findViewById(R.id.deleteNoteButton);
        final ListView notesListView = (ListView) findViewById(R.id.notesList);

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

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_ACTIVITY);
            }
        });

        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteNoteActivity.class);
                intent.putExtra("notes", (Serializable) _notes);
                startActivityForResult(intent, DELETE_NOTE_ACTIVITY);
            }
        });

        _sharedPreferences = getPreferences(MODE_PRIVATE);
        LoadSettings();
        _arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, _notes);
        notesListView.setAdapter(_arrayAdapter);
        _arrayAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_ACTIVITY && resultCode == RESULT_OK && data != null) {
            _notes.add((Note)data.getSerializableExtra("newNote"));
            _arrayAdapter.notifyDataSetChanged();
            SaveSettings();
        }
        else if (requestCode == DELETE_NOTE_ACTIVITY && resultCode == RESULT_OK && data != null) {
            _notes.remove((int)data.getLongExtra("removedNote", -1));
            _arrayAdapter.notifyDataSetChanged();
            SaveSettings();
        }
    }

    private static void LoadSettings()
    {
        String notesString = _sharedPreferences.getString(PREFERENCES_NOTES_KEY, null);

        Gson gson = new Gson();
        if(notesString == null)
            _notes = new ArrayList<>();
        else
            _notes = new ArrayList(Arrays.asList(gson.fromJson(notesString, Note[].class)));
    }

    private static void SaveSettings()
    {
        Gson gson = new Gson();
        String notesJson = gson.toJson(_notes);
        SharedPreferences.Editor preferencesEditor = _sharedPreferences.edit();
        preferencesEditor.putString(PREFERENCES_NOTES_KEY, notesJson);
        preferencesEditor.commit();
    }
}
