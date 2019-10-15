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

        // put all info a fragment needs in a bundle
        Bundle bundle = new Bundle();
        // never use a hard-coded string as a key, preferably use a final String reference, for our purposes hard-coding is a shortcut
        bundle.putStringArray("planets", getResources().getStringArray(R.array.planets));
        // set arguments for fragment using bundle
        directoryFragment.setArguments(bundle);
        // add a container for your fragment
        getSupportFragmentManager().beginTransaction().add(R.id.container_1, directoryFragment).commit();
    }

    @Override
    public void planetSelected(String planetName) {

    }
}
