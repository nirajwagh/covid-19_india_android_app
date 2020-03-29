package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DistrictWise extends AppCompatActivity {

    private RecyclerView districtRecycler;
    private RequestQueue requestQueue;
    private String stateName;
    private TextView txt_state_name, txt_no_cases;
    private LottieAnimationView no_cases_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_wise);

        districtRecycler=findViewById(R.id.districtRecycler);
        txt_state_name=findViewById(R.id.txt_state_name);
        no_cases_animation=findViewById(R.id.no_cases_animation);
        txt_no_cases=findViewById(R.id.txt_no_cases);

        no_cases_animation.setVisibility(View.INVISIBLE);
        txt_no_cases.setVisibility(View.INVISIBLE);

        districtRecycler.setLayoutManager(new LinearLayoutManager(this));
        stateName=getIntent().getStringExtra("stateName");
        txt_state_name.setText(stateName);
        requestQueue= Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson() {

            String url="https://api.covid19india.org/state_district_wise.json";
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject state=response.getJSONObject(stateName);
                        JSONObject districtData=state.getJSONObject("districtData");
                        JSONArray keys=districtData.names();
                        int length=keys.length();
                        String district[]=new String[length];
                        String cases[]=new String[length];
                        for (int i = 0; i < length; ++i) {
                            district[i]=keys.getString(i);
                            cases[i]=districtData.getJSONObject(district[i]).getString("confirmed");
                        }

                        districtRecycler.setAdapter(new DistrictAdapter(district, cases));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        no_cases_animation.setVisibility(View.VISIBLE);
                        txt_no_cases.setVisibility(View.VISIBLE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(request);
        }
}
