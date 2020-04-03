
package dev.tonini.trentinoconsegna.api.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

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
    @SerializedName("ownerId")
    @Expose
    private int ownerId;
    @SerializedName("ownerName")
    @Expose
    private OwnerName ownerName;
    @SerializedName("mainNodeId")
    @Expose
    private int mainNodeId;
    @SerializedName("sectionIdentifier")
    @Expose
    private String sectionIdentifier;
    @SerializedName("stateIdentifiers")
    @Expose
    private List<String> stateIdentifiers = null;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("parentNodes")
    @Expose
    private List<Integer> parentNodes = null;
    @SerializedName("link")
    @Expose
    private String link;

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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public OwnerName getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(OwnerName ownerName) {
        this.ownerName = ownerName;
    }

    public int getMainNodeId() {
        return mainNodeId;
    }

    public void setMainNodeId(int mainNodeId) {
        this.mainNodeId = mainNodeId;
    }

    public String getSectionIdentifier() {
        return sectionIdentifier;
    }

    public void setSectionIdentifier(String sectionIdentifier) {
        this.sectionIdentifier = sectionIdentifier;
    }

    public List<String> getStateIdentifiers() {
        return stateIdentifiers;
    }

    public void setStateIdentifiers(List<String> stateIdentifiers) {
        this.stateIdentifiers = stateIdentifiers;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Integer> getParentNodes() {
        return parentNodes;
    }

    public void setParentNodes(List<Integer> parentNodes) {
        this.parentNodes = parentNodes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
