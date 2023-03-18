//Ballistics calculator class which is responsible for brute forcing ballistic calculations
import java.util.ArrayList;

public class BallisticCalculator {
    Projectile proj; //the projectile to use for calculations
    double initialVelocity; //initial velocity of projectile (m/s)
    double angle; //angle the projectile is shot at (radian)
    double timeStep; //how much time between each calculation (s) generally 0.01 is a good default
    double yStop; //what y value to stop the simulation at (m) set to 0 in the sim
    Trajectory traj; //trajectory of the projectile
    double gravity;

    public BallisticCalculator(Projectile proj, double initialVelocity, double angle, double yStop, double timeStep, double gravity){
        this.proj = proj;
        this.initialVelocity = initialVelocity;
        this.angle = angle;
        this.yStop = yStop;
        this.timeStep = timeStep;
        this.gravity = gravity;
    }

    //Method to calculate the trajectory of the projectile and return a trajectory
    public Trajectory calculate(){
        //constant variables
        double tempArea = proj.getArea();
        double tempDragCoefficient = proj.getDragCoefficient();
        double tempFluidDensity = proj.getFluidDensity();
        double mass = proj.getMass();

        //variables
        double tempAngle = angle;
        double yVel;
        double xVel;
        double xCord = 0;
        double yCord = 0;
        double airRes;
        double airResX;
        double airResY;
        double vel = initialVelocity;

        traj = new Trajectory(new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<Double>(), angle);

        traj.addxCord(xCord);
        traj.addyCord(yCord);
        traj.addVelocity(vel);


        //loop through and calculate air resistance then split all forces and velocities into their x and y components so that they can be applied to each other
        while (yCord >= yStop && gravity < 0 || yCord <= yStop && gravity > 0) {
            airRes = -0.5 * tempFluidDensity * (vel * vel) * tempDragCoefficient * tempArea; //Calculate air resistance (N)

            airResX = Math.cos(tempAngle) * airRes; //split air resistance for X (N)
            airResY = Math.sin(tempAngle) * airRes; //split air resistance for Y (N)

            xVel = Math.cos(tempAngle) * vel; //split velocity for X (m/s)
            yVel = Math.sin(tempAngle) * vel; //split velocity for Y (m/s)

            airResX = airResX/mass; //find the acceleration due to air resistance for X (m/(s*s))
            airResY = airResY/mass; //find the acceleration due to air resistance for Y (m/(s*s))

            xVel = xVel + airResX * timeStep; //apply the negative acceleration of air resistance to the x velocity
            yVel = yVel + (airResY + gravity) * timeStep; //apply the negative acceleration of air resistance and gravity to the y velocity

            xCord += xVel * timeStep; //calculate displacement for x
            yCord += yVel * timeStep; //calculate displacement for y

            vel = Math.sqrt((xVel*xVel)+(yVel*yVel)); //recalculate velocity

            tempAngle = Math.atan(yVel/xVel); //recalculate angle

            traj.addxCord(xCord);
            traj.addyCord(yCord);
            traj.addVelocity(vel);

        }

        return traj;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }

    public Trajectory getTraj() {
        return traj;
    }

    public Projectile getProj() {
        return proj;
    }

    public void setProj(Projectile proj) {
        this.proj = proj;
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getyStop() {
        return yStop;
    }

    public void setyStop(double zStop) {
        this.yStop = zStop;
    }
}
