public class Treatment {

    private String treatmentID;
    private String patientID;
    private String bloodPressure;
    private String weight;

    public Treatment(String treatmentID, String patientID, String bloodPressure, String weight) {
        this.treatmentID = treatmentID;
        this.patientID = patientID;
        this.bloodPressure = bloodPressure;
        this.weight = weight;
    }

    public String toString() {
        return "Treatment ID: " + treatmentID +
                " | Patient ID: " + patientID +
                " | BP: " + bloodPressure +
                " | Weight: " + weight;
    }
}