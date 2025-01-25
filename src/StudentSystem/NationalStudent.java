package StudentSystem;

public class NationalStudent extends Student {
    public NationalStudent(String name, String id, int age) {
        super(name, id, age);
    }

    @Override
    public String toString() {
        return "National Student: " + super.toString();
    }
}
