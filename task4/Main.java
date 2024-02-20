package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String numsUrl = args[0];
        int[] nums = parseNums(numsUrl);
        System.out.println(countSteps(nums));
    }

    private static int[] parseNums(String numsUrl) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(numsUrl))) {
            List<String> partsNums = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                partsNums.add(line);
            }
            int[] nums = new int[partsNums.size()];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(partsNums.get(i));
            }
            return nums;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int countSteps(int[] nums) {
        int middleNumber = Arrays.stream(nums).sum() / nums.length;
        int minStep = Math.abs(nums[0] - middleNumber);
        int middleID = 0;
        for (int i = 1; i < nums.length; i++) {
            int minStepToMiddle = Math.abs(nums[i] - middleNumber);
            if (minStepToMiddle < minStep) {
                minStep = minStepToMiddle;
                middleID = i;
            }
        }
        int finalMiddleID = middleID;
        return Arrays.stream(nums)
                .map(x -> Math.abs(nums[finalMiddleID] - x))
                .sum();
    }
}
