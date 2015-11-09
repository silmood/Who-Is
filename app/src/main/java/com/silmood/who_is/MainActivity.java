package com.silmood.who_is;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView mCharacterImage;
    private TextView mCharacterName;

    private Character[] mCharacters = new Character[] {
            new Character(R.string.bruce_name, R.drawable.bruce),
            new Character(R.string.jim_name, R.drawable.jim),
            new Character(R.string.nicolas_name, R.drawable.nicolas)
    };

    private Random mRandom;
    private int mCurrentIndex = 0;
    private int mCurrentNameId;

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

        updateCharacter();
    }


    private void updateCharacter(){
        int characterImage = mCharacters[mCurrentIndex].getImageResId();
        int characterName = mCharacters[mRandom.nextInt(3)].getNameResId();

        mCharacterImage.setImageResource(characterImage);
        mCharacterName.setText(characterName);

        mCurrentNameId = characterName;
    }

    private void checkAnswer(boolean answer){
        Toast.makeText(MainActivity.this, answer == compareNames()? R.string.correct_answer : R.string.incorrect_answer,
                Toast.LENGTH_SHORT).show();

        mCurrentIndex = mRandom.nextInt(3);
        updateCharacter();
    }

    private boolean compareNames(){
        return mCurrentNameId == mCharacters[mCurrentIndex].getNameResId();
    }
}
