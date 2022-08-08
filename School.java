import java.util.ArrayList;

//School class, holds an arraylist of students, teachers and subjects. Also holds the age, budget and name of the school
public class School {
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> subjects = new ArrayList<>();

    private String name;
    private int age;
    private double budget;

    //Simple constructor for this class
    public School(String name, int age, double budget){
        this.name = name;
        this.age = age;
        this.budget = budget;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public Teacher getTeacher(int index){
        return teachers.get(index);
    }

    public void removeTeacher(int index){
        teachers.remove(index);
    }


    public void addStudent(Student student){
        students.add(student);
    }

    public Student getStudent(int index){
        return students.get(index);
    }

    public void removeStudent(int index){
        students.remove(index);
    }


    public void addSubject(String subject){
        subjects.add(subject);
    }

    public String getSubject(int index){
        return subjects.get(index);
    }

    public void removeSubject(int index){
        subjects.remove(index);
    }

    //method which returns the first and last name of all the students
    public String showStudents(){
        String allStudents = "";
        for (int i = 0; i < students.size(); i++){
            if (i != 0){
            allStudents += ", " + students.get(i).getFirstName() + " " + students.get(i).getLastName();
            }
            else{
                allStudents += students.get(i).getFirstName() + " " + students.get(i).getLastName();
            }
        }
        return allStudents;
    }

    //method which returns the first and last name of all the teachers
    public String showTeachers(){
        String allTeachers = "";
        for (int i = 0; i < teachers.size(); i++){
            if (i != 0){
                allTeachers += ", " + teachers.get(i).getFirstName() + " " + teachers.get(i).getLastName();
            }
            else{
                allTeachers += teachers.get(i).getFirstName() + " " + teachers.get(i).getLastName();
            }
        }
        return allTeachers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}