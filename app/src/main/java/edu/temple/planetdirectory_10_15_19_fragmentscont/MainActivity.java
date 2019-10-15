package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DirectoryFragment.PlanetSelectedInterface {

    DisplayFragment displayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayFragment = new DisplayFragment(); // old way of creating fragment, doesn't mean we need new way

        // Now Activity just asks Fragment class to give it an instance, it doesn't know how Fragment is created
        DirectoryFragment directoryFragment = DirectoryFragment.newInstance(getResources().getStringArray(R.array.planets)); // new way to create a fragment
        // add a container for your fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1, directoryFragment)
                .add(R.id.container_2, displayFragment)
                .commit();
    }

    @Override
    public void planetSelected(String planetName) {
        // callback the fragment will invoke with the planetName, call on an instance of a DisplayFragment and tell it which image to display
        displayFragment.displayPlanet(planetName);
    }
}
