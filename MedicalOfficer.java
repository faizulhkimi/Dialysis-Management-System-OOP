public class MedicalOfficer extends Person {

    private String department;

    public MedicalOfficer(String id, String name, String phone,
                          String gender, int age, double height,
                          double weight, String department) {

        super(id, name, phone, gender, age, height, weight);
        this.department = department;
    }

    public String displayInfo() {
        return "Medical Officer ID : " + id + "\n" +
               "Name               : " + name + "\n" +
               "Phone Number       : " + phone + "\n" +
               "Gender             : " + gender + "\n" +
               "Age                : " + age + "\n" +
               "Height             : " + height + " cm\n" +
               "Weight             : " + weight + " kg\n" +
               "Department         : " + department;
    }
}