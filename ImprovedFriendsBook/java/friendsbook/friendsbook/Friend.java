package friendsbook.friendsbook;

public class Friend {

    private String firstName;
    private String lastName;
    private int age;

    private int id;
    private static int idCounter = 0;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public Friend(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = idCounter;
        idCounter ++;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return this.firstName+" "+this.lastName;
    }
}
