package ninja.wsc.sportscountdown;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_training);

        this.constructDefaultExercises();
    }

    public void startTraining() {
        TextView currentExerciseText = findViewById(R.id.currentExercise);
        final TextView nextExerciseText = findViewById(R.id.nextExerciseLabel);
        final TextView countdownText = findViewById(R.id.countdown);

        for (int i = 0; i < exercisesList.size(); i++) {
            currentExerciseText.setText(exercisesList.get(i).getTitle());

            if (i < exercisesList.size()) {
                nextExerciseText.setText(exercisesList.get(i + 1).getTitle());
            }

            // Countdown for the exercise
            new CountDownTimer(exercisesList.get(i).getExerciseDuration(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countdownText.setText("" + millisUntilFinished /  1000);
                }

                @Override
                public void onFinish() {
                    // Do nothing, wait for pause timer
                }
            }.start();

            currentExerciseText.setText("Short break");

            // Countdown for the pause
            new CountDownTimer(exercisesList.get(i).getExerciseDuration(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countdownText.setText("" + millisUntilFinished /  1000);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
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
