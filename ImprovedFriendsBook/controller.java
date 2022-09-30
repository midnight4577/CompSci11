package friendsbook.friendsbook;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class controller {

    public ListView<Friend> friendList = new ListView<>();

    public Button addFriendBut;

    public TextField ageInp;
    public TextField lastNameInp;
    public TextField firstNameInp;
    public Text firstNameText;
    public Text lastNameText;
    public Text ageText;
    public Button saveDataBut;
    public Button loadDataBut;
    public TextField fileNameInp;
    public Button deleteFriendBut;


    public boolean checkFriendInputs(){
        try {
            Integer.parseInt(ageInp.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        if (Integer.parseInt(ageInp.getText()) < 0 || firstNameInp.getText().equals("") || lastNameInp.getText().equals("")){
            return false;
        }
        return true;
    }

    public void addFriend(MouseEvent mouseEvent) {
        if (checkFriendInputs()){
            friendList.getItems().add(new Friend(firstNameInp.getText(), lastNameInp.getText(), Integer.parseInt(ageInp.getText())));
            firstNameInp.clear();
            lastNameInp.clear();
            ageInp.clear();
        }
    }

    public void dispFriend(MouseEvent mouseEvent) {
        Friend temp;
        temp = friendList.getSelectionModel().getSelectedItem();
        firstNameText.setText(temp.getFirstName());
        lastNameText.setText(temp.getLastName());
        ageText.setText(String.valueOf(temp.getAge()));
    }

    public void saveData(MouseEvent mouseEvent) throws IOException {
        if (fileNameInp.getText() != "") {
            fileIO.writeFile(fileNameInp.getText()+".txt", friendList);
        }
    }

    public void loadData(MouseEvent mouseEvent) throws IOException {
        ArrayList<String> temp = new ArrayList<>();
        fileIO.readFile(fileNameInp.getText()+".txt", temp, true);
        fileIO.parseData(temp, friendList);
    }

    public void deleteFriend(MouseEvent mouseEvent) {
        try {
            if (friendList.getItems().size() == 1) {
                friendList.getItems().clear();
            }else {
                friendList.getItems().remove(friendList.getSelectionModel().getSelectedItem().getId());
            }
        }
        catch (Exception e){
        }
    }
}
