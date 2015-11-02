package app.za.co.stillie.android.testingsearchfeature.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.za.co.stillie.android.testingsearchfeature.R;
import app.za.co.stillie.android.testingsearchfeature.search.Search;

public class HomeScreenFragment extends FragmentActivity {

    Button button;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_screen);

        button = (Button) findViewById(R.id.homeButton);
        text = (EditText) findViewById(R.id.searchEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenFragment.this, Search.class);
                final String searchText = text.getText().toString();
                intent.putExtra("search", searchText);
                startActivity(intent);
            }
        });
    }

}
