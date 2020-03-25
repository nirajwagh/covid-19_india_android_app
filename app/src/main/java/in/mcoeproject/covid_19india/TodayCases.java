package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TodayCases extends AppCompatActivity {

    private TextView txt_confirmed_today, txt_deaths_tod, txt_recovered_tod, txt_updated_today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_cases);

        txt_confirmed_today=findViewById(R.id.txt_confirmed_today);
        txt_deaths_tod=findViewById(R.id.txt_deaths_tod);
        txt_recovered_tod=findViewById(R.id.txt_recovered_tod);
        txt_updated_today=findViewById(R.id.txt_updated_today);



        String confirmed=getIntent().getStringExtra("confirmed");
        String deaths=getIntent().getStringExtra("deaths");
        String recovered=getIntent().getStringExtra("recovered");
        String lastUpdated=getIntent().getStringExtra("lastUpdated");

        txt_confirmed_today.setText(confirmed);
        txt_deaths_tod.setText(deaths);
        txt_recovered_tod.setText(recovered);
        txt_updated_today.setText("Last Updated: "+lastUpdated);

    }


}
