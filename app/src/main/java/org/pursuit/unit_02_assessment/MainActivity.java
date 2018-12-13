package org.pursuit.unit_02_assessment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText inputEditText;
    private Button submitButton;
    RandomGame randomGame;
    public int randomNumber;
    public static final String RESULT = "result";
    private String editTextInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            editTextInput = inputEditText.getText().toString();
            inputEditText.setText(editTextInput);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = "mail.pursuit.org";
                Uri uri = Uri.fromParts("mailto", emailAddress, null);
                Intent email = new Intent(Intent.ACTION_SENDTO, uri);
                email.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                email.putExtra(Intent.EXTRA_SUBJECT, "Email from Pursuit");
                email.putExtra(Intent.EXTRA_TEXT, "This is my text!");
                startActivity(Intent.createChooser(email, "Choose an email client: "));
            }
        });

        randomGame = new RandomGame();
        inputEditText = findViewById(R.id.number_edittext);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String editTextValue = inputEditText.getText().toString();
               randomGame.getRandomNumber();
                if (randomGame.checkGuess(randomGame.stringToInt(editTextValue), randomNumber)) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    String winningPhrase = randomGame.getStringResult(true);
                    intent.putExtra(RESULT, winningPhrase);
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    String losingPhase = randomGame.getStringResult(false);
                    intent.putExtra(RESULT, losingPhase);
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toast) {
            Toast.makeText(MainActivity.this, "Hello, Pursuit", Toast.LENGTH_LONG).show();
            // TODO: Write code to handle the "Toast" Option click, and display a Toast to the screen with the text "Hello, Pursuit!".
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_phone:
                String phone = "+2125551212";
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(phoneIntent);
                break;
            case R.id.nav_sms:
                String textNumber = "+2125551212";
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setData(Uri.parse("smsto:" + textNumber)); // This ensures only SMS apps respond
                textIntent.putExtra("sms_body", "Welcome to Pursuit");
                startActivity(textIntent);
                break;
            case R.id.nav_map_location:
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("0,0?q=40.7429595,-73.94192149999998(Pursuit Android HQ)"));
                startActivity(mapIntent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
