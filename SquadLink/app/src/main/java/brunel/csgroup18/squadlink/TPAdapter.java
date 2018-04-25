package brunel.csgroup18.squadlink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nathan Hoy on 29/03/2018.
 */

public class TPAdapter extends RecyclerView.Adapter<TPAdapter.ViewHolderTrainingPlans>{

    private ArrayList<CTPData> data = new ArrayList<CTPData>();

    private LayoutInflater layoutInflater;

    public TPAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    public void setTitleList(ArrayList<CTPData> data){
        this.data = data;
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public ViewHolderTrainingPlans onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.coach_tp_list, parent, false);
        ViewHolderTrainingPlans viewHolder = new ViewHolderTrainingPlans(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTrainingPlans holder, int position) {
        CTPData currentData = data.get(position);
        holder.tpTitle.setText(currentData.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<CTPData> getData() {
        return data;
    }

    public class ViewHolderTrainingPlans extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tpTitle;

        public ViewHolderTrainingPlans(View itemView){
            super(itemView);

            tpTitle = (TextView) itemView.findViewById(R.id.tp_title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.i("Item Clicked", "Clicked at position " + getLayoutPosition());
            Intent intent = new Intent(itemView.getContext(),ViewCTP.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Data", (Serializable) TPAdapter.this.getData());
            bundle.putInt("Position",getLayoutPosition());
            intent.putExtra("Bundle",bundle);
            itemView.getContext().startActivity(intent);


        }

    }


}
