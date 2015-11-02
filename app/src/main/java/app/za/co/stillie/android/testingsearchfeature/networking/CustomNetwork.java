package app.za.co.stillie.android.testingsearchfeature.networking;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ryan vdW on 2015/10/09.
 */
public class CustomNetwork extends Request<JSONArray> {

    private Response.Listener<JSONArray> listener;

    public CustomNetwork(String url,
                         Response.Listener<JSONArray> reponseListener,
                         Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = reponseListener;
    }

    public CustomNetwork(int method,
                         String url,
                         Response.Listener<JSONArray> responseListener,
                         Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = responseListener;
    }


    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        listener.onResponse(response);
    }


    @Override
    public byte[] getBody() throws AuthFailureError {
        return super.getBody();
    }


}
