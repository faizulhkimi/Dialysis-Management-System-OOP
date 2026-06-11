public class DialysisAssistant extends Person {

    private String shift;

    public DialysisAssistant(String id, String name, String phone,
                             String gender, int age, double height,
                             double weight, String shift) {

        super(id, name, phone, gender, age, height, weight);
        this.shift = shift;
    }

    public String displayInfo() {
        return "Assistant ID : " + id + "\n" +
               "Name         : " + name + "\n" +
               "Phone Number : " + phone + "\n" +
               "Gender       : " + gender + "\n" +
               "Age          : " + age + "\n" +
               "Height       : " + height + " cm\n" +
               "Weight       : " + weight + " kg\n" +
               "Shift        : " + shift;
    }
}