package UnionFind.UnionV3;

/**
 * 1.在树回溯的过程中，不断降低树的高度；2.将小的连通集合挂载到大的连通集合下。
 */
public class QuickFindUF {
    public int[] id;
    public int[] size;

    public QuickFindUF(int N) {
        id   = new int[N];
        size = new int[N];
        for (int i=0; i<N; i++){
            id[i]   = i;
            size[i] = 1;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i     = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if(size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }
}
