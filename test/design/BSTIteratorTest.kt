package design

import junit.framework.TestCase
import util.TreeNode

class BSTIteratorTest : TestCase() {
    fun testTrivial() {
        val it = BSTIterator(null)
        TestCase.assertFalse(it.hasNext())
    }

    fun testOneElement() {
        checkIterator(TreeNode(1), intArrayOf(1))
    }

    fun testOnlyLeft() {
        val tree = TreeNode(4)
        tree.left = TreeNode(1)

        checkIterator(tree, intArrayOf(1, 4))
    }

    fun testOnlyRight() {
        val tree = TreeNode(4)
        tree.right = TreeNode(6)

        checkIterator(tree, intArrayOf(4, 6))
    }

    fun testBothLeftAndRight() {
        val tree = TreeNode(4)
        tree.left = TreeNode(2)
        tree.right = TreeNode(6)

        checkIterator(tree, intArrayOf(2, 4, 6))
    }

    fun testLongerTree() {
        val tree = TreeNode(5)
        tree.left = TreeNode(3)
        tree.left.left = TreeNode(2)
        tree.left.right = TreeNode(4)
        tree.left.left.left = TreeNode(1)
        tree.left.left.left.left = TreeNode(0)
        tree.right = TreeNode(7)
        tree.right.left = TreeNode(6)
        tree.right.right = TreeNode(8)
        tree.right.right.right = TreeNode(9)

        checkIterator(tree, (0..9).toList().toIntArray())
    }

    private fun checkIterator(tree: TreeNode, expectedElements: IntArray) {
        val it = BSTIterator(tree)

        for (element in expectedElements) {
            assertTrue(it.hasNext())
            assertEquals(element, it.next())
        }
        assertFalse(it.hasNext())
    }

}
