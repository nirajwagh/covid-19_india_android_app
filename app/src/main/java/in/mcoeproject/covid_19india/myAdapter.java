package in.mcoeproject.covid_19india;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private StateData[] stateData;

    public myAdapter(StateData[] stateData) {
        this.stateData = stateData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text1.setText(stateData[position].getState());

        String details =
                "Confirmed: " + stateData[position].getConfirmed() +
                        " | Active: " + stateData[position].getActive() +
                        " | Recovered: " + stateData[position].getRecovered() +
                        " | Deaths: " + stateData[position].getDeaths();

        holder.text2.setText(details);
    }

    @Override
    public int getItemCount() {
        return stateData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}