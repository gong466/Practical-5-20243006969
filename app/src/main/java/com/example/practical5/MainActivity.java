package com.example.practical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText municipalityEditText;
    private final TextProcessor textProcessor = new TextProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        municipalityEditText = findViewById(R.id.et_municipality);

        // The keyboard "Go" / "Done" action (imeOptions="actionGo") fires here.
        municipalityEditText.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_DONE) {
                handleUserInput();
                return true;
            }
            return false;
        });
    }

    private void handleUserInput() {
        String rawInput = municipalityEditText.getText().toString();

        // Single validation entry point shared with Municipality via TextProcessor.
        String municipality = textProcessor.processMunicipality(rawInput);

        if (municipality == null) {
            municipalityEditText.setError(getString(R.string.error_invalid_municipality));
            return;
        }

        municipalityEditText.setError(null);

        // Explicit intent targeting the Municipality activity.
        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(TextProcessor.EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }
}
