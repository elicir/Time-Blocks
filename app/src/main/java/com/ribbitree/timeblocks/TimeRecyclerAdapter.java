package com.ribbitree.timeblocks;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TimeRecyclerAdapter extends RecyclerView.Adapter<TimeRecyclerAdapter.ViewHolder>{

    RecyclerView recyclerView;
    private ListData[] listData;
    private OnTimeListener onTimeListener;

    public TimeRecyclerAdapter(ListData[] listData, OnTimeListener onTimeListener) {
        this.listData = listData;
        this.onTimeListener = onTimeListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem, onTimeListener, recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.timeView.setText(listData[position].getTime());
    }


    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView timeView;
        public TextView blockView;
        public OnTimeListener onTimeListener;
        public RecyclerView recyclerView;
        public ViewHolder(View itemView, OnTimeListener onTimeListener, RecyclerView recyclerView) {
            super(itemView);
            this.onTimeListener = onTimeListener;
            this.timeView = itemView.findViewById(R.id.time);
            this.blockView = itemView.findViewById(R.id.lineBg);
            this.recyclerView = recyclerView;
            blockView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTimeListener.onTimeClick(getAdapterPosition());
        }
    }

    public interface OnTimeListener{
        void onTimeClick(int position);
    }
}