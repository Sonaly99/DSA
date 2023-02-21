import java.util.*;

public class BorderLine {

    public static int[][] getBorderLine(int[][] height) {
        List<int[]> border = new ArrayList<>();
        Map<Integer, Integer> rightEdgeToHeight = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < height.length; i++) {
            int leftEdge = height[i][0];
            int rightEdge = height[i][1];
            int h = height[i][2];

            while (rightEdgeToHeight.size() > 0 && ((TreeMap<Integer, Integer>) rightEdgeToHeight).firstKey() > leftEdge) {
                int r = ((TreeMap<Integer, Integer>) rightEdgeToHeight).firstKey();
                int hr = rightEdgeToHeight.get(r);

                if (hr == rightEdgeToHeight.values().stream().max(Integer::compare).get()) {
                    border.add(new int[]{r, hr});
                }

                rightEdgeToHeight.remove(r);
            }

            rightEdgeToHeight.put(rightEdge, h);

            if (h == rightEdgeToHeight.values().stream().max(Integer::compare).get()) {
                border.add(new int[]{leftEdge, h});
            }
        }

        while (rightEdgeToHeight.size() > 0) {
            int r = ((TreeMap<Integer, Integer>) rightEdgeToHeight).firstKey();
            int hr = rightEdgeToHeight.get(r);

            if (hr == rightEdgeToHeight.values().stream().max(Integer::compare).get()) {
                border.add(new int[]{r, hr});
            }

            rightEdgeToHeight.remove(r);
        }

        int[][] result = new int[border.size()][2];

        for (int i = 0; i < border.size(); i++) {
            result[i][0] = border.get(i)[0];
            result[i][1] = border.get(i)[1];
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        int[][] border = getBorderLine(height);

        for (int i = 0; i < border.length; i++) {
            System.out.println(Arrays.toString(border[i]));
        }
    }
}