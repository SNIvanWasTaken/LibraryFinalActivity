module com.ilm2223.unit5.libraryfinalactivity {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires android.json;


    opens com.ilm2223.unit5.libraryfinalactivity to javafx.fxml, org.hibernate.orm.core;
    exports com.ilm2223.unit5.libraryfinalactivity;
}