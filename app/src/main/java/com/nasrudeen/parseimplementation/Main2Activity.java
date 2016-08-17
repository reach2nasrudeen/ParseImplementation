package com.nasrudeen.parseimplementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RelativeTimeTextView v = (RelativeTimeTextView)findViewById(R.id.timestamp); //Or just use Butterknife!
        v.setReferenceTime(new Date().getTime());
    }
}
