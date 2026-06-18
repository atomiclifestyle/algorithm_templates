// node - location of node data.(0 for root node)
// 2 * node + 1 - left child location
// 2 * node + 2 - right child location
// tree[node] - data in a node
// low, high - intervals of that node
// l,r - target interval

public class Segment_Tree {
    int n;
    int[] tree = new int[4*n];

    //O(n) - both side recursion is called
    public void construct(int[] arr, int node, int l, int r){
        if(l == r){
            tree[node] = arr[l];
            return;
        }

        int mid = l + (r-l)/2;
        int left = 2 * node + 1;
        int right = 2 * node + 2;
        construct(arr, left, l, mid);
        construct(arr, right, mid+1, r);

        tree[node] = tree[left] + tree[right];
    }

    //O(log n)
    public int query(int node, int low, int high, int l, int r){
        if(low >= l && high <= r){
            return tree[node];
        }

        if(high < l || low > r){
            return 0;
        }

        int mid = low + (high-low)/2;
        int left = 2 * node + 1;
        int right = 2 * node + 2;

        return (query(left, low, mid, l, r) + query(right, mid+1, high, l, r));
    }

    //O(log n)
    public void update(int node, int low, int high, int idx, int val){
        if(low == high){
            tree[node] = val;
            return;
        }

        int mid = low + (high-low)/2;
        int left = 2 * node + 1;
        int right = 2 * node + 2;

        if(idx <= mid){
            update(left, low, mid, idx, val);
        } else {
            update(right, mid+1, high, idx, val);
        }

        tree[node] = tree[left] + tree[right];
    }
}
