package com.example.zaliczenie_p_dobrzynski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    TextView TextViewCookieNumber, TextViewPassive,TextViewClick;
    ImageButton ButtonClick;
    Button ButtonSave,ButtonShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra);

        TextViewCookieNumber= (TextView) findViewById(R.id.TextViewCookieNumber) ;
        TextViewPassive= (TextView) findViewById(R.id.textViewPassive) ;
        TextViewClick= (TextView) findViewById(R.id.TextViewClick) ;
        ButtonClick= (ImageButton) findViewById(R.id.imageButtonCookie);
        ButtonShop= (Button) findViewById(R.id.buttonShop);
        ButtonSave= (Button) findViewById(R.id.buttonSave);

        if(Data.checker==true) {
            RefreshPerSec();
        }
        Refresh();

        ButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.Cookies=Data.Cookies + Data.CookiesPerClick;
                TextViewCookieNumber.setText(Integer.toString(Data.Cookies));
                ButtonClick.setImageResource(R.drawable.cookiebreak);
            }
        });
        ButtonShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Shop.class);
                startActivity(i);
            }
        });

        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Save.class);
                startActivity(i);
            }
        });

    }

    private void RefreshPerSec() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Data.Cookies=Data.Cookies + Data.Granies + (10*Data.Bakery);
               Data.checker=false;
                RefreshPerSec();
            }
        }, 1000);
    }

    private void Refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TextViewCookieNumber.setText(Integer.toString(Data.Cookies));
                TextViewPassive.setText(String.valueOf((Data.Granies+(Data.Bakery*10))));
                TextViewClick.setText(String.valueOf(Data.CookiesPerClick));
                ButtonClick.setImageResource(R.drawable.cookie);

                Refresh();
            }
        }, 1000);
    }
}