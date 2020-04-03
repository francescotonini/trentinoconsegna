
package dev.tonini.trentinoconsegna.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerName {

    @SerializedName("eng-GB")
    @Expose
    private String engGB;

    public String getEngGB() {
        return engGB;
    }

    public void setEngGB(String engGB) {
        this.engGB = engGB;
    }

}
