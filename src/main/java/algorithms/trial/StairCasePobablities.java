package algorithms.trial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StairCasePobablities {

    /**
     *
     * Problem statement: Given a total number of steps and list of stepCounts one person can take,
     * find how many possibilities one can reach the top.
     * E.g (totalsteps 3 and stepCounts (1, 2)) we have the below possibilities
     * 1. 1, 1, 1
     * 2. 1, 2
     * 3. 2, 1
     * the method should return 3 in this case.
     *
     * Start from step 1 and calculate the possibilities with
     * allowed steps (assume the steps Allowed is sorted (if not we can add a sort at the initial step.
     *
     * 1. Calculate the possiblity of step 1 and persist it in the map with the number of possibilities
     * 2. For all the remaining steps follow the below steps
     *      a. for each of the allowed steps get the value calculated from the map possibilities.get(currentStep = currentStepsAllowed) and count
     *      b. persist the total value in the possibilities map
     *      c. Continue until you reach the totalSteps
     *
     * if totalCounts is n and we x stepCounts the runtime of this method is O(nx) in Worst and Average Case
     * */
    public Long findPossibleStepCombinations(int totalSteps, List<Integer> stepsAllowed) {
        Map<Integer, Long> possibilities = new HashMap<>();
        possibilities.put(0, -1l);
        for(int x = 1; x <= totalSteps; x++) {
            long currentPossibility = 0;
            for(Integer currentStep : stepsAllowed) {
                if(currentStep > x || (x - currentStep) < 0) {
                    break;
                }
                else if((x - currentStep) == 0) {
                    currentPossibility += 1;
                }
                else {
                    long previousPossibility = possibilities.get(x - currentStep);
                    currentPossibility += (previousPossibility == 0) ? 0 : previousPossibility;
                }
            }
            possibilities.put(x, currentPossibility);
        }
        Long result = possibilities.get(totalSteps);
        return result == null ? 0 : result;
    }

    public static void main(String... args) {
        StairCasePobablities stairCasePobablities = new StairCasePobablities();
        long possibilities = stairCasePobablities.findPossibleStepCombinations(5, Arrays.asList(1, 2));
        System.out.println(possibilities);
    }

}
