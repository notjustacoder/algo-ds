public class BinaryLinkedListToInteger {
    public int getDecimalValue(ListNode head) {
        if(head == null) {
            return 0;
        }
        int num = 0;
        while(head != null) {
            num = num * 2 + head.val;
            head = head.next;
        }
        return num;
    }

    public static void main(String args[]) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(0);
        list.next.next = new ListNode(1);
        list.next.next.next = new ListNode(0);
        System.out.println(new BinaryLinkedListToInteger().getDecimalValue(list));
    }
}
