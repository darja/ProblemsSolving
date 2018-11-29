package set;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @see <a href="https://leetcode.com/problems/contains-duplicate-iii/">Problem Description</a>
 */
// todo not accepted
public class ContainsDuplicate3 extends TestCase {

    public void test() {
//        testSingle(true, new int[] {1,2,2,2,3,1}, 3, 0);
        testSingle(false, new int[] {1,2,5,4,2,5,1,2,3,1}, 2, 0);
        testSingle(false, new int[] {1}, 6, 0);
        testSingle(false, new int[] {1}, 1, 9);
        testSingle(true, new int[] {1,1}, 2, 0);
        testSingle(true, new int[] {1,1}, 5, 3);
        testSingle(false, new int[] {1,2,4,1}, 2, 0);
        testSingle(true, new int[] {1,2,4,1}, 2, 1);
        testSingle(false, new int[] {1,11,111}, 2, 4);
        testSingle(true, new int[] {10,100,11,9}, 1, 2);
    }

    private void testSingle(boolean expected, int[] nums, int k, int t) {
        assertEquals(expected, containsNearbyAlmostDuplicate(nums, k, t));
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Number> tree = new TreeSet<Number>(new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                if (o1.value == o2.value) {
                    return o1.index - o2.index;
                }
                return o1.value - o2.value;
            }
        });

        for (int i = 0; i < nums.length; ++i) {
            tree.add(new Number(nums[i], i));
        }

        Number prev = tree.pollFirst();
        while (!tree.isEmpty()) {
            Number curr = tree.pollFirst();
            if (Math.abs(prev.value - curr.value) <= t && Math.abs(prev.index - curr.index) < k) {
                return true;
            }
            prev = curr;
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums.length < 2) {
            return false;
        }
        Number root = new Number(nums[0], 0);
        for (int i = 1; i < nums.length; ++i) {
            boolean check = root.addChildCheckingAlmostDuplicate(new Number(nums[i], i), k, t);
            if (check) {
                return true;
            }
        }
        return false;
    }

    class Number {
        int value;
        int index;
        Number left;
        Number right;

        public Number(int value, int index) {
            this.value = value;
            this.index = index;
        }

        boolean isAlmostDuplicate(Number node, int k, int t) {
            return (Math.abs(node.value - value) <= t && Math.abs(node.index - index) <= k);
        }

        boolean addChildCheckingAlmostDuplicate(Number node, int k, int t) {
            if (isAlmostDuplicate(node, k, t)) {
                return true;
            }

            if (node.value < this.value) {
                if (left == null) {
                    left = node;
                } else {
                    return left.addChildCheckingAlmostDuplicate(node, k, t);
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    return right.addChildCheckingAlmostDuplicate(node, k, t);
                }
            }

            return false;
        }
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Node tNode = new Node(nums[0]);
        int n = nums.length;

        boolean isNearby;
        for (int i = 1; i < Math.min(n-1, k); ++i) {
            isNearby = tNode.addCheckingNearby(new Node(nums[i]), t);
            if (isNearby) {
                return true;
            }
        }

        if (k+1 < n) {
            for (int i = k; i < n - k; ++i) {
                tNode = tNode.remove(nums[i - k]);
                isNearby = tNode.addCheckingNearby(new Node(nums[i]), t);
                if (isNearby) {
                    return true;
                }
            }

            for (int i = Math.min(k, n-k); i < n; ++i) {
                isNearby = tNode.addCheckingNearby(new Node(nums[i]), t);
                if (isNearby) {
                    return true;
                }
            }
        }
        return false;
    }

    class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }

        boolean isNearby(Node node, int t) {
            return (Math.abs(node.value - value) <= t);
        }

        boolean addCheckingNearby(Node node, int t) {
            if (isNearby(node, t)) {
                return true;
            }

            if (node.value < this.value) {
                if (left == null) {
                    left = node;
                } else {
                    return left.addCheckingNearby(node, t);
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    return right.addCheckingNearby(node, t);
                }
            }

            return false;
        }

        void insert(Node node) {
            if (node.value < this.value) {
                if (left == null) {
                    left = node;
                } else {
                    left.insert(node);
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    right.insert(node);
                }
            }
        }

        Node remove(int value) {
            if (this.value == value) {
                if (right == null) {
                    return left;
                } else if (left == null) {
                    return right;
                } else {
                    right.insert(left);
                    return right;
                }
            } else if (this.value > value) {
                left = left.remove(value);
                return this;
            } else {
                right = right.remove(value);
                return this;
            }
        }
    }
}
