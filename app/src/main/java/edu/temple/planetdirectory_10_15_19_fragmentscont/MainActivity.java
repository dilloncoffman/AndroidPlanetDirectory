package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DirectoryFragment.PlanetSelectedInterface {

    DisplayFragment displayFragment;
    String[] planets;

    boolean singlePane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Know which layout you're in
        singlePane = (findViewById(R.id.container_2) == null); // if there is no container_2 (only on landscape), then there's only one container

        planets = getResources().getStringArray(R.array.planets); // have a reference to your planets resources, the Activity now has this object to use as well
        displayFragment = new DisplayFragment(); // old way of creating fragment, doesn't mean we need new way

        // Now Activity just asks Fragment class to give it an instance, it doesn't know how Fragment is created
        DirectoryFragment directoryFragment = DirectoryFragment.newInstance(planets); // new way to create a fragment
        // add a container for your fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1, directoryFragment)
                .commit();

        if (!singlePane) {
            // then you're in double pane, landscape mode, add fragment in container_2
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_2, displayFragment)
                    .commit();
        }
    }

    @Override
    public void planetSelected(int position) {
        // callback the fragment will invoke with the planetName, call on an instance of a DisplayFragment and tell it which image to display
        int planetResource;
        if (position == 0) {
            planetResource = R.drawable.pluto;
        } else if (position == 1) {
            planetResource = R.drawable.neptune;
        } else if (position == 2) {
            planetResource = R.drawable.jupiter;
        } else if (position == 3) {
            planetResource = R.drawable.saturn;
        } else {
            planetResource = R.drawable.mars;
        }

        if (singlePane) {
            // Add DisplayFragment where current DirectoryFragment is, operation to execute this is asynchronous; this causes our app to crash - recall the Fragment's lifecycle, until it executes the way it should, certain things aren't available to you;
            // When we call displayPlanet below then this Fragment Transaction has NOT completed so our app crashes, imageView will be NULL in DisplayFragment

            // To Fix
            // 1. Bundle
            Bundle bundle = new Bundle();
            bundle.putInt(DisplayFragment.PLANET_KEY, planetResource);
            // 2.
            displayFragment.setArguments(bundle);
            // By now you've provided the fragment with the info that it needs
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_1, displayFragment)
                    .commit();
        } else {
            displayFragment.displayPlanet(planetResource); // only gets called in landscape mode
        }
    }
}
