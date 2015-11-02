package app.za.co.stillie.android.testingsearchfeature.search;

import android.app.Activity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.za.co.stillie.android.testingsearchfeature.networking.CustomNetwork;
import app.za.co.stillie.android.testingsearchfeature.networking.NetworkCallBack;


/**
 * Created by Ryan vdW on 2015/10/09.
 */
public class CountrySearchController {

    String url = "http://stillie.co.za/flags/countries.json";

    private NetworkCallBack mCallBack;

    public CountrySearchController(NetworkCallBack callBack) {
        mCallBack = callBack;
    }

    ArrayList<Countries> countriesArrayList;

    public void getCountries(Activity activity) {

        RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        CustomNetwork jsObjRequest = new CustomNetwork(url, createRequestSuccessListener(), createRequestErrorListener());

        requestQueue.add(jsObjRequest);
    }

    //     Method called when there is an error in the network call
//    ----------------------------------------Error Listener for Request---------------------------------------
    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Networking", "Error is at: " + error.toString());
            }
        };
    }

    private Response.Listener<JSONArray> createRequestSuccessListener() {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Log.i("Networking", "Response is: " + response.toString());
                countriesArrayList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tempObject = response.getJSONObject(i);
                        String countryName = tempObject.getString("name");
                        String countryFlag = tempObject.getString("flag_32");
                        countriesArrayList.add(new Countries(countryName, countryFlag));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setCountriesArrayList(countriesArrayList);
                mCallBack.callBack();
            }
        };

    }

    public ArrayList<Countries> getCountriesArrayList() {
        return countriesArrayList;
    }

    public void setCountriesArrayList(ArrayList<Countries> countriesArrayList) {
        this.countriesArrayList = countriesArrayList;
    }
}
