
package dev.tonini.trentinoconsegna.api.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dev.tonini.trentinoconsegna.api.models.SearchHit;

public class APIResponse {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("nextPageQuery")
    @Expose
    private String nextPageQuery;
    @SerializedName("totalCount")
    @Expose
    private int totalCount;
    @SerializedName("searchHits")
    @Expose
    private List<SearchHit> searchHits = null;
    @SerializedName("facets")
    @Expose
    private List<Object> facets = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getNextPageQuery() {
        return nextPageQuery;
    }

    public void setNextPageQuery(String nextPageQuery) {
        this.nextPageQuery = nextPageQuery;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<SearchHit> getSearchHits() {
        return searchHits;
    }

    public void setSearchHits(List<SearchHit> searchHits) {
        this.searchHits = searchHits;
    }

    public List<Object> getFacets() {
        return facets;
    }

    public void setFacets(List<Object> facets) {
        this.facets = facets;
    }

}
