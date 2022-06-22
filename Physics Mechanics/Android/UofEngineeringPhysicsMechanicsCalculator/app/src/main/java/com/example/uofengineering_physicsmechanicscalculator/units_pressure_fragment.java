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

public class units_pressure_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units_pressure_fragment, container, false);

        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.pressure_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.pressure_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_pressure_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_pressure_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_pressure_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.pressure_units, android.R.layout.simple_spinner_item);
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
                    ConvertPressureUnits(response_value, query_value, response_unit_type, query_unit_type);
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
                    ConvertPressureUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                if (query_value.hasFocus()) {
                    ConvertPressureUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                if (response_value.hasFocus()) {
                    ConvertPressureUnits(response_value, query_value, response_unit_type, query_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener( v -> ConvertPressureUnits(query_value, response_value, query_unit_type, response_unit_type));

        return view;
    }

//        <item>Pa</item>
//        <item>kPa</item>
//        <item>atm</item>
//        <item>cmHg</item>
//        <item>mmHg</item>
//        <item>lb/in^2</item>
//        <item>lb/ft^2</item>

//    Pascal conversions
    private static double paTokpa(double pa){
        return pa*0.001;
    }
    private static double paToatm(double pa){
        return pa*9.869*Math.pow(10, -6);
    }
    private static double paTocmhg(double pa){
        return pa*7.501*Math.pow(10, -4);
    }
    private static double paTommhg(double pa){
        return paTocmhg(pa)*10;
    }
    private static double paTolbin(double pa){
        return pa*1.450*Math.pow(10, -4);
    }
    private static double paTolbft(double pa){
        return pa*2.089*Math.pow(10, -2);
    }

//    Kilopascal conversions
    private static double kpaTopa(double kpa){
        return kpa*1000;
    }
    private static double kpaToatm(double kpa){
        return paToatm(kpaTopa(kpa));
    }
    private static double kpaTocmhg(double kpa){
        return paTocmhg(kpaTopa(kpa));
    }
    private static double kpaTommhg(double kpa){
        return paTommhg(kpaTopa(kpa));
    }
    private static double kpaTolbin(double kpa){
        return paTolbin(kpaTopa(kpa));
    }
    private static double kpaTolbft(double kpa){
        return paTolbft(kpaTopa(kpa));
    }

//    Atmosphere conversions
    private static double atmTopa(double atm){
        return atm*1.01325*Math.pow(10, 5);
    }
    private static double atmTokpa(double atm){
        return paTokpa(atmTopa(atm));
    }
    private static double atmTocmhg(double atm){
        return paTocmhg(atmTopa(atm));
    }
    private static double atmTommhg(double atm){
        return paTommhg(atmTopa(atm));
    }
    private static double atmTolbin(double atm){
        return paTolbin(atmTopa(atm));
    }
    private static double atmTolbft(double atm){
        return paTolbft(atmTopa(atm));
    }

//    Centimeters of mercury conversions
    private static double cmhgTopa(double cmhg){
        return cmhg*1.33322*Math.pow(10, 3);
    }
    private static double cmhgTokpa(double cmhg){
        return paTokpa(cmhgTopa(cmhg));
    }
    private static double cmhgToatm(double cmhg){
        return paToatm(cmhgTopa(cmhg));
    }
    private static double cmhgTommhg(double cmhg){
        return paTommhg(cmhgTopa(cmhg));
    }
    private static double cmhgTolbin(double cmhg){
        return paTolbin(cmhgTopa(cmhg));
    }
    private static double cmhgTolbft(double cmhg){
        return paTolbft(cmhgTopa(cmhg));
    }

//    Millimeters of mercury conversions
    private static double mmhgTopa(double mmhg){
        return mmhg*1.33322*Math.pow(10, 3)*10;
    }
    private static double mmhgTokpa(double mmhg){
        return paTokpa(mmhgTopa(mmhg));
    }
    private static double mmhgToatm(double mmhg){
        return paToatm(mmhgTopa(mmhg));
    }
    private static double mmhgTocmhg(double mmhg){
        return mmhg*0.1;
    }
    private static double mmhgTolbin(double mmhg){
        return paTolbin(mmhgTopa(mmhg));
    }
    private static double mmhgTolbft(double mmhg){
        return paTolbft(mmhgTopa(mmhg));
    }

//    Pounds per square inch
    private static double lbinTopa(double lbin){
        return lbin*6.89476*Math.pow(10, 3);
    }
    private static double lbinTokpa(double lbin){
        return paTokpa(lbinTopa(lbin));
    }
    private static double lbinToatm(double lbin){
        return paToatm(lbinTopa(lbin));
    }
    private static double lbinTocmhg(double lbin){
        return paTocmhg(lbinTopa(lbin));
    }
    private static double lbinTommhg(double lbin){
        return paTommhg(lbinTopa(lbin));
    }
    private static double lbinTolbft(double lbin){
        return paTolbft(lbinTopa(lbin));
    }

//    Pounds per square foot
    private static double lbftTopa(double lbft){
        return lbft*47.88;
    }
    private static double lbftTokpa(double lbft){
        return paTokpa(lbftTopa(lbft));
    }
    private static double lbftToatm(double lbft){
        return paToatm(lbftTopa(lbft));
    }
    private static double lbftTocmhg(double lbft){
        return paTocmhg(lbftTopa(lbft));
    }
    private static double lbftTommhg(double lbft){
        return paTommhg(lbftTopa(lbft));
    }
    private static double lbftTolbin(double lbft){
        return paTolbin(lbftTopa(lbft));
    }


    private void ConvertPressureUnits(TextInputEditText query_value, TextInputEditText response_value,
                                      String query_unit_type, String response_unit_type){
        double response;
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())) {
            double query = Double.parseDouble(Objects.requireNonNull(query_value.getText()).toString());
            switch (query_unit_type){
                case "Pa":
                    switch (response_unit_type){
                        case "kPa":
                            response = paTokpa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "atm":
                            response = paToatm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cmHg":
                            response = paTocmhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mmHg":
                            response = paTommhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/in^2":
                            response = paTolbin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/ft^2":
                            response = paTolbft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "kPa":
                    switch (response_unit_type){
                        case "Pa":
                            response = kpaTopa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "atm":
                            response = kpaToatm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cmHg":
                            response = kpaTocmhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mmHg":
                            response = kpaTommhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/in^2":
                            response = kpaTolbin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/ft^2":
                            response = kpaTolbft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "atm":
                    switch (response_unit_type){
                        case "Pa":
                            response = atmTopa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kPa":
                            response = atmTokpa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cmHg":
                            response = atmTocmhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mmHg":
                            response = atmTommhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/in62":
                            response = atmTolbin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/ft^2":
                            response = atmTolbft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "cmHg":
                    switch (response_unit_type){
                        case "Pa":
                            response = cmhgTopa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kPa":
                            response = cmhgTokpa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "atm":
                            response = cmhgToatm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mmHg":
                            response = cmhgTommhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/in^2":
                            response = cmhgTolbin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/ft^2":
                            response = cmhgTolbft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "mmHg":
                    switch (response_unit_type){
                        case "Pa":
                            response = mmhgTopa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kPa":
                            response = mmhgTokpa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "atm":
                            response = mmhgToatm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cmHg":
                            response = mmhgTocmhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/in^2":
                            response = mmhgTolbin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/ft^2":
                            response = mmhgTolbft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "lb/in^2":
                    switch (response_unit_type){
                        case "Pa":
                            response = lbinTopa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kPa":
                            response = lbinTokpa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "atm":
                            response = lbinToatm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cmHg":
                            response = lbinTocmhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mmHg":
                            response = lbinTommhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/ft^2":
                            response = lbinTolbft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(query_value.getText().toString());
                            break;
                    }
                    break;
                case "lb/ft^2":
                    switch (response_unit_type){
                        case "Pa":
                            response = lbftTopa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "kPa":
                            response = lbftTokpa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "atm":
                            response = lbftToatm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cmHg":
                            response = lbftTocmhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mmHg":
                            response = lbftTommhg(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "lb/in^2":
                            response = lbftTolbin(query);
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