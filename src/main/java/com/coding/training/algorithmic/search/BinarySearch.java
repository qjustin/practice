package com.coding.training.algorithmic.search;

/**
 * 首先引用一下《编程珠玑》中的两句话：
 * <p>
 * 尽管给了那么充裕的时间，只有大约10%的专业程序员能够写出正确的二分查找。
 * 尽管第一个二分查找程序于1946年就公布了，但是第一个没有bug的二分查找程序在1962年才出现。
 * <p>
 * 二分查找
 * 1. 如果找不 那么low 时左边插入的位， 而 high 是右边插入的位置
 * A = [1,3,3,5, '7' ,7,7,7,8,14,14]
 * <p>
 * https://zhuanlan.zhihu.com/p/42185010
 * <p>
 * 1. 最基本的二分
 * 其中，有几个要注意的点：
 * 1. 循环的判定条件是：low <= high
 * 2. 为了防止数值溢出，mid = low + (high - low)/2
 * 3. 当 A[mid]不等于target时，high = mid - 1或low = mid + 1
 * <p>
 * 二分变种
 * <p>
 * 2. 查找目标值区域的左边界/查找与目标值相等的第一个位置/查找第一个不小于目标值数的位置
 * A = [1,3,3,5, '7' ,7,7,7,8,14,14]
 * target = 7
 * return 4
 * <p>
 * 3. 查找目标值区域的右边界/查找与目标值相等的最后一个位置/查找最后一个不大于目标值数的位置
 * A = [1,3,3,5,7,7,7, '7' ,8,14,14]
 * target = 7
 * return 7
 * <p>
 * 4. 查找最后一个小于目标值的数/查找比目标值小但是最接近目标值的数
 * A = [1,3,3, '5' ,7,7,7,7,8,14,14]
 * target = 7
 * return 3
 * <p>
 * 5. 查找第一个大于目标值的数/查找比目标值大但是最接近目标值的数
 * A = [1,3,3,5,7,7,7,7, '8' ,14,14]
 * target = 7
 * return 8
 * <p>
 * 6. 旋转数组返回最小元素
 * 6.1 查找旋转数组的最小元素（假设不存在重复数字）
 * Input: [3,4,5, '1' ,2]
 * Output: 1
 * <p>
 * 6.2 查找旋转数组的最小元素（存在重复项）
 * Input: [2,2,2, '0' ,1]
 * Output: 0
 * <p>
 * 7. 在旋转排序数组中搜索
 * 7.1 不考虑重复项
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * 方法一：
 * 先利用方法 6.1 查找数组中的最小元素，即确定分界点的位置
 * 把旋转的数组当成偏移，用(offset + mid) % len来求真实的 mid 的位置。
 * 然后用二分查找来定位目标值
 * 方法二：
 * 其实没有必要找到旋转数组的分界点，对于搜索左侧还是右侧我们是可以根据mid跟high的元素
 * 大小来判定出来的，直接根据target的值做二分搜索就可以了。
 * 7.2 存在重复项
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * <p>
 * <p>
 * 8. 二分查找左右边界
 * 9. 二分查找极值点
 * <p>
 * 重要提示：
 * 记住规律：当查找的字符有重复时，大于号，小于号 箭头指向的方向就是取边界的方向，
 * 例如:
 * 取左边界 target <= arr[pivot]
 * 取右边界 target >= arr[pivot]
 * <p>
 * 不论左边界还有有边界，当元素找不到时，low就是元素应该插入的位置。前提时元素找不到。
 * <p>
 * return (low < arr.length && target == arr[low]) ? low : -1;
 * return (high >= 0 && target == arr[high]) ? high : -1;
 * return high < 0 ? -1 : high;
 * return low > (arr.length - 1) ? -1 : low;
 */
public class BinarySearch {

    /**
     * 1. 找基本二分
     * 2. 找二分左边界
     * 3. 找二分右边界
     * 4. 找最后一个小于目标值
     * 5. 找第一个大于目标值
     * 6. 找最小值
     * 7. 旋转数组二分
     */

