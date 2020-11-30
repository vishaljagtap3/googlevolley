package in.bitcode.googlevolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://www.bitcode.in",
                new StringReqResListener(),
                new StringReqErrListener()
        );
        VolleyRequestQueue.getInstance(this).add(stringRequest);
        //requestQueue.add(stringRequest);

        JSONObject input = new JSONObject();
        try {
            input.put("name", "Vishal");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyCkC8zr3VnxvJk7M4LLy3_S6DDH8eU6N9E";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                input,
                new JSONObjectReqListener(),
                new StringReqErrListener()
        );
        VolleyRequestQueue.getInstance(this).add(jsonObjectRequest);

    }

    class JSONObjectReqListener implements Response.Listener<JSONObject> {
        @Override
        public void onResponse(JSONObject response) {
            Log.e("tag", response.toString());
        }
    }

    class StringReqResListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            Log.e("tag", response);
        }
    }

    class StringReqErrListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("tag", error.getMessage());
            Log.e("tag", "Error while using volley...");
        }
    }

}