package tree

import junit.framework.TestCase
import util.TreeNode

/**
 * @see <a href="https://leetcode.com/problems/balanced-binary-tree/">Problem Description</a>
 */
@Suppress("MemberVisibilityCanBePrivate")
// todo try again
class BalancedBinaryTree : TestCase() {
    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }

        var leftHeight = height(root.left, 0)
        var rightHeight = height(root.right, 0)
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false
        }

        return isBalanced(root.left) && isBalanced(root.right)

//        val minHeight = minHeight(root, 0)
//        val maxHeight = maxHeight(root, 0)
//
//        return maxHeight - minHeight <= 1
    }

    fun minHeight(node: TreeNode?, height: Int): Int {
        if (node == null) {
            return height
        }
        val rightHeight = minHeight(node.right, height + 1)
        val leftHeight = minHeight(node.left, height + 1)
        return Math.min(rightHeight, leftHeight)
    }

    fun maxHeight(node: TreeNode?, height: Int): Int {
        if (node == null) {
            return height
        }
        val rightHeight = maxHeight(node.right, height + 1)
        val leftHeight = maxHeight(node.left, height + 1)
        return Math.max(rightHeight, leftHeight)
    }

    fun height(root: TreeNode?, rootHeight: Int): Int {
        if (root == null) {
            return rootHeight
        }
        val leftHeight = height(root.left, rootHeight + 1)
        val rightHeight = height(root.right, rootHeight + 1)
        return Math.max(leftHeight, rightHeight)
    }

    fun test1() {
        var tree = TreeNode(1)
        tree.left = TreeNode(2)
        tree.left!!.left = TreeNode(3)

        assertFalse(isBalanced(tree))
    }

    fun test2() {
        var tree = TreeNode(1)
        tree.left = TreeNode(2)
        tree.right = TreeNode(2)

        assertTrue(isBalanced(tree))
    }

    fun test3() {
        var tree = TreeNode(1)
        tree.left = TreeNode(2)
        tree.right = TreeNode(2)
        tree.left!!.right = TreeNode(3)

        assertTrue(isBalanced(tree))
    }

    fun test4() {
        var tree = TreeNode(1)
        tree.left = TreeNode(2)
        tree.right = TreeNode(2)

        tree.left!!.left = TreeNode(3)
        tree.left!!.right = TreeNode(3)
        tree.right!!.left = TreeNode(3)
        tree.right!!.right = TreeNode(3)

        tree.left!!.left!!.left = TreeNode(4)
        tree.left!!.left!!.right = TreeNode(4)
        tree.left!!.right!!.left = TreeNode(4)
        tree.left!!.right!!.right = TreeNode(4)
        tree.right!!.left!!.left = TreeNode(4)
        tree.right!!.left!!.right = TreeNode(4)

        tree.left!!.left!!.left!!.left = TreeNode(5)
        tree.left!!.left!!.left!!.right = TreeNode(5)

        assertTrue(isBalanced(tree))
    }
}