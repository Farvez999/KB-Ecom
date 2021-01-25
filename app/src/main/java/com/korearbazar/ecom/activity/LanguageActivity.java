package com.korearbazar.ecom.activity;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.LanguageData;

import java.util.Arrays;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

//    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
//    private PSDialogMsg psDialogMsg;

    @VisibleForTesting
//    private AutoClearedValue<FragmentLanguageBinding> binding;
    private int selectedPosition = 0;

    private List<LanguageData> languageDataList = Arrays.asList(
            new LanguageData("English", "en", ""),
            new LanguageData("Arabic", "ar", ""),
            new LanguageData("Spanish", "es", ""),
            new LanguageData("Chinese (Mandarin)", "zh", ""),
            new LanguageData("French", "fr", ""),
            new LanguageData("German", "de", ""),
            new LanguageData("India (Hindi)", "hi", "rIN"),
            new LanguageData("Indonesian", "in", ""),
            new LanguageData("Italian", "it", ""),
            new LanguageData("Japanese", "ja", ""),
            new LanguageData("Korean", "ko", ""),
            new LanguageData("Malay", "ms", ""),
            new LanguageData("Portuguese", "pt", ""),
            new LanguageData("Russian", "ru", ""),
            new LanguageData("Thai", "th", ""),
            new LanguageData("Turkish", "tr", ""));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);


    }
}