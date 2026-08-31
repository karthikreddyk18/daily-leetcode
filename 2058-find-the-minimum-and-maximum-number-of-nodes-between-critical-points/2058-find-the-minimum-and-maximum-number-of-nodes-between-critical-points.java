/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if(head==null || head.next==null || head.next.next==null){
            return new int[]{-1,-1};
        }
        int firstCric=-1;
        int prevCric=-1;
        int minD=Integer.MAX_VALUE;
        ListNode prev=head;
        ListNode cur=head.next;
        int i=1;
        while(cur.next!=null){
            ListNode next=cur.next;
            if((cur.val>prev.val&&cur.val>next.val)||(cur.val<prev.val&&cur.val<next.val)){
                if(firstCric==-1){
                    firstCric=i;
                }else{
                    minD=Math.min(minD,i-prevCric);
                }
                prevCric=i;
            }
            prev=cur;
            cur=next;
            i++;
        }
        if(firstCric==-1 || prevCric==firstCric){
            return new int[]{-1,-1};
        }
        int maxD=prevCric-firstCric;
        return new int[]{minD,maxD};

    }
}