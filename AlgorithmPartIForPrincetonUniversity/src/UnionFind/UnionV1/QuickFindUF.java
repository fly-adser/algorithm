package UnionFind.UnionV1;

/**
 * 连通集合合并方式：通过遍历方式将其中一个集合的所有根节点替换掉。合并集合的时间复杂度为N
 */
public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i=0; i<N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[p]==id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i=0; i<id.length; i++) {
            if(id[i]==pid) {
                id[i] = qid;
            }
        }
    }
}