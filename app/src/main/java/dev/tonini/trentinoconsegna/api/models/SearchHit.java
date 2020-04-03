
package dev.tonini.trentinoconsegna.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchHit {

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("data")
    @Expose
    private Data data;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
