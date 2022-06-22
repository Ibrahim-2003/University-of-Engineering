package com.example.uofengineering_physicsmechanicscalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class units_mass_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_units_mass_fragment, container, false);
        
        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.mass_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.mass_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_mass_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_mass_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_mass_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.mass_units, android.R.layout.simple_spinner_item);
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
                    ConvertMassUnits(response_value, query_value, response_unit_type, query_unit_type);
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
                if (!fragLoad) {
                    fragLoad = Boolean.TRUE;
                }else{
                    ConvertMassUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertMassUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                        ConvertMassUnits(query_value, response_value, query_unit_type, response_unit_type);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener(v -> ConvertMassUnits(query_value, response_value, query_unit_type, response_unit_type));

        return view;
    }

    //Kilogram conversions
    private static double kgTog(double kg){
        return kg*1000;
    }
    private static double kgToslug(double kg){
        return kg/14.59;
    }
    private static double kgToamu(double kg){
        return kg*6.024*(Math.pow(10,26));
    }

    //Gram conversions
    private static double gTokg(double g){
        return g*0.001;
    }
    private static double gToslug(double g){
        return g*0.001/14.59;
    }
    private static double gToamu(double g){
        return g*0.001*6.024*(Math.pow(10,26));
    }

    //Slug conversions
    private static double slugTokg(double slug){
        return slug*14.59;
    }
    private static double slugTog(double slug){
        return slug*14.59*1000;
    }
    private static double slugToamu(double slug){
        return slug*8.789*Math.pow(10, 27);
    }

    //Amu conversions
    private static double amuTokg(double amu){
        return amu*1.66*Math.pow(10,-27);
    }
    private static double amuTog(double amu){
        return amu*1.66*Math.pow(10,-24);
    }
    private static double amuToslug(double amu){
        return amu*1.137*Math.pow(10,-28);
    }
    
    private void ConvertMassUnits(TextInputEditText query_value, TextInputEditText response_value,
                                    String query_unit_type, String response_unit_type){
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())) {
            double query = Double.parseDouble(Objects.requireNonNull(query_value.getText()).toString());
            switch (query_unit_type) {
                case "kg":
                    switch (response_unit_type) {
                        case "g":
                            double response = kgTog(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "slug":
                            response = kgToslug(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "amu":
                            response = kgToamu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "g":
                    switch (response_unit_type) {
                        case "kg":
                            double response = gTokg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "slug":
                            response = gToslug(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "amu":
                            response = gToamu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "slug":
                    switch (response_unit_type){
                        case "kg":
                            double response = slugTokg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "g":
                            response = slugTog(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "amu":
                            response = slugToamu(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "amu":
                    switch (response_unit_type){
                        case "kg":
                            double response = amuTokg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "g":
                            response = amuTog(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "slug":
                            response = amuToslug(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                default:
                    break;
            }

        }
    }
}