package dev.tonini.trentinoconsegna.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.tonini.trentinoconsegna.R;
import dev.tonini.trentinoconsegna.TrentinoConsegnaApp;
import dev.tonini.trentinoconsegna.adapters.ShopsAdapter;
import dev.tonini.trentinoconsegna.databinding.ActivityMainBinding;
import dev.tonini.trentinoconsegna.helpers.DialogHelper;
import dev.tonini.trentinoconsegna.helpers.SimpleDividerItemDecoration;
import dev.tonini.trentinoconsegna.models.Category;
import dev.tonini.trentinoconsegna.models.City;
import dev.tonini.trentinoconsegna.models.Shop;
import dev.tonini.trentinoconsegna.viewmodels.ShopsViewModel;

public class MainActivity extends BaseActivity implements ShopsAdapter.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private ActivityMainBinding binding;
    private ShopsViewModel viewModel;
    private ShopsAdapter shopsAdapter;
    // private ActionBarDrawerToggle actionBarDrawerToggle;

    private int spinnerPreviousId = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected ShopsViewModel getViewModel() {
        if (viewModel == null) {
            ShopsViewModel.Factory factory = new ShopsViewModel.Factory(
                    getApplication(),
                    ((TrentinoConsegnaApp)getApplication()).getDataRepository().getShopsRepository());

            viewModel = new ViewModelProvider(this, factory).get(ShopsViewModel.class);
        }

        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutId());

        // Toolbar id is set on toolbar.xml, hence I can't use binding
        setSupportActionBar(findViewById(R.id.toolbar));
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Navigation view icon
        /*
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.activityMainDrawerLayout, 0, 0);
        actionBarDrawerToggle.syncState();
        binding.activityMainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        */

        binding.mainRecyclerViewRefresh.setEnabled(false);
        binding.mainRecyclerViewRefresh.setRefreshing(true);

        // RecyclerView
        shopsAdapter = new ShopsAdapter(this);
        binding.mainRecyclerView.setAdapter(shopsAdapter);
        binding.mainRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                this,
                getResources().getColor(R.color.divider),
                1));

        getViewModel().getErrors().observe(this, (error) -> {
            DialogHelper.show(this, R.string.error_title, R.string.error_description, R.string.ok);
        });

        getViewModel().getShops().observe(this, (shops) -> {
            binding.mainRecyclerViewRefresh.setRefreshing(false);

            shopsAdapter.update(shops);
        });

        /*
        getViewModel().getCategories().observe(this, (categories -> {
            Menu menu = binding.activityMainNavigationView.getMenu();
            SubMenu submenu = menu.addSubMenu("Categorie");

            for(Category category: categories) {
                submenu.add(category.getName());
            }

            binding.activityMainNavigationView.invalidate();
        }));
        */

        getViewModel().getCities().observe(this, cities -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                cities.stream().map(v -> v.getName()).collect(Collectors.toList())
            );

            adapter.setDropDownViewResource(R.layout.spinner_menu_item);

            AppCompatSpinner spinner = findViewById(R.id.activity_main_cities);
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        });
    }

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.activityMainDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        actionBarDrawerToggle.syncState();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    */

    @Override
    public void onItemClick(Shop shop) {
        Intent goToShopDetails = new Intent(this, ShopDetailsActivity.class);
        goToShopDetails.putExtra("shop", (new Gson()).toJson(shop));

        startActivity(goToShopDetails);
    }
;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == spinnerPreviousId) {
            return;
        }

        spinnerPreviousId = position;

        binding.mainRecyclerViewRefresh.setRefreshing(true);

        getViewModel().filterShopsByCity(getViewModel().getCities().getValue().get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
