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

public class units_force_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units_force_fragment, container, false);

        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.force_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.force_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_force_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_force_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_force_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.force_units, android.R.layout.simple_spinner_item);
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
                    ConvertForceUnits(response_value, query_value, response_unit_type, query_unit_type);
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
                    ConvertForceUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertForceUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertForceUnits(response_value, query_value, response_unit_type, query_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener( v -> ConvertForceUnits(query_value, response_value, query_unit_type, response_unit_type));
        
        return view;
    }

    private static double nTolb(double n){
        return n*0.2248;
    }
    private static double lbTon(double lb){
        return lb*4.448;
    }

    private void ConvertForceUnits(TextInputEditText query_value, TextInputEditText response_value,
                                   String query_unit_type, String response_unit_type){
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())) {
            double query = Double.parseDouble(Objects.requireNonNull(query_value.getText()).toString());
            if (query_unit_type.equals("N") && response_unit_type.equals("lb")){
                double response = nTolb(query);
                response_value.setText(String.valueOf(response));
            }else if (query_unit_type.equals("lb") && response_unit_type.equals("N")){
                double response = lbTon(query);
                response_value.setText(String.valueOf(response));
            }else if (query_unit_type.equals(response_unit_type)){
                response_value.setText(query_value.getText().toString());
            }
        }
    }


}