package Class;

public class Fournisseur {

    private int id;
    private String nom ;
    private String prenom;
    private String email;
    private int tel;
    public Fournisseur(){}
    public Fournisseur(int id,String nom,String prenom,String email,int tel) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.tel=tel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getTel() {
        return tel;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }



}
