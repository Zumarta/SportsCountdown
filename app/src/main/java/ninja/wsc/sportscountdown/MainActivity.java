package ninja.wsc.sportscountdown;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startUpDefaultTraining(View view) {
        Intent intent = new Intent(this, DefaultTrainingActivity.class);
        // Start new activity
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Mö Gimmick
        if (resultCode == RESULT_OK) {
            TextView loveStory = findViewById(R.id.loveStory);
            loveStory.setVisibility(View.VISIBLE);
        }
    }
}
