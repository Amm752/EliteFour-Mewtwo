package com.example.ahmad.anagram;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
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
        static int score=0;
        boolean done = false;

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
                                if (guess.getText().toString().equals("ENTER")) {

                                        if (answer.getText().toString().toLowerCase().equals(word.toLowerCase())) {
                                                ana.setText("CORRECT");
                                                score += 10;
                                        } else {
                                                ana.setText("Nooooo");
                                        }
                                        nextRound();

                                } else if (guess.getText().toString().equals("NEXT ROUND")) {
                                        resetGame();

                                } else if (guess.getText().toString().equals("SEE RESULTS")) {
                                        startActivity(new Intent(getApplicationContext(), Results.class));
                                }
                        }
                });
                Timer timer = new Timer(true);
                timer.scheduleAtFixedRate(new Task(), 1000, 1000);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
                }
        public void btnQuit(View v){
            if(v.getId() == R.id.Quitbtn){
                Intent i = new Intent(StartGame.this, MainActivity.class );
                startActivity(i);
            }
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.action_settings) {
                return true;
                }

                return super.onOptionsItemSelected(item);
                }

        private void nextRound() {
                if(round == total) {
                guess.setText("SEE RESULTS");
                } else {
                guess.setText("NEXT ROUND");
                }
                }

        private void resetGame() {
                round++;
                count.setText(round + "/" + total);
                word = getWord();
                ana.setText(generateAnagram(word));
                answer.setText("");
                answer.setHint("Enter answer here...");
                guess.setText("ENTER");
        }

        private String generateAnagram(String input) {
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
                return words.get((int) Math.round(Math.random() * (words.size() - 5)));
                }

        class Task extends TimerTask
        {
                long seconds;
                final TextView text;
                final Runnable runnable;

                public Task()
                {
                        super();
                        text = (TextView)findViewById(R.id.timerText);
                        text.setText("00:00");
                        runnable = new Runnable()
                        {
                                @Override
                                public void run()
                                {
                                        text.setText(String.format("%02d:%02d", ++seconds / 60,
                                                seconds % 60));
                                }
                        };
                }

                @Override
                public void run()
                {
                        if(!done)
                                StartGame.this.runOnUiThread(runnable);
                }
        }
}
              
