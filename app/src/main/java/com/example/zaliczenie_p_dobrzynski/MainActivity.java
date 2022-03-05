package com.example.zaliczenie_p_dobrzynski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Continiue=  (Button) findViewById(R.id.ButtonGame);
        Button NewGame=  (Button) findViewById(R.id.buttonnNewGame);

        NewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase database123=openOrCreateDatabase("CookieBase.db", Context.MODE_PRIVATE,null);
                database123.execSQL("CREATE TABLE IF NOT EXISTS 'pantry'( funckje STRING, wartosc INTEGER)");
                database123.execSQL("DROP TABLE pantry");

                Data.Cookies=1;
                Data.CookiesPerClick=1;
                Data.Granies=0;
                Data.Bakery=0;
                Data.PriceGranny=100;
                Data.PriceBakery=1000;
                Data.PriceClick=25;

                Intent i = new Intent(getApplicationContext(), Game.class);
                startActivity(i);
            }
        });

        Continiue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database123=openOrCreateDatabase("CookieBase.db", Context.MODE_PRIVATE,null);

                try{
                    Cursor c= database123.rawQuery("Select * from pantry;",null);
                    c.moveToFirst();
                    String CookieData1=c.getString(1);
                    c.moveToNext();
                    String CookieData2=c.getString(1);
                    c.moveToNext();
                    String CookieData3=c.getString(1);
                    c.moveToNext();
                    String CookieData4=c.getString(1);
                    database123.close();

                    Data.Cookies = Integer.parseInt(CookieData1);
                    Data.CookiesPerClick = Integer.parseInt(CookieData2);
                    Data.Granies = Integer.parseInt(CookieData3);
                    Data.Bakery = Integer.parseInt(CookieData4);

                    //Data.checker=false;

                    Intent i = new Intent(getApplicationContext(), Game.class);
                    startActivity(i);

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "There is no save file, create new game!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}