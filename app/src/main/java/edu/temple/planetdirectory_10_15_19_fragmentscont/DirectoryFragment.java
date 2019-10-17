package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectoryFragment.PlanetSelectedInterface} interface
 * to handle interaction events.
 */
public class DirectoryFragment extends Fragment {

    Object[] planets;

    public final static String PLANET_KEY = "planets";

    private PlanetSelectedInterface fragmentParent;

    // Android needs to create and destroy elements at runtime
    public DirectoryFragment() {
        // Required empty public constructor
    }
    // static because it belongs to the class, not to any object; with return type being the class type; method in the class that will return instances of this class
    // factory method that takes set of argument to create the objects
    public static DirectoryFragment newInstance(String[] planets) {
        // how do we provide our fragment with information it can use at the time it's created?
        // A fragment has a special property called an argument, it's hidden but it's always there
        // When you set the argument of a fragment, before you attach it to an Activity,
        // Android keeps track of your arguments
        DirectoryFragment directoryFragment = new DirectoryFragment();
        // put all info a fragment needs in a bundle
        Bundle bundle = new Bundle();
        // never use a hard-coded string as a key, preferably use a final String reference, for our purposes hard-coding is a shortcut
        bundle.putStringArray(PLANET_KEY, planets);
        // set arguments for fragment using bundle
        directoryFragment.setArguments(bundle);
        // returns directoryFragment since this is a factory method, now when Activity needs an instance of DirectoryFragment, it will use this
        return directoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments(); // fragment calls getArguments to access information passed by bundle in Activity
        if (bundle != null) {
            // assume values were provided in bundle
            planets = bundle.getStringArray(PLANET_KEY); // once again, use a final constant for your key value ideally
        }
    }

    // View is inflated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView listView = (ListView) inflater.inflate(R.layout.fragment_directory, container, false);

        // create adapter for listView to give it data, cast fragmentParent as Context
        listView.setAdapter(new ArrayAdapter<>((Context) fragmentParent, android.R.layout.simple_list_item_1, planets));

        // listView needs on OnItemClickedListener to inform the parent, use planetSelected() in PlanetSelectedInterface
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // whenever you click an element, tell the fragment's parent a certain planet was selected
                fragmentParent.planetSelected(position); // instead of passing String of name of planet, we changed the interface
            }
        });

        // return listView for this fragment's layout - this view needs an adapter, can just use an ArrayAdapter
        return listView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlanetSelectedInterface) {
            fragmentParent = (PlanetSelectedInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentParent = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface PlanetSelectedInterface {
        void planetSelected(int position); // pass index of selected item, will pass the index of what was selected instead of passing String for planet name, the position is the bare minimum you need to give to Activity
    }
}
