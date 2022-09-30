package friendsbook.friendsbook;
import javafx.scene.control.ListView;

import java.io.*;
import java.util.ArrayList;

public class fileIO {

    public static void readFile(String fileName, ArrayList<String> outputArrayList, boolean clearArray) throws IOException {
        if (clearArray){
            outputArrayList.clear();
        }
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String curLine;
        while ((curLine = br.readLine()) != null){
            outputArrayList.add(curLine);
        }
    }

    public static void parseData(ArrayList<String> inputArrayList, ListView<Friend> outputListView){
        outputListView.getItems().clear();
        String friendString = "";
        int curDataType;
        int curFriend = 0;

        String tempFirstName = "";
        String tempLastName = "";

        for (String line : inputArrayList){
            if (!line.equals(";")){
                friendString += line;
            }else{
                curDataType = 0;
                for (int i = 0; i < friendString.length(); i++){
                    if (friendString.substring(i, i+1).equals(",")){
                        curDataType++;
                        switch (curDataType){
                            case 1:
                                tempFirstName = friendString.substring(0,i);
                                break;
                            case 2:
                                tempLastName = friendString.substring(0,i);
                                break;
                            case 3:
                                outputListView.getItems().add(curFriend, new Friend(tempFirstName, tempLastName, Integer.parseInt(friendString.substring(0,i))));
                                break;
                        }
                        friendString = friendString.substring(i+1, friendString.length());
                        i = 0;
                    }
                }
                curFriend++;
                friendString = "";
            }
        }
    }

    public static void writeFile(String fileName, ListView<Friend> friendData) throws IOException{
        FileWriter fw = new FileWriter(fileName, false);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < friendData.getItems().size(); i++) {
            bw.write(friendData.getItems().get(i).getFirstName() + ",\r");
            bw.write(friendData.getItems().get(i).getLastName() + ",\r");
            bw.write(friendData.getItems().get(i).getAge() + ",\r");
            bw.write(";\r");
        }
        bw.close();
    }

}
