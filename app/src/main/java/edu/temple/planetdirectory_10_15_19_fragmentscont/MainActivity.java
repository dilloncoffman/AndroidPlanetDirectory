package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.support.v4.app.Fragment;
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

        DirectoryFragment directoryFragment;
        Fragment container1Fragment = getSupportFragmentManager().findFragmentById(R.id.container_1);
        // check if a Fragment is already there, is this the result of a restart or something else; skips recreating one if it's already there
        if (container1Fragment == null) {
            // Now Activity just asks Fragment class to give it an instance, it doesn't know how Fragment is created
            // add a container for your fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_1, DirectoryFragment.newInstance(planets))
                    .commit();
        } else if (container1Fragment instanceof DisplayFragment) { // grab instance of DirectoryFragment
            // pop it off stack if it is a DisplayFragment so it doesn't show over planet list
            getSupportFragmentManager().popBackStack();
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

        displayFragment = new DisplayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DisplayFragment.PLANET_KEY, planetResource);
        displayFragment.setArguments(bundle);

        if (singlePane) {
            // Add DisplayFragment where current DirectoryFragment is, operation to execute this is asynchronous; this causes our app to crash - recall the Fragment's lifecycle, until it executes the way it should, certain things aren't available to you;
            // When we call displayPlanet below then this Fragment Transaction has NOT completed so our app crashes, imageView will be NULL in DisplayFragment
            // By now you've provided the fragment with the info that it needs
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null) // allows us to hit back arrow and go back to last DirectoryFragment rather than going back to home screen and closing the app
                    .replace(R.id.container_1, displayFragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_2, displayFragment)
                    .commit();
        }
    }
}
