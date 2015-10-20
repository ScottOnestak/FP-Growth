import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImplementFPGrowth<T extends Comparable<T>>{

	//global variables
	private Map<String,ArrayList<TreeNode<T>>> nodeLinks = new HashMap<String,ArrayList<TreeNode<T>>>();
	private TreeNode<T> root;
	public String name;
	public TreeNode<T> parent;
	public int count;
	public ArrayList<TreeNode<T>> children = new ArrayList<TreeNode<T>>();
	
	public ImplementFPGrowth(){
		root = new TreeNode<T>("root",null,0);
	}
	
	//before inserting into the tree, sort the attributes by ranking
	public Map<String,ArrayList<TreeNode<T>>> insertPrep(ArrayList<String> array, ArrayList<String> rankings){
		ArrayList<String> attr = prime(array,rankings);
		insert(attr,root);
		return nodeLinks;
		
	}
	
	//insert into the tree
	private void insert(ArrayList<String> attr, TreeNode<T> root){
		//if array of attributes is greater than 0
		if(attr.size() > 0){
			boolean contains = false;
			int index = 0;
			//check to see if the first attribute is already a child of the current node...if it is, break
			for(int i = 0; i < root.children.size(); i++){
				if(root.children.get(i).name.equals(attr.get(0))){
					contains = true;
					index = i;
					break;
				}
			}
			
			//if it is a child, increment count, remove attribute, and recursively call method
			//if not a child, create new node with count 1, add the treeNode to the arraylist of node links if it already exists...
			//...if it does not exist, create new item in hashmap, remove the attribute, and recursively call the method
			if(contains){
				root.children.get(index).count++;
				attr.remove(0);
				insert(attr,root.children.get(index));
			} else {			
				TreeNode<T> newNode = new TreeNode<T>(attr.get(0),root,1);
				root.children.add(newNode);
				if(nodeLinks.containsKey(newNode.name)){
					nodeLinks.get(newNode.name).add(newNode);
				} else {
					ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
					nodes.add(newNode);
					nodeLinks.put(newNode.name, nodes);
				}
				attr.remove(0);
				insert(attr,root.children.get(root.children.indexOf(newNode)));
			}
		}
		
	}

	//sorts the attributes by ranking and returns the array of attributes in ranking order
	private ArrayList<String> prime(ArrayList<String> array,ArrayList<String> rankings){
		ArrayList<String> attSort = new ArrayList<String>();
		
		for(int i = 0; i < rankings.size(); i++){
			if(array.contains(rankings.get(i))){
				attSort.add(rankings.get(i));
			}
		}
		
		return attSort;
	}
	
	//construct the frequency pattern tree...return its nodeLinks
	public Map<String,ArrayList<TreeNode<T>>> constructFPTree(Map<ArrayList<String>,Integer> map){
		for(Map.Entry<ArrayList<String>, Integer> entry: map.entrySet()){
			treeConstruct(entry.getKey(),entry.getValue(),root);
		}
		return nodeLinks;
	}
	
	//constructs tree...same setup as insert, only this time we do not increment, but increase by the frequency
	private void treeConstruct(ArrayList<String> attr, int count, TreeNode<T> root){
		if(attr.size() > 0){
			boolean contains = false;
			int index = 0;
			for(int i = 0; i < root.children.size(); i++){
				if(root.children.get(i).name.equals(attr.get(0))){
					contains = true;
					index = i;
					break;
				}
			}
			
			if(contains){
				root.children.get(index).count += count;
				attr.remove(0);
				treeConstruct(attr,count,root.children.get(index));
			} else {
				TreeNode<T> newNode = new TreeNode<T>(attr.get(0),root,count);
				root.children.add(newNode);
				if(nodeLinks.containsKey(newNode.name)){
					nodeLinks.get(newNode.name).add(newNode);
				} else {
					ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
					nodes.add(newNode);
					nodeLinks.put(newNode.name, nodes);
				}
				attr.remove(0);
				treeConstruct(attr,count,root.children.get(root.children.indexOf(newNode)));
			}
		}
	}
}
