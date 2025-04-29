package com.example.pharmacie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initToolbar();
        setupUI();
    }

    private void initToolbar() {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Animation corrigée sans .then()
        toolbar.setTranslationY(-toolbar.getHeight());
        toolbar.setAlpha(0f);

        toolbar.animate()
                .translationY(0)
                .alpha(1)
                .setDuration(500)
                .setStartDelay(100) // Petit délai pour l'effet
                .start();
    }

    private void setupUI() {
        // Gestion déconnexion
        findViewById(R.id.btn_logout).setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content),
                            "Déconnexion en cours...",
                            Snackbar.LENGTH_LONG)
                    .setAction("ANNULER", view -> { /* Annulation */ })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent))
                    .show();

            // Déconnexion après délai
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, LoginActivity.class));
                finishAffinity();
            }, 1500);
        });

        // Configuration des cartes
        setupCard(R.id.card_dashboard, dashboard.class); // Classe corrigée (majuscule)
        setupCard(R.id.card_profile, ProfileActivity.class);
        setupCard(R.id.card_settings, SettingsActivity.class);
    }

    private void setupCard(int cardId, Class<?> destination) {
        MaterialCardView card = findViewById(cardId);
        card.setOnClickListener(v -> {
            card.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        card.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();
                        startActivity(new Intent(this, destination));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    })
                    .start();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}