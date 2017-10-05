package com.saik0116algonquinlive.hilo_game;
/**
 *  Purpose/Description of your app
 * Josh Saikaly (saik0116) HI LO GAME
 * You must guess the random number generated from 1-1000 within 10 guesses.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.app.DialogFragment;

import java.util.Timer;


public class MainActivity extends Activity {
    private static final String ABOUT_DIALOG_TAG = "About Dialog";




    public int theNumber = 0;
    public int numbTries = 0;


    private EditText theGuess;
    TextView myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theNumber = (int) Math.ceil(Math.random() * 1000);
        theGuess = (EditText) findViewById(R.id.user_guess);
        final Button rollBtn = (Button) findViewById(R.id.rollBtn);
        final Button resetBtn = (Button) findViewById(R.id.resetBtn);


        myText = (TextView) findViewById(R.id.amountLeft);



        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                theNumber = (int) Math.ceil(Math.random() * 1000);
                numbTries = 0;
                Button rollBtn = (Button) findViewById(R.id.rollBtn);
                rollBtn.setEnabled(true);
                theGuess.setText("");
                myText.setText("");

//        Toast.makeText(getApplicationContext(),
//                Integer.toString(theNumber),
//                Toast.LENGTH_SHORT).show();
            }


        });
        resetBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), theNumber + " Is the number, cheater",
                        Toast.LENGTH_SHORT).show();


                // theGuess.setText(Integer.toString(theNumber));
                // theGuess.setText("");

                return true;
            }
        });
        rollBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Toast.makeText( getApplicationContext(),
//                        Integer.toString(theNumber),
//                        Toast.LENGTH_SHORT).show();

                String userRoll = theGuess.getText().toString();


                if (userRoll.isEmpty()) {
                    theGuess.setError("You must add a number");
                    theGuess.requestFocus();
                    return;
                }

                if (!userRoll.matches("[0-9]+")) {
                    theGuess.setError("You must use only digits");
                    theGuess.requestFocus();
                    return;
                }


                int userGuess = Integer.valueOf(userRoll);
                // int numOfTries = 4;

                if (userGuess > 1000 || Integer.valueOf(userRoll) < 1) {

                    theGuess.setError("Please enter a number between 1-1000");
                    theGuess.requestFocus();
                    return;
                }

//                if (userGuess != theNumber){
//                    Toast.makeText(getApplicationContext(), "adding " + numbTries,
//                            Toast.LENGTH_SHORT).show();
//                }

                if (numbTries >= 9 && userGuess != theNumber) {
                    Toast.makeText(getApplicationContext(), "You lose, please reset to play again",
                            Toast.LENGTH_SHORT).show();
                    myText.setText("Game Over!");
                    rollBtn.setEnabled(false);
                    return;


                }


                if (userGuess == theNumber) {

                    if (numbTries <= 5) {
                        Toast.makeText(getApplicationContext(), "Superior Win! Reset to play again",
                                Toast.LENGTH_SHORT).show();
                    }

                    if ((numbTries > 5) && (numbTries <= 10)) {
                        Toast.makeText(getApplicationContext(), "Excellent win! Reset to play again",
                                Toast.LENGTH_SHORT).show();
                    }


                    //  theGuess.setTextColor(getResources().getColor(R.color.red));
                    myText.setText("You Win!!");
                    rollBtn.setEnabled(false);
                    // rollBtn.setTextColor(R.color.colorTextLight);

                    return;


                } else if (userGuess > theNumber) {
                    Toast.makeText(getApplicationContext(),
                            "Too High! Try lower",
                            Toast.LENGTH_SHORT).show();
                    numbTries++;

                } else if (userGuess < theNumber) {
                    Toast.makeText(getApplicationContext(),
                            "Too Low! Try Higher",
                            Toast.LENGTH_SHORT).show();
                    numbTries++;

                }
                myText.setText(numbTries + " Guess Count");

                theGuess.setText("");

            }



        }); // end onclick


    } // end oncreate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void onLongClick(View view) {
//        theGuess.setText(theNumber);
//    }
//
//    public void onClickReset(View view) {
//        theNumber = (int) Math.ceil(Math.random() * 10);
//        numbTries = 0;
//        Button rollBtn = (Button) findViewById(R.id.rollBtn);
//        rollBtn.setEnabled(true);
//        theGuess.setText("");
//
////        Toast.makeText(getApplicationContext(),
////                Integer.toString(theNumber),
////                Toast.LENGTH_SHORT).show();
//    }


} // endclass
