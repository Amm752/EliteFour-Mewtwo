package com.example.ahmad.anagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Results extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_results);
   }

   public void buttonStartClicked(View v) {
    startActivity(new Intent(getApplicationContext(), StartGame.class));
   }

   public void buttonHomeClicked(View v) {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
   }
  }