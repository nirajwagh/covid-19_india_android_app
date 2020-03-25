package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView txt_total, txt_active, txt_recovered, txt_deaths, txt_updated;
    private CardView card_today, card_tests, card_about, card_state, card_district, card_myths;
    private RequestQueue requestQueue;
    private JSONObject response1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_total=findViewById(R.id.txt_total);
        txt_active=findViewById(R.id.txt_active);
        txt_recovered=findViewById(R.id.txt_recovered);
        txt_deaths=findViewById(R.id.txt_deaths);
        txt_updated=findViewById(R.id.txt_updated);
        card_today=findViewById(R.id.card_today);
        card_tests=findViewById(R.id.card_tests);
        card_about=findViewById(R.id.card_about);
        card_state=findViewById(R.id.card_state);
        card_district=findViewById(R.id.card_district);
        card_myths=findViewById(R.id.card_myths);


        requestQueue= Volley.newRequestQueue(this);

        parseJson();

        card_today.setOnClickListener(new View.OnClickListener() {
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

        card_tests.setOnClickListener(new View.OnClickListener() {
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

        card_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        card_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon...", Toast.LENGTH_LONG).show();
            }
        });

        card_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon...", Toast.LENGTH_LONG).show();

            }
        });

        card_myths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon...", Toast.LENGTH_LONG).show();

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

                    Log.d("dataaa", object.toString());
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
