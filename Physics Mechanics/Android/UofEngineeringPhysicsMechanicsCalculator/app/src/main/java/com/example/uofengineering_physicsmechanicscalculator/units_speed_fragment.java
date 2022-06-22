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

public class units_speed_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units_speed_fragment, container, false);

        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.speed_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.speed_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_speed_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_speed_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_speed_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.speed_units, android.R.layout.simple_spinner_item);
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
                    ConvertSpeedUnits(response_value, query_value, response_unit_type, query_unit_type);
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
                    ConvertSpeedUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertSpeedUnits(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertSpeedUnits(response_value, query_value, response_unit_type, query_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener( v -> ConvertSpeedUnits(query_value, response_value, query_unit_type, response_unit_type));

        return view;
    }

    // Meters per second conversions
    private static double mpsTocms(double mps){
        return mps*100;
    }
    private static double mpsTokmh(double mps){
        return mps/1000*60*60;
    }
    private static double mpsTomph(double mps){
        return mps*2.237;
    }
    private static double mpsTofps(double mps){
        return mps*3.281;
    }
    private static double mpsToyps(double mps){
        return mpsTofps(mps)/3;
    }

    // Centimeters per second conversions
    private static double cmsTomps(double cms){
        return cms / 100;
    }
    private static double cmsTokmh(double cms){
        return mpsTokmh(cmsTomps(cms));
    }
    private static double cmsTomph(double cms){
        return mpsTomph(cmsTomps(cms));
    }
    private static double cmsTofps(double cms){
        return mpsTofps(cmsTomps(cms));
    }
    private static double cmsToyps(double cms){
        return mpsToyps(cmsTomps(cms));
    }

    // Kilometers per hour conversions
    private static double kmhTocms(double kmh){
        return kmh*1000*100/60/60;
    }
    private static double kmhTomps(double kmh){
        return cmsTomps(kmhTocms(kmh));
    }
    private static double kmhTomph(double kmh){
        return cmsTomph(kmhTocms(kmh));
    }
    private static double kmhTofps(double kmh){
        return cmsTofps(kmhTocms(kmh));
    }
    private static double kmhToyps(double kmh){
        return cmsToyps(kmhTocms(kmh));
    }

    // Miles per hour conversions
    private static double mphTomps(double mph){
        return 0.447*mph;
    }
    private static double mphTocms(double mph){
        return 44.7*mph;
    }
    private static double mphTokmh(double mph){
        return mpsTokmh(mphTomps(mph));
    }
    private static double mphTofps(double mph){
        return mpsTofps(mphTomps(mph));
    }
    private static double mphToyps(double mph){
        return mpsToyps(mphTomps(mph));
    }

    // Feet per second conversions
    private static double fpsTomps(double fps){
        return 0.3048*fps;
    }
    private static double fpsTocms(double fps){
        return mpsTocms(fpsTomps(fps));
    }
    private static double fpsTomph(double fps){
        return mpsTomph(fpsTomps(fps));
    }
    private static double fpsTokmh(double fps){
        return mpsTokmh(fpsTomps(fps));
    }
    private static double fpsToyps(double fps){
        return fps/3;
    }

    // Yard per second conversions
    private static double ypsTofps(double yps){
        return yps*3;
    }
    private static double ypsTomps(double yps){
        return fpsTomps(ypsTofps(yps));
    }
    private static double ypsTocms(double yps){
        return fpsTocms(ypsTofps(yps));
    }
    private static double ypsTomph(double yps){
        return fpsTomph(ypsTofps(yps));
    }
    private static double ypsTokmh(double yps){
        return fpsTokmh(ypsTofps(yps));
    }

    private void ConvertSpeedUnits(TextInputEditText query_value, TextInputEditText response_value,
                                   String query_unit_type, String response_unit_type) {
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())) {
            double query = Double.parseDouble(Objects.requireNonNull(query_value.getText()).toString());
            switch (query_unit_type) {
                case "m/s":
                    switch (response_unit_type) {
                        case "cm/s":
                            double response = mpsTocms(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km/h":
                            response = mpsTokmh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mph":
                            response = mpsTomph(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft/s":
                            response = mpsTofps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd/s":
                            response = mpsToyps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "cm/s":
                    switch (response_unit_type) {
                        case "m/s":
                            double response = cmsTomps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km/h":
                            response = cmsTokmh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mph":
                            response = cmsTomph(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft/s":
                            response = cmsTofps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd/s":
                            response = cmsToyps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "km/h":
                    switch (response_unit_type){
                        case "cm/s":
                            double response = kmhTocms(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m/s":
                            response = kmhTomps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mph":
                            response = kmhTomph(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft/s":
                            response = kmhTofps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd/s":
                            response = kmhToyps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "mph":
                    switch (response_unit_type){
                        case "cm/s":
                            double response = mphTocms(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m/s":
                            response = mphTomps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km/h":
                            response = mphTokmh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft/s":
                            response = mphTofps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd/s":
                            response = mphToyps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "ft/s":
                    switch (response_unit_type){
                        case "cm/s":
                            double response = fpsTocms(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m/s":
                            response = fpsTomps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km/h":
                            response = fpsTokmh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mph":
                            response = fpsTomph(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd/s":
                            response = fpsToyps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "yd/s":
                    switch (response_unit_type){
                        case "cm/s":
                            double response = ypsTocms(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m/s":
                            response = ypsTomps(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km/h":
                            response = ypsTokmh(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mph":
                            response = ypsTomph(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft/s":
                            response = ypsTofps(query);
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