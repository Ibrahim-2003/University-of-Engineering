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

public class units_area_fragment extends Fragment {

    View view;

    private String query_unit_type;
    private String response_unit_type;
    private Boolean fragLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_units_area_fragment, container, false);

        fragLoad = Boolean.FALSE;

        final Spinner[] query_dropdown = {(Spinner) view.findViewById(R.id.area_query_dropdown)};
        Spinner response_dropdown = (Spinner) view.findViewById(R.id.area_response_dropdown);
        TextInputEditText query_value = (TextInputEditText) view.findViewById(R.id.convert_area_query_value);
        TextInputEditText response_value = (TextInputEditText) view.findViewById(R.id.converted_area_answer);
        CardView convert_btn = (CardView) view.findViewById(R.id.convert_area_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.area, android.R.layout.simple_spinner_item);
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
                    ConvertArea(response_value, query_value, response_unit_type, query_unit_type);
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
                    ConvertArea(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertArea(query_value, response_value, query_unit_type, response_unit_type);
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
                    ConvertArea(query_value, response_value, query_unit_type, response_unit_type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        convert_btn.setOnClickListener(v -> ConvertArea(query_value, response_value, query_unit_type, response_unit_type));

        return view;
    }

    //CONVERSION FUNCTIONS
    //Millimeter conversions
    private static double mmTocm(double mm){
        return Math.pow(0.1*mm, 2);
    }
    private static double mmTom(double mm){
        return Math.pow(0.001*mm, 2);
    }
    private static double mmTokm(double mm){
        return Math.pow(0.001*0.001*mm, 2);
    }
    private static double mmToin(double mm){
        return Math.pow(0.03937008*mm, 2);
    }
    private static double mmToft(double mm){
        return Math.pow(0.03937008*mm*1/12, 2);
    }
    private static double mmToyd(double mm){
        return Math.pow(0.03937008*mm*1/12*1/3, 2);
    }
    private static double mmTomi(double mm){
        return Math.pow(0.03937008*mm*1/12*1/5280, 2);
    }
    //a, ac, ha
    private static double mmToa(double mm) { return mm*Math.pow(10, -8); }
    private static double mmToac(double mm) { return 2.4711*mm*Math.pow(10, -10); }
    private static double mmToha(double mm) { return mm*Math.pow(10,-10);}

    //Centimeter conversions
    private static double cmTomm(double cm){
        return Math.pow(10*cm, 2);
    }
    private static double cmTom(double cm){
        return Math.pow(0.01*cm, 2);
    }
    private static double cmTokm(double cm){
        return Math.pow(0.01*cm*0.001, 2);
    }
    private static double cmToin(double cm){
        return Math.pow(0.3937008*cm, 2);
    }
    private static double cmToft(double cm){
        return Math.pow(0.3937008*cm*1/12, 2);
    }
    private static double cmToyd(double cm){
        return Math.pow(0.3937008*cm*1/12*1/3, 2);
    }
    private static double cmTomi(double cm){
        return Math.pow(0.3937008*cm*1/12*1/5280, 2);
    }
    //a,ac,ha
    private static double cmToa(double cm) { return mmToa(cmTomm(cm)); }
    private static double cmToac(double cm) { return mmToac(cmTomm(cm)); }
    private static double cmToha(double cm) { return mmToha(cmTomm(cm)); }

    //Meter conversion
    private static double mTomm(double m){
        return Math.pow(m*0.001, 2);
    }
    private static double mTocm(double m){
        return Math.pow(m*0.01, 2);
    }
    private static double mTokm(double m){
        return Math.pow(m*0.001, 2);
    }
    private static double mToin(double m){
        return Math.pow(39.37008*m, 2);
    }
    private static double mToft(double m){
        return Math.pow(39.37008*m*1/12, 2);
    }
    private static double mToyd(double m){
        return Math.pow(39.37008*m*1/12*1/3, 2);
    }
    private static double mTomi(double m){
        return Math.pow(39.37008*m*1/12*1/5280, 2);
    }

    //Kilometer conversions
    private static double kmTomm(double km){
        return Math.pow(km*0.001*0.001, 2);
    }
    private static double kmTocm(double km){
        return Math.pow(km*0.001*0.01, 2);
    }
    private static double kmTom(double km){
        return Math.pow(km*0.001, 2);
    }
    private static double kmToin(double km){
        return Math.pow(39370.08*km, 2);
    }
    private static double kmToft(double km){
        return Math.pow(39370.08*km*1/12, 2);
    }
    private static double kmToyd(double km){
        return Math.pow(39370.08*km*1/12*1/3, 2);
    }
    private static double kmTomi(double km){
        return Math.pow(39370.08*km*1/12*1/5280, 2);
    }

    //Inch conversions
    private static double inTomm(double in){
        return Math.pow(in*25.4, 2);
    }
    private static double inTocm(double in){
        return Math.pow(in*2.54, 2);
    }
    private static double inTom(double in){
        return Math.pow(in*0.0254, 2);
    }
    private static double inTokm(double in){
        return Math.pow(in*0.0254*0.001, 2);
    }
    private static double inToft(double in){
        return Math.pow(in*1/12, 2);
    }
    private static double inToyd(double in){
        return Math.pow(in*1/12*1/3, 2);
    }
    private static double inTomi(double in){
        return Math.pow(in*1/12*1/5280, 2);
    }


    //Foot conversions
    private static double ftTomm(double ft){
        return Math.pow(ft*12*25.4, 2);
    }
    private static double ftTocm(double ft){
        return Math.pow(ft*12*2.54, 2);
    }
    private static double ftTom(double ft){
        return Math.pow(ft*12*2.54*0.01, 2);
    }
    private static double ftTokm(double ft){
        return Math.pow(ft*12*0.0254*0.001, 2);
    }
    private static double ftToin(double ft){
        return Math.pow(ft*1/12, 2);
    }
    private static double ftToyd(double ft){
        return Math.pow(ft*1/3, 2);
    }
    private static double ftTomi(double ft){
        return Math.pow(ft*1/5280, 2);
    }

    //Yard conversions
    private static double ydTomm(double yd){
        return Math.pow(yd*3*12*25.4, 2);
    }
    private static double ydTocm(double yd){
        return Math.pow(yd*3*12*2.54, 2);
    }
    private static double ydTom(double yd){
        return Math.pow(yd*3*12*2.54*0.01, 2);
    }
    private static double ydTokm(double yd){
        return Math.pow(yd*3*12*0.0254*0.001, 2);
    }
    private static double ydToin(double yd){
        return Math.pow(yd*1/3*1/12, 2);
    }
    private static double ydToft(double yd){
        return Math.pow(yd*3, 2);
    }
    private static double ydTomi(double yd){
        return Math.pow(yd*3*1/5280, 2);
    }

    //Mile conversions
    private static double miTomm(double mi){
        return Math.pow(mi*5280*12*25.4, 2);
    }
    private static double miTocm(double mi){
        return Math.pow(mi*5280*12*25.4*0.1, 2);
    }
    private static double miTom(double mi){
        return Math.pow(mi*5280*12*25.4*0.001, 2);
    }
    private static double miTokm(double mi){
        return Math.pow(mi*5280*12*25.4*0.001*0.001, 2);
    }
    private static double miToin(double mi){
        return Math.pow(mi*5280*12, 2);
    }
    private static double miToft(double mi){
        return Math.pow(mi*5280, 2);
    }
    private static double miToyd(double mi){
        return Math.pow(mi*5280*1/3, 2);
    }

    //a, ac, ha
    private static double mToa(double m) { return mmToa(mTomm(m)); }
    private static double mToac(double m) { return mmToac(mTomm(m)); }
    private static double mToha(double m) { return mmToha(mTomm(m)); }
    private static double kmToa(double km) { return mmToa(kmTomm(km)); }
    private static double kmToac(double km) { return mmToac(kmTomm(km)); }
    private static double kmToha(double km) { return mmToha(kmTomm(km)); }
    private static double inToa(double in) { return mmToa(inTomm(in)); }
    private static double inToac(double in) { return mmToac(inTomm(in)); }
    private static double inToha(double in) { return mmToha(inTomm(in)); }
    private static double ftToa(double ft) { return mmToa(ftTomm(ft)); }
    private static double ftToac(double ft) { return mmToac(ftTomm(ft)); }
    private static double ftToha(double ft) { return mmToha(ftTomm(ft)); }
    private static double ydToa(double yd) { return mmToa(ydTomm(yd)); }
    private static double ydToac(double yd) { return mmToac(ydTomm(yd)); }
    private static double ydToha(double yd) { return mmToha(ydTomm(yd)); }
    private static double miToa(double mi) { return mmToa(miTomm(mi)); }
    private static double miToac(double mi) { return mmToac(miTomm(mi)); }
    private static double miToha(double mi) { return mmToha(miTomm(mi)); }

    //Ares conversions
    private static double aTomm(double a) { return a*Math.pow(10, 8); }
    private static double aTocm(double a) { return mmTocm(aTomm(a)); }
    private static double aTom(double a) { return mmTom(aTomm(a)); }
    private static double aTokm(double a) { return mmTokm(aTomm(a)); }
    private static double aToin(double a) { return mmToin(aTomm(a)); }
    private static double aToft(double a) { return mmToft(aTomm(a)); }
    private static double aToyd(double a) { return mmToyd(aTomm(a)); }
    private static double aTomi(double a) { return mmTomi(aTomm(a)); }
    private static double aToac(double a) { return mmToac(aTomm(a)); }
    private static double aToha(double a) {return mmToha(aTomm(a)); }

    //Acres conversions
    private static double acTomm(double ac) { return ac*4.047*Math.pow(10, 9); }
    private static double acTocm(double ac) { return mmTocm(acTomm(ac)); }
    private static double acTom(double ac) { return mmTom(acTomm(ac)); }
    private static double acTokm(double ac) { return mmTokm(acTomm(ac)); }
    private static double acToin(double ac) { return mmToin(acTomm(ac)); }
    private static double acToft(double ac) { return mmToft(acTomm(ac)); }
    private static double acToyd(double ac) { return mmToyd(acTomm(ac)); }
    private static double acTomi(double ac) { return mmTomi(acTomm(ac)); }
    private static double acToa(double ac) {return mmToa(acTomm(ac)); }
    private static double acToha(double ac) {return mmToha(acTomm(ac)); }

    //Hectares conversions
    private static double haTomm(double ha) { return ha*Math.pow(10, 10); }
    private static double haTocm(double ha) { return mmTocm(haTomm(ha)); }
    private static double haTom(double ha) { return mmTom(haTomm(ha)); }
    private static double haTokm(double ha) { return mmTokm(haTomm(ha)); }
    private static double haToin(double ha) { return mmToin(haTomm(ha)); }
    private static double haToft(double ha) { return mmToft(haTomm(ha)); }
    private static double haToyd(double ha) { return mmToyd(haTomm(ha)); }
    private static double haTomi(double ha) { return mmTomi(haTomm(ha)); }
    private static double haToa(double ha) { return mmToa(haTomm(ha)); }
    private static double haToac(double ha) { return mmToac(haTomm(ha)); }

    private void ConvertArea(TextInputEditText query_value, TextInputEditText response_value,
                             String query_unit_type, String response_unit_type){
        if (!TextUtils.isEmpty(Objects.requireNonNull(query_value.getText()).toString())){
            double query = Double.parseDouble(Objects.requireNonNull(query_value.getText()).toString());
            double response;
            switch (query_unit_type) {
                case "mm^2":
                    switch (response_unit_type) {
                        case "cm^2":
                            response = mmTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = mmTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = mmTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = mmToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = mmToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = mmToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = mmTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = mmToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = mmToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = mmToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "cm^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = cmTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = cmTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = cmTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = cmToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = cmToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = cmToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = cmTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = cmToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = cmToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = cmToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "m^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = mTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = mTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = mTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = mToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = mToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = mToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = mTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = mToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = mToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = mToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "km^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = kmTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = kmTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = kmTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = kmToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = kmToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = kmToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = kmTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = kmToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = kmToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = kmToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "in^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = inTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = inTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = inTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = inTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = inToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = inToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = inTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = inToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = inToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = inToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "ft^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = ftTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = ftTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = ftTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = ftTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = ftToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = ftToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = ftTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = ftToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = ftToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = ftToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "yd^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = ydTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = ydTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = ydTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = ydTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = ydToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = ydToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = ydTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = ydToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = ydToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = ydToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "mi^2":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = miTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = miTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = miTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = miTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = miToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = miToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = miToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = miToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = miToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = miToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "a":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = aTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = aTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = aTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = aTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = aToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = aToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = aToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = aTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = aToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = aToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "ac":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = acTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = acTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = acTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = acTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = acToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = acToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = acToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = acTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = acToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ha":
                            response = acToha(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
                case "ha":
                    switch (response_unit_type) {
                        case "mm^2":
                            response = haTomm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "cm^2":
                            response = haTocm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "m^2":
                            response = haTom(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "km^2":
                            response = haTokm(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "in^2":
                            response = haToin(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ft^2":
                            response = haToft(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "yd^2":
                            response = haToyd(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "mi^2":
                            response = haTomi(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "a":
                            response = haToa(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        case "ac":
                            response = haToac(query);
                            response_value.setText(String.valueOf(response));
                            break;
                        default:
                            response_value.setText(Objects.requireNonNull(query_value.getText()).toString());
                            break;
                    }
                    break;
            }
        }
    }



}