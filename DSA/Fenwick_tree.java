//Fenwick or Binary Indexed Tree
public class Fenwick_tree {
    //O(n) extra space
    static int[] BIT;
    static int n;

    //O(n.log n)
    public void construct(int[] arr){
        n = arr.length;
        BIT = new int[n+1];

        for(int i=0; i<n; i++){
            update(i, arr[i]);
        }
    }

    //O(log n)
    public void update(int i, int val){
        int idx = i+1;

        while(idx <= n){
            BIT[idx] += val;
            idx += idx & (-idx);
        }
    }

    //O(log n)
    public int query(int idx){
        int sum = 0;
        idx += 1;

        while(idx > 0){
            sum += BIT[idx];
            idx -= idx & (-idx);
        }

        return sum;
    }
}
