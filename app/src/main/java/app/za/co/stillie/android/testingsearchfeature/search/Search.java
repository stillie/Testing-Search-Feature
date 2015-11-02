package app.za.co.stillie.android.testingsearchfeature.search;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import app.za.co.stillie.android.testingsearchfeature.R;
import app.za.co.stillie.android.testingsearchfeature.search.searchresults.ISearch;
import app.za.co.stillie.android.testingsearchfeature.search.searchresults.SearchResultsDetailsFragment;


public class Search extends FragmentActivity implements ISearch {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void searchResult(String result) {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        SearchResultsDetailsFragment f2 = (SearchResultsDetailsFragment)manager.findFragmentById(R.id.fragmentDescription);
        f2.changeText(result);
    }


}
