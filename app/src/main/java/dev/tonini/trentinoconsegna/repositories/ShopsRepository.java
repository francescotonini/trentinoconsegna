package dev.tonini.trentinoconsegna.repositories;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dev.tonini.trentinoconsegna.AppExecutors;
import dev.tonini.trentinoconsegna.api.models.APIResponse;
import dev.tonini.trentinoconsegna.api.ConsegnoInTrentinoAPI;
import dev.tonini.trentinoconsegna.api.models.ItaIT;
import dev.tonini.trentinoconsegna.api.models.SearchHit;
import dev.tonini.trentinoconsegna.models.Category;
import dev.tonini.trentinoconsegna.models.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsRepository extends BaseRepository {
    private final MutableLiveData<List<Shop>> shops;
    private final MutableLiveData<List<Category>> categories;

    public ShopsRepository(AppExecutors appExecutors, ConsegnoInTrentinoAPI api) {
        super(appExecutors, api);

        this.shops = new MutableLiveData<>();
        this.categories = new MutableLiveData<>();
    }

    public LiveData<List<Shop>> getShops() {
        getAppExecutors().networkIO().execute(() -> getApi().getShops().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                // TODO: error management

                // API models to Shop conversion
                List<Shop> result = new ArrayList<>();
                APIResponse body = response.body();

                for (SearchHit hit: body.getSearchHits()) {
                    ItaIT data = hit.getData().getItaIT();

                    Shop newShop = new Shop();
                    newShop.setName(data.getInsegna());
                    newShop.setDescription(data.getDescrizione());
                    newShop.setLat(data.getGeo().getLatitude());
                    newShop.setLon(data.getGeo().getLongitude());
                    newShop.setCategory(data.getCategoriaMerceologica().get(0).getName().getItaIT());

                    // Fix this to avoid common parse exceptions
                    newShop.setDeliversEverywhere(data.getConsegnaTuttoTrentino().get(0) != "No");

                    newShop.setEmail(data.getEmail());
                    newShop.setWhatsappNumber(data.getWhatsapp());
                    newShop.setFacebookUrl(data.getPaginaFacebook());
                    newShop.setPhoneNumber(data.getTelefono());

                    result.add(newShop);
                }

                shops.setValue(result);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        }));

        return shops;
    }

    public LiveData<List<Category>> getCategories() {
        getAppExecutors().networkIO().execute(() -> getApi().getCategories().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                // TODO: error management

                // API models to Shop conversion
                List<Category> result = new ArrayList<>();
                APIResponse body = response.body();

                for (SearchHit hit: body.getSearchHits()) {
                    Category category = new Category();
                    category.setName(hit.getMetadata().getName().getItaIT());

                    result.add(category);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                }

                categories.setValue(result);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        }));

        return categories;
    }
}
