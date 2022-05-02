module ru.nsu.fit.oop.task321 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens ru.nsu.fit.oop.task321 to javafx.fxml;
    exports ru.nsu.fit.oop.task321;
    exports ru.nsu.fit.oop.task321.view;
    exports ru.nsu.fit.oop.task321.model;
    exports ru.nsu.fit.oop.task321.controller;
    opens ru.nsu.fit.oop.task321.view to javafx.fxml;
}