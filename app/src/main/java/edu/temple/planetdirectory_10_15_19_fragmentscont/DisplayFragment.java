package edu.temple.planetdirectory_10_15_19_fragmentscont;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    ImageView imageView;
    public static final String PLANET_KEY = "planet";
    int planetResource = -1;

    public DisplayFragment() {
        // Required empty public constructor
    }

    // factory method to quickly create DisplayFragments
    public static DisplayFragment newInstance(int planetResource) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PLANET_KEY, planetResource);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            planetResource = bundle.getInt(PLANET_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageView = (ImageView) inflater.inflate(R.layout.fragment_display, container, false);

        if (planetResource >= 0) { // now DisplayFragment checks if planetResource exists
            displayPlanet(planetResource); // only calling this indirectly, after it's passed
        }

        return imageView; // so Activity can attach it to its ViewGroup
    }

    // public method for Activity to talk to fragment
    public void displayPlanet(int planetResource) {
        imageView.setImageResource(planetResource);
    }

}
