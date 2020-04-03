
package dev.tonini.trentinoconsegna.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("ita-IT")
    @Expose
    private ItaIT itaIT;

    public ItaIT getItaIT() {
        return itaIT;
    }

    public void setItaIT(ItaIT itaIT) {
        this.itaIT = itaIT;
    }

}
