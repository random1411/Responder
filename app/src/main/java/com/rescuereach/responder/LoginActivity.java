package com.rescuereach.responder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.button.MaterialButton;
import com.rescuereach.responder.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private AutoCompleteTextView stateDropdown, roleDropdown;
    private TextInputLayout emailLayout, passwordLayout, stateLayout, roleLayout;
    private CheckBox rememberMeCheckbox;
    private MaterialButton loginButton;

    private Context context;
    private String[] states, roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        context = this;

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        stateDropdown = findViewById(R.id.stateAutoCompleteTextView);
        roleDropdown = findViewById(R.id.roleAutoCompleteTextView);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        loginButton = findViewById(R.id.loginButton);

        emailLayout = findViewById(R.id.emailInputLayout);
        passwordLayout = findViewById(R.id.passwordInputLayout);
        stateLayout = findViewById(R.id.stateInputLayout);
        roleLayout = findViewById(R.id.roleInputLayout);

        // Load strings from resources
        states = getResources().getStringArray(R.array.indian_states);
        roles = getResources().getStringArray(R.array.user_roles);

        // Setup dropdown adapters
        setupDropdown(stateDropdown, states);
        setupDropdown(roleDropdown, roles);

        // Login button listener
        loginButton.setOnClickListener(view -> {
            if (validateInput()) {
                Toast.makeText(context, R.string.login_attempt, Toast.LENGTH_SHORT).show();
                // TODO: Replace this with actual login logic
                Toast.makeText(context, R.string.login_success, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, ResponderMainActivity.class));
                finish();
            }
        });
    }

    private void setupDropdown(AutoCompleteTextView dropdown, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);
    }

    private boolean validateInput() {
        boolean valid = true;

        String email = emailEditText.getText() != null ? emailEditText.getText().toString().trim() : "";
        String password = passwordEditText.getText() != null ? passwordEditText.getText().toString().trim() : "";
        String selectedState = stateDropdown.getText() != null ? stateDropdown.getText().toString().trim() : "";
        String selectedRole = roleDropdown.getText() != null ? roleDropdown.getText().toString().trim() : "";

        emailLayout.setError(null);
        passwordLayout.setError(null);
        stateLayout.setError(null);
        roleLayout.setError(null);

        if (email.isEmpty()) {
            emailLayout.setError(getString(R.string.error_required_field));
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError(getString(R.string.error_invalid_email));
            valid = false;
        }

        if (password.isEmpty()) {
            passwordLayout.setError(getString(R.string.error_required_field));
            valid = false;
        } else if (password.length() < 8) {
            passwordLayout.setError(getString(R.string.error_invalid_password));
            valid = false;
        }

        if (selectedState.isEmpty()) {
            stateLayout.setError(getString(R.string.error_select_state));
            valid = false;
        }

        if (selectedRole.isEmpty()) {
            roleLayout.setError(getString(R.string.error_select_role));
            valid = false;
        }

        return valid;
    }
}
