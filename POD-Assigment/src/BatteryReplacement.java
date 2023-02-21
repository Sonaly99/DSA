import java.util.*;

public class BatteryReplacement {
    public static int numberOfBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        int numReplacements = 0;
        int currentMiles = startChargeCapacity;
        PriorityQueue<Integer> batteryCapacities = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < serviceCenters.length; i++) {
            int nextPos = i == serviceCenters.length - 1 ? targetMiles : serviceCenters[i+1][0];
            if (nextPos - serviceCenters[i][0] > currentMiles) {
                if (batteryCapacities.isEmpty()) {
                    return -1;
                }
                currentMiles = batteryCapacities.poll();
                numReplacements++;
                i--;
            } else {
                batteryCapacities.offer(serviceCenters[i][1]);
                currentMiles -= serviceCenters[i][0] - (i == 0 ? 0 : serviceCenters[i-1][0]);
            }
        }

        int finalDistance = targetMiles - serviceCenters[serviceCenters.length-1][0];
        while (finalDistance > currentMiles) {
            if (batteryCapacities.isEmpty()) {
                return -1;
            }
            currentMiles = batteryCapacities.poll();
            numReplacements++;
        }

        return numReplacements;
    }

    public static void main(String[] args) {
        int[][] serviceCenters = {{10,60},{20,30},{30,30},{60,40}};
        int targetMiles = 100;
        int startChargeCapacity = 10;
        int numReplacements = numberOfBatteryReplacements(serviceCenters, targetMiles, startChargeCapacity);
        System.out.println(numReplacements);
    }
}
