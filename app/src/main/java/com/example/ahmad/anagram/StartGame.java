package com.example.ahmad.anagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by ahmad on 10/12/2015.
 */
public class StartGame extends Activity {

    // This is the part for the KeyBoard
    EditText editInput;
    //  End Of the KeyBoard PArt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgame);

        // This is the part for the KeyBoard
        editInput =(EditText) findViewById(R.id.edit_input);
        //  End Of the KeyBoard PArt
    }

    public void onButtonClick2(View v){
        if(v.getId() == R.id.Bscore){
            Intent i = new Intent(StartGame.this, Scorepage.class );
            startActivity(i);
        }
    }

    // This is the part for the KeyBoard
    public void showKeyboard(View v) {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editInput, InputMethodManager.SHOW_IMPLICIT);
    }
    //  End Of the KeyBoard PArt
}
