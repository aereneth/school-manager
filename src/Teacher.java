public class Teacher extends Person {
    private int HOF_TEACHING_HOURS = 8;
    private int CO_TEACHING_HOURS = 13;
    private int L_TEACHING_HOURS = 18;
    private double BASE_SALARY = 1200.0;
    private double OVERTIME_RATE = 325.0;
    private double HA_PERCENTAGE = 0.1;
    private double MA_PERCENTAGE = 0.03;
    private double TA_PERCENTAGE = 0.05;

    private String department;
    private String designation;

    private double salary = 0;

    public String getDepartment() {
        return this.department;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void logAttendance(int teachingHourCount) {
        double overtimeSalary = 0;

        switch(designation) {
            case "Head of Faculty":
                overtimeSalary = (teachingHourCount - HOF_TEACHING_HOURS) * OVERTIME_RATE;
                break;
            case "Coordinator":
                overtimeSalary = (teachingHourCount - CO_TEACHING_HOURS) * OVERTIME_RATE;
                break;
            case "Lecturer":
                overtimeSalary = (teachingHourCount - L_TEACHING_HOURS) * OVERTIME_RATE;
                break;
        }

        salary += BASE_SALARY + overtimeSalary;
    }

    private double calculateSalary() {
        double housingAllowance = salary * HA_PERCENTAGE;
        double medicalAllowance = salary * MA_PERCENTAGE;
        double travellingAllowance = salary * TA_PERCENTAGE;

        return salary + housingAllowance + medicalAllowance + travellingAllowance;
    }
    
    public void setSalary(double salary){
        this.salary = salary;
    }
    
    public double getSalary(){
        return salary;
    }

    public double getNetSalary() {
        return calculateSalary();
    }
}
