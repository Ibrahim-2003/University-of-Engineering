package com.example.uofengineering_physicsmechanicscalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INSTANTIATE BUTTONS
        ImageView theme_changer = (ImageView) findViewById(R.id.theme_changer);
        ImageView settings = (ImageView) findViewById(R.id.settings);

        // CHANGE THE THEME COLOR ON BUTTON PRESS
        theme_changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nightModeFlags =
                        v.getContext().getResources().getConfiguration().uiMode &
                                Configuration.UI_MODE_NIGHT_MASK;
                switch (nightModeFlags) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;

                    case Configuration.UI_MODE_NIGHT_NO:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;

                    default:
                        break;

                }
            }
        });

        // OPEN SETTINGS
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, settings.class));
            }
        });

        CardView units, oneDMotion, vectors, twoDMotion, uniform_circular_motion,
                laws_of_motion, circular_motion, resistive_forces, work, kinetic_energy,
                friction, power, potential_energy, conservation_of_energy, linear_momentum,
                impulse, oneDCollisions, twoDCollisions, rocket_propulsion, rotational_motion,
                rotational_energy, moment_of_inertia, torque, angular_momentum, center_of_mass,
                elasticity, gravitation, oscillatory_motion;

        units = (CardView) findViewById(R.id.units);
        units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, unit_calculator.class));
            }
        });



    }
}