package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestsReport extends AppCompatActivity {

    private TextView txt_total_tests, txt_updated_tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_report);

        txt_total_tests=findViewById(R.id.txt_total_tests);
        txt_updated_tes=findViewById(R.id.txt_updated_tes);

        String totalCases=getIntent().getStringExtra("totalsamplestested");
        String lastUpdated=getIntent().getStringExtra("lastUpdated");

        txt_total_tests.setText(totalCases);
        txt_updated_tes.setText("Last Updated: " +lastUpdated);
    }
}
