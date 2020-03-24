package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView txt_total, txt_active, txt_recovered, txt_deaths, txt_updated, txt_tests, txt_today;
    private RequestQueue requestQueue;
    private JSONObject response1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_active=findViewById(R.id.txt_positive_cases);
        txt_total=findViewById(R.id.txt_total_tests);
        txt_recovered=findViewById(R.id.txt_deaths_today);
        txt_deaths=findViewById(R.id.txt_deaths);
        txt_updated=findViewById(R.id.txt_updated_tests);
        txt_tests=findViewById(R.id.txt_tests);
        txt_today=findViewById(R.id.txt_today);


        requestQueue= Volley.newRequestQueue(this);

        parseJson();

        txt_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TodayCases.class);
                try {
                    JSONArray array=response1.getJSONArray("key_values");
                    JSONObject object=array.getJSONObject(0);
                    intent.putExtra("confirmed", object.getString("confirmeddelta"));
                    intent.putExtra("deaths", object.getString("deceaseddelta"));
                    intent.putExtra("lastUpdated", object.getString("lastupdatedtime"));
                    intent.putExtra("recovered", object.getString("recovereddelta"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        txt_tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TestsReport.class);
                try {
                    JSONArray array=response1.getJSONArray("tested");
                    JSONObject object=array.getJSONObject(array.length()-1);
                    intent.putExtra("totalIndividuals", object.getString("totalindividualstested"));
                    intent.putExtra("totalPositive", object.getString("totalpositivecases"));
                    intent.putExtra("lastUpdated", object.getString("updatetimestamp"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void parseJson() {
        String url="https://api.covid19india.org/data.json";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                response1=response;

                try {
                    JSONArray array=response.getJSONArray("statewise");
                    JSONObject object=array.getJSONObject(0);

                    txt_active.setText(object.getString("active"));
                    txt_total.setText(object.getString("confirmed"));
                    txt_recovered.setText(object.getString("recovered"));
                    txt_deaths.setText(object.getString("deaths"));
                    String lastUpdated="Last Updated: "+ object.getString("lastupdatedtime");
                    txt_updated.setText(lastUpdated);
                } catch (JSONException e) {
                    e.printStackTrace();
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
