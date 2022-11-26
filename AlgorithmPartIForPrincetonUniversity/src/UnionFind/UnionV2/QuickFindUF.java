package UnionFind.UnionV2;

/**
 * 连通集合合并方式：通过树回溯的方式分别找到两个节点所在集合的根节点，将其中一个节点的根节点的父节点设定为另外一个根节点。平均时间复杂度为logN，最坏情况下为N
 */
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
