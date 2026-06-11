public class Patient {

    private String patientID;
    private String patientName;
    private String phoneNumber;
    private String dialysisType;

    public Patient(String patientID, String patientName, String phoneNumber, String dialysisType) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.dialysisType = dialysisType;
    }

    public String getPatientID() {
        return patientID;
    }

    public String toString() {
        return "ID: " + patientID +
                " | Name: " + patientName +
                " | Phone: " + phoneNumber +
                " | Type: " + dialysisType;
    }
}