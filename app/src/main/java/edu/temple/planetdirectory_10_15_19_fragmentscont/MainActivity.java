package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DirectoryFragment.PlanetSelectedInterface {

    DisplayFragment displayFragment;
    String[] planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        planets = getResources().getStringArray(R.array.planets); // have a reference to your planets resources, the Activity now has this object to use as well
        displayFragment = new DisplayFragment(); // old way of creating fragment, doesn't mean we need new way

        // Now Activity just asks Fragment class to give it an instance, it doesn't know how Fragment is created
        DirectoryFragment directoryFragment = DirectoryFragment.newInstance(planets); // new way to create a fragment
        // add a container for your fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1, directoryFragment)
                .add(R.id.container_2, displayFragment)
                .commit();
    }

    @Override
    public void planetSelected(int position) {
        // callback the fragment will invoke with the planetName, call on an instance of a DisplayFragment and tell it which image to display
        int planetResource;
        if (position == 2) {
            planetResource = R.drawable.mars;
        } else if (position == 1) {
            planetResource = R.drawable.pluto;
        } else {
            planetResource = R.drawable.neptune;
        }
        displayFragment.displayPlanet(planetResource);
    }
}
