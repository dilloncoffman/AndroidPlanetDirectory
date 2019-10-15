package edu.temple.planetdirectory_10_15_19_fragmentscont;

import android.content.Context;
import android.os.Bundle;
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

    String[] planets;

    private PlanetSelectedInterface fragmentParent;

    // Android needs to create and destroy elements at runtime
    public DirectoryFragment() {
        // Required empty public constructor
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
                fragmentParent.planetSelected(parent.getItemAtPosition(position).toString()); // use toString() since you know you'll get the value you expect instead of a memory location
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
        void planetSelected(String planetName);
    }
}
