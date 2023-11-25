package pl.jwz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifierSensors {

    private int defaultLength = 60;
    private int l1 = defaultLength;

    public ModifierSensors() {
    }

    public void test(boolean collision) {
        if (collision) {
            l1--;
        } else {
            if (l1 < defaultLength) {
                l1++;
            }
        }
        //System.out.println(collision);
    }
}
