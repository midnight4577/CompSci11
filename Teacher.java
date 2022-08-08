public class Teacher {
    //simple teacher class containing a first and last name, and a subject.

    private String firstName;
    private String lastName;
    private String subject;

    //teacher constructor, literally just sets the values to whatever the user inputs
    public Teacher(String firstName, String lastName, String subject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    //an override of the toString function which returns a string containing the name and subject of the teacher.
    public String toString(){
        return  "Name: " + this.firstName + "  Subject: " + this.subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
