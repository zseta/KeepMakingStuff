package making.stuff.recyclerwithcardviews;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<PersonalData> dataList;

    public DataAdapter(List<PersonalData> list){
        dataList = list;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        PersonalData data = dataList.get(position);
        holder.tvName.setText(data.name);
        holder.tvEmail.setText(data.email);
        holder.tvCity.setText(data.city);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvEmail, tvCity;
        public DataViewHolder(View itemView){
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            tvCity = (TextView) itemView.findViewById(R.id.city);
        }
    }
}