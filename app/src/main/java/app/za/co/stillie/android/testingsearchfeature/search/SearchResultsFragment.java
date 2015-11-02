package app.za.co.stillie.android.testingsearchfeature.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.za.co.stillie.android.testingsearchfeature.R;
import app.za.co.stillie.android.testingsearchfeature.networking.NetworkCallBack;
import app.za.co.stillie.android.testingsearchfeature.search.searchresults.ISearch;

/**
 * Created by Ryan vdW on 2015/10/16.
 */
public class SearchResultsFragment extends Fragment implements NetworkCallBack {


    private static final String TAG = SearchResultsFragment.class.getSimpleName();

    CountrySearchController controller = new CountrySearchController(this);
    String searchPhrase;
    ListView listView;
    ProgressBar pb;

    ISearch mISearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mISearch = (ISearch) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();

        controller.getCountries(getActivity());

        Intent intent = getActivity().getIntent();

        if (intent.hasExtra("search")) {
            searchPhrase = intent.getExtras().getString("search");
        } else {
            searchPhrase = "";
        }

        Log.i(TAG, "Search phrase is " + searchPhrase);

        pb = (ProgressBar) getView().findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
        listView = (ListView) getView().findViewById(R.id.searchResultsListView);


    }

    @Override
    public void callBack() {
        setSearchResultsList();
        TextView emptyResultsTextView = (TextView) getView().findViewById(R.id.emptyResultsSearch);
        emptyResultsTextView.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
    }


    private void setSearchResultsList() {
        final CountryAdapter countryAdapter = new CountryAdapter(getActivity(), controller.getCountriesArrayList());
        countryAdapter.getFilter().filter(searchPhrase);
        countryAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), SearchResultsDetailsFragment.class);
//                intent.putExtra("countrySelected", controller.getCountriesArrayList().get(+position).getCountryName());
//                startActivity(intent);

                Countries countries = (Countries) parent.getItemAtPosition(position);
                String selectedCountry = countries.getCountryName();

                mISearch.searchResult(selectedCountry);

            }
        });

        listView.setAdapter(countryAdapter);
    }

}
