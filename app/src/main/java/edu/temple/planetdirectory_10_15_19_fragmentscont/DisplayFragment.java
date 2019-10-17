package edu.temple.planetdirectory_10_15_19_fragmentscont;


import android.os.Bundle;
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

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageView = (ImageView) inflater.inflate(R.layout.fragment_display, container, false);
        return imageView; // so Activity can attach it to its ViewGroup
    }

    // public method for Activity to talk to fragment
    public void displayPlanet(int planetResource) {
        imageView.setImageResource(planetResource);
    }

}
