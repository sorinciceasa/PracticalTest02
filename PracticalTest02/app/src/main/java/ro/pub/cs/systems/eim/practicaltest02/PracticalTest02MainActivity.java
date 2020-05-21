package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    // Server widgets
    private Button setButton = null;
    private Button resetButton = null;
    private Button pollButton = null;

    // Client widgets
    private EditText hoursEditText = null;
    private EditText minutesEditText = null;


    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private SetButtonClickListener setButtonClickListener = new SetButtonClickListener();
    private class SetButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = "13";
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }

    }


    private ResetButtonClickListener resetButtonClickListener = new ResetButtonClickListener();
    private class ResetButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }


        private PollButtonClickListener pollButtonClickListener = new PollButtonClickListener();

        private class PollButtonClickListener implements Button.OnClickListener {

            @Override
            public void onClick(View view) {

            }

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_practical_test02_main);

            setButton = (Button)findViewById(R.id.button);
            resetButton = (Button)findViewById(R.id.button2);
            pollButton = (Button)findViewById(R.id.button3);

            hoursEditText = (EditText)findViewById(R.id.hour);
            minutesEditText = (EditText)findViewById(R.id.minutes);
        }

        @Override
        protected void onDestroy() {
            Log.i(Constants.TAG, "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
            if (serverThread != null) {
                serverThread.stopThread();
            }
            super.onDestroy();
        }
    }
