## Algorithm Part I of Princeton University  
course link: [Algorithm Part I.Princeton University](https://www.coursera.org/learn/algorithms-part1)  
- 并查集  
  - 通过遍历方式合并连通集合，UnionV1 
  - 通过树回溯的方式分别找到两个节点所在集合的根节点，将其中一个节点的根节点的父节点设定为另外一个根节点，UnionV2
  - 在树回溯的过程中，不断将当前节点挂载到其父节点的父节点下，从而降低树的高度；最终将小的连通子集挂载到大的连通子集下，UnionV3 
  - 渗透阈值模拟系统，HomeWork  
  - 学习笔记总结：[[老刘的刷题笔记系列一]：并查集](https://zhuanlan.zhihu.com/p/587154818)