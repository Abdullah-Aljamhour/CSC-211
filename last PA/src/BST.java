class BSTNode <K extends Comparable<K>, T>  {
	public K key;
	public T data;
	public BSTNode<K,T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(K k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}
}

public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	BSTNode<K,T> root, current;
	public BST() {
		root = current = null;
	}

	
	public void clear () {
		root = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	public boolean full() {
		return false;
	}
	
	public T retrieve () {
		if(current == null) {
			return null;
		}
		return current.data;
	}
	
	public void update(T e) {
		current.data=e;
	}
	
	public int nbKeyComp(K key) {
		BSTNode<K,T> r=root;
		int i = 1;
		if(empty()) {
			return 0;
		}
		while(r!=null) {
			if(r.key.compareTo(key)==0) {
				//current=r;
				return i;
			}
			if(r.key.compareTo(key) < 0) {
				r = r.right;
				i++;
			}
			if(r.key.compareTo(key) > 0) {
				r = r.left;
				i++;
			}
		}	
		return 0;
	}

	public boolean find(K tkey) {
		BSTNode<K,T> p = root,q = current;
				
		if(empty())
			return false;
		
		while(p != null) {
			if(p.key.compareTo(tkey) == 0) {
				current = p;
				return true;
			}
			else if(p.key.compareTo(tkey) > 0)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		//System.out.println(q.data);
		return false;
	}

	private boolean find_(K tkey) {
		BSTNode<K,T> p = root,q = root;
				
		if(empty())
			return false;
		
		while(p != null) {
			q=p;
			if(p.key.compareTo(tkey) == 0) {
				current = p;
				return true;
			}
			else if(p.key.compareTo(tkey) > 0)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		//System.out.println(q.data);
		return false;
	}

	public boolean insert(K key , T data ) {
			BSTNode<K,T> p, q = current;
			
			if(find_(key)) {
				current = q;  // findkey() modified current
				return false; // key already in the BST
			}
			
			p = new BSTNode<K,T>(key, data);
			if (empty()) {
				root = current = p;
				return true;
			}
			else {
				// current is pointing to parent of the new key
				if (current.key.compareTo(key)>0)
					current.left = p;
				else
					current.right = p;
				current = p;
				return true;
			}
		}

	public boolean remove (K key) {
		Boolean removed = new Boolean(false);
		BSTNode<K,T> p;
		p = remove_aux(key, root, removed);
		current = root = p;
		return removed;
	}
	
	private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, Boolean flag) {
		BSTNode<K,T> q, child = null;
		if(p == null)
			return null;
		if(key.compareTo(p.key) <0 )
			p.left = remove_aux(key, p.left, flag); //go left
		else if(key.compareTo(p.key) >0)
			p.right = remove_aux(key, p.right, flag); //go right
		else {
			flag=true;
			if (p.left != null && p.right != null){ //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}
			else {
				if (p.right == null) //one child
					child = p.left;
				else if (p.left == null) //one child
					child = p.right;
				return child;
			}
		}
		return p;
	}

	private BSTNode<K,T> find_min(BSTNode<K,T> p){
		if(p == null)
			return null;
		
		while(p.left != null){
			p = p.left;
		}
		
		return p;
	}
	
	public List<Pair<K, T>> getAll(){
		BSTNode<K,T> father = root;
		//BSTNode<K,T> pointer = father.left;
		List<Pair<K, T>> list = new LinkedList<Pair<K, T>>();
		makeTree(list,father);
		return list;
		
	
	}
	
	private void makeTree(List<Pair<K, T>> list ,BSTNode<K,T> node ) {
	//	System.out.println(node.data);
		if(node.left!=null) {
			//System.out.println("right ");
			makeTree(list,node.left);
		}
		list.insert(new Pair<K, T> (node.key , node.data));
		 if(node.right!=null){
			// System.out.println("left ");
			 makeTree(list,node.right);
			 }

	}

	private void makeTree(List<Pair<K, T>> list ,BSTNode<K,T> node  , K k1 , K k2 ) {
		
		//System.out.println("makeTree1 "+node.data);
		if(node.left!=null) {
			//System.out.println("right ");
			makeTree(list,node.left,  k1 ,  k2);
		}
		if((node.key==k1 || node.key==k2) || (node.key.compareTo(k1)>0 && node.key.compareTo(k2)<0 ))
			list.insert(new Pair<K, T> (node.key , node.data));
		 if(node.right!=null){
			// System.out.println("left ");
			 makeTree(list,node.right,  k1 ,  k2);
			 }

	}
	
	public List<Pair<K, T>> getRange(K k1, K k2){
		BSTNode<K,T> father = root;
		List<Pair<K, T>> list = new LinkedList<Pair<K, T>>();
		makeTree(list,father,k1,k2);
		return list;
	}
	
	public int nbKeyComp(K k1, K k2) {
		BSTNode<K,T> r=root;
		int i = 1;
		if(empty()) {
			
			return 0;
		}
		n(r,k1,k2,i);
		return i;
		//return ;
	}

	private int n (BSTNode<K,T> node , K k1 , K k2,int i) {
	//	System.out.println(node.key);
		if(node.key.compareTo(k1)==0) {
			i++;
			n(node.right,k2,k2,i);
			return i;
		}
		if(node.key.compareTo(k1)>0) {
			i++;
			n(node.left,k1,k2,i);
		}
		if(node.key.compareTo(k1)<0) {
			i++;
			n(node.right,k1,k2,i);
		}
		return i;
		
	}

	private int n2 (BSTNode<K,T> node , K k1 , K k2,int i) {
		if(node.key.compareTo(k1)==0) {
			//n2
			return i;
		}
		if(node.key.compareTo(k1)>0) {
			i++;
			n(node.left,k1,k2,i);
		}
		if(node.key.compareTo(k1)<0) {
			i++;
			n(node.right,k1,k2,i);
		}
		return i;
		
	}

	public void show() {
		if(!this.empty()) {
			BSTNode<K,T> r = root;
			show2(r);
		}
		}
	
	private void show2(BSTNode<K,T> node) {
		System.out.println("show2 value "+node.key);
		if(node.left!=null) {
			//System.out.println("right ");
			show2(node.left);
		}

		 if(node.right!=null){
			// System.out.println("left ");
			 show2(node.right);
			 }
	}

	
	
	
	
	
	
}





















