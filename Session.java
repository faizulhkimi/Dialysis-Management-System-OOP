public class Session {

    private String sessionID;
    private String patientID;
    private String date;
    private String time;

    public Session(String sessionID, String patientID, String date, String time) {
        this.sessionID = sessionID;
        this.patientID = patientID;
        this.date = date;
        this.time = time;
    }

    public String toString() {
        return "Session ID: " + sessionID +
                " | Patient ID: " + patientID +
                " | Date: " + date +
                " | Time: " + time;
    }
}