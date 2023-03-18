import java.io.*;
import java.util.ArrayList;

//File input and output class
//Responsible for all data persistence
public class fileIO {

    //method to read a file then return it as a string (Only reads the first line)
    public static String readFile(String fileName) throws FileNotFoundException {
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
        } catch (IOException e) {
            line = null;
        }
        return line;
    }

    //method to write a string to a file
    public static void writeFile(String fileName, String data) throws IOException {
        FileWriter fw = new FileWriter(fileName, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.close();
    }

    //Method which encodes all the data in the Trajectory class into a single string
    public static String encodeTraj(Trajectory traj) {
        //the basic way in which the data is encoded is by using "<BREAK>" and ":" to separate the data then using a switch statement in the decoding method
        StringBuilder encodedData;
        encodedData = new StringBuilder();
        int length = traj.getCordsLength();

        double angle;
        angle = traj.getAngle();

        //create a string starting in "<START>" so the  program can tell if anything else comes first
        encodedData = new StringBuilder("<START><BREAK>ANGLE:" + angle + "<BREAK>");
        encodedData.append("LENGTH:").append(traj.getCordsLength()).append("<BREAK>");
        //loop through and save each piece of data in the array list separated by ","
        encodedData.append("XCORDS:");
        for (int i = 0; i < length; i++) {
            encodedData.append(traj.getCordID(i)[0]);
            if (i != length - 1) {
                encodedData.append(",");
            } else {
                encodedData.append("<BREAK>");
            }
        }
        encodedData.append("YCORDS:");
        for (int i = 0; i < length; i++) {
            encodedData.append(traj.getCordID(i)[1]);
            if (i != length - 1) {
                encodedData.append(",");
            } else {
                encodedData.append("<BREAK>");
            }
        }
        encodedData.append("VELOCITY:");
        for (int i = 0; i < length; i++) {
            encodedData.append(traj.getCordID(i)[2]);
            if (i != length - 1) {
                encodedData.append(",");
            } else {
                encodedData.append("<BREAK>");
            }
        }

        //add something so that the program knows there is no more data
        encodedData.append("<END>");

        return encodedData.toString();
    }

    //Method which decodes the data of the "encodeTraj" method to return a Trajectory
    public static Trajectory decodeTraj(String encodedData) {
        //create variables to store and decode the data from the file
        int length;
        double angle = -1;
        String xCordsData = "";
        String yCordsData = "";
        String velocityData = "";
        String[] xCordsDataSplit;
        String[] yCordsDataSplit;
        String[] velocityDataSplit;
        ArrayList<Double> xCords = new ArrayList<>();
        ArrayList<Double> yCords = new ArrayList<>();
        ArrayList<Double> velocity = new ArrayList<>();

        Trajectory outputTraj;

        String[] data;
        String[] entry;

        //Make sure it starts with "<START>"
        if (encodedData != null && encodedData.substring(0, 7).equals("<START>")) {
            // split the file and search through each bit of data to assign it to the correct variable
            data = encodedData.substring(7, encodedData.length()).split("<BREAK>");
            for (int i = 1; i < data.length; i++) {
                if (data[i].equals("<END>")) {
                    i = data.length;
                } else {
                    entry = data[i].split(":");
                    switch (entry[0]) {
                        case "ANGLE":
                            angle = Double.parseDouble(entry[1]);
                            break;
                        case "LENGTH":
                            length = Integer.parseInt(entry[1]);
                            break;
                        case "XCORDS":
                            xCordsData = entry[1];
                            break;
                        case "YCORDS":
                            yCordsData = entry[1];
                            break;
                        case "VELOCITY":
                            velocityData = entry[1];
                            break;
                    }
                }
            }

            //parse the x coordinate data into a arraylist
            xCordsDataSplit = xCordsData.split(",");
            for (String s : xCordsDataSplit) {
                xCords.add(Double.parseDouble(s));
            }

            //parse the y coordinate data into a arraylist
            yCordsDataSplit = yCordsData.split(",");
            for (String s : yCordsDataSplit) {
                yCords.add(Double.parseDouble(s));
            }

            //parse the velocity data into a arraylist
            velocityDataSplit = velocityData.split(",");
            for (String s : velocityDataSplit) {
                velocity.add(Double.parseDouble(s));
            }

            //create a trajectory with all the file's data in it
            outputTraj = new Trajectory(xCords, yCords, velocity, angle);
            return outputTraj;
        } else {
            return null;
        }
    }

    //Method which encodes all the data in the Projectile class into a single string
    public static String encodeProj(Projectile proj) {
        //the basic way in which the data is encoded is by using "<BREAK>" and ":" to separate the data then using a switch statement in the decoding method
        String encodedData;
        encodedData = "";

        String name;
        name = proj.getName();
        double dragCoefficient;
        dragCoefficient = proj.getDragCoefficient();
        double area;
        area = proj.getArea();
        double fluidDensity;
        fluidDensity = proj.getFluidDensity();
        double mass;
        mass = proj.getMass();

        encodedData = "<START><BREAK>NAME:" + name + "<BREAK>";
        encodedData += "DRAGCOEFFICENT:" + dragCoefficient + "<BREAK>";
        encodedData += "AREA:" + area + "<BREAK>";
        encodedData += "FLUIDDENSITY:" + fluidDensity + "<BREAK>";
        encodedData += "MASS:" + mass + "<BREAK>";
        encodedData += "<END>";//add something so that the program knows there is no more data

        return encodedData;
    }

    //Method which decodes the data of the "encodeProj" method to return a Projectile
    public static Projectile decodeProj(String encodedData) {
        //create variables to store and decode the data from the file
        String name;
        name = "INVALID";
        double dragCoefficient;
        dragCoefficient = 0;
        double area;
        area = 0;
        double fluidDensity;
        fluidDensity = 0;
        double mass;
        mass = 0;

        Projectile outputProj;

        String[] data;
        String[] entry;

        //Make sure it starts with "<START>"
        if (encodedData != null && encodedData.substring(0, 7).equals("<START>")) {
            // split the file and search through each bit of data to assign it to the correct variable
            data = encodedData.substring(7, encodedData.length()).split("<BREAK>");
            for (int i = 1; i < data.length; i++) {
                if (data[i].equals("<END>")) {
                    i = data.length;
                } else {
                    entry = data[i].split(":");
                    switch (entry[0]) {
                        case "NAME":
                            name = entry[1];
                            break;
                        case "DRAGCOEFFICENT":
                            dragCoefficient = Double.parseDouble(entry[1]);
                            break;
                        case "AREA":
                            area = Double.parseDouble(entry[1]);
                            break;
                        case "FLUIDDENSITY":
                            fluidDensity = Double.parseDouble(entry[1]);
                            break;
                        case "MASS":
                            mass = Double.parseDouble(entry[1]);
                            break;
                    }
                }
            }
            //create a projectile with all the file's data in it
            outputProj = new Projectile(name, dragCoefficient, area, fluidDensity, mass);
            return outputProj;
        } else {
            //return null if the file doesn't seem to be correct
            return null;
        }
    }
}
