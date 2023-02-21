import java.util.Arrays;

public class MinimumProductDifference {
    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 11};
        int minProductDiff = minimumProductDifference(nums);
        System.out.println("Minimum product difference: " + minProductDiff);
    }
    public static int minimumProductDifference(int[] nums) {
        int n = nums.length;
        int half = n / 2;
        int[] firstHalf = Arrays.copyOfRange(nums, 0, half);
        int[] secondHalf = Arrays.copyOfRange(nums, half, n);
        int minProductDiff = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << half); mask++) {
            int product1 = product(firstHalf, mask);
            int product2 = product(secondHalf, ~mask);
            int diff = Math.abs(product1 - product2);
            minProductDiff = Math.min(minProductDiff, diff);
        }
        return minProductDiff;
    }

    public static int product(int[] nums, int mask) {
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) != 0) {
                product *= nums[i];
            }
        }
        return product;
    }
    }

