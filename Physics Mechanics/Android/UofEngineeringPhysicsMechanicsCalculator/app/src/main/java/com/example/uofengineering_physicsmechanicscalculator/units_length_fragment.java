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

public class units_length_fragment extends Fragment{

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoaded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_units_length_fragment, container, false);

        fragLoaded = Boolean.FALSE;


        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.length_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.length_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        query_dropdown[0].setAdapter(adapter);
        query_dropdown[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cck = parent.getItemAtPosition(position).toString();
                query_value.setHint(cck);
                query_unit_type = cck;
                if (!fragLoaded) {
                    fragLoaded = Boolean.TRUE;
                }else{
                    ConvertLength(response_value, query_value, response_unit_type, query_unit_type);
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
                if (!fragLoaded) {
                    fragLoaded = Boolean.TRUE;
                }else{
                    ConvertLength(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertLength(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertLength(response_value, query_value, response_unit_type, query_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener(v -> ConvertLength(query_value, response_value, query_unit_type, response_unit_type));

        return view;
    }

    //CONVERSION FUNCTIONS
    //Millimeter conversions
    private static double mmTocm(double mm){
        return 0.1*mm;
    }
    private static double mmTom(double mm){
        return 0.001*mm;
    }
    private static double mmTokm(double mm){
        return 0.001*0.001*mm;
    }
    private static double mmToin(double mm){
        return 0.03937008*mm;
    }
    private static double mmToft(double mm){
        return 0.03937008*mm*1/12;
    }
    private static double mmToyd(double mm){
        return 0.03937008*mm*1/12*1/3;
    }
    private static double mmTomi(double mm){
        return 0.03937008*mm*1/12*1/5280;
    }
    private static double mmToNM(double mm){
        return 0.03937008*mm*1/12*1/5280*0.868976;
    }

    //Centimeter conversions
    private static double cmTomm(double cm){
        return 10*cm;
    }
    private static double cmTom(double cm){
        return 0.01*cm;
    }
    private static double cmTokm(double cm){
        return 0.01*cm*0.001;
    }
    private static double cmToin(double cm){
        return 0.3937008*cm;
    }
    private static double cmToft(double cm){
        return 0.3937008*cm*1/12;
    }
    private static double cmToyd(double cm){
        return 0.3937008*cm*1/12*1/3;
    }
    private static double cmTomi(double cm){
        return 0.3937008*cm*1/12*1/5280;
    }
    private static double cmToNM(double cm){
        return 0.3937008*cm*1/12*1/5280*0.868976;
    }

    //Meter conversion
    private static double mTomm(double m){
        return m*0.001;
    }
    private static double mTocm(double m){
        return m*0.01;
    }
    private static double mTokm(double m){
        return m*0.001;
    }
    private static double mToin(double m){
        return 39.37008*m;
    }
    private static double mToft(double m){
        return 39.37008*m*1/12;
    }
    private static double mToyd(double m){
        return 39.37008*m*1/12*1/3;
    }
    private static double mTomi(double m){
        return 39.37008*m*1/12*1/5280;
    }
    private static double mToNM(double m){
        return 39.37008*m*1/12*1/5280*0.868976;
    }

    //Kilometer conversions
    private static double kmTomm(double km){
        return km*0.001*0.001;
    }
    private static double kmTocm(double km){
        return km*0.001*0.01;
    }
    private static double kmTom(double km){
        return km*0.001;
    }
    private static double kmToin(double km){
        return 39370.08*km;
    }
    private static double kmToft(double km){
        return 39370.08*km*1/12;
    }
    private static double kmToyd(double km){
        return 39370.08*km*1/12*1/3;
    }
    private static double kmTomi(double km){
        return 39370.08*km*1/12*1/5280;
    }
    private static double kmToNM(double km){
        return 39370.08*km*1/12*1/5280*0.868976;
    }

    //Inch conversions
    private static double inTomm(double in){
        return in*25.4;
    }
    private static double inTocm(double in){
        return in*2.54;
    }
    private static double inTom(double in){
        return in*0.0254;
    }
    private static double inTokm(double in){
        return in*0.0254*0.001;
    }
    private static double inToft(double in){
        return in*1/12;
    }
    private static double inToyd(double in){
        return in*1/12*1/3;
    }
    private static double inTomi(double in){
        return in*1/12*1/5280;
    }
    private static double inToNM(double in){
        return in*1/12*1/5280*0.868976;
    }


    //Foot conversions
    private static double ftTomm(double ft){
        return ft*12*25.4;
    }
    private static double ftTocm(double ft){
        return ft*12*2.54;
    }
    private static double ftTom(double ft){
        return ft*12*2.54*0.01;
    }
    private static double ftTokm(double ft){
        return ft*12*0.0254*0.001;
    }
    private static double ftToin(double ft){
        return ft*1/12;
    }
    private static double ftToyd(double ft){
        return ft*1/3;
    }
    private static double ftTomi(double ft){
        return ft*1/5280;
    }
    private static double ftToNM(double ft){
        return ft*1/5280*0.868976;
    }

    //Yard conversions
    private static double ydTomm(double yd){
        return yd*3*12*25.4;
    }
    private static double ydTocm(double yd){
        return yd*3*12*2.54;
    }
    private static double ydTom(double yd){
        return yd*3*12*2.54*0.01;
    }
    private static double ydTokm(double yd){
        return yd*3*12*0.0254*0.001;
    }
    private static double ydToin(double yd){
        return yd*1/3*1/12;
    }
    private static double ydToft(double yd){
        return yd*3;
    }
    private static double ydTomi(double yd){
        return yd*3*1/5280;
    }
    private static double ydToNM(double yd){
        return yd*3*1/5280*0.868976;
    }

    //Mile conversions
    private static double miTomm(double mi){
        return mi*5280*12*25.4;
    }
    private static double miTocm(double mi){
        return mi*5280*12*25.4*0.1;
    }
    private static double miTom(double mi){
        return mi*5280*12*25.4*0.001;
    }
    private static double miTokm(double mi){
        return mi*5280*12*25.4*0.001*0.001;
    }
    private static double miToin(double mi){
        return mi*5280*12;
    }
    private static double miToft(double mi){
        return mi*5280;
    }
    private static double miToyd(double mi){
        return mi*5280*1/3;
    }
    private static double miToNM(double mi){
        return mi*0.868976;
    }

    //Nautical mile conversions
    private static double NMTomm(double NM){
        return NM*0.868976*5280*12*25.4;
    }
    private static double NMTocm(double NM){
        return NM*0.868976*5280*12*25.4*0.1;
    }
    private static double NMTom(double NM){
        return NM*0.868976*5280*12*25.4*0.001;
    }
    private static double NMTokm(double NM){
        return NM*0.868976*5280*12*25.4*0.001*0.001;
    }
    private static double NMToin(double NM){
        return NM*0.868976*5280*12;
    }
    private static double NMToft(double NM){
        return NM*0.868976*5280;
    }
    private static double NMToyd(double NM){
        return NM*0.868976*5280*1/3;
    }
    private static double NMTomi(double NM){
        return NM/0.868976;
    }

    private static void ConvertLength(TextInputEditText from, TextInputEditText to, String from_unit_type, String to_unit_type){
        if (!TextUtils.isEmpty(Objects.requireNonNull(from.getText()).toString())) {
            double query = Double.parseDouble(Objects.requireNonNull(from.getText()).toString());
            switch (from_unit_type) {
                case "mm":
                    switch (to_unit_type) {
                        case "cm":
                            double response = mmTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = mmTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = mmTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = mmToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = mmToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = mmToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = mmTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = mmToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "cm":
                    switch (to_unit_type) {
                        case "mm":
                            double response = cmTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = cmTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = cmTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = cmToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = cmToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = cmToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = cmTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = cmToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "m":
                    switch (to_unit_type) {
                        case "mm":
                            double response = mTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = mTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = mTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = mToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = mToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = mToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = mTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = mToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "km":
                    switch (to_unit_type) {
                        case "mm":
                            double response = kmTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = kmTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = kmTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = kmToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = kmToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = kmToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = kmTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = kmToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "in":
                    switch (to_unit_type) {
                        case "mm":
                            double response = inTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = inTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = inTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = inTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = inToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = inToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = inTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = inToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "ft":
                    switch (to_unit_type) {
                        case "mm":
                            double response = ftTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = ftTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = ftTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = ftTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = ftToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = ftToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = ftTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = ftToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "yd":
                    switch (to_unit_type) {
                        case "mm":
                            double response = ydTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = ydTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = ydTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = ydTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = ydToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = ydToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = ydTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = ydToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "mi":
                    switch (to_unit_type) {
                        case "mm":
                            double response = miTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = miTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = miTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = miTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = miToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = miToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = miToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "NM":
                            response = miToNM(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                case "NM":
                    switch (to_unit_type) {
                        case "mm":
                            double response = NMTomm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "cm":
                            response = NMTocm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "m":
                            response = NMTom(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "km":
                            response = NMTokm(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "in":
                            response = NMToin(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "ft":
                            response = NMToft(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "yd":
                            response = NMToyd(query);
                            to.setText(String.valueOf(response));
                            break;
                        case "mi":
                            response = NMTomi(query);
                            to.setText(String.valueOf(response));
                            break;
                        default:
                            to.setText(Objects.requireNonNull(from.getText()).toString());
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

}