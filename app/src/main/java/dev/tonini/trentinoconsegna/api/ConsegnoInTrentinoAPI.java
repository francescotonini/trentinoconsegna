package dev.tonini.trentinoconsegna.api;

import dev.tonini.trentinoconsegna.api.models.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConsegnoInTrentinoAPI {
    @GET("class = [esercizio_commerciale] limit 100 offset {offset} sort [ insegna => asc ]")
    Call<APIResponse> getShops(@Path("offset") int offset);

    @GET("comuni_consegna.id = [ {id} ] limit 30")
    Call<APIResponse> getShopsByCity(@Path("id") int cityId);

    @GET("class = [categoria_merceologica] limit 100")
    Call<APIResponse> getCategories();

    @GET("class = [comune] limit 100 offset {offset} sort [ name => asc ]")
    Call<APIResponse> getCities(@Path("offset") int offset);
}
