package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DirectoryFragment.PlanetSelectedInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // how do we provide our fragment with information it can use at the time it's created?
        // A fragment has a special property called an argument, it's hidden but it's always there
        // When you set the argument of a fragment, before you attach it to an Activity,
        // Android keeps track of your arguments
        DirectoryFragment directoryFragment = new DirectoryFragment();
    }

    @Override
    public void planetSelected(String planetName) {

    }
}
