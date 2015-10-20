import java.util.ArrayList;

public class TreeNode<T extends Comparable<T>> {
	public String name;
	public TreeNode<T> parent;
	public int count;
	public ArrayList<TreeNode<T>> children;
	
	public TreeNode(String name, TreeNode<T> parent, int count){
		this.name = name;
		this.parent = parent;
		this.count = count;
		children = new ArrayList<TreeNode<T>>();
	}
}
