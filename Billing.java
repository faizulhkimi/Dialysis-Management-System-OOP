public class Billing {

    private String billID;
    private String patientID;
    private double treatmentCost;
    private double medicineCost;

    public Billing(String billID, String patientID, double treatmentCost, double medicineCost) {
        this.billID = billID;
        this.patientID = patientID;
        this.treatmentCost = treatmentCost;
        this.medicineCost = medicineCost;
    }

    public double calculateTotal() {
        return treatmentCost + medicineCost;
    }

    public String toString() {
        return "Bill ID: " + billID +
                "\nPatient ID: " + patientID +
                "\nTreatment Cost: RM " + treatmentCost +
                "\nMedicine Cost: RM " + medicineCost +
                "\nTotal: RM " + calculateTotal();
    }
}