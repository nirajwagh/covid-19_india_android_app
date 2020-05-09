//Adapter for displaying the districts using Recyclerview.

package in.mcoeproject.covid_19india;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.myViewHolder>{

    private String districts[], cases[];

    public DistrictAdapter(String[] districts, String[] cases) {
        this.districts = districts;
        this.cases = cases;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        //Inflating the district_list.xml file for displaying the district.
        View view=layoutInflater.inflate(R.layout.district_list, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        //Accessing and assigning the current recyclerview item at position "position".
        String districtName=districts[position];
        String confirmedCases=cases[position];
        holder.txt_district_name.setText(districtName);
        holder.txt_district_count.setText(confirmedCases);
    }

    @Override
    public int getItemCount() {
        return districts.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView txt_district_name, txt_district_count;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_district_name=itemView.findViewById(R.id.txt_district_name);
            txt_district_count=itemView.findViewById(R.id.txt_district_count);
        }
    }
}
