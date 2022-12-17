## Algorithm Part I of Princeton University  
course link: [Algorithm Part I.Princeton University](https://www.coursera.org/learn/algorithms-part1)  
- 并查集(UnionFind)  
  - 通过遍历方式合并连通集合，UnionV1 
  - 通过树回溯的方式分别找到两个节点所在集合的根节点，将其中一个节点的根节点的父节点设定为另外一个根节点，UnionV2
  - 在树回溯的过程中，不断将当前节点挂载到其父节点的父节点下，从而降低树的高度；最终将小的连通子集挂载到大的连通子集下，UnionV3 
  - 渗透阈值模拟系统，HomeWork  
  - 学习笔记总结：[[老刘的刷题笔记系列一]：并查集](https://zhuanlan.zhihu.com/p/587154818)  

- 栈和队列(StackAndQueue)  
  - 使用数组和列表两种方式，通过泛型实现可兼容任意数据类型的栈和队列   
  - 分别实现双端队列和随机队列  
  - 学习笔记总结：[[老刘的刷题笔记系列二]：栈和队列](https://zhuanlan.zhihu.com/p/588469212)   

- 排序算法(SortAlgorithm) 
  - 通过选择排序、插入排序、希尔排序、归并排序和快速排序算法对抽象数据类型的数组进行排序  
  - 编写一个程序，找出给定点集合中的所有直线  
  - 学习笔记总结：[[老刘的刷题笔记系列三]：排序算法](https://zhuanlan.zhihu.com/p/589593857)  

- 优先队列(PriorityQueue) 
  - 实现优先队列和堆排序  
  - 编写程序，通过A* 搜索算法解决8魔方问题
  - 学习笔记总结：[[老刘的刷题比例系列四]：优先队列](https://zhuanlan.zhihu.com/p/592606000)