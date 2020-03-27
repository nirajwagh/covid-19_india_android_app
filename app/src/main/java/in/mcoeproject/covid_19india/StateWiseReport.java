package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StateWiseReport extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private  StateData[] stateData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_wise_report);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue= Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson() {
        String url="https://api.covid19india.org/data.json";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array=response.getJSONArray("statewise");
                    GsonBuilder gsonBuilder=new GsonBuilder();
                    Gson gson=gsonBuilder.create();
                    stateData=gson.fromJson(String.valueOf(array), StateData[].class);
                    recyclerView.setAdapter(new myAdapter(stateData));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );

        requestQueue.add(request);

    }
}
