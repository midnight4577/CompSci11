//projectile class, simply stores a bunch of ballistic variables including the density of fluid to allow for testing in different conditions
public class Projectile {
    private String name;
    private double dragCoefficient;
    private double area;
    private double fluidDensity;
    private double mass;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getFluidDensity() {
        return fluidDensity;
    }

    public void setFluidDensity(double fluidDensity) {
        this.fluidDensity = fluidDensity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String toString(){
        return this.name;
    }

    public Projectile(String name, double dragCoefficient, double area, double fluidDensity, double mass) {
        this.name = name;
        this.dragCoefficient = dragCoefficient;
        this.area = area;
        this.fluidDensity = fluidDensity;
        this.mass = mass;
    }
}
