//Java class for the splash screen

package in.mcoeproject.covid_19india;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


public class Splash extends AppCompatActivity {

    private TextView txt_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txt_error=findViewById(R.id.txt_error);
        txt_error.setVisibility(View.INVISIBLE);

        //Checking is device os version for network connectivity notification.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            confirmInternetConnection(4150);
        }else{
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }, 4150);
        }

        txt_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_error.setVisibility(View.INVISIBLE);
                confirmInternetConnection(500);
            }
        });
    }

    private void confirmInternetConnection(int delay){
        boolean isConnected=checkForInternetConnection();

        if(isConnected){
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }, delay);
        }else{
            txt_error.setVisibility(View.VISIBLE);
        }
    }


    //Method for checking the network connectivity.
    private boolean checkForInternetConnection() {

        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();

    }
}
