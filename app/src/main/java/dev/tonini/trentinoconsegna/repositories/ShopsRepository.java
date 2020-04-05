package dev.tonini.trentinoconsegna.repositories;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.tonini.trentinoconsegna.AppExecutors;
import dev.tonini.trentinoconsegna.api.models.APIResponse;
import dev.tonini.trentinoconsegna.api.ConsegnoInTrentinoAPI;
import dev.tonini.trentinoconsegna.api.models.ComuniConsegna;
import dev.tonini.trentinoconsegna.api.models.ItaIT;
import dev.tonini.trentinoconsegna.api.models.SearchHit;
import dev.tonini.trentinoconsegna.models.Category;
import dev.tonini.trentinoconsegna.models.City;
import dev.tonini.trentinoconsegna.models.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsRepository extends BaseRepository {
    private final MutableLiveData<List<Shop>> shops;
    private final MutableLiveData<List<Category>> categories;
    private final MutableLiveData<List<City>> cities;
    private final int offsetStep = 100;

    private int shopsOffset = 0;
    private int citiesOffset = 0;

    public ShopsRepository(AppExecutors appExecutors, ConsegnoInTrentinoAPI api) {
        super(appExecutors, api);

        this.shops = new MutableLiveData<>();
        this.categories = new MutableLiveData<>();

        // Add "All city" option
        List<City> cities = new ArrayList<>();
        City allCities = new City();
        allCities.setName("Tutte le città");
        cities.add(allCities);

        this.cities = new MutableLiveData<>();
        this.cities.setValue(cities);
    }

    public LiveData<List<Shop>> getShops() {
        getAppExecutors().networkIO().execute(() -> getApi().getShops(shopsOffset).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (!response.isSuccessful()) {
                    fireError();

                    return;
                }

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
                    newShop.setEmail(data.getEmail());
                    newShop.setWhatsappNumber(data.getWhatsapp());
                    newShop.setFacebookUrl(data.getPaginaFacebook());
                    newShop.setUrl(data.getSitoWeb());
                    newShop.setPhoneNumber(data.getTelefono());

                    try {
                        newShop.setDeliversEverywhere(!data.getConsegnaTuttoTrentino().get(0).equals("No"));
                    } catch (Exception ex) {
                        newShop.setDeliversEverywhere(false);
                    }

                    // Cities
                    List<String> cities = new ArrayList<>();
                    for (ComuniConsegna city: data.getComuniConsegna()) {
                        cities.add(city.getName().getItaIT());
                    }
                    newShop.setDeliveryCities(cities);

                    // Payments
                    List<String> payments = new ArrayList<>();
                    for (String payment: data.getPagamento()) {
                        if (payment.length() == 0) {
                            continue;
                        }

                        payments.add(payment);
                    }
                    newShop.setPaymentMethods(payments);

                    result.add(newShop);
                }

                if (shopsOffset == 0 || shops.getValue() == null || shops.getValue().size() == 0) {
                    shops.setValue(result);
                } else {
                    shops.getValue().addAll(result);
                    shops.setValue(shops.getValue());
                }

                shopsOffset += offsetStep;
                if (shopsOffset < response.body().getTotalCount()) {
                    getShops();
                } else {
                    // Reset offset for next request
                    shopsOffset = 0;
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                fireError();
            }
        }));

        return shops;
    }

    public LiveData<List<Category>> getCategories() {
        getAppExecutors().networkIO().execute(() -> getApi().getCategories().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (!response.isSuccessful()) {
                    fireError();

                    return;
                }

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

    public LiveData<List<City>> getCities() {
        getAppExecutors().networkIO().execute(() -> getApi().getCities(citiesOffset).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (!response.isSuccessful()) {
                    fireError();

                    return;
                }

                // API models to Shop conversion
                List<City> result = new ArrayList<>();
                APIResponse body = response.body();

                for (SearchHit hit: body.getSearchHits()) {
                    City city = new City();
                    city.setName(hit.getMetadata().getName().getItaIT());
                    city.setId(hit.getMetadata().getId());

                    result.add(city);
                }

                if (result.size() > 0) {
                    cities.getValue().addAll(result);
                    cities.setValue(cities.getValue());
                }

                // If there are more data, launch a new request
                citiesOffset += offsetStep;
                if (citiesOffset < response.body().getTotalCount()) {
                    getCities();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                fireError();
            }
        }));

        return cities;
    }

    public void filterShopsByCity(City city) {
        getAppExecutors().networkIO().execute(() -> getApi().getShopsByCity(city.getId()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (!response.isSuccessful()) {
                    fireError();

                    return;
                }

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
                    newShop.setEmail(data.getEmail());
                    newShop.setWhatsappNumber(data.getWhatsapp());
                    newShop.setFacebookUrl(data.getPaginaFacebook());
                    newShop.setUrl(data.getSitoWeb());
                    newShop.setPhoneNumber(data.getTelefono());

                    try {
                        newShop.setDeliversEverywhere(!data.getConsegnaTuttoTrentino().get(0).equals("No"));
                    } catch (Exception ex) {
                        newShop.setDeliversEverywhere(false);
                    }

                    // Cities
                    List<String> cities = new ArrayList<>();
                    for (ComuniConsegna city: data.getComuniConsegna()) {
                        cities.add(city.getName().getItaIT());
                    }
                    newShop.setDeliveryCities(cities);

                    // Payments
                    List<String> payments = new ArrayList<>();
                    for (String payment: data.getPagamento()) {
                        if (payment.length() == 0) {
                            continue;
                        }

                        payments.add(payment);
                    }
                    newShop.setPaymentMethods(payments);

                    result.add(newShop);
                }

                if (shopsOffset == 0 || shops.getValue() == null || shops.getValue().size() == 0) {
                    shops.setValue(result);
                } else {
                    shops.getValue().addAll(result);
                    shops.setValue(shops.getValue());
                }

                shopsOffset += offsetStep;
                if (shopsOffset < response.body().getTotalCount()) {
                    getShops();
                } else {
                    // Reset offset for next request
                    shopsOffset = 0;
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                fireError();
            }
        }));
    }
}
