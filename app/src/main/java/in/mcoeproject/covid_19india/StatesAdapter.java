//Adapter for the recyclerview for displaying states.

package in.mcoeproject.covid_19india;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.myViewHolder> {

    private String stateName[];
    private Context mContext;

    public StatesAdapter(String[] stateName, Context context) {
        this.stateName = stateName;
        this.mContext = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.state_list, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final String name=stateName[position];
        holder.txt_name.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, DistrictWise.class);
                intent.putExtra("stateName", name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateName.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView txt_name;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name=itemView.findViewById(R.id.txt_name);
        }
    }
}
