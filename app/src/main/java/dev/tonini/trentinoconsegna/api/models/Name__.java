
package dev.tonini.trentinoconsegna.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name__ {

    @SerializedName("ita-IT")
    @Expose
    private String itaIT;

    public String getItaIT() {
        return itaIT;
    }

    public void setItaIT(String itaIT) {
        this.itaIT = itaIT;
    }

}
