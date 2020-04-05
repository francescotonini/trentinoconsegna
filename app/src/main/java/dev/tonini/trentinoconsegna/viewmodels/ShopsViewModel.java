package dev.tonini.trentinoconsegna.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dev.tonini.trentinoconsegna.helpers.SingleLiveEvent;
import dev.tonini.trentinoconsegna.models.Category;
import dev.tonini.trentinoconsegna.models.City;
import dev.tonini.trentinoconsegna.models.Shop;
import dev.tonini.trentinoconsegna.repositories.ShopsRepository;

public class ShopsViewModel extends BaseViewModel {
    private final ShopsRepository shopsRepository;
    private final MediatorLiveData<List<Shop>> shops;
    private final MediatorLiveData<List<City>> cities;

    public ShopsViewModel(@NonNull Application application, ShopsRepository shopsRepository) {
        super(application);

        this.shopsRepository = shopsRepository;
        this.shops = new MediatorLiveData<>();
        this.cities = new MediatorLiveData<>();
    }

    public SingleLiveEvent<Void> getErrors() {
        return this.shopsRepository.getErrors();
    }

    public LiveData<List<Shop>> getShops() {
        if (!this.shops.hasActiveObservers()) {
            this.shops.addSource(this.shopsRepository.getShops(), result -> this.shops.setValue(result));
        }

        return this.shops;
    }

    public LiveData<List<Category>> getCategories() { return shopsRepository.getCategories(); }

    public LiveData<List<City>> getCities() {
        if (!this.cities.hasActiveObservers()) {
            this.cities.addSource(this.shopsRepository.getCities(), result -> this.cities.setValue(result));
        }

        return this.cities;
    }

    public void filterShopsByCity(City city) {
        if (city.getId() == 0) {
            // Show all
            shopsRepository.getShops();

            return;
        }

        shopsRepository.filterShopsByCity(city);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application application;
        private final ShopsRepository shopsRepository;

        public Factory(Application application, ShopsRepository shopsRepository) {
            this.application = application;
            this.shopsRepository = shopsRepository;
        }

        @Override public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ShopsViewModel(application, shopsRepository);
        }
    }
}
