module ru.geekbrains {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.geekbrains to javafx.fxml;
    exports ru.geekbrains;
}