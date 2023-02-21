public class LinklistSort {
    public static void main(String[] args) {
            int[] arr = {5, 2, 1, 6, 3};
            int steps = getSortSteps(arr);
            System.out.println("Number of steps required: " + steps);
        }

        public static int getSortSteps(int[] arr) {
            ListNode head = createLinkedList(arr);
            int steps = 0;
            boolean isSorted = false;
            while (!isSorted) {
                ListNode prev = null;
                ListNode curr = head;
                isSorted = true;
                while (curr != null && curr.next != null) {
                    if (curr.val > curr.next.val) {
                        if (prev == null) {
                            head = curr.next;
                        } else {
                            prev.next = curr.next;
                        }
                        curr = curr.next;
                        isSorted = false;
                    } else {
                        prev = curr;
                        curr = curr.next;
                    }
                }
                steps++;
            }
            return steps;
        }

        public static ListNode createLinkedList(int[] arr) {
            ListNode head = new ListNode(arr[0]);
            ListNode curr = head;
            for (int i = 1; i < arr.length; i++) {
                curr.next = new ListNode(arr[i]);
                curr = curr.next;
            }
            return head;
        }

        static class ListNode {
            int val;
            ListNode next;
            ListNode(int val) {
                this.val = val;
            }
        }
    }



