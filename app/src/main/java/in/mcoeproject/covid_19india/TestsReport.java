package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TestsReport extends AppCompatActivity {

    private TextView txt_total_tests, txt_positive_cases, txt_updated_tests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_report);

        txt_positive_cases=findViewById(R.id.txt_positive_cases);
        txt_total_tests=findViewById(R.id.txt_total_tests);
        txt_updated_tests=findViewById(R.id.txt_updated_tests);

        String positiveCases=getIntent().getStringExtra("totalPositive");
        String totalTests=getIntent().getStringExtra("totalIndividuals");
        String lastUpdated=getIntent().getStringExtra("lastUpdated");

        txt_positive_cases.setText(positiveCases);
        txt_total_tests.setText(totalTests);
        txt_updated_tests.setText("Last Updated: " +lastUpdated);
    }
}
