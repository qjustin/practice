# 集合

>1.集合和数组的区别
* 数组声明了它容纳的元素的类型，而集合不声明。
* 数组是静态的，一个数组实例具有固定的大小，一旦创建了就无法改变容量了。而集合是可以动态扩展容量，可以根据需要动态改变大小，集合提供更多的成员方法，能满足更多的需求。
* 数组的存放的类型只能是一种（基本类型/引用类型）,集合存放的类型可以不是一种(不加泛型时添加的类型是Object)。
* 数组是java语言中内置的数据类型,是线性排列的,执行效率或者类型检查都是最快的。

>2.常用的集合类有哪些?
* Map接口和Collection接口是所有集合框架的父接口：
* Collection接口的子接口包括：Set接口和List接口
* Map接口的实现类主要有：HashMap、TreeMap、Hashtable、ConcurrentHashMap以及Properties等
* Set接口的实现类主要有：HashSet、TreeSet、LinkedHashSet等
* List接口的实现类主要有：ArrayList、LinkedList、Stack以及Vector等
    
>3.集合框架底层数据结构
* Collection
    * List
        * Arraylist： Object数组
        * Vector： Object数组
        * LinkedList： 双向循环链表
    * Set
        * HashSet（无序，唯一）：基于 HashMap 实现的，底层采用 HashMap 来保存元素
        * LinkedHashSet： LinkedHashSet 继承与 HashSet，并且其内部是通过 LinkedHashMap 来实现的。有点类似于我们之前说的LinkedHashMap 其内部是基于 Hashmap 实现一样，不过还是有一点点区别的。
        * TreeSet（有序，唯一）： 红黑树(自平衡的排序二叉树。)
* Map
    * HashMap： JDK1.8之前HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）.JDK1.8以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间
    * LinkedHashMap：LinkedHashMap 继承自 HashMap，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，LinkedHashMap 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
    * HashTable： 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的
    * TreeMap： 红黑树（自平衡的排序二叉树）
    
>4.哪些集合类是线程安全的？
* vector：就比arraylist多了个同步化机制（线程安全），因为效率较低，现在已经不太建议使用。在web应用中，特别是前台页面，往往效率（页面响应速度）是优先考虑的。
* statck：堆栈类，先进后出。
* hashtable：就比hashmap多了个线程安全。
* enumeration：枚举，相当于迭代器。

>5.Java集合的快速失败机制 “fail-fast”？
* 是java集合的一种错误检测机制，当多个线程对集合进行结构上的改变的操作时，有可能会产生 fail-fast 机制。
* 例如：假设存在两个线程（线程1、线程2），线程1通过Iterator在遍历集合A中的元素，在某个时候线程2修改了集合A的结构（是结构上面的修改，而不是简单的修改集合元素的内容），那么这个时候程序就会抛出 ConcurrentModificationException 异常，从而产生fail-fast机制。
* 原因：迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCount 变量。集合在被遍历期间如果内容发生变化，就会改变modCount的值。每当迭代器使用hashNext()/next()遍历下一个元素之前，都会检测modCount变量是否为expectedmodCount值，是的话就返回遍历；否则抛出异常，终止遍历。
* 解决办法：
    * 在遍历过程中，所有涉及到改变modCount值得地方全部加上synchronized。
    * 使用CopyOnWriteArrayList来替换ArrayList

>6.怎么确保一个集合不能被修改？
* 可以使用 Collections. unmodifiableCollection(Collection c) 方法来创建一个只读集合，这样改变集合的任何操作都会抛出 Java. lang. UnsupportedOperationException 异常。
```
    示例代码如下：
    
    List<String> list = new ArrayList<>();
    list. add("x");
    Collection<String> clist = Collections. unmodifiableCollection(list);
    clist. add("y"); // 运行时此行报错
    System. out. println(list. size());
```
>7.HashMap 和 ConcurrentHashMap
* JDK7与JDK8中HashMap的不同点
    * JDK8中使用了红黑树
    * JDK7中链表使用了头插法（扩容转移元素的时候也使用头插法，头插法速度更快，无需遍历链表，但在多线程扩容的情况下使用头插法会出现循环链表的问题，导致CPU飙升）。JDK8中链表使用的是尾插法（JDK8中要去计算链表中节点的个数，需要遍历链表来获取总数，所以直接使用尾插法）
    * JDK7的Hash算法比JDK8中的更复杂，Hash算法越复杂生成的hashcode则更散列，那么HashMap中的元素则更散列，更散列则HashMap的查询性能更好，JDK7中没有红黑树，所以只能优化Hash算法使得元素分布更散列，而JDK8中增加了红黑树，查询性能得到了保障，所以可以简化一下Hash算法，比较Hash算法越复杂就越消耗CPU
    * 扩容过程中JDK7中有可能会重新对key进行哈希（重新Hash跟哈希种子有关系），而JDK8中没有这部分逻辑
    * JDK8中扩容条件和JDK7中不一样，除开判断size是否大于阈值之外，JDK7中还判断了tab[i]是否为空，不为空的时候才进行扩容，而JDK8中则没有该条件
    * JDK8还多了一个API：putIfAbsent(key,value)
    * JDK7和JDK8扩容过程中转移元素的逻辑不一样，JDK7每次转移一个元素，JDK8是先算出来当前位置上哪些元素在新数组的低位上，哪些在新数组的高位上，然后在一次性转移