    public static void main(String[] args) {
        String value = "value:{1, 1, 3, 5, 7, 7, 7, 7, 8, 14, 14}";
        String index = "index:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9 , 10]";
        int[] arr = new int[]{1, 1, 3, 5, 7, 7, 7, 7, 8, 14, 14};

        System.out.println(binarySearchLeftBound(arr, 14));
        System.out.println(binarySearchRightBound(arr, 14));
    }


    public static int binarySearchLeftBound(int[] arr, int target) {
        if (arr.length == 0) return -1;

        int low = 0;
        int high = arr.length - 1;


        while (low < high) {
            int pivot = low + (high - low) / 2;

            if (target <= arr[pivot]) {
                high = pivot - 1;
            } else {
                low = pivot + 1;
            }
        }

        return low < arr.length && target == arr[low] ? low : -1;
    }

    public static int binarySearchRightBound(int[] arr, int target) {
        if (arr.length == 0) return -1;

        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;

            if (target >= arr[pivot]) {
                low = pivot + 1;
            } else {
                high = pivot - 1;
            }
        }

        return (high >= 0 && target == arr[high]) ? high : -1;
    }















































//    public static void main(String[] args) {
//        String value = "value:{1, 1, 3, 5, 7, 7, 7, 7, 8, 14, 14}";
//        String index = "index:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9 , 10]";
//        int[] arr = new int[]{1, 1, 3, 5, 7, 7, 7, 7, 8, 14, 14};
//
//        System.out.println(value);
//        System.out.println(index);
//        System.out.println("基本二分查找: target=1, expected=0, idx=" + baseBinarySearchNotFoundReturnMinus(arr, 1));
//        System.out.println("基本二分查找: target=4, expected=-1, idx=" + baseBinarySearchNotFoundReturnMinus(arr, 4));
//        System.out.println("基本二分查找: target=5, expected=3, idx=" + baseBinarySearchNotFoundReturnMinus(arr, 5));
//        System.out.println("基本二分查找: target=7, expected=4, idx=" + baseBinarySearchNotFoundReturnMinus(arr, 7));
//        System.out.println("基本二分查找: target=14, expected=9, idx=" + baseBinarySearchNotFoundReturnMinus(arr, 14));
//
//        System.out.println("基本二分查找: target=1, expected=0, idx=" + baseBinarySearchNotFoundReturnInsertPosition(arr, 1));
//        System.out.println("基本二分查找: target=4, expected=3, idx=" + baseBinarySearchNotFoundReturnInsertPosition(arr, 4));
//        System.out.println("基本二分查找: target=5, expected=3, idx=" + baseBinarySearchNotFoundReturnInsertPosition(arr, 5));
//        System.out.println("基本二分查找: target=7, expected=4, idx=" + baseBinarySearchNotFoundReturnInsertPosition(arr, 7));
//        System.out.println("基本二分查找: target=14, expected=9, idx=" + baseBinarySearchNotFoundReturnInsertPosition(arr, 14));
//
//        System.out.println();
//        System.out.println(value);
//        System.out.println(index);
//
//        System.out.println("查找目标值区域的'左'边界: target=1, expected=0, idx=" + binarySearchLowerBound(arr, 1));
//        System.out.println("查找目标值区域的'左'边界: target=4, expected=-1, idx=" + binarySearchLowerBound(arr, 4));
//        System.out.println("查找目标值区域的'左'边界: target=5, expected=3, idx=" + binarySearchLowerBound(arr, 5));
//        System.out.println("查找目标值区域的'左'边界: target=7, expected=4, idx=" + binarySearchLowerBound(arr, 7));
//        System.out.println("查找目标值区域的'左'边界: target=14, expected=9, idx=" + binarySearchLowerBound(arr, 14));
//
//        System.out.println("查找目标值区域的'右'边界: target=1, expected=1, idx=" + binarySearchUpperBound(arr, 1));
//        System.out.println("查找目标值区域的'右'边界: target=4, expected=-1, idx=" + binarySearchUpperBound(arr, 4));
//        System.out.println("查找目标值区域的'右'边界: target=5, expected=3, idx=" + binarySearchUpperBound(arr, 5));
//        System.out.println("查找目标值区域的'右'边界: target=7, expected=7, idx=" + binarySearchUpperBound(arr, 7));
//        System.out.println("查找目标值区域的'右'边界: target=14, expected=10, idx=" + binarySearchUpperBound(arr, 14));
//
//        System.out.println();
//        System.out.println(value);
//        System.out.println(index);
//
//        System.out.println("查找最后一个小于目标值的数(找不到 X ，则输出 X 插入的下标): target=1, expected=X, idx=" + binarySearchLastLessThenTarget(arr, 1));
//        System.out.println("查找最后一个小于目标值的数(找不到 X ，则输出 X 插入的下标): target=4, expected=X, idx=" + binarySearchLastLessThenTarget(arr, 4));
//        System.out.println("查找最后一个小于目标值的数(找不到 X ，则输出 X 插入的下标): target=5, expected=2, idx=" + binarySearchLastLessThenTarget(arr, 5));
//        System.out.println("查找最后一个小于目标值的数(找不到 X ，则输出 X 插入的下标): target=7, expected=3, idx=" + binarySearchLastLessThenTarget(arr, 7));
//        System.out.println("查找最后一个小于目标值的数(找不到 X ，则输出 X 插入的下标): target=14, expected=8, idx=" + binarySearchLastLessThenTarget(arr, 14));
//
//        System.out.println("查找第一个大于目标值的数字(找不到 X ，则输出 X 插入的下标): target=1, expected=2, idx=" + binarySearchFirstGreatherThenTarget(arr, 1));
//        System.out.println("查找第一个大于目标值的数字(找不到 X ，则输出 X 插入的下标): target=4, expected=X, idx=" + binarySearchFirstGreatherThenTarget(arr, 4));
//        System.out.println("查找第一个大于目标值的数字(找不到 X ，则输出 X 插入的下标): target=5, expected=4, idx=" + binarySearchFirstGreatherThenTarget(arr, 5));
//        System.out.println("查找第一个大于目标值的数字(找不到 X ，则输出 X 插入的下标): target=7, expected=5, idx=" + binarySearchFirstGreatherThenTarget(arr, 7));
//        System.out.println("查找第一个大于目标值的数字(找不到 X ，则输出 X 插入的下标): target=14, expected=X, idx=" + binarySearchFirstGreatherThenTarget(arr, 14));
//
//        System.out.println();
//        System.out.println(value);
//        System.out.println(index);
//        System.out.println(search(arr, 1));
//        System.out.println(search(arr, 4));
//        System.out.println(search(arr, 5));
//        System.out.println(search(arr, 7));
//        System.out.println(search(arr, 14));
//
//        System.out.println("记住规律：当查找的字符有重复时，大于号，小于号 箭头指向的方向就是取边界的方向，例如:取做边界那么target <= arr[pivot] 取右边界时 target >= arr[pivot]");
//    }
//
//    /**
//     * 1. 基本二分查找的正确写法: 找不到时返回插入位置 (支持重复字符)
//     *
//     * @param arr
//     * @param target
//     * @return
//     */
//    public static int baseBinarySearchNotFoundReturnInsertPosition(int arr[], int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//
//            if (target <= arr[pivot]) {
//                high = pivot - 1;
//            } else {
//                low = pivot + 1;
//            }
//        }
//
//        return low;
//    }
//
//    /**
//     * 1. 基本二分查找的正确写法: 找不到时返回 -1 (支持重复字符)
//     *
//     * @param arr
//     * @param target
//     * @return
//     */
//    public static int baseBinarySearchNotFoundReturnMinus(int arr[], int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//
//            if (target <= arr[pivot]) {
//                high = pivot - 1;
//            } else {
//                low = pivot + 1;
//            }
//        }
//
//        // 注意：这里 low < arr.length 不是 high
//        return (low < len && target == arr[low]) ? low : -1;
//    }
//
//    /**
//     * 2. 查找目标值区域的左边界/查找与目标值相等的第一个位置/查找第一个不小于目标值数的位置
//     * A = [1,3,3,5, '7' ,7,7,7,8,14,14]
//     * target = 7
//     * return 4
//     */
//    public static int binarySearchLowerBound(int arr[], int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//            // 注意:这里是 ' >= '
//            if (target <= arr[pivot]) {
//                high = pivot - 1;
//            } else {
//                low = pivot + 1;
//            }
//        }
//
//        // 注意：这里 low < arr.length 不是 high, 另外 target = arr[low]
//        return (low < len && target == arr[low]) ? low : -1;
//    }
//
//    /**
//     * 3. 查找目标值区域的右边界/查找与目标值相等的最后一个位置/查找最后一个不大于目标值数的位置
//     * A = [1,3,3,5,7,7,7, '7' ,8,14,14]
//     * target = 7
//     * return 7
//     */
//    public static int binarySearchUpperBound(int[] arr, int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//
//            // 注意:这里是 ' >= '
//            if (target >= arr[pivot]) {
//                low = pivot + 1;
//            } else {
//                high = pivot - 1;
//            }
//        }
//
//        // 注意：这里 high >= 0 , 另外 target == arr[high]
//        return (high >= 0 && target == arr[high]) ? high : -1;
//    }
//
//    /**
//     * 4. 查找最后一个小于目标值的数/查找比目标值小但是最接近目标值的数 (找不到x则输出x所在的下标)
//     * A = [1,3,3, '5' ,7,7,7,7,8,14,14]
//     * target = 7
//     * return 3
//     * <p>
//     * 找到左边界 向后退一位即可 low - 1，当心越界。
//     */
//    public static int binarySearchLastLessThenTarget(int[] arr, int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//
//            if (target <= arr[pivot]) {
//                high = pivot - 1;
//            } else {
//                low = pivot + 1;
//            }
//        }
//
//        return high < 0 ? -1 : high;
//    }
//
//    /**
//     * 5. 查找第一个大于目标值的数/查找比目标值大但是最接近目标值的数 (找不到x则输出x所在的下标)
//     * <p>
//     * A = [1,3,3,5,7,7,7,7, '8' ,14,14]
//     * target = 7
//     * return 8
//     */
//    public static int binarySearchFirstGreatherThenTarget(int[] arr, int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//
//            if (target >= arr[pivot]) {
//                low = pivot + 1;
//            } else {
//                high = pivot - 1;
//            }
//        }
//
//        return low > (len - 1) ? -1 : low;
//    }
//
//    /**
//     * 6.2 查找旋转数组的最小元素（存在重复项）
//     */
//    public static int findMin(int[] arr) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low < high) {
//            pivot = low + (high - low) / 2;
//
//            if (arr[pivot] > arr[high])
//                low = pivot + 1;
//            else if (arr[pivot] < arr[high])    // else
//                high = pivot;                   // high - pivot;
//            else                                // 移除
//                high--;                         // 移除
//        }
//
//        return arr[low];
//    }
//
//    /**
//     * 7. 在旋转排序数组中搜索 (存在重复项)
//     */
//    public static int search(int[] arr, int target) {
//        int len = arr.length;
//        if (len == 0) return -1;
//
//        int low = 0;
//        int high = len - 1;
//        int pivot;
//
//        while (low <= high) {
//            pivot = low + (high - low) / 2;
//            if (arr[pivot] == target) {
//                return pivot;
//            } else if (arr[pivot] > arr[high]) {                    // } else if(nums[low] <= nums[pivot]) {
//                if (target < arr[pivot] && target >= arr[low])
//                    high = pivot;                                   // high = pivot - 1;
//                else
//                    low = pivot + 1;
//            } else if (arr[pivot] < arr[high]) {                     // } else if (arr[pivot] <= arr[high]) {
//                if (target > arr[pivot] && target <= arr[high])
//                    low = pivot + 1;
//                else
//                    high = pivot;                                   // high = pivot - 1;
//            } else {                                                // 移除
//                high--;                                             // 移除
//            }                                                       // 移除
//        }
//
//        return -1;
//    }
}











































