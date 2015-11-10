package com.silmood.who_is;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String KEY_INDEX = "index";
    public static final String KEY_NAME_ID = "name";

    private ImageView mCharacterImage;
    private TextView mCharacterName;

    private Character[] mCharacters = new Character[] {
            new Character(R.string.bruce_name, R.drawable.bruce),
            new Character(R.string.jim_name, R.drawable.jim),
            new Character(R.string.nicolas_name, R.drawable.nicolas)
    };

    private Random mRandom;
    private int mCurrentIndex = 0;
    private int mCurrentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trueButton = (Button) findViewById(R.id.button_true);
        Button falseButton = (Button) findViewById(R.id.button_false);
        mCharacterImage = (ImageView) findViewById(R.id.image_character);
        mCharacterName = (TextView) findViewById(R.id.name_character);
        mRandom = new Random();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        if (savedInstanceState != null)
            restoreCharacterData(savedInstanceState);

        updateCharacter();
    }

    private void restoreCharacterData(Bundle savedInstanceState){
        mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        mCurrentName = savedInstanceState.getInt(KEY_NAME_ID, 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putInt(KEY_NAME_ID, mCurrentName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    private void updateCharacter(){
        int characterImage = mCharacters[mCurrentIndex].getImageResId();
        int characterName = mCharacters[mCurrentName].getNameResId();

        mCharacterImage.setImageResource(characterImage);
        mCharacterName.setText(characterName);
    }

    private void checkAnswer(boolean answer){
        Toast.makeText(MainActivity.this, answer == compareNames()? R.string.correct_answer : R.string.incorrect_answer,
                Toast.LENGTH_SHORT).show();

        mCurrentIndex = mRandom.nextInt(3);
        mCurrentName = mRandom.nextInt(3);

        updateCharacter();
    }

    private boolean compareNames(){
        return mCurrentIndex == mCurrentName;
    }
}
