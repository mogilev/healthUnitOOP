import java.io.Serializable;

public class HealthProfessionalClass extends Person implements HealthProfessional, Serializable{

    private static final long serialVersionUID = 1L;
    private Category category;

    public HealthProfessionalClass(Category category, String name) {
        super(name);
        this.category = category;
    }

    public String getCategoryName() {
        return this.category.toString();
    }

    public Category getCategory() {
        return this.category;
    }

}