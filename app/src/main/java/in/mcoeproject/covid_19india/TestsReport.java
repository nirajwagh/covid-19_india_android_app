package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TestsReport extends AppCompatActivity {

    private TextView txt_total_tests_rep, txt_positive_cas, txt_updated_tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_report);

        txt_positive_cas=findViewById(R.id.txt_positive_cas);
        txt_total_tests_rep=findViewById(R.id.txt_total_tests_rep);
        txt_updated_tes=findViewById(R.id.txt_updated_tes);

        String positiveCases=getIntent().getStringExtra("totalPositive");
        String totalTests=getIntent().getStringExtra("totalIndividuals");
        String lastUpdated=getIntent().getStringExtra("lastUpdated");

        txt_positive_cas.setText(positiveCases);
        txt_total_tests_rep.setText(totalTests);
        txt_updated_tes.setText("Last Updated: " +lastUpdated);
    }
}
