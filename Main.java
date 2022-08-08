public class Main {
    public static void main(String[] args) {

        School VLN = new School("Vancouver Learning Network", 32, 100000);

        Student A = new Student("A","a", 97.67);
        VLN.addStudent(A);
        Student B = new Student("B","b", 97.57);
        VLN.addStudent(B);
        Student C = new Student("C","c", 46.26);
        VLN.addStudent(C);
        Student D = new Student("D","d", 37.38);
        VLN.addStudent(D);
        Student E = new Student("E","e", 89.45);
        VLN.addStudent(E);
        Student F = new Student("F","f", 76.45);
        VLN.addStudent(F);
        Student G = new Student("G","g", 56.67);
        VLN.addStudent(G);
        Student H = new Student("H","h", 84.56);
        VLN.addStudent(H);
        Student I = new Student("I","i", 72.84);
        VLN.addStudent(I);
        Student J = new Student("J","j", 87.34);
        VLN.addStudent(J);

        Teacher Ta = new Teacher("Ta","tA","Math");
        VLN.addTeacher(Ta);
        Teacher Tb = new Teacher("Tb","tB","Science");
        VLN.addTeacher(Tb);
        Teacher Tc = new Teacher("Tc","tC","Computer Science");
        VLN.addTeacher(Tc);

        System.out.println(VLN.showStudents());
        System.out.println(VLN.showTeachers());

        VLN.removeStudent(0);
        VLN.removeStudent(0);
        VLN.removeTeacher(0);

        System.out.println(VLN.showStudents());
        System.out.println(VLN.showTeachers());
    }
}
