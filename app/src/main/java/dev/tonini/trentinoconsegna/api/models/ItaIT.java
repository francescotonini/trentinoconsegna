
package dev.tonini.trentinoconsegna.api.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItaIT {

    @SerializedName("ragione_sociale")
    @Expose
    private String ragioneSociale;
    @SerializedName("insegna")
    @Expose
    private String insegna;
    @SerializedName("categoria_merceologica")
    @Expose
    private List<CategoriaMerceologica> categoriaMerceologica = null;
    @SerializedName("sotto_categoria")
    @Expose
    private Object sottoCategoria;
    @SerializedName("descrizione")
    @Expose
    private String descrizione;
    @SerializedName("piva")
    @Expose
    private Object piva;
    @SerializedName("geo")
    @Expose
    private Geo geo;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("whatsapp")
    @Expose
    private String whatsapp;
    @SerializedName("sito_web")
    @Expose
    private String sitoWeb;
    @SerializedName("pagina_facebook")
    @Expose
    private String paginaFacebook;
    @SerializedName("comuni_consegna")
    @Expose
    private List<ComuniConsegna> comuniConsegna = null;
    @SerializedName("comunita_consegna")
    @Expose
    private List<ComunitaConsegna> comunitaConsegna = null;
    @SerializedName("consegna_tutto_trentino")
    @Expose
    private List<String> consegnaTuttoTrentino = null;
    @SerializedName("note_ambito")
    @Expose
    private Object noteAmbito;
    @SerializedName("note_consegna")
    @Expose
    private Object noteConsegna;
    @SerializedName("pagamento")
    @Expose
    private List<String> pagamento = null;
    @SerializedName("recaptcha")
    @Expose
    private Object recaptcha;

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getInsegna() {
        return insegna;
    }

    public void setInsegna(String insegna) {
        this.insegna = insegna;
    }

    public List<CategoriaMerceologica> getCategoriaMerceologica() {
        return categoriaMerceologica;
    }

    public void setCategoriaMerceologica(List<CategoriaMerceologica> categoriaMerceologica) {
        this.categoriaMerceologica = categoriaMerceologica;
    }

    public Object getSottoCategoria() {
        return sottoCategoria;
    }

    public void setSottoCategoria(Object sottoCategoria) {
        this.sottoCategoria = sottoCategoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Object getPiva() {
        return piva;
    }

    public void setPiva(Object piva) {
        this.piva = piva;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    public String getPaginaFacebook() {
        return paginaFacebook;
    }

    public void setPaginaFacebook(String paginaFacebook) {
        this.paginaFacebook = paginaFacebook;
    }

    public List<ComuniConsegna> getComuniConsegna() {
        return comuniConsegna;
    }

    public void setComuniConsegna(List<ComuniConsegna> comuniConsegna) {
        this.comuniConsegna = comuniConsegna;
    }

    public List<ComunitaConsegna> getComunitaConsegna() {
        return comunitaConsegna;
    }

    public void setComunitaConsegna(List<ComunitaConsegna> comunitaConsegna) {
        this.comunitaConsegna = comunitaConsegna;
    }

    public List<String> getConsegnaTuttoTrentino() {
        return consegnaTuttoTrentino;
    }

    public void setConsegnaTuttoTrentino(List<String> consegnaTuttoTrentino) {
        this.consegnaTuttoTrentino = consegnaTuttoTrentino;
    }

    public Object getNoteAmbito() {
        return noteAmbito;
    }

    public void setNoteAmbito(Object noteAmbito) {
        this.noteAmbito = noteAmbito;
    }

    public Object getNoteConsegna() {
        return noteConsegna;
    }

    public void setNoteConsegna(Object noteConsegna) {
        this.noteConsegna = noteConsegna;
    }

    public List<String> getPagamento() {
        return pagamento;
    }

    public void setPagamento(List<String> pagamento) {
        this.pagamento = pagamento;
    }

    public Object getRecaptcha() {
        return recaptcha;
    }

    public void setRecaptcha(Object recaptcha) {
        this.recaptcha = recaptcha;
    }

}
