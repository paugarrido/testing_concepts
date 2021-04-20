package cat.itb.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        usernameEditText = findViewById(R.id.usernameEditText);

//        button.setOnClickListener(v -> button.setText(R.string.back));
//        button.setOnClickListener(v -> button.setText(R.string.logged));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(MainActivity.this, SecondActivity.class);
                secondActivity.putExtra("username", usernameEditText.getText().toString());
                startActivity(secondActivity);
            }
        });
    }
}