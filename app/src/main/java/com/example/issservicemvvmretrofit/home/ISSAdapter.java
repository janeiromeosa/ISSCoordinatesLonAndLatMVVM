package com.example.issservicemvvmretrofit.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issservicemvvmretrofit.R;
import com.example.issservicemvvmretrofit.data.Response;

import java.util.ArrayList;
import java.util.List;

public class ISSAdapter extends RecyclerView.Adapter<ISSAdapter.ISSViewHolder> {

    private List<Response> responseList = new ArrayList<>();

    public void setData(List<Response> data){
        responseList.clear();
        responseList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ISSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_iss_pass, viewGroup, false);
        return new ISSViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ISSViewHolder issViewHolder, int position) {
        issViewHolder.tvDuration.setText(responseList.get(position).getDuration());
        issViewHolder.tvRiseTime.setText(responseList.get(position).getRisetime());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    static class ISSViewHolder extends RecyclerView.ViewHolder{
        TextView tvRiseTime;
        TextView tvDuration;

        public ISSViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRiseTime = itemView.findViewById(R.id.tvRiseTime);
            tvDuration = itemView.findViewById(R.id.tvDuration);
        }
    }
}
