package dev.tonini.trentinoconsegna.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.tonini.trentinoconsegna.R;
import dev.tonini.trentinoconsegna.databinding.ItemShopBinding;
import dev.tonini.trentinoconsegna.models.Shop;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Shop shop);
    }

    private List<Shop> shops;
    private OnItemClickListener listener;

    public ShopsAdapter(OnItemClickListener listener) {
        this.shops = new ArrayList<>();
        this.listener = listener;
    }

    public void update(List<Shop> shops) {
        this.shops = shops;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShopBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_shop,
                parent, false
        );

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.set(shops.get(position));
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemShopBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }

        public void set(Shop shop) {
            binding.shopItemText.setText(shop.getName());

            itemView.setOnClickListener(v -> {
               listener.onItemClick(shop);
            });
        }
    }
}
