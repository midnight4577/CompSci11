import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class Controller {
    public TextField inputAngle;
    public ListView<Projectile> projListView;
    public TextField inputName;
    public TextField inputDragCoe;
    public TextField inputArea;
    public TextField inputFluidDensity;
    public TextField inputMass;
    public Text outputProjErrorText;
    public TextField inputFilename;
    public Text inputFilenameErrorText;
    public Text nameViewText;
    public Text dragCoeViewText;
    public Text areaViewText;
    public Text fluidDensityViewText;
    public Text massViewText;
    public TextField inputVelocity;
    public TextField inputTimeStep;
    public TextField inputYStop;
    public TextField inputGravity;
    public Text outputCalcWarning;
    @FXML LineChart<Number, Number> trajGraph;

    //gets the current projectile if there is one
    public Projectile getCurProj() {
        if (projListView.getItems().size() != 0) {
            return projListView.getSelectionModel().getSelectedItem();
        }else return null;
    }

    //updates the projectile information
    public void displayProj(){
        Projectile tempProj;
        //make sure there is actually a projectile selected
        if (getCurProj() != null) {
            tempProj = getCurProj();
            nameViewText.setText(tempProj.getName());
            dragCoeViewText.setText(tempProj.getDragCoefficient() + "");
            areaViewText.setText(tempProj.getArea() + "");
            fluidDensityViewText.setText(tempProj.getFluidDensity() + "");
            massViewText.setText(tempProj.getMass() + "");
        }
    }

    //Method which takes user input and creates a new Projectile object in the listview
    public void addProjToListView(){
        Projectile tempProj;
        String tempName = "";
        double tempDragCoefficient = 0.0;
        double tempArea = 0.0;
        double tempFluidDensity = 0.0;
        double tempMass = 0.0;

        //A string to warn the user if there are any errors in the input
        String errorWarn = "";

        //tries and catches for each input, if anything is wrong add an error to the error warning string
        try {
            tempName = inputName.getText();
        }
        catch (Exception e){
            errorWarn = "Name Invalid";
        }

        try {
            tempDragCoefficient = Double.parseDouble(inputDragCoe.getText());
        }
        catch (Exception e){
            //see if the error warning string has anything in it for formatting reasons
            if (errorWarn.equals("")) {
                errorWarn += "Drag Coefficient Invalid";
            }else {
                errorWarn += ", Drag Coefficient Invalid";
            }
        }

        try {
            tempArea = Double.parseDouble(inputArea.getText());
        }
        catch (Exception e){
            if (errorWarn.equals("")) {
                errorWarn += "Area Invalid";
            }else {
                errorWarn += ", Area Invalid";
            }
        }

        try {
            tempFluidDensity = Double.parseDouble(inputFluidDensity.getText());
        }
        catch (Exception e){
            if (errorWarn.equals("")) {
                errorWarn += "Fluid Density Invalid";
            }else {
                errorWarn += ", Fluid Density Invalid";
            }
        }

        try {
            tempMass = Double.parseDouble(inputMass.getText());
        }
        catch (Exception e){
            if (errorWarn.equals("")) {
                errorWarn += "Mass Invalid";
            }else {
                errorWarn += ", Mass Invalid";
            }
        }

        //set error message (if there isn't one it is just blank/clears from last time)
        outputProjErrorText.setText(errorWarn);

        //make sure there isn't an error then save the projectile to the listview
        if (errorWarn.equals("")) {
            tempProj = new Projectile(tempName, tempDragCoefficient, tempArea, tempFluidDensity, tempMass);
            projListView.getItems().add(tempProj);
            displayProj();
            inputName.clear();
            inputDragCoe.clear();
            inputArea.clear();
            inputFluidDensity.clear();
            inputMass.clear();
        }
    }

    public void calcGraph(){
        String errorWarn = "";
        //make sure we have a projectile
        if (getCurProj() != null) {
            Projectile proj;
            proj = getCurProj();
            BallisticCalculator calc;

            //try and grab some user input, if there's something wrong return
            try {
                calc = new BallisticCalculator(proj, Double.parseDouble(inputVelocity.getText()), (Double.parseDouble(inputAngle.getText()) * Math.PI / 180), Double.parseDouble(inputYStop.getText()), Double.parseDouble(inputTimeStep.getText()), Double.parseDouble(inputGravity.getText()));
                if (Double.parseDouble(inputGravity.getText()) >= 0 && Double.parseDouble(inputYStop.getText()) <= 0 || Double.parseDouble(inputGravity.getText()) <= 0 && Double.parseDouble(inputYStop.getText()) >= 0){
                    errorWarn += "NO POSSIBLE SOLUTION";
                }
            }catch (Exception e){
                errorWarn += "INPUT ERROR";
                outputCalcWarning.setText(errorWarn);
                return;
            }

            if (errorWarn.equals("")) {
                //calculate the trajectory
                Trajectory traj;
                traj = calc.calculate();

                //add the trajectory to the graph
                XYChart.Series<Number, Number> trajSeries = new XYChart.Series<Number, Number>();
                trajSeries.setName("Trajectory (" + inputAngle.getText() + ")");

                for (int i = 0; i < traj.getCordsLength(); i++) {
                    double[] cords;
                    cords = traj.getCordID(i);
                    trajSeries.getData().add(new XYChart.Data<Number, Number>(cords[0], cords[1]));
                }
                trajGraph.getData().add(trajSeries);

                //format the graph so that it's a 1:1 ratio
                //turn off auto ranging so that the graph does not format itself
                trajGraph.getXAxis().setAutoRanging(false);
                trajGraph.getYAxis().setAutoRanging(false);

                //get max X and max Y
                double maxX = traj.getCordID(traj.getCordsLength()-1)[0];
                double maxY = traj.getMaxY();

                //make sure that the last graph isn't bigger so the data isn't cut off
                if (maxX > maxY && ((NumberAxis)trajGraph.getXAxis()).getUpperBound() < (maxX+maxX*0.05)*0.92) {
                    ((NumberAxis)trajGraph.getXAxis()).setTickUnit((maxX+maxX*0.05)/20);//set the tick unit to 1/20 of the range
                    ((NumberAxis)trajGraph.getXAxis()).setLowerBound(0); //lower bound of the graph
                    ((NumberAxis)trajGraph.getXAxis()).setUpperBound((maxX+maxX*0.05)*1.04); //Upper bound of the Graph. 1.04 is the width divided by the height, multiplying the x-axis by it will make the ratio perfectly 1:1

                    ((NumberAxis)trajGraph.getYAxis()).setTickUnit((maxX+maxX*0.05)/20);
                    ((NumberAxis)trajGraph.getYAxis()).setLowerBound(0);
                    ((NumberAxis)trajGraph.getYAxis()).setUpperBound(maxX+maxX*0.05); //The 0.05  adds a small buffer for ease of viewing
                }else if (maxY > maxX && ((NumberAxis)trajGraph.getYAxis()).getUpperBound() < maxY+maxY*0.05) {
                    //everything here is the same except the max Y value is used instead of max X
                    ((NumberAxis)trajGraph.getXAxis()).setTickUnit((maxY+maxY*0.05)/20);
                    ((NumberAxis) trajGraph.getXAxis()).setLowerBound(0);
                    ((NumberAxis) trajGraph.getXAxis()).setUpperBound((maxY+maxY*0.05)*1.04);

                    ((NumberAxis)trajGraph.getYAxis()).setTickUnit((maxY+maxY*0.05)/20);
                    ((NumberAxis) trajGraph.getYAxis()).setLowerBound(0);
                    ((NumberAxis) trajGraph.getYAxis()).setUpperBound(maxY+maxY*0.05);
                }
            }
            outputCalcWarning.setText(errorWarn);
        }
    }

    public void clearGraph(){
        //clear all the data
        trajGraph.getData().clear();

        //reset the size so that the graph isn't stuck being too big
        ((NumberAxis)trajGraph.getXAxis()).setUpperBound(0);
        ((NumberAxis)trajGraph.getYAxis()).setUpperBound(0);
    }

    //method to delete the currently selected projectile
    public void deleteCurProj(ActionEvent actionEvent) {
        try {
            if (projListView.getItems().size() == 1) {
                projListView.getItems().clear();
            }else {
                projListView.getItems().remove(projListView.getSelectionModel().getSelectedIndex());
            }
        }
        catch (Exception e){
        }
    }

    //save a projectile as a file
    public void saveCurProj(ActionEvent actionEvent) {
        try {
            //make sure there is a projectile to save
            if (getCurProj() != null){
                try {
                    //check that the name is something
                    if (inputFilename.getText().equals("")){
                        inputFilenameErrorText.setText("Invalid Filename");
                    }else {
                        fileIO.writeFile(inputFilename.getText() + ".proj", fileIO.encodeProj(getCurProj()));
                        inputFilenameErrorText.setText("");
                        inputFilename.clear();
                    }
                }catch (Exception e){
                    inputFilenameErrorText.setText("ERROR");
                }
            }
        }catch (Exception e){
            inputFilenameErrorText.setText("ERROR");
        }
    }

    //method to get a projectile from a file then save it to the listview
    public void getProjFromFile(ActionEvent actionEvent) {
        try {
            //try and grab and decode data from the file, give an error message is something breaks
            if (fileIO.decodeProj(fileIO.readFile(inputFilename.getText())) != null) {
                projListView.getItems().add(fileIO.decodeProj(fileIO.readFile(inputFilename.getText())));
                inputFilenameErrorText.setText("");
                displayProj();
            }else {
                inputFilenameErrorText.setText("File Corrupt/Not Found");
            }
        } catch (FileNotFoundException e) {
            inputFilenameErrorText.setText("File Not Found");
            e.printStackTrace();
        }
    }
}
