package dev.tonini.trentinoconsegna.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dev.tonini.trentinoconsegna.models.Category;
import dev.tonini.trentinoconsegna.models.Shop;
import dev.tonini.trentinoconsegna.repositories.ShopsRepository;

public class ShopsViewModel extends BaseViewModel {
    private final ShopsRepository shopsRepository;

    public ShopsViewModel(@NonNull Application application, ShopsRepository shopsRepository) {
        super(application);

        this.shopsRepository = shopsRepository;
    }

    public LiveData<List<Shop>> getShops() {
        return shopsRepository.getShops();
    }

    public LiveData<List<Category>> getCategories() { return shopsRepository.getCategories(); }

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
