import java.util.Stack;


public class BinaryTree{
	public static Node root;
	
	BinaryTree()
	{
		root = null;
	}
	
	public boolean find(int data)
	{
		Node current = root;
		while(current!=null)
		{
			if(current.data == data)
				return true;
			else if(current.data> data)
			{
				current = current.left;
			}
			else
			{
				current = current.right;
			}
		}
		return false;
				
	}
	
	public void insert(int data)
	{
		Node newNode = new Node(data);
		if (root == null)
		{
			root = newNode;
			return;
		}
		Node parent  = null;
		Node current = root;
		while(current!= null)
		{
			parent = current;
			if(current.data > newNode.data)
			{
				current = current.left;
				if(current == null)
				{
					newNode.parent = parent;
					current = newNode;
					return;
				}
			}
			else {
				current = current.right;
				if(current == null)
				{
					newNode.parent = parent;
					current = newNode;
					return;
				}
			}
		}
			
	}
	
	public boolean delete(int data)
	{
		Node current = root;
		Node parent = null;
		boolean isLeftChild = false;
		
		while(current.data != data)
		{
			parent = current;
			if(current.data> data)
			{
				isLeftChild = true;
				current = current.left;		
				
			}
			else {
				current = current.right;
				isLeftChild = false;
			}
			
			if(current == null)
			{
				return false;
			}
		}
		
		if(current.left == null && current.right == null)
		{
			if(current == root)
			{
				root = null;
			}
			else if(isLeftChild == true)
			{
				parent.left = null;
			}
			else{
				parent.right = null;
			}
			
		}
		else if(current.left == null)
		{
			if(current == root)
			{
				root = current.right;
			}
			else if(isLeftChild)
			{
				parent.left = current.right;
			}
			else{
				parent.right = current.right;
			}
		}
		else if(current.right == null)
		{
			if(current == root)
			{
				root = current.left;
			}
			else if(isLeftChild)
			{
				parent.left = current.left;
			}
			else{
				parent.right = current.left;
			}
		}
		else if(current.left!=null && current.right!= null)
		{
			Node successor = getSuccessor(current);
			
			if(current==root)
			{
				root = successor;
			}
			else if(isLeftChild)
			{
				parent.left = successor;
			}
			else{
				parent.right = successor;
			}
			successor.left = current.left;
		}
		
		return true;
	}
	
	public Node getSuccessor(Node deleteNode)
	{
		Node successor = null;
		Node successorParent = null;
		Node current = deleteNode.right;
		while(current!=null)
		{
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		
		if(successor.right !=null)
		{
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		
		return successor;
	}
	
	public void preOrderTraversal(Node rt)
	{
		if (rt == null)
		{
			System.out.println("No Nodes");
			
		}
		
		Stack<Node> st = new Stack<Node>();
		st.push(root);
		while(st.isEmpty() == false)
		{
			Node n = st.peek();
			System.out.println(n.data);
			st.pop();
			if(n.right != null)
				st.push(n.right);
			if(n.left != null)
				st.push(n.left);
		}
		
	}
	
	public void inOrderTraversal(Node rt)
	{
		if(rt == null)
		{
			System.out.println("No Nodes");
		}
		
		Stack<Node> st = new Stack<Node>();
		Node current = root;
		st.push(root);
		while(st.isEmpty() == false || current != null)
		{
			if(current!= null)
			{
				st.push(current);
				current = current.left;
			}
			else{
				Node n = st.peek();
				System.out.println(n.data);
				st.pop();
				current = current.right;
			}
		}
	}
	
	public void postOrderTraversal(Node rt)
	{
		if(rt == null)
		{
			System.out.println("No Nodes");
		}
		
		Stack<Node> st = new Stack<Node>();
		Node current = root;
		st.push(root);
		while(st.isEmpty() == false || current !=null)
		{
			if(current.left !=null)
			{
				st.push(current.left);
				current = current.left;
			}
			else{
				Node n = st.peek();
				if(n.right !=null)
				{
					current = n.right;
				}
				else{
					System.out.println(n.data);
					st.pop();
				}
			}
		}
	}

}

class Node{
	int data;
	Node left, right, parent;
	Node(int data){
		this.data = data;
		left = right = parent = null;
	}
}