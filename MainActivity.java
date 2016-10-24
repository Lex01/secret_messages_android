package androidapps.secmessages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    //set up variables for GUI
    EditText txtIn;
    EditText txtKey;
    EditText txtOut;
    SeekBar sb;
    Button button;


    public String encode(String message, int k) {

        String output = "";
        char key = (char)k;

        for(int x = 0; x < message.length(); x++) {	//go through message
            char in = message.charAt(x);	//take one letter from string
            if (in >= 'A' && in <= 'Z') {	//capital letters
                in += key;
                if (in > 'Z') {	// if goes past Z, wrap back around
                    in -= 26;
                }
                if (in < 'A') {	// if it goes less than A, wrap around
                    in += 26;
                }
            }
            else if (in >= 'a' && in <= 'z') {	//lower case letters
                in += key;
                if (in > 'z') {	//if it goes past z, wrap around
                    in -= 26;
                }
                if (in < 'a') {	// if it goes before a, wrap around
                    in += 26;
                }
            }
            else if (in >= '0' && in <= '9') {
                in += key%10;

                if (in > '9') {
                    in -= 10;
                }
                if (in < '0') {
                    in += 10;
                }
            }
            output += in;	//write encoded letter to output string
        }
        return output;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtIn = (EditText)findViewById(R.id.txtIn);
        txtKey = (EditText)findViewById(R.id.txtKey);
        txtOut = (EditText)findViewById(R.id.txtOut);
        sb = (SeekBar)findViewById(R.id.seekBar);
        button = (Button)findViewById(R.id.button);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int key = progress - 13; //to change to -13 -> 13
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                txtOut.setText(out);
                txtKey.setText("" + key);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int key = Integer.parseInt(txtKey.getText().toString());
                int progress = key + 13; //to display on the seekBar
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                txtOut.setText(out);
                sb.setProgress(progress);


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
}
