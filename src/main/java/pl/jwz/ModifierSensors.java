package pl.jwz;

import lombok.Getter;
import lombok.Setter;
import pl.jwz.models.Sensors;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class ModifierSensors {

    private int defaultLength = 60;
    private int l1 = defaultLength;
    private int maxLength = 100;
    private int minLength = 0;

    public void test(Sensors sensors, Graphics2D graphics, BufferedImage image) {
        if (sensors.isLeftCollision()) {
            while (sensors.isLeftCollision()){
                l1--;
                sensors.setLeftSensorLength(l1);
                sensors.createSensors(graphics, image);
            }
        } else {
            while (!sensors.isLeftCollision()) {
                l1++;
                sensors.setLeftSensorLength(l1);
                sensors.createSensors(graphics, image);
            }
        }


//        if (collision) {
//            l1--;
//        } else {
//            if (l1 < defaultLength) {
//                l1++;
//            }
//        }
        //System.out.println(collision);
    }
}
