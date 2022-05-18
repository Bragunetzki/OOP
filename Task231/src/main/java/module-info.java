module ru.nsu.fit.oop.task231 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens ru.nsu.fit.oop.task231 to javafx.fxml;
    exports ru.nsu.fit.oop.task231;
}