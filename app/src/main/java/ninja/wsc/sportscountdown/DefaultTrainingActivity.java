package ninja.wsc.sportscountdown;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ninja.wsc.sportscountdown.models.Exercise;

/**
 * Default training activity consists only of a already known set
 * of exercises, totally ungeneric
 */
public class DefaultTrainingActivity extends AppCompatActivity {

    // Contains our exercises. Will be filled on startup of this activity
    private ArrayList<Exercise> exercisesList = new ArrayList<>();

    // UI Elements
    TextView currentExerciseText, nextExercise, countdownText, nextExerciseLabel, exerciseLabel, breakText, navBackButton;

    // Buttons
    Button playButton, holdButton;

    // Countdown object
    CountDownTimer countdown, pauseCountdown = null;

    // Store list index to prevent not showing stuff in for-loops
    int currentListIndex = 0;

    // Ringtone
    Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_training);
        // Keep phone awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Ringtone component
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

        this.constructDefaultExercises();
        this.initUI();

        /*
          Action to execute when the play button is pressed:
          Start the exercise countdowns
         */
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        /*
            No breaks allowed!
         */
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // There is no break!
                breakText.setVisibility(View.VISIBLE);
            }
        });

        navBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }



    /**
     * Initialize activity elements
     */
    private void initUI() {
        // TextViews
        currentExerciseText = findViewById(R.id.currentExercise);
        nextExercise = findViewById(R.id.nextExercise);
        countdownText = findViewById(R.id.countdown);
        nextExerciseLabel = findViewById(R.id.nextExerciseLabel);
        exerciseLabel = findViewById(R.id.excerciseLabel);
        breakText = findViewById(R.id.breakText);
        navBackButton = findViewById(R.id.navBackButton);

        // Buttons
        playButton = findViewById(R.id.playButton);
        holdButton = findViewById(R.id.holdButton);

        // Set up the first exercise in the view
        Exercise initObject = exercisesList.get(0);
        currentExerciseText.setText(initObject.getTitle());
        countdownText.setText("Noch " + initObject.getExerciseDuration() + " Sekunden");
    }

    /**
     * The overall timer logic.
     */
    private void startTimer() {
        if(currentListIndex <= exercisesList.size()) {

            // Exercise countdown
            countdown = new CountDownTimer(exercisesList.get(currentListIndex).getExerciseDuration() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // Update counter every time
                    countdownText.setText("Noch " + millisUntilFinished/1000 + " Sekunden");

                    // Set current exercise text
                    currentExerciseText.setText("" + exercisesList.get(currentListIndex).getTitle());

                    // Hide nextExercise and nextExerciseLabel
                    if(currentListIndex < exercisesList.size() - 1) {
                        nextExerciseLabel.setVisibility(View.VISIBLE);
                        nextExercise.setText(exercisesList.get(currentListIndex + 1).getTitle());
                    } else {
                        // Hide next exercise when it's the last one
                        nextExerciseLabel.setVisibility(View.GONE);
                        nextExercise.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFinish() {
                    // Play sound
                    try {
                        ringtone.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Initialize pause
                    currentExerciseText.setText("Päuschen...");

                    // Pause countdown
                    pauseCountdown = new CountDownTimer(exercisesList.get(currentListIndex).getPauseDuration() * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            countdownText.setText("Noch " + millisUntilFinished/1000 + " Sekunden");
                        }

                        @Override
                        public void onFinish() {
                            // Play sound
                            try {
                                ringtone.play();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Prepare next exercise
                            currentListIndex++;

                            // If it's not the last entry, restart timer
                            if(currentListIndex < exercisesList.size()) {
                                // Restart timer after pause
                                startTimer();
                            } else {
                                congrats();
                            }
                        }
                    }.start();
                }
            }.start();
        }
    }

    /**
     * Set up finisher screen
     */
    private void congrats() {
        // Say something kind to the user
        exerciseLabel.setVisibility(View.GONE);
        currentExerciseText.setText("Das war's");
        countdownText.setText("Genieß Deinen Tag!");

        // Hide buttons and go back to front page
        playButton.setVisibility(View.GONE);
        holdButton.setVisibility(View.GONE);

        // Show Navback Button
        navBackButton.setVisibility(View.VISIBLE);
    }

    /**
     * This method creates default exercises based on a specific plan.
     */
    private void constructDefaultExercises() {
        this.exercisesList.add(new Exercise("Squats", 90, 20));
        this.exercisesList.add(new Exercise("Sitting Twists", 90, 20));
        this.exercisesList.add(new Exercise("Renegade Raws", 90, 20));

        this.exercisesList.add(new Exercise("Plank Jump-Ins", 90, 20));
        this.exercisesList.add(new Exercise("Flutter Kicks", 90, 20));
        this.exercisesList.add(new Exercise("Back Kicks", 90, 20));

        this.exercisesList.add(new Exercise("Bridge Taps", 90, 20));
        this.exercisesList.add(new Exercise("Climbers", 90, 20));
        this.exercisesList.add(new Exercise("Butt-Ups", 90, 20));

        this.exercisesList.add(new Exercise("Back Rotations", 90, 20));
        this.exercisesList.add(new Exercise("Knee Tuck Jumps", 90, 20));
        this.exercisesList.add(new Exercise("Cross Punch Sit Ups", 90, 20));
    }
}
