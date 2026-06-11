public class Person {

    protected String id;
    protected String name;
    protected String phone;
    protected String gender;
    protected int age;
    protected double height;
    protected double weight;

    public Person(String id, String name, String phone,
                  String gender, int age, double height, double weight) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String displayInfo() {
        return "ID              : " + id + "\n" +
               "Name            : " + name + "\n" +
               "Phone Number    : " + phone + "\n" +
               "Gender          : " + gender + "\n" +
               "Age             : " + age + "\n" +
               "Height          : " + height + " cm\n" +
               "Weight          : " + weight + " kg";
    }
}