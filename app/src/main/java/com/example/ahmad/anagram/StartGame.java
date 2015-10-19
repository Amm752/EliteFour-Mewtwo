package com.example.ahmad.anagram;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class StartGame extends Activity {
        String word;
        TextView count;
        EditText answer;
        TextView ana;
        Button guess;
        ArrayList<String> words;
        int round = 1;
        int total = 10;
        public int score=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.startgame);

                try {
                initializeWords(this);
                } catch (Exception e) {
                words = new ArrayList<>();
                System.out.println(e);
                }

                word = getWord();
                count = (TextView) findViewById(R.id.roundCounter);
                ana = (TextView) findViewById(R.id.anagram);
                answer = (EditText)findViewById(R.id.answer);
                guess = (Button) findViewById(R.id.enterGuess);

                //Initialize round counter and anagram
                count.setText(round + "/" + total);
                ana.setText(generateAnagram(word));
                ana.setTextSize(72);

                guess.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                if (guess.getText().toString().equals("ENTER")) {                              //If button is in ENTER mode

                if (answer.getText().toString().toLowerCase().equals(word.toLowerCase())) {     //Check if inputted guess is correct
                ana.setText("SUCCESS");
                    score+=10;
                } else {
                ana.setText("WRONG");
                }
                nextRound();                                                                    //Set button to NEXT ROUND mode

                } else if (guess.getText().toString().equals("NEXT ROUND")) {                  //If button is in NEXT ROUND mode
                resetGame();                                                                    //reset for next round

                } else if (guess.getText().toString().equals("SEE RESULTS")) {                  //If button is in SEE RESULTS mode
                        startActivity(new Intent(getApplicationContext(), Results.class));              //Go to results page
                }
                }
                });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
                }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                return true;
                }

                return super.onOptionsItemSelected(item);
                }

        private void nextRound() {
                //Set text of button for going to next round
                if(round == total) {
                guess.setText("SEE RESULTS");
                } else {
                guess.setText("NEXT ROUND");
                }
                }

        private void resetGame() {
                //Reset the anagram, increment round counter, set button for entering an answer
                round++;
                count.setText(round + "/" + total);

                ana.setText(generateAnagram(getWord()));
                answer.setText("");
                answer.setHint("Enter answer here...");
                guess.setText("ENTER");
        }

        private String generateAnagram(String input) {
                //Shuffles the letters in a word to generate an anagram
                ArrayList<Character> anagram = new ArrayList<>();
                for(char letter:input.toLowerCase().toCharArray()) {
                anagram.add(letter);
                }
                StringBuilder output = new StringBuilder(input.length());
                while(anagram.size()!=0){
                int rand = (int)(Math.random() * anagram.size());
                output.append(anagram.remove(rand));
                }
                return output.toString().toUpperCase();
                }

        private void initializeWords(Activity activity)
                throws XmlPullParserException, IOException {
                words = new ArrayList<>();
                Resources res = activity.getResources();
                XmlResourceParser xml = res.getXml(R.xml.words);
                int eventType = xml.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.TEXT) {
                words.add(xml.getText());
                }
                eventType = xml.next();
                }
                }

        private String getWord() {
                return words.get((int) Math.round(Math.random() * (words.size() - 1)));
                }
                }