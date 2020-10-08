package no.trymv.fantj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import no.trymv.fantj.data.model.FantMarket;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ConversationViewHolder> {
    OnClickListener listener = position -> {};
    List<FantMarket> items;

    public MarketAdapter() {
        this.items = new ArrayList<>();
    }

    public List<FantMarket> getConversations() {
        return items;
    }

    public void setConversations(List<FantMarket> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        FantMarket item = getConversations().get(position);
        holder.title.setText(item.getItem(0).getTitle());
        holder.description.setText(item.getItem(0).getDescription());
        // TODO get item 0?
    }

    @Override
    public int getItemCount() {
        return getConversations().size();
    }

    interface OnClickListener {
        void onClick(int position);
    }

    class ConversationViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public ConversationViewHolder(View view) {
            super(view);
            view.setOnClickListener(v -> listener.onClick(getAdapterPosition()));
            this.title = view.findViewById(R.id.title);
            this.description = view.findViewById(R.id.description);
        }
    }
}