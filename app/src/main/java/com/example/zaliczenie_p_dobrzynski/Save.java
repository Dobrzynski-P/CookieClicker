package com.example.zaliczenie_p_dobrzynski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Save extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        TextView textViewCookies= (TextView) findViewById(R.id.textViewCookies);
        TextView textViewClick= (TextView) findViewById(R.id.textViewClickData);
        TextView textViewGranny= (TextView) findViewById(R.id.textViewGranniesData);
        TextView textViewBakery= (TextView) findViewById(R.id.textViewBakieresData);


        textViewCookies.setText(Integer.toString(Data.Cookies));
        textViewClick.setText(Integer.toString(Data.CookiesPerClick));
        textViewGranny.setText(Integer.toString(Data.Granies));
        textViewBakery.setText(Integer.toString(Data.Bakery));


        Button buttonKliksave = (Button) findViewById(R.id.buttonSave);
        buttonKliksave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database123 = openOrCreateDatabase("CookieBase.db", Context.MODE_PRIVATE, null);
                database123.execSQL("DROP TABLE IF EXISTS pantry");
                database123.execSQL("CREATE TABLE IF NOT EXISTS 'pantry'( What STRING, Value INTEGER)");
                String Com1 = "INSERT OR REPLACE INTO pantry (What,Value)Values('Cookies','" + Data.Cookies + "');";
                String Com2 = "INSERT OR REPLACE INTO pantry (What,Value)Values('Click','" + Data.CookiesPerClick + "');";
                String Com3 = "INSERT OR REPLACE INTO pantry (What,Value)Values('Grannies','" + Data.Granies + "');";
                String Com4 = "INSERT OR REPLACE INTO pantry (What,Value)Values('Bakeries','" + Data.Bakery + "');";

                database123.execSQL(Com1);
                database123.execSQL(Com2);
                database123.execSQL(Com3);
                database123.execSQL(Com4);
                database123.close();


                FileOutputStream plikWord;
                String DataforExcel1, DataforExcel2, DataforExcel3, DataforExcel4;
                deleteFile("List.doc");
                try {
                        plikWord = openFileOutput("List.doc", MODE_APPEND);
                        DataforExcel1 = " 1"+ " Cookies " + Data.Cookies+"\n" ;
                        DataforExcel2 = " 2"+ " Click " + Data.CookiesPerClick+"\n"  ;
                        DataforExcel3 = " 3"+ " Grannies " + Data.Granies+"\n"  ;
                        DataforExcel4 = " 4"+ " Bakeries " + Data.Bakery+"\n"  ;
                        plikWord.write(DataforExcel1.getBytes());
                        plikWord.write(DataforExcel2.getBytes());
                        plikWord.write(DataforExcel3.getBytes());
                        plikWord.write(DataforExcel4.getBytes());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}