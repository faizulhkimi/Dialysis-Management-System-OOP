public class Patient extends Person {

    private String bloodType;
    private String dialysisType;
    private String schedulePattern;
    private String sessionTime;
    private String machineNumber;
    private String treatmentID;
    private String medicalOfficerID;
    private String dialysisAssistantID;

    public Patient(String id, String name, String phone,
                   String gender, int age, double height, double weight,
                   String bloodType, String dialysisType,
                   String schedulePattern, String sessionTime,
                   String machineNumber, String treatmentID,
                   String medicalOfficerID,
                   String dialysisAssistantID) {

        super(id, name, phone, gender, age, height, weight);

        this.bloodType = bloodType;
        this.dialysisType = dialysisType;
        this.schedulePattern = schedulePattern;
        this.sessionTime = sessionTime;
        this.machineNumber = machineNumber;
        this.treatmentID = treatmentID;
        this.medicalOfficerID = medicalOfficerID;
        this.dialysisAssistantID = dialysisAssistantID;
    }

    public String displayInfo() {
        return "Patient ID             : " + id + "\n" +
               "Name                   : " + name + "\n" +
               "Phone Number           : " + phone + "\n" +
               "Gender                 : " + gender + "\n" +
               "Age                    : " + age + "\n" +
               "Height                 : " + height + " cm\n" +
               "Weight                 : " + weight + " kg\n" +
               "Blood Type             : " + bloodType + "\n" +
               "Dialysis Type          : " + dialysisType + "\n" +
               "Schedule Pattern       : " + schedulePattern + "\n" +
               "Session Time           : " + sessionTime + "\n" +
               "Machine Number         : " + machineNumber + "\n" +
               "Treatment ID           : " + treatmentID + "\n" +
               "Medical Officer ID     : " + medicalOfficerID + "\n" +
               "Dialysis Assistant ID  : " + dialysisAssistantID;
    }
}