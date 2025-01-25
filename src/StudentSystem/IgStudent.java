package StudentSystem;

public class IgStudent extends Student {
    public IgStudent(String name, String id, int age) {
        super(name, id, age);
    }

    @Override
    public String toString() {
        return "International Student: " + super.toString();
    }
}
