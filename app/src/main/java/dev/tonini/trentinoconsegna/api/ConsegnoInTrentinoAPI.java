package dev.tonini.trentinoconsegna.api;

import dev.tonini.trentinoconsegna.api.models.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ConsegnoInTrentinoAPI {
    @GET("class%20%3D%20%5Besercizio_commerciale%5D")
    Call<APIResponse> getShops();

    @GET("class%20%3D%20%5Bcategoria_merceologica%5D")
    Call<APIResponse> getCategories();
}
