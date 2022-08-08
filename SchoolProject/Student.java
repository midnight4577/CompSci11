public class Student {
    //simple student class, contains their first and last name, grade, and a unique student number

    private String firstName;
    private String lastName;
    private double grade;
    private int studentNum;
    static int studentCount = 0;

    //simple constructor for the student class, the studentNum is set to the studentCount which is a static, so it iterates for every object of this class
    public Student(String firstName, String lastName, double grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentNum = studentCount;
        studentCount++;
    }

    //an override of the toString function which returns a string containing the name and grade of the student.
    public String toString(){
        return  "Name: " + this.firstName + "  Grade: " + this.grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public static int getStudentCount() {
        return studentCount;
    }
}
