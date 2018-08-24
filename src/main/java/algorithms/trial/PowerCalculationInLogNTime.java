package algorithms.trial;

import java.util.HashMap;
import java.util.Map;

public class PowerCalculationInLogNTime {

    private static volatile Map<BasePower, Double> storedPowers = new HashMap<>();

    /**
     *
     * For Calculating the Power of a Number, we can calculate the Binary String of the number and calculate only for needed powers.
     * E.g 50 ^ 50 can be calculated as (50 ^ 32) * (50 ^ 16) * (50 ^ 2)    (Binary String of 50 = 110010)
     * E.g 50 ^ 47 can be calculated as (50 ^ 32) * (50 ^ 8) * (50 ^ 4) * (50 ^ 2) * (50 ^ 1)   (Binary String of 47 = 101111)
     * When the method execution starts I put the powers of base with 0 and 1 in the hashmap, the power of base with 2 is calculated by
     * multiplying the base with power 1 twice and stored in map. further calculation of base with power 4 is calculated using the stored
     * value of base with power 2 and it goes on until we reach with the required values for finding the actual result.
     *
     * */
    public Double findPower(int base, int power) {
        storedPowers.putIfAbsent(new BasePower(base, 0), 1.0);
        storedPowers.putIfAbsent(new BasePower(base, 1), (double)base);
        int counter = 1;
        double result = 1.0;
        while(power > 0) {
            int remainder = power % 2;
            double currentPower;
            BasePower basePowerWithCounter = new BasePower(base, counter);
            if(storedPowers.get(basePowerWithCounter) == null) {
                BasePower basePowerWithHalfCounter = new BasePower(base, counter / 2);
                currentPower = storedPowers.get(basePowerWithHalfCounter) *
                        storedPowers.get(basePowerWithHalfCounter);
            }
            else {
                currentPower = storedPowers.get(basePowerWithCounter);
            }
            if(remainder == 1) {
                result *= currentPower;
            }
            storedPowers.putIfAbsent(basePowerWithCounter, currentPower);
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
        System.out.println(new PowerCalculationInLogNTime().findPower(50, 40));
    }
}
