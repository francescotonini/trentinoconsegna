package dev.tonini.trentinoconsegna.views;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;

import dev.tonini.trentinoconsegna.R;
import dev.tonini.trentinoconsegna.databinding.ActivityShopDetailsBinding;
import dev.tonini.trentinoconsegna.models.Shop;
import dev.tonini.trentinoconsegna.viewmodels.BaseViewModel;

public class ShopDetailsActivity extends BaseActivity {
    private ActivityShopDetailsBinding binding;
    private Shop shop;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_details;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, getLayoutId());


        shop = (new Gson()).fromJson(getIntent().getStringExtra("shop"), Shop.class);

        binding.activityShopDetailsCategory.setText("Alimentari");
        binding.activityShopDetailsName.setText(shop.getName());
        binding.activityShopDetailsDescription.setText(shop.getDescription());
    }
}
