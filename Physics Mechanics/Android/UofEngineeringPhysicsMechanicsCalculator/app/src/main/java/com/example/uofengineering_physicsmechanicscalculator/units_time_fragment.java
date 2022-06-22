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

public class units_time_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units_time_fragment, container, false);

        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.time_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.time_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_time_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_time_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_time_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.time_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        query_dropdown[0].setAdapter(adapter);
        query_dropdown[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cck =  parent.getItemAtPosition(position).toString();
                query_value.setHint(cck);
                query_unit_type = cck;
                if (!fragLoad){
                    fragLoad = Boolean.TRUE;
                }else{
                    ConvertTimeUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                String cck =  parent.getItemAtPosition(position).toString();
                response_value.setHint(cck);
                response_unit_type = cck;

                if(!fragLoad){
                    fragLoad = Boolean.TRUE;
                }else{
                    ConvertTimeUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertTimeUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertTimeUnits(response_value, query_value, response_unit_type, query_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener(v -> ConvertTimeUnits(query_value, response_value, query_unit_type, response_unit_type));


        return view;
    }

    //Second conversions
    private static double sTomin(double s){
        return s/60;
    }
    private static double sTohr(double s){
        return sTomin(s)/60;
    }
    private static double sToday(double s){
        return sTohr(s)/24;
    }
    private static double sToyr(double s){
        return sToday(s)/365;
    }

    //Minute conversions
    private static double minTos(double min){
        return min*60;
    }
    private static double minTohr(double min){
        return min/60;
    }
    private static double minToday(double min){
        return minTohr(min)/24;
    }
    private static double minToyr(double min){
        return minToday(min)/365;
    }

    //Hour conversions
    private static double hrTos(double hr){
        return hr*60*60;
    }
    private static double hrTomin(double hr){
        return hr*60;
    }
    private static double hrToday(double hr){
        return hr/24;
    }
    private static double hrToyr(double hr){
        return hrToday(hr)/365;
    }

    //Day conversions
    private static double dayTos(double day){
        return day*24*60*60;
    }
    private static double dayTomin(double day){
        return dayTos(day)*60;
    }
    private static double dayTohr(double day){
        return dayTomin(day)*60;
    }
    private static double dayToyr(double day){
        return day/365;
    }

    //Year conversions
    private static double yrTos(double yr){
        return yr*365*24*60*60;
    }
    private static double yrTomin(double yr){
        return yrTos(yr)*60;
    }
    private static double yrTohr(double yr){
        return yrTomin(yr)*60;
    }
    private static double yrToday(double yr){
        return yr*365;
    }

    private void ConvertTimeUnits(TextInputEditText query_value, TextInputEditText response_value,
                                  String query_unit_type, String response_unit_type){
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())){
            double query = Double.parseDouble(query_value.getText().toString());
            switch (query_unit_type){
                case "sec":
                    switch (response_unit_type){
                        case "min":
                            double response = sTomin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "hr":
                            response = sTohr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "day":
                            response = sToday(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yr":
                            response = sToyr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "min":
                    switch (response_unit_type){
                        case "sec":
                            double response = minTos(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "hr":
                            response = minTohr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "day":
                            response = minToday(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yr":
                            response = minToyr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "hr":
                    switch (response_unit_type){
                        case "sec":
                            double response = hrTos(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "min":
                            response = hrTomin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "day":
                            response = hrToday(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yr":
                            response = hrToyr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "day":
                    switch (response_unit_type){
                        case "sec":
                            double response = dayTos(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "min":
                            response = dayTomin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "hr":
                            response = dayTohr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yr":
                            response = dayToyr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "yr":
                    switch (response_unit_type){
                        case "sec":
                            double response = yrTos(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "min":
                            response = yrTomin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "hr":
                            response = yrTohr(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "day":
                            response = yrToday(query);
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