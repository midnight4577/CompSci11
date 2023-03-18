//trajectory class which is used to store the flight path data from a projectile
import java.util.ArrayList;

public class Trajectory {
    private ArrayList<Double> xCords = new ArrayList<Double>();
    private ArrayList<Double> yCords = new ArrayList<Double>();
    private ArrayList<Double> velocity = new ArrayList<Double>();
    private double angle;

    public Trajectory(ArrayList<Double> xCords, ArrayList<Double> yCords, ArrayList<Double> velocity, double angle){
        this.angle = angle;
        this.xCords = xCords;
        this.yCords = yCords;
        this.velocity = velocity;
    }

    public void printCords(){
        for (int i = 0; i < xCords.size(); i++) {
            System.out.println("("+xCords.get(i)+","+yCords.get(i)+")");
        }
    }

    public double getMaxY(){
        double maxY = 0;
        for (int i = 0; i < yCords.size(); i++) {
            if (maxY < yCords.get(i)){
                maxY = yCords.get(i);
            }
        }
        return maxY;
    }

    public int getCordsLength(){
        return xCords.size();
    }

    public double[] getCordID(int ID){
        double[] cords;
        cords = new double[3];
        cords[0] = xCords.get(ID);
        cords[1] = yCords.get(ID);
        cords[2] = velocity.get(ID);
        return cords;
    }

    public void addxCord(double xCord){
        this.xCords.add(xCord);
    }

    public void addyCord(double yCord){
        this.yCords.add(yCord);
    }

    public void addVelocity(double velocity){
        this.velocity.add(velocity);
    }

    public ArrayList<Double> getxCords() {
        return xCords;
    }

    public void setxCords(ArrayList<Double> xCords) {
        this.xCords = xCords;
    }

    public ArrayList<Double> getyCords() {
        return yCords;
    }

    public void setyCords(ArrayList<Double> yCords) {
        this.yCords = yCords;
    }

    public ArrayList<Double> getVelocity() {
        return velocity;
    }

    public void setVelocity(ArrayList<Double> velocity) {
        this.velocity = velocity;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
