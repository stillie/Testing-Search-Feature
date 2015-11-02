package app.za.co.stillie.android.testingsearchfeature.search.searchresults;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.za.co.stillie.android.testingsearchfeature.R;

/**
 * Created by Ryan vdW on 2015/10/16.
 */
public class SearchResultsDetailsFragment extends Fragment {

    TextView selectedCountry;
    String country;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        selectedCountry = (TextView) getActivity().findViewById(R.id.selectedCountry);
        if (savedInstanceState != null) {
            country = savedInstanceState.getString("SelectedCountry");
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Bundle text = new Bundle();
        String textfrom = selectedCountry.getText().toString();
        text.putString("SelectedCountry", textfrom);
        super.onSaveInstanceState(outState);
    }


    public void changeText(String data) {

        if (data != null) {
            country = data;
            selectedCountry.setText(data);
        }
    }
}
