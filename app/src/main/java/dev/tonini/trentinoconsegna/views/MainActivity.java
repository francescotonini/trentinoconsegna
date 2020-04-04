package dev.tonini.trentinoconsegna.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.List;

import dev.tonini.trentinoconsegna.R;
import dev.tonini.trentinoconsegna.TrentinoConsegnaApp;
import dev.tonini.trentinoconsegna.adapters.ShopsAdapter;
import dev.tonini.trentinoconsegna.databinding.ActivityMainBinding;
import dev.tonini.trentinoconsegna.helpers.SimpleDividerItemDecoration;
import dev.tonini.trentinoconsegna.models.Category;
import dev.tonini.trentinoconsegna.models.Shop;
import dev.tonini.trentinoconsegna.viewmodels.ShopsViewModel;

public class MainActivity extends BaseActivity implements ShopsAdapter.OnItemClickListener {
    private ActivityMainBinding binding;
    private ShopsViewModel viewModel;
    private ShopsAdapter shopsAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Navigation view icon
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.activityMainDrawerLayout, 0, 0);
        actionBarDrawerToggle.syncState();
        binding.activityMainDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        // RecyclerView
        shopsAdapter = new ShopsAdapter(this);
        binding.mainRecyclerView.setAdapter(shopsAdapter);
        binding.mainRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                this,
                getResources().getColor(R.color.divider),
                2));

        getViewModel().getShops().observe(this, (shops) -> {
            shopsAdapter.update(shops);
        });

        getViewModel().getCategories().observe(this, (categories -> {
            Menu menu = binding.activityMainNavigationView.getMenu();
            SubMenu submenu = menu.addSubMenu("Categorie");

            for(Category category: categories) {
                submenu.add(category.getName());
            }

            binding.activityMainNavigationView.invalidate();
        }));
    }

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

    @Override
    public void onItemClick(Shop shop) {
        Intent goToShopDetails = new Intent(this, ShopDetailsActivity.class);
        goToShopDetails.putExtra("shop", (new Gson()).toJson(shop));

        startActivity(goToShopDetails);
    }
}
