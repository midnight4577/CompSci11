module friendsbook.friendsbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens friendsbook.friendsbook to javafx.fxml;
    exports friendsbook.friendsbook;
}
