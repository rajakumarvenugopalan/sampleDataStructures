package algorithms.trial;

import java.util.HashMap;
import java.util.Map;

public class PowerCalculationInLogNTime {

    private static volatile Map<BasePower, Double> storedPowers = new HashMap<>();

    public Double findPower(int base, int power) {
        storedPowers.putIfAbsent(new BasePower(base, 0), 1.0);
        storedPowers.putIfAbsent(new BasePower(base, 1), (double)base);
        int counter = 1;
        double result = 1.0;
        while(power > 0) {
            int remainder = power % 2;
            double currentPower;
            if(storedPowers.get(new BasePower(base, counter)) == null) {
                currentPower = storedPowers.get(new BasePower(base, counter / 2)) *
                        storedPowers.get(new BasePower(base, counter / 2));
            }
            else {
                currentPower = storedPowers.get(new BasePower(base, counter));
            }
            if(remainder == 1) {
                result *= currentPower;
            }
            storedPowers.putIfAbsent(new BasePower(base, counter), currentPower);
            counter *= 2;
            power /= 2;
        }
        return result;
    }

    private class BasePower {
        int power;
        int base;

        BasePower(int base, int power) {
            this.base = base;
            this.power = power;
        }

        public boolean equals(Object o) {
            if(o instanceof BasePower) {
                return this.getBase() == ((BasePower)o).getBase() &&
                        this.getPower() == ((BasePower)o).getPower();
            }
            return false;
        }

        public int hashCode() {
            return this.getBase() * 100 + this.getPower();
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getBase() {
            return base;
        }

        public void setBase(int base) {
            this.base = base;
        }
    }

    public static void main(String... args) {
        System.out.println(new PowerCalculationInLogNTime().findPower(50, 50));
    }
}
