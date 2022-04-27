import java.io.Serializable;

public class FamilyClass implements Family, Serializable {

    private static final long serialVersionUID = 1L;
    String nome;

    public FamilyClass(String nome) {
        this.nome = nome;
    }

    @Override
    public String getFamilyName() {
        return this.nome;
    }

}
