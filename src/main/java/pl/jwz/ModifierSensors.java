package pl.jwz;

import lombok.Getter;
import lombok.Setter;
import pl.jwz.models.Sensors;

@Getter
@Setter
public class ModifierSensors {

    private int defaultLength = 60;
    private int l1 = defaultLength;
    private int maxLength = 100;
    private int minLength = 0;

    public ModifierSensors() {
    }

    public void test(Sensors sensors) {
        if (sensors.isLeftCollision()) {
            do {
                if (l1 > minLength)
                    l1--;
                else
                    break;
            } while (!sensors.isLeftCollision());
        } else {
            do {
                if (l1 < maxLength)
                    l1++;
                else
                    break;
            } while (sensors.isLeftCollision());
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
