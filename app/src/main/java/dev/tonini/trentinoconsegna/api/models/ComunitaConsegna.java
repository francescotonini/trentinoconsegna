
package dev.tonini.trentinoconsegna.api.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dev.tonini.trentinoconsegna.api.models.Name___;

public class ComunitaConsegna {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("remoteId")
    @Expose
    private String remoteId;
    @SerializedName("classIdentifier")
    @Expose
    private String classIdentifier;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("name")
    @Expose
    private Name___ name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("mainNodeId")
    @Expose
    private String mainNodeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public String getClassIdentifier() {
        return classIdentifier;
    }

    public void setClassIdentifier(String classIdentifier) {
        this.classIdentifier = classIdentifier;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Name___ getName() {
        return name;
    }

    public void setName(Name___ name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMainNodeId() {
        return mainNodeId;
    }

    public void setMainNodeId(String mainNodeId) {
        this.mainNodeId = mainNodeId;
    }

}
