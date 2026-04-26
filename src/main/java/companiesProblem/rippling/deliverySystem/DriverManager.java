package companiesProblem.rippling.deliverySystem;

import java.util.ArrayList;
import java.util.List;

public class DriverManager {
    List<Driver> driverList= new ArrayList<>();

    public void addDriver(Driver ram) {
        driverList.add(ram);
    }

    public Driver getDriver() {
        for(Driver d:driverList){
            if(d.isAvailable){
                return d;
            }
        }
        return null;
    }

}
