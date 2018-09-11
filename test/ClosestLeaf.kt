import junit.framework.TestCase
import util.TreeNode

class ClosestLeaf : TestCase() {
    fun findClosestLeaf(root: TreeNode?, k: Int): Int {
        val kNode = findNode(root, k) ?: return k

        val leaf = findNearestLeaf(kNode, 0)
        return leaf!!.first
    }

    fun findNearestLeaf(node: TreeNode?, height: Int): Pair<Int, Int>? {
        if (node == null) {
            return null
        }

        if (node.left == null && node.right == null) {
            return Pair(node.`val`, height)
        }

        val left = findNearestLeaf(node.left, height + 1)
        val right = findNearestLeaf(node.right, height + 1)

        if (left == null) {
            return right
        }

        if (right == null) {
            return left
        }

        if (left.second < right.second) {
            return left
        } else {
            return right
        }
    }

    fun findNode(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) {
            return null
        }

        if (root.`val` == target) {
            return root
        }

        val found = findNode(root.left, target)
        if (found != null) {
            return found
        } else {
            return findNode(root.right, target)
        }
    }

    fun testSingleLeaf() {
        var root = TreeNode(1)
        assertEquals(1, findClosestLeaf(root, 1))

        root = TreeNode(2)
        assertEquals(2, findClosestLeaf(root, 2))
    }

    fun testSmallTree() {
        val root = TreeNode(1)
        root.left = TreeNode(2)

        assertEquals(2, findClosestLeaf(root, 1))
    }

    fun testLongerTree() {
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(7)
        root.left.left = TreeNode(3)
        root.left.left.left = TreeNode(4)
        root.left.left.right = TreeNode(6)
        root.left.left.left.left = TreeNode(5)
        root.left.left.left.left.left = TreeNode(8)

        assertEquals(7, findClosestLeaf(root, 1))
        assertEquals(7, findClosestLeaf(root, 7))
        assertEquals(6, findClosestLeaf(root, 2))
        assertEquals(6, findClosestLeaf(root, 3))
        assertEquals(6, findClosestLeaf(root, 6))
        assertEquals(8, findClosestLeaf(root, 4))
        assertEquals(8, findClosestLeaf(root, 5))
        assertEquals(8, findClosestLeaf(root, 8))
    }

    fun testSample() {
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left.left = TreeNode(4)
        root.left.left.left = TreeNode(5)
        root.left.left.left.left = TreeNode(6)

        assertEquals(3, findClosestLeaf(root, 1))
        assertEquals(6, findClosestLeaf(root, 2))
        assertEquals(6, findClosestLeaf(root, 4))
        assertEquals(6, findClosestLeaf(root, 5))
        assertEquals(6, findClosestLeaf(root, 6))
        assertEquals(3, findClosestLeaf(root, 3))
    }
}