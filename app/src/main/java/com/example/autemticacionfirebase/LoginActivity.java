// LoginActivity.java
package com.example.autemticacionfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "FIREBASE_AUTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Configurar el botón de login
        Button btnLoginMicrosoft = findViewById(R.id.btnLoginMicrosoft);
        btnLoginMicrosoft.setOnClickListener(v -> signInWithMicrosoft());
    }

    private void signInWithMicrosoft() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Conectando con Microsoft...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        OAuthProvider.Builder provider = OAuthProvider.newBuilder("microsoft.com");
        provider.addCustomParameter("prompt", "consent");
        provider.addCustomParameter("login_hint", "");

        Log.d(TAG, "Iniciando proceso de autenticación con Microsoft");

        mAuth.startActivityForSignInWithProvider(this, provider.build())
                .addOnCompleteListener(task -> {
                    progressDialog.dismiss();
                    if (!task.isSuccessful()) {
                        Exception e = task.getException();
                        String errorMessage = e != null ? e.getMessage() : "Error desconocido";
                        Log.e(TAG, "Error completo: " + errorMessage);
                        showError("Error específico: " + errorMessage);
                    } else {
                        Log.d(TAG, "Autenticación exitosa");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            intent.putExtra("name", user.getDisplayName());
                            intent.putExtra("email", user.getEmail());
                            startActivity(intent);
                            finish();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Log.e(TAG, "Fallo en el proceso de inicio: " + e.getMessage());
                    showError("Error en el proceso de inicio de sesión: " + e.getMessage());
                });
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}