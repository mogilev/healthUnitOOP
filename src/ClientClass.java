
import java.io.Serializable;

public class ClientClass extends Person implements Client, Serializable {

    private static final long serialVersionUID = 1L;
    private Family family;
    private AgeGroup ageGroup;


    public ClientClass(String name, AgeGroup ageGroup) {
        super(name);
        this.ageGroup = ageGroup;
    }

    public Family getFamily() {
        return this.family;
    }

    public Family setFamily(Family family) {
        this.family = family;
        return this.family;
    }

    public String getAgeGroupName() {
        return this.ageGroup.toString();
    }

    @Override
    public String getFamilyName() {
        return family.getFamilyName();
    }

    @Override
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }


}