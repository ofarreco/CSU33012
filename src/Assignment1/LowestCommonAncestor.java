package Assignment1;

public class LowestCommonAncestor <Key extends Comparable<Key>, Value>{
	public Node root;
	
	public class Node{
		public Key key;
		public Value value;
		public Node leftSide, rightSide;
		public int N;
		
		public Node(Key key, Value value){
			this.value = value;
			this.key = key;
		}
	}
	//Is empty implementation
		public boolean isEmpty()
		{
			return size() == 0;
		}
		
		public int size() 
		{
			  return(size(root)); 
			}
			private int size(Node node) 
			{ 
			  if (node == null) return(0); 
			  else { 
			    return(size(node.leftSide) + 1 + size(node.rightSide)); 
			  } 
			} 
			//Put method, inserts value into tree
			public void put(Key key, Value value){
				root = put(root, key, value);
			}
			
			private Node put(Node x, Key key, Value value){
				if(x == null)
				{
					return new Node(key, value);
				}
				int comp = key.compareTo(x.key);
				if(comp < 0)
				{
					x.leftSide = put(x.leftSide, key, value);
				}
				else if(comp > 0)
				{
					x.rightSide = put(x.rightSide, key, value);
				}else
				{
					x.value = value;
				}
				x.N = 1 + size(x.leftSide) + size(x.rightSide);
				return x;
		 	}
			
			//Get method, search by key order

			public Value get(Key key){
				Node x = root;
				
				while(x != null){
					
					int comp = key.compareTo(x.key);
					if(comp < 0)
					{
						x = x.leftSide;
					}
					else if(comp > 0)
					{
						x = x.rightSide;
					}
					else{
						return x.value;
					}
				}
				return null;
			}
			//Function to find LCA
			public Node search(Node root, Key value1, Key value2)
			{
				if(root != null &&(get(value1)!=null &&get(value2)!=null))
				{
					
					if(root.key.compareTo(value1) == 0 || root.key.compareTo(value2) == 0)
					{
						return root;
					}

					Node leftBranch = search(root.leftSide, value1,value2);
					Node rightBranch = search(root.rightSide,value1,value2);
					//If they both have an answer this is the LCA 
					
					if(leftBranch != null && rightBranch != null)
					{
						return root;
					}
					//Find & return node with value or return null if doesn't exist
					if(leftBranch != null) 
					{
						return leftBranch;

					}
					else if(rightBranch != null)
					{
						return rightBranch;
					}
					else 
						return null;
				}
				return null;
			}
}
