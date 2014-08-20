/*
 * 今天看到一道面试题，想不出很好的解法，请大牛们过过目，指点下，哈哈。

给定一个二叉树，所有的节点值（包括中间，叶子节点）有可能重复，题目要求找出所
有的没有重复节点的子树（包括叶子节点，这个算作一个节点的子树）。

e.g.

     3
  2     4
1 5   7 2

总共有6个这样的子树，即除了3之外，所有的节点所对应的子树都符合要求。
http://www.mitbbs.com/article_t/JobHunting/32763201.html

 */
import java.util.*;

public class FindNonDupeSubTree {

	private class Node{
		int val;
		Node left;
		Node right;
		Node (int val){
			this.val = val;
			left = null;
			right = null;
		}
	}
	
	public boolean FindSubTree(Node root, Set<Integer> nodeSet, ArrayList<Integer> validTreeRoots){
		if (root == null)
			return true;
		Set<Integer> sleft = new HashSet<Integer>();
		Set<Integer> sright = new HashSet<Integer>();
		
		boolean left = FindSubTree(root.left, sleft, validTreeRoots);
		boolean right = FindSubTree(root.right, sright, validTreeRoots);
		if (left && right){
			// both are valid, let's check if left and right has any overlaps.
			for (Integer i : sleft){
				if (sright.contains(i))
					return false;
			}
		}
		
		// now there's no overlap between left tree and right tree, check root is in any of them or not
		if (sleft.contains(root.val) || sright.contains(root.val))
			return false;
		
		// everything checked out as unique, merge root into all sets, and add root to the validtreeroots
		validTreeRoots.add(root.val);
		nodeSet.addAll(sright);
		nodeSet.addAll(sleft);
		nodeSet.add(root.val);
		
		return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FindNonDupeSubTree ft = new FindNonDupeSubTree();
		Node root = ft.new Node(3);
		Node temp = null;
		temp = ft.new Node(2);
		root.left = temp;
		temp = ft.new Node(4);
		root.right = temp;
		temp = ft.new Node(1);
		root.left.left = temp;
		temp = ft.new Node(5);
		root.left.right = temp;
		temp = ft.new Node(7);
		root.right.left = temp;
		temp = ft.new Node(2);
		root.right.right = temp;
		Set<Integer> s = new HashSet<Integer>();
		ArrayList<Integer> v = new ArrayList<Integer>();
		
		ft.FindSubTree(root,  s, v);
		System.out.println(v.size());
		
	}

}
