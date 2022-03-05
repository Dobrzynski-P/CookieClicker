package com.example.zaliczenie_p_dobrzynski;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Shop extends AppCompatActivity {
   Button BuyGranny,BuyBakery,BuyClick;
   TextView TextViewPriceGranny,TextViewPriceClick,TextViewPriceBakery,TextViewCookiesShop;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

         TextViewCookiesShop = findViewById(R.id.TextViewCookies);
         TextViewPriceClick =  findViewById(R.id.TextViewClickPrice);
         TextViewPriceGranny = findViewById(R.id.TextViewPriceGranny);
         TextViewPriceBakery =  findViewById(R.id.TextViewPriceBakery);

         TextViewCookiesShop.setText(Integer.toString(Data.Cookies));
         TextViewPriceClick.setText(Integer.toString(Data.PriceClick));
         TextViewPriceGranny.setText(Integer.toString(Data.PriceGranny));
         TextViewPriceBakery.setText(Integer.toString(Data.PriceBakery));

         BuyClick=(Button) findViewById(R.id.ButtonUpgClick);
         BuyGranny=(Button) findViewById(R.id.ButtonGranny);
         BuyBakery=(Button) findViewById(R.id.ButtonBakery);

         BuyBakery.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Data.PriceBakery=(Data.Bakery+1)*1000;
                 if(Data.Cookies>=Data.PriceBakery)
                 {
                     Data.Cookies=Data.Cookies-Data.PriceBakery;
                     Data.Bakery++;
                     Data.PriceBakery=(Data.Bakery+1)*1000;

                     TextViewCookiesShop.setText(Integer.toString(Data.Cookies));
                     TextViewPriceBakery.setText(Integer.toString(Data.PriceBakery));
                 }
                 else{
                     Toast.makeText(getApplicationContext(), "Not enought COOKIES!", Toast.LENGTH_SHORT).show();
                 }
             }
         });

         BuyGranny.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Data.PriceGranny=(Data.Granies+1)*100;
                 if(Data.Cookies>=Data.PriceGranny)
                 {
                     Data.Cookies=Data.Cookies-Data.PriceGranny;
                     Data.Granies++;
                     Data.PriceGranny=(Data.Granies+1)*100;

                     TextViewCookiesShop.setText(Integer.toString(Data.Cookies));
                     TextViewPriceGranny.setText(Integer.toString(Data.PriceGranny));
                 }
                 else{
                     Toast.makeText(getApplicationContext(), "Not enought COOKIES!", Toast.LENGTH_SHORT).show();
                 }
             }
         });

        BuyClick.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Data.PriceClick=Data.CookiesPerClick*25;
        if(Data.Cookies>=Data.PriceClick)
        {
            Data.Cookies=Data.Cookies-Data.PriceClick;
            Data.CookiesPerClick++;
            Data.PriceClick=Data.CookiesPerClick*25;

            TextViewCookiesShop.setText(Integer.toString(Data.Cookies));
            TextViewPriceClick.setText(Integer.toString(Data.PriceClick));
        }
        else{
            Toast.makeText(getApplicationContext(), "Not enought COOKIES!", Toast.LENGTH_SHORT).show();
        }
    }
});
     }
}