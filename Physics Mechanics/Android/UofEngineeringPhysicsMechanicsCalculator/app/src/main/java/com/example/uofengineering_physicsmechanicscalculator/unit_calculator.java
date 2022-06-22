package com.example.uofengineering_physicsmechanicscalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class unit_calculator extends AppCompatActivity {

    CardView length_btn, mass_btn, time_btn, speed_btn, energy_btn, force_btn, pressure_btn, area_btn, volume_btn, return_btn;

    ImageView length_btn_icon, mass_btn_icon, time_btn_icon, speed_btn_icon, energy_btn_icon, force_btn_icon,
                pressure_btn_icon, area_btn_icon, volume_btn_icon;

    TextView length_btn_text, mass_btn_text, speed_btn_text, energy_btn_text, force_btn_text, pressure_btn_text,
            area_btn_text, volume_btn_text, time_btn_text;

    @SuppressLint({"DefaultLocale", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_calculator);

        length_btn = (CardView) findViewById(R.id.units_length);
        mass_btn = (CardView) findViewById(R.id.units_mass);
        time_btn = (CardView) findViewById(R.id.units_time);
        speed_btn = (CardView) findViewById(R.id.units_speed);
        energy_btn = (CardView) findViewById(R.id.units_energy);
        force_btn = (CardView) findViewById(R.id.units_force);
        pressure_btn = (CardView) findViewById(R.id.units_pressure);
        area_btn = (CardView) findViewById(R.id.units_area);
        volume_btn = (CardView) findViewById(R.id.units_volume);
        return_btn = (CardView) findViewById(R.id.units_return_btn);

        length_btn_icon = (ImageView) findViewById(R.id.length_btn_icon);
        mass_btn_icon = (ImageView) findViewById(R.id.mass_btn_icon);
        time_btn_icon = (ImageView) findViewById(R.id.time_btn_icon);
        speed_btn_icon = (ImageView) findViewById(R.id.speed_btn_icon);
        energy_btn_icon = (ImageView) findViewById(R.id.energy_btn_icon);
        force_btn_icon = (ImageView) findViewById(R.id.force_btn_icon);
        pressure_btn_icon = (ImageView) findViewById(R.id.pressure_btn_icon);
        area_btn_icon = (ImageView) findViewById(R.id.area_btn_icon);
        volume_btn_icon = (ImageView) findViewById(R.id.volume_btn_icon);

        length_btn_text = (TextView) findViewById(R.id.length_btn_text);
        mass_btn_text = (TextView) findViewById(R.id.mass_btn_text);
        time_btn_text = (TextView) findViewById(R.id.time_btn_text);
        speed_btn_text = (TextView) findViewById(R.id.speed_btn_text);
        energy_btn_text = (TextView) findViewById(R.id.energy_btn_text);
        force_btn_text = (TextView) findViewById(R.id.force_btn_text);
        pressure_btn_text = (TextView) findViewById(R.id.pressure_btn_text);
        area_btn_text = (TextView) findViewById(R.id.area_btn_text);
        volume_btn_text = (TextView) findViewById(R.id.volume_btn_text);


        CardView[] card_list = {length_btn, mass_btn, time_btn, speed_btn, energy_btn,
                                force_btn, pressure_btn, area_btn, volume_btn};
        ImageView[] icon_list = {length_btn_icon, mass_btn_icon, time_btn_icon, speed_btn_icon, energy_btn_icon, force_btn_icon,
                pressure_btn_icon, area_btn_icon, volume_btn_icon};
        TextView[] text_list = {length_btn_text, mass_btn_text, speed_btn_text, energy_btn_text, force_btn_text, pressure_btn_text,
                area_btn_text, volume_btn_text, time_btn_text};


        return_btn.setOnClickListener(v -> startActivity(new Intent(unit_calculator.this, MainActivity.class)));

        for(CardView card : card_list){
            card.setOnClickListener(v -> {


                int nightModeFlags =
                        v.getContext().getResources().getConfiguration().uiMode &
                                Configuration.UI_MODE_NIGHT_MASK;
                switch (nightModeFlags) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        card.setCardBackgroundColor(ContextCompat.getColor(getApplication(), R.color.card_color));

                        for(CardView card_check : card_list){
                            if(!String.format("%d", card_check.getId()).equals(String.format("%d", v.getId()))){
                                card_check.setCardBackgroundColor(ContextCompat.getColor(getApplication(), R.color.primary_color));
                            }
                        }

                        break;

                    case Configuration.UI_MODE_NIGHT_NO:
                        card.setCardBackgroundColor(ContextCompat.getColor(getApplication(), R.color.primary_blue));

                        for(CardView card_check : card_list){
                            if(!String.format("%d", card_check.getId()).equals(String.format("%d", v.getId()))){
                                card_check.setCardBackgroundColor(ContextCompat.getColor(getApplication(), R.color.curiosity_white));
                            }
                        }
                        break;

                    default:
                        break;

                }

                int card_id = card.getId();

                switch(card_id){
                    case R.id.units_mass:
                        recolorIconText(mass_btn_icon, icon_list, mass_btn_text, text_list, v);
                        replaceFragment(new units_mass_fragment());
                        break;
                    case R.id.units_time:
                        recolorIconText(time_btn_icon, icon_list, time_btn_text, text_list, v);
                        replaceFragment(new units_time_fragment());
                        break;
                    case R.id.units_speed:
                        recolorIconText(speed_btn_icon, icon_list, speed_btn_text, text_list, v);
                        replaceFragment(new units_speed_fragment());
                        break;
                    case R.id.units_force:
                        recolorIconText(force_btn_icon, icon_list, force_btn_text, text_list, v);
                        replaceFragment(new units_force_fragment());
                        break;
                    case R.id.units_energy:
                        recolorIconText(energy_btn_icon, icon_list, energy_btn_text, text_list, v);
                        replaceFragment(new units_energy_fragment());
                        break;
                    case R.id.units_pressure:
                        recolorIconText(pressure_btn_icon, icon_list, pressure_btn_text, text_list, v);
                        replaceFragment(new units_pressure_fragment());
                        break;
                    case R.id.units_area:
                        recolorIconText(area_btn_icon, icon_list, area_btn_text, text_list, v);
                        break;
                    case R.id.units_volume:
                        recolorIconText(volume_btn_icon, icon_list, volume_btn_text, text_list, v);
                        break;
                    default:
                        recolorIconText(length_btn_icon, icon_list, length_btn_text, text_list, v);
                        replaceFragment(new units_length_fragment());
                        break;
                }
            });
        }

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.units_frame, fragment);
        fragmentTransaction.commit();

    }

    private void recolorIconText(ImageView color_icon, ImageView[] icon_list, TextView color_text, TextView[] text_list, View v){

        int nightModeFlags =
                v.getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                for (ImageView icon:icon_list){
                    if(icon.getId() != color_icon.getId()){
                        icon.setColorFilter(ContextCompat.getColor(getApplication(), R.color.card_color));
                    }else{
                        icon.setColorFilter(ContextCompat.getColor(getApplication(), R.color.primary_color));
                    }
                }

                for (TextView text:text_list){
                    if (text.getId() != color_text.getId()){
                        text.setTextColor(ContextCompat.getColor(getApplication(), R.color.card_color));
                    }else{
                        text.setTextColor(ContextCompat.getColor(getApplication(), R.color.primary_color));
                    }
                }
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                for (ImageView icon:icon_list){
                    if(icon.getId() != color_icon.getId()){
                        icon.setColorFilter(ContextCompat.getColor(getApplication(), R.color.primary_blue));
                    }else{
                        icon.setColorFilter(ContextCompat.getColor(getApplication(), R.color.curiosity_white));
                    }
                }

                for (TextView text:text_list){
                    if (text.getId() != color_text.getId()){
                        text.setTextColor(ContextCompat.getColor(getApplication(), R.color.primary_blue));
                    }else{
                        text.setTextColor(ContextCompat.getColor(getApplication(), R.color.curiosity_white));
                    }
                }
                break;

            default:
                break;

        }
    }

}