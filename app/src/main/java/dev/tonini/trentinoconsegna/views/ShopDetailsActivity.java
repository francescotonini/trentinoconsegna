package dev.tonini.trentinoconsegna.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import dev.tonini.trentinoconsegna.R;
import dev.tonini.trentinoconsegna.databinding.ActivityShopDetailsBinding;
import dev.tonini.trentinoconsegna.models.Shop;
import dev.tonini.trentinoconsegna.viewmodels.BaseViewModel;

public class ShopDetailsActivity extends BaseActivity implements OnMapReadyCallback {
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

        binding.activityShopDetailsCategory.setText(shop.getCategory());
        binding.activityShopDetailsName.setText(shop.getName());

        ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.activity_shop_details_map))
            .getMapAsync(this);

        // Description
        if (shop.getDeliveryNotes() != null) {
            binding.activityShopDetailsDescription.setText(shop.getDescription() + "\n" + shop.getDeliveryNotes());
        } else {
            binding.activityShopDetailsDescription.setText(shop.getDescription());
        }

        // Delivery cities
        String cities = "";
        if (shop.isDeliversEverywhere()) {
            cities = "Tutto il Trentino";
        } else if (shop.getDeliveryCities() != null && shop.getDeliveryCities().size() > 0) {
            for (String city: shop.getDeliveryCities()) {
                cities += city + ", ";
            }

            cities = cities.replaceAll(", $", "");
        } else {
            binding.activityShopCitiesLayout.setVisibility(View.GONE);
        }
        binding.activityShopDetailsCities.setText(cities);

        // Phone number
        if (shop.getPhoneNumber() != null) {
            binding.activityShopDetailsPhone.setText(shop.getPhoneNumber());
        } else {
            binding.activityShopPhoneLayout.setVisibility(View.GONE);
        }

        // WA
        if (shop.getWhatsappNumber() != null) {
            binding.activityShopDetailsWa.setText(shop.getWhatsappNumber());
        } else {
            binding.activityShopWaLayout.setVisibility(View.GONE);
        }

        // FB
        if (shop.getFacebookUrl() != null) {
            binding.activityShopDetailsFb.setText(shop.getFacebookUrl());
        } else {
            binding.activityShopFbLayout.setVisibility(View.GONE);
        }

        // Euro
        String payments = "";
        if (shop.getPaymentMethods() != null && shop.getPaymentMethods().size() > 0) {
            for (String payment: shop.getPaymentMethods()) {
                payments += payment + ", ";
            }

            payments = payments.replaceAll(", $", "");
        } else {
            binding.activityShopPaymentLayout.setVisibility(View.GONE);
        }
        binding.activityShopDetailsPayment.setText(payments);

        // Email
        if (shop.getEmail() != null) {
            binding.activityShopDetailsEmail.setText(shop.getEmail());
        } else {
            binding.activityShopEmailLayout.setVisibility(View.GONE);
        }

        // Web
        if (shop.getUrl() != null) {
            binding.activityShopDetailsWeb.setText(shop.getUrl());
        } else {
            binding.activityShopWebLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng pos = new LatLng(shop.getLat(), shop.getLon());

        googleMap.addMarker(new MarkerOptions().position(pos));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
    }
}
