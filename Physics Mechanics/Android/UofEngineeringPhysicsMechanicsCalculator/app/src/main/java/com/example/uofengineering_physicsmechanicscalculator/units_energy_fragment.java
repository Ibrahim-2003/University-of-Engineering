package com.example.uofengineering_physicsmechanicscalculator;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class units_energy_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units_energy_fragment, container, false);

        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.energy_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.energy_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_energy_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_energy_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_energy_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.energy_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        query_dropdown[0].setAdapter(adapter);
        query_dropdown[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cck = parent.getItemAtPosition(position).toString();
                query_value.setHint(cck);
                query_unit_type = cck;

                if (!fragLoad) {
                    fragLoad = Boolean.TRUE;
                }else{
                    ConvertEnergyUnits(response_value, query_value, response_unit_type, query_unit_type);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        response_dropdown.setAdapter(adapter);
        response_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cck = parent.getItemAtPosition(position).toString();
                response_value.setHint(cck);
                response_unit_type = cck;

                if (!fragLoad){
                    fragLoad = Boolean.TRUE;
                }else{
                    ConvertEnergyUnits(query_value, response_value, query_unit_type, response_unit_type);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        query_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (query_value.hasFocus()){
                    ConvertEnergyUnits(query_value, response_value, query_unit_type, response_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        response_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (response_value.hasFocus()){
                    ConvertEnergyUnits(response_value, query_value, response_unit_type, query_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    // Joule conversions
    private static double jToftlb(double j){
        return j*0.7376;
    }
    private static double jToev(double j){
        return j*6.242*Math.pow(10, 18);
    }
    private static double jTocal(double j){
        return j*0.2389;
    }
    private static double jTobtu(double j){
        return j*9.481*Math.pow(10, -4);
    }
    private static double jTokwh(double j){
        return j*2.778*Math.pow(10, -7);
    }

    // Foot pound conversions
    private static double ftlbToj(double ftlb){
        return ftlb*1.356;
    }
    private static double ftlbToev(double ftlb){
        return jToev(ftlbToj(ftlb));
    }
    private static double ftlbTocal(double ftlb){
        return jTocal(ftlbToj(ftlb));
    }
    private static double ftlbTobtu(double ftlb){
        return jTobtu(ftlbToj(ftlb));
    }
    private static double ftlbTokwh(double ftlb){
        return jTokwh(ftlbToj(ftlb));
    }

    // Electron volt conversions
    private static double evToj(double ev){
        return ev*1.602*Math.pow(10, -19);
    }
    private static double evToftlb(double ev){
        return jToftlb(evToj(ev));
    }
    private static double evTocal(double ev){
        return jTocal(evToj(ev));
    }
    private static double evTobtu(double ev){
        return jTobtu(evToj(ev));
    }
    private static double evTokwh(double ev){
        return jTokwh(evToj(ev));
    }

    // Calorie conversions
    private static double calToj(double cal){
        return cal*4.186;
    }
    private static double calToftlb(double cal){
        return jToftlb(calToj(cal));
    }
    private static double calToev(double cal){
        return jToev(calToj(cal));
    }
    private static double calTobtu(double cal){
        return jTobtu(calToj(cal));
    }
    private static double calTokwh(double cal){
        return jTokwh(calToj(cal));
    }

    // Btu conversions
    private static double btuToj(double btu){
        return btu*1.055*Math.pow(10, 3);
    }
    private static double btuToftlb(double btu){
        return jToftlb(btuToj(btu));
    }
    private static double btuToev(double btu){
        return jToev(btuToj(btu));
    }
    private static double btuTocal(double btu){
        return jTocal(btuToj(btu));
    }
    private static double btuTokwh(double btu){
        return jTokwh(btuToj(btu));
    }

    // Kilowatt hour conversions
    private static double kwhToj(double kwh){
        return kwh*3.6*Math.pow(10, 6);
    }
    private static double kwhToftlb(double kwh){
        return jToftlb(kwhToj(kwh));
    }
    private static double kwhToev(double kwh){
        return jToev(kwhToj(kwh));
    }
    private static double kwhTocal(double kwh){
        return jTocal(kwhToj(kwh));
    }
    private static double kwhTobtu(double kwh){
        return jTobtu(kwhToj(kwh));
    }

    private void ConvertEnergyUnits(TextInputEditText query_value, TextInputEditText response_value,
                                    String query_unit_type, String response_unit_type){
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())){
            double query = Double.parseDouble(query_value.getText().toString());
            switch (query_unit_type){
                case "J":
                    switch (response_unit_type){
                        case "ft-lb":
                            double response = jToftlb(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "eV":
                            response = jToev(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cal":
                            response = jTocal(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "Btu":
                            response = jTobtu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kWh":
                            response = jTokwh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "ft-lb":
                    switch (response_unit_type){
                        case "J":
                            double response = ftlbToj(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "eV":
                            response = ftlbToev(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cal":
                            response = ftlbTocal(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "Btu":
                            response = ftlbTobtu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kWh":
                            response = ftlbTokwh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "eV":
                    switch (response_unit_type){
                        case "J":
                            double response = evToj(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft-lb":
                            response = evToftlb(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cal":
                            response = evTocal(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "Btu":
                            response = evTobtu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kWh":
                            response = evTokwh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "cal":
                    switch (response_unit_type){
                        case "J":
                            double response = calToj(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft-lb":
                            response = calToftlb(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "eV":
                            response = calToev(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "Btu":
                            response = calTobtu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kWh":
                            response = calTokwh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "Btu":
                    switch (response_unit_type){
                        case "J":
                            double response = btuToj(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft-lb":
                            response = btuToftlb(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "eV":
                            response = btuToev(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cal":
                            response = btuTocal(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kWh":
                            response = btuTokwh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "kWh":
                    switch (response_unit_type){
                        case "J":
                            double response = kwhToj(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft-lb":
                            response = kwhToftlb(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cal":
                            response = kwhTocal(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "Btu":
                            response = kwhTobtu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "eV":
                            response = kwhToev(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }


}