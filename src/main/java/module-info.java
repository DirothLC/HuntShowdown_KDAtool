module kz.huntshowdown.kdacalc.huntshowdownkdacalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;


    exports kz.huntshowdown.kdacalc.huntshowdownkdacalculator.runner;
    opens kz.huntshowdown.kdacalc.huntshowdownkdacalculator.runner to javafx.fxml;
    exports kz.huntshowdown.kdacalc.huntshowdownkdacalculator.controllers;
    opens kz.huntshowdown.kdacalc.huntshowdownkdacalculator.controllers to javafx.fxml;
}