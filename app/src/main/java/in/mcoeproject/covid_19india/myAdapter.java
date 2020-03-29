package in.mcoeproject.covid_19india;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    private StateData[] stateData;

    public myAdapter(StateData[] stateData) {
        this.stateData = stateData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_state, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {
        i++;
        String stateName=stateData[i].getStateName();
        String confirmed=stateData[i].getConfirmed();
        String active=stateData[i].getActive();
        String recovered=stateData[i].getRecovered();
        String deaths=stateData[i].getDeaths();
        String lastupdatedtime=stateData[i].getLastupdatedtime();

        holder.txt_confirmed_st.setText(confirmed);
        holder.txt_active_st.setText(active);
        holder.txt_recovered_st.setText(recovered);
        holder.txt_deaths_st.setText(deaths);
        holder.txt_state.setText(stateName);
        holder.txt_updated_stt.setText(lastupdatedtime);
    }

    @Override
    public int getItemCount() {
        return stateData.length-1;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView txt_confirmed_st, txt_active_st, txt_recovered_st, txt_deaths_st, txt_state, txt_updated_stt;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_confirmed_st=itemView.findViewById(R.id.txt_confirmed_st);
            txt_active_st=itemView.findViewById(R.id.txt_active_st);
            txt_recovered_st=itemView.findViewById(R.id.txt_recovered_st);
            txt_deaths_st=itemView.findViewById(R.id.txt_deaths_st);
            txt_state=itemView.findViewById(R.id.txt_state);
            txt_updated_stt=itemView.findViewById(R.id.txt_updated_stt);

        }
    }
}
