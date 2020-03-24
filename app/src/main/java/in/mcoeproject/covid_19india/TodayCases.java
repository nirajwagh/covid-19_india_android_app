package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class TodayCases extends AppCompatActivity {

    private TextView txt_confirmed_today, txt_deaths_today, txt_recovered_today, txt_updated_today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_cases);

        txt_confirmed_today=findViewById(R.id.txt_total_tests);
        txt_deaths_today=findViewById(R.id.txt_deaths_today);
        txt_recovered_today=findViewById(R.id.txt_positive_cases);
        txt_updated_today=findViewById(R.id.txt_updated_tests);



        String confirmed=getIntent().getStringExtra("confirmed");
        String deaths=getIntent().getStringExtra("deaths");
        String recovered=getIntent().getStringExtra("recovered");
        String lastUpdated=getIntent().getStringExtra("lastUpdated");

        txt_confirmed_today.setText(confirmed);
        txt_deaths_today.setText(deaths);
        txt_recovered_today.setText(recovered);
        txt_updated_today.setText("Last Updated: "+lastUpdated);

    }


}