* JDK1.8 HashMap 内部的数据结构?
    * JDK1.8版本的，内部使用数组 + 链表红黑树 
    ![avatar](https://github.com/qjustin/practice/blob/master/documents/images/00001.jpg)
    
* JDK1.8 HashMap put操作过程 (00002.jpg)
    * 判断数组是否为空，为空进行初始化;
    * 不为空，计算 k 的 hash 值，通过(n - 1) & hash计算应当存放在数组中的下标 index;
    * 查看 table[index] 是否存在数据，没有数据就构造一个Node节点存放在 table[index] 中；
    * 存在数据，说明发生了hash冲突(存在二个节点key的hash值一样), 继续判断key是否相等，相等，用新的value替换原数据(onlyIfAbsent为false)；
    * 如果不相等，判断当前节点类型是不是树型节点，如果是树型节点，创造树型节点插入红黑树中；
    * 如果不是树型节点，创建普通Node加入链表中；判断链表长度是否大于 8， 大于的话链表转换为红黑树；
    * 插入完成之后判断当前节点数是否大于阈值，如果大于开始扩容为原数组的二倍。

* JDK1.8 HashMap get操作过程
    * 根据key生成hashcode
    * 如果数组为空，则直接返回空
    * 数组不为空，则利用hashcode和数组的长度通过逻辑与操作算出key所对应的数组下标i
    * 如果数组的第i个位置上没有元素，则直接返回空
    * 如果数组的第1个位置上的元素key等于get方法所传进来的key，则返回元素，并获取该元素的value
    * 如果不等于则判断该元素还有没有下一个元素，如果没有，则返回空
    * 如果有则判断该元素的类型是链表节点还是红黑树节点
        a. 如果是链表则遍历链表
        b. 如果是红黑树则遍历红黑树
    * 找到则返回元素，找不到直接返回空
    
* JDK1.8 那HashMap怎么设定初始容量大小的？
    * 默认大小是16，负载因子是0.75， 如果自己传入初始大小k，初始化大小为 大于k的 2的整数次方，例如如果传10，大小为16。
    * 下图(00003.jpg)是详细过程，算法就是让初始二进制右移1，2，4，8，16位，分别与自己异或，把高位第一个为1的数通过不断右移，把高位为1的后几位全变为1，  
      111111 + 1 = 1000000 = 2^6 （符合大于50并且是2的整数次幂 ）

* 你知道HashMap的哈希函数怎么设计的吗？
    hash函数是先拿到通过key 的hashcode，是32位的int值，然后让hashcode的高16位和低16位进行异或操作。
    ``` 
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
    }
    ```
* 那你知道为什么这么设计吗？
    * 一定要尽可能降低hash碰撞，越分散越好；
    * 算法一定要尽可能高效，因为这是高频操作, 因此采用位运算；
    
* 为什么采用hashcode的高16位和低16位异或能降低hash碰撞？hash函数能不能直接用key的hashcode？
    * 因为key.hashCode()函数调用的是key键值类型自带的哈希函数，返回int型散列值。int值范围为**-2147483648~2147483647**，前后加起来大概40亿的映射空间。只要哈希函数映射得比较均匀松散，一般应用是很难出现碰撞的。但问题是一个40亿长度的数组，内存是放不下的。你想，如果HashMap数组的初始大小才16，用之前需要对数组的长度取模运算，得到的余数才能用来访问数组下标。
    * 源码中模运算就是把散列值和数组长度-1做一个"与"操作，位运算比%运算要快。
    ```
    bucketIndex = indexFor(hash, table.length);
    static int indexFor(int h, int length) {
         return h & (length-1);
    }
    ```
* 为什么HashMap的数组长度要取2的整数幂?
    * 因为这样（数组长度-1）正好相当于一个“低位掩码”。“与”操作的结果就是散列值的高位全部归零，只保留低位值，用来做数组下标访问。以初始长度16为例，16-1=15。2进制表示是00000000 00000000 00001111。和某散列值做“与”操作如下，结果就是截取了最低的四位值。
    ```
      10100101 11000100 00100101
    & 00000000 00000000 00001111
    ----------------------------------
      00000000 00000000 00000101    //高位全部归零，只保留末四位  
    ```
    * 但这时候问题就来了，这样就算我的散列值分布再松散，要是只取最后几位的话，碰撞也会很严重。更要命的是如果散列本身做得不好，分布上成等差数列的漏洞，如果正好让最后几个低位呈现规律性重复，就无比蛋疼。
  时候“扰动函数”的价值就体现出来了，说到这里大家应该猜出来了。看下面这个图(00004.jpg)
    * 右位移16位，正好是32bit的一半，自己的高半区和低半区做异或，就是为了混合原始哈希码的高位和低位，以此来加大低位的随机性。而且混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留下来。
      最后我们来看一下Peter Lawley的一篇专栏文章《An introduction to optimising a hashing strategy》里的的一个实验：他随机选取了352个字符串，在他们散列值完全没有冲突的前提下，对它们做低位掩码，取数组下标。
      (00005.jpg)
    * 结果显示，当HashMap数组长度为512的时候（2^9），也就是用掩码取低9位的时候，在没有扰动函数的情况下，发生了103次碰撞，接近30%。而在使用了扰动函数之后只有92次碰撞。碰撞减少了将近10%。看来扰动函数确实还是有功效的。
    * 另外Java1.8相比1.7做了调整，1.7做了四次移位和四次异或，但明显Java 8觉得扰动做一次就够了，做4次的话，多了可能边际效用也不大，所谓为了效率考虑就改成一次了。
    下面是1.7的hash代码：
    ```
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    ```
* HashMap是怎么解决哈希冲突的？

* JDK 1.8 对HashMap做了那些优化？
    * 数组+链表改成了数组+链表或红黑树；
    * 链表的插入方式从头插法改成了尾插法，简单说就是插入时，如果数组位置上已经有元素，1.7将新元素放到数组中，原始节点作为新节点的后继节点，1.8遍历链表，将元素放置到链表的最后；
    * 扩容的时候1.7需要对原数组中的元素进行重新hash定位在新数组的位置，1.8采用更简单的判断逻辑，位置不变或索引+旧容量大小；
    * 在插入时，1.7先判断是否需要扩容，再插入，1.8先进行插入，插入完成再判断是否需要扩容；

* JDK 1.8为什么要做这几点优化？
    * 1.8使用红黑树：防止发生hash冲突，链表长度过长，将时间复杂度由O(n)降为O(logn);
    * 1.8使用尾插法：因为1.7头插法扩容时，头插法会使链表发生反转，多线程环境下会产生环；
    A线程在插入节点B，B线程也在插入，遇到容量不够开始扩容，重新hash，放置元素，采用头插法，后遍历到的B节点放入了头部，这样形成了环，如下图所示：
    (00006.jpg)

* 扩容的时候为什么1.8 不用重新hash就可以直接定位原节点在新数据的位置呢?
     * 这是由于扩容是扩大为原数组大小的2倍，用于计算数组位置的掩码仅仅只是高位多了一个1，怎么理解呢？
     * 扩容前长度为16，用于计算(n-1) & hash 的二进制n-1为0000 1111，扩容为32后的二进制就高位多了1，为0001 1111。
     * 因为是& 运算，1和任何数 & 都是它本身，那就分二种情况，如下图：原数据hashcode高位第4位为0和高位为1的情况；
     * 第四位高位为0，重新hash数值不变，第四位为1，重新hash数值比原来大16（旧数组的容量）
     (00007.jpg)

* 那HashMap是线程安全的吗？
    * 不是，在多线程环境下，1.7 会产生死循环、数据丢失、数据覆盖的问题，1.8 中会有数据覆盖的问题，以1.8为例，看👇的代码，当A线程判断index位置为空后正好挂起，B线程开始往index位置的写入节点数据，这时A线程恢复现场，执行赋值操作，就把A线程的数据给覆盖了；还有++size这个地方也会造成多线程同时扩容等问题。

* 那你平常怎么解决这个线程不安全的问题？
    * Java中有HashTable、Collections.synchronizedMap、以及ConcurrentHashMap可以实现线程安全的Map。
      HashTable是直接在操作方法上加synchronized关键字，锁住整个数组，锁粒度比较大，Collections.synchronizedMap是使用Collections集合工具的内部类，通过传入Map封装出一个SynchronizedMap对象，内部定义了一个对象锁，方法通过对象锁实现线程安全；ConcurrentHashMap使用分段锁，降低了锁粒度，让并发度大大提高。

* 那你知道ConcurrentHashMap的分段锁的实现原理吗？
    * ConcurrentHashMap成员变量使用volatile 修饰，免除了指令重排序，同时保证内存可见性，另外使用CAS操作和synchronized结合实现赋值操作，多线程操作只会锁住当前操作索引的节点。
    * 如下图，线程A锁住A节点所在链表，线程B锁住B节点所在链表，操作互不干涉。
     (00008.jpg)

* 链表转红黑树是链表长度达到阈值，这个阈值是多少？
    * 阈值是8，红黑树转链表阈值为6
    
* 为什么是8，不是16，32甚至是7 ？又为什么红黑树转链表的阈值是6，不是8了呢？
    * 在hash函数设计合理的情况下，发生hash碰撞8次的几率为百万分之6，概率说话。。因为8够用了，至于为什么转回来是6，因为如果hash碰撞次数在8附近徘徊，会一直发生链表和红黑树的互相转化，为了预防这种情况的发生，设置为6。

* HashMap内部节点是有序的吗？
    * 是无序的，根据hash值随机插入

* JDK8中HashMap为什么要使用红黑树
    * 当元素个数小于一个阈值时，链表整体的插入和查询效率要高于红黑树，但元素个数大于此阈值时，链表整体的插入查询效率要低于红黑树。此阈值在HashMap中为8。

* JDK8中HashMap什么时候将链表转换为红黑树
    * 这个问题很容易打错，大部分答案是：单链表中元素大于8时就会把链表转化为红黑树。但其实还有另外一个限制：当发现链表中的元素个数大于8之后，还会判断一下当前数组的长度，如果长度小于64是，此时并不会转化为红黑树，而是进行扩容。只有链表中的元素大于8，并且数组的长度大于等于64时才会将链表转化为红黑树。
上面扩容的原因是，如果数组长度还比较小，就先利用扩容来缩小链表的长度。

* JDK7中的ConcurrentHashMap的底层原理
    * ConcurrentHashMap底层是由两层嵌套数组实现的：
        1. ConcurrentHashMap对象中有一个属性segments，类型Segment[]
        2. Segment对象中有一个属性table，类型为HashEntry[]
    * 当调用ConcurrentHashMap的put方法时，现根据key计算出对应的Segment[]数组下标j，确定好当前key和value应该插入到哪个Segment对象中，如果segments[j]为空，则利用自旋锁的方式在j位置上生成一个Segment对象
    * 然后调用Segment对象的put方法
    * Segment对象的put方法会先加锁，然后根据key计算出所对应的HashEntry[]数组下标i，然后将key和value封装成HashEntry对象放入该位置，此过程和JDK7的HashMap的put方法一样，操作完成后解锁。
    * 在加锁的过程中逻辑比较复杂，先通过自旋加锁，如果超过一定次数就会直接阻塞等等加锁。

* JDK8中的ConcurrentHashMap是怎样保证并发安全的
    * 主要利用了Unsafe类操作+synchronized关键字。
    * Unsafe类仍然和JDK7中类似，主要复杂并发安全的修改对象属性或数组某个位置的值。
    * synchronized主要负责在需要操作某个位置是进行加锁（该位置不为空），比如向某个位置的链表插入节点，向某个位置的红黑树插入节点。
    * JDK8中其实仍然有分段锁的思想，只不过JDK7中段数是可以控制的，而JDK8中是数组的每一个位置都有一把锁。
    * 当向ConcurrentHashMap中put一个key、value时：
        1. 首先根据key计算对应数组下标i，如果该位置没有元素，则通过自旋的方式去向该位置赋值。
        2. 如果该位置有元素，则使用synchronized加锁
        3. 加锁成功后，在判断该元素的类型
        a. 如果是链接则添加节点到链表中
        b. 如果是红黑树则添加节点到红黑树中
        4. 添加成功后，判断是否需要进行树化
        5. addCount，这个方法的意思的ConcurrentHashMap的元素个数加1，但是这个操作也是需要并发安全的，并且元素个数加1成功后，会继续判断是否要进行扩容，如需要，则会进行扩容，所以这个方法很重要
        6. 同一个线程在put时如果发现当前ConcurrentHashMap正在进行扩容则会帮助扩容。

* JDK7和JDK8中的ConcurrentHashMap的不同点
    * JDK8中没有分段锁了，而是使用synchronized关键字来进行控制
    * DK8中的扩容性能更好，支持多线程同时扩容，实际上JDK7中也支持多线程扩容，因为JDK7中的扩容是针对每个Segment的，所以也可能多线程扩容，但是性能没有JDK8高，因为JDK8中对于任意一个线程都可以去帮助扩容。
    * JDK8中的元素个数统计的实现也不一样了，JDK8中增加了CounterCell来帮助计数，而JDK7中没有，JDK7中是put的时候每个Segment内部计数，统计的时候是遍历每个Segment对象加锁统计

* HashMap是怎么解决哈希冲突的？
    * 什么是哈希？
        * Hash，一般翻译为“散列”，也有直接音译为“哈希”的，这就是把任意长度的输入通过散列算法，变换成固定长度的输出，该输出就是散列值（哈希值）；这种转换是一种压缩映射，也就是，散列值的空间通常远小于输入的空间，不同的输入可能会散列成相同的输出，所以不可能从散列值来唯一的确定输入值。简单的说就是一种将任意长度的消息压缩到某一固定长度的消息摘要的函数。
        * 所有散列函数都有如下一个基本特性：根据同一散列函数计算出的散列值如果不同，那么输入值肯定也不同。但是，根据同一散列函数计算出的散列值如果相同，输入值不一定相同。
    * 什么是哈希冲突？
        * 当两个不同的输入值，根据同一散列函数计算出相同的散列值的现象，我们就把它叫做碰撞（哈希碰撞）。
    * HashMap的数据结构
        * 在Java中，保存数据有两种比较简单的数据结构：数组和链表。数组的特点是：寻址容易，插入和删除困难；链表的特点是：寻址困难，但插入和删除容易；所以我们将数组和链表结合在一起，发挥两者各自的优势，使用一种叫做链地址法的方式可以解决哈希冲突：
        （00009.png） 这样我们就可以将拥有相同哈希值的对象组织成一个链表放在hash值所对应的bucket下，但相比于hashCode返回的int类型，我们HashMap初始的容量大小DEFAULT_INITIAL_CAPACITY = 1 << 4（即2的四次方16）要远小于int类型的范围，所以我们如果只是单纯的用hashCode取余来获取对应的bucket这将会大大增加哈希碰撞的概率，并且最坏情况下还会将HashMap变成一个单链表，所以我们还需要对hashCode作一定的优化
    * hash()函数
        * 上面提到的问题，主要是因为如果使用hashCode取余，那么相当于参与运算的只有hashCode的低位，高位是没有起到任何作用的，所以我们的思路就是让hashCode取值出的高位也参与运算，进一步降低hash碰撞的概率，使得数据分布更平均，我们把这样的操作称为扰动，在JDK 1.8中的hash()函数如下：
        ```
        static final int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);// 与自己右移16位进行异或运算（高低位异或）
        }
        ```
    * 通过上面的链地址法（使用散列表）和扰动函数我们成功让我们的数据分布更平均，哈希碰撞减少，但是当我们的HashMap中存在大量数据时，加入我们某个bucket下对应的链表有n个元素，那么遍历时间复杂度就为O(n)，为了针对这个问题，JDK1.8在HashMap中新增了红黑树的数据结构，进一步使得遍历复杂度降低至O(logn)；
    * 简单总结一下HashMap是使用了哪些方法来有效解决哈希冲突的：
        * 使用链地址法（使用散列表）来链接拥有相同hash值的数据；
        * 使用2次扰动函数（hash函数）来降低哈希冲突的概率，使得数据分布更平均；
        * 引入红黑树进一步降低遍历的时间复杂度，使得遍历更快；

* HashMap为什么不直接使用hashCode()处理后的哈希值直接作为table的下标？
    * hashCode()方法返回的是int整数类型，其范围为-(2 ^ 31)~(2 ^ 31 - 1)，约有40亿个映射空间，而HashMap的容量范围是在16（初始化默认值）~2 ^ 30，HashMap通常情况下是取不到最大值的，并且设备上也难以提供这么多的存储空间，从而导致通过hashCode()计算出的哈希值可能不在数组大小范围内，进而无法匹配存储位置；

* 那怎么解决呢？
    * HashMap自己实现了自己的hash()方法，通过两次扰动使得它自己的哈希值高低位自行进行异或运算，降低哈希碰撞概率也使得数据分布更平均；
    * 在保证数组长度为2的幂次方的时候，使用hash()运算之后的值与运算（&）（数组长度 - 1）来获取数组下标的方式进行存储，这样一来是比取余操作更加有效率，二来也是因为只有当数组长度为2的幂次方时，h&(length-1)才等价于h%length，三来解决了“哈希值与数组大小范围不匹配”的问题；

* 为什么数组长度要保证为2的幂次方呢？
    * 只有当数组长度为2的幂次方时，h&(length-1)才等价于h%length，即实现了key的定位，2的幂次方也可以减少冲突次数，提高HashMap的查询效率；
    * 如果 length 为 2 的次幂 则 length-1 转化为二进制必定是 11111……的形式，在于 h 的二进制与操作效率会非常的快，而且空间不浪费；如果 length 不是 2 的次幂，比如 length 为 15，则 length - 1 为 14，对应的二进制为 1110，在于 h 与操作，最后一位都为 0 ，而 0001，0011，0101，1001，1011，0111，1101 这几个位置永远都不能存放元素了，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！这样就会造成空间的浪费。
 
>8.Iterator 怎么使用？有什么特点？
* Iterator 的特点是只能单向遍历，但是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，就会抛出 ConcurrentModificationException 异常。

>9.如何边遍历边移除 Collection 中的元素？
* 边遍历边修改 Collection 的唯一正确方式是使用 Iterator.remove() 方法，如下：
    ```
    正确方式
    Iterator<Integer> it = list.iterator();
    while(it.hasNext()) {
       *// do something*
       it.remove();
    }
    ```
* 运行以上错误代码会报 ConcurrentModificationException 异常。这是因为当使用 foreach(for(Integer i : list)) 语句时，会自动生成一个iterator 来遍历该 list，但同时该 list 正在被 Iterator.remove() 修改。Java 一般不允许一个线程在遍历 Collection 时另一个线程修改它。
    ```
    错误方式
    for(Integer i : list) {
          list.remove(i)
    }
    ```
>10.为什么HashMap中String、Integer这样的包装类适合作为K？
* String、Integer等包装类的特性能够保证Hash值的不可更改性和计算准确性，能够有效的减少Hash碰撞的几率
* 都是final类型，即不可变性，保证key的不可更改性，不会存在获取hash值不同的情况
* 内部已重写了equals()、hashCode()等方法，遵守了HashMap内部的规范（不清楚可以去上面看看putValue的过程），不容易出现Hash值计算错误的情况；


* HashSet是如何保证数据不可重复的？
    * HashSet的底层其实就是HashMap，只不过我们HashSet是实现了Set接口并且把数据作为K值，而V值一直使用一个相同的虚值来保存，我们可以看到源码：
    ```
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;// 调用HashMap的put方法,PRESENT是一个至始至终都相同的虚值
    }
    ```
    * 由于HashMap的K值本身就不允许重复，并且在HashMap中如果K/V相同时，会用新的V覆盖掉旧的V，然后返回旧的V，那么在HashSet中执行这一句话始终会返回一个false，导致插入失败，这样就保证了数据的不可重复性；

>11.ref
* https://thinkwon.blog.csdn.net/article/details/104588551

#基础类型
>1.String
* 字符串常量池的设计意图是什么？
    * 字符串的分配，和其他的对象分配一样，耗费高昂的时间与空间代价，作为最基础的数据类型，大量频繁的创建字符串，极大程度地影响程序的性能
    * JVM为了提高性能和减少内存开销，在实例化字符串常量的时候进行了一些优化
        * 为字符串开辟一个字符串常量池，类似于缓存区
        * 创建字符串常量时，首先坚持字符串常量池是否存在该字符串
        * 存在该字符串，返回引用实例，不存在，实例化该字符串并放入池中
    * 实现的基础
        * 实现该优化的基础是因为字符串是不可变的，可以不用担心数据冲突进行共享
        * 运行时实例创建的全局字符串常量池中有一个表，总是为池中每个唯一的字符串对象维护一个引用,这就意味着它们一直引用着字符串常量池中的对象，所以，在常量池中的这些字符串不会被垃圾收集器回收
    * 从字符串常量池中获取相应的字符串
    ```
    String str1 = “hello”;
    String str2 = “hello”;
    System.out.printl（"str1 == str2" : str1 == str2 ) //true 
    ```
* 字符串常量池在哪里？
    * 字符串常量池则存在于方法区
    * 堆栈方法区存储字符串
    ```
    String str1 = “abc”;
    String str2 = “abc”;
    String str3 = “abc”;
    String str4 = new String(“abc”);
    String str5 = new String(“abc”);
    ```
    (00010.jpg)
    
    * 字符串对象的创建
        * String str4 = new String(“abc”) 创建多少个对象？
            * 在常量池中查找是否有“abc”对象
                * 有则返回对应的引用实例
                * 没有则创建对应的实例对象
            * 在堆中 new 一个 String("abc") 对象
            * 将对象地址赋值给str4,创建一个引用
            * 所以，常量池中没有“abc”字面量则创建两个对象，否则创建一个对象，以及创建一个引用 
        * String str1 = new String("A"+"B") ; 会创建多少个对象? 
            ```
            str1:
            字符串常量池: "A","B","AB": 3个
            堆: new String("AB"): 1个
            引用: str1: 个
            总共: 5个
            ```
        * String str2 = new String("ABC") + "ABC" ; 会创建多少个对象? 
            ```
            str2:
            字符串常量池:"ABC": 1个
            堆: new String("ABC"): 1个
            引用: str2 : 1个
            总共:3个
            ```
    * 基础类型的变量和常量，变量和引用存储在栈中，常量存储在常量池中
    ```
    int a1 = 1;
    int a2 = 1;
    int a3 = 1;
    
    public static int INT1 =1 ;
    public static int INT2 =1 ;
    public static int INT3 =1 ;
    ```
    (00011.jpg)
* 如何操作字符串常量池？
    * JVM实例化字符串常量池时
    ```
    String str1 = “hello”;
    String str2 = “hello”;
    
    System.out.printl（"str1 == str2" : str1 == str2 ) //true
    ```
    * String.intern()
        * 通过new操作符创建的字符串对象不指向字符串池中的任何对象，但是可以通过使用字符串的intern()方法来指向其中的某一个。java.lang.String.intern()返回一个保留池字符串，就是一个在全局字符串池中有了一个入口。如果以前没有在全局字符串池中，那么它就会被添加到里面
        ```
         // Create three strings in three different ways.
         String s1 = "Hello";
         String s2 = new StringBuffer("He").append("llo").toString();
         String s3 = s2.intern();
       
         // Determine which strings are equivalent using the ==
         // operator
         System.out.println("s1 == s2? " + (s1 == s2)); // false
         System.out.println("s1 == s3? " + (s1 == s3)); // true
        ```
* 字面量和常量池初探
    * 字符串对象内部是用字符数组存储的
        ```
        String m = "hello,world";
        String n = "hello,world";
        String u = new String(m);
        String v = new String("hello,world");
        ```
        * 会分配一个11长度的char数组，并在常量池分配一个由这个char数组组成的字符串，然后由m去引用这个字符串
        * 用n去引用常量池里边的字符串，所以和n引用的是同一个对象
        * 生成一个新的字符串，但内部的字符数组引用着m内部的字符数组
        * 同样会生成一个新的字符串，但内部的字符数组引用常量池里边的字符串内部的字符数组，意思是和u是同样的字符数组
        使用图来表示的话，情况就大概是这样的(使用虚线只是表示两者其实没什么特别的关系)
        (00012.png)
        ```
        String m = "hello,world";
        String n = "hello,world";
        String u = new String(m);
        String v = new String("hello,world");
        
        System.out.println(m == n); //true 
        System.out.println(m == u); //false
        System.out.println(m == v); //false
        System.out.println(u == v); //false 
        ```
        结论：
        * m和n是同一个对象
        * m,u,v都是不同的对象
        * m,u,v,n但都使用了同样的字符数组，并且用equal判断的话也会返回true
#泛型
#JVM
#多线程
 








































































