import java.io.Serializable;

abstract public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}