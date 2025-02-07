package com.example.autemticacionfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView txtWelcomeUser = findViewById(R.id.txtWelcomeWelcomeUser);
        TextView txtUserEmail = findViewById(R.id.txtUserEmail);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Obtener los datos del usuario
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        txtWelcomeUser.setText("Bienvenido, " + (name != null ? name : "Usuario") + "!");
        txtUserEmail.setText("Correo: " + email);

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish();
        });
    }
}
