package dev.tonini.trentinoconsegna.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dev.tonini.trentinoconsegna.AppExecutors;
import dev.tonini.trentinoconsegna.api.models.APIResponse;
import dev.tonini.trentinoconsegna.api.ConsegnoInTrentinoAPI;
import dev.tonini.trentinoconsegna.api.models.ItaIT;
import dev.tonini.trentinoconsegna.api.models.SearchHit;
import dev.tonini.trentinoconsegna.models.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsRepository extends BaseRepository {
    private MutableLiveData<List<Shop>> shops;


    public ShopsRepository(AppExecutors appExecutors, ConsegnoInTrentinoAPI api) {
        super(appExecutors, api);

        this.shops = new MutableLiveData<>();
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
}
