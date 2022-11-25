package UnionFind.UnionV2;

public class QuickFindUF {
    public int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i=0; i<N; i++){
            id[i] = i;
        }
    }

    public int root(int i) {
        while(id[i]!=i){
            i = id[i];
        }

        return i;
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (pid != qid){
            id[pid] = qid;
        }
    }
}
