
public class MyMain {

	public static void main(String[] args) {
		
		//LocNotManager s = new LocNotManager ();
		//List<Pair<Double, LocNot>> list = new LinkedList<Pair<Double, LocNot>>();
		Map<Double,Map<Double,LocNot>> big = new BST<Double,Map<Double,LocNot>>();
		Map<Double,LocNot> small = new BST<Double,LocNot>();
		Map<Double,LocNot> small2 = new BST<Double,LocNot>();
		Map<Double,LocNot> small3 = new BST<Double,LocNot>();
		
		LocNot txt = new LocNot("buy",100.0,10.1,0,0);
		LocNot txt1 = new LocNot("sell",100.0,9.3,0,0);
		LocNot txt2 = new LocNot("steal",100.0,9.0,0,0);
		LocNot txt3 = new LocNot("grab",100.0,19.0,0,0);
		
		//LocNot txt10 = new LocNot("a",105.6,10.22,0,0);
		//LocNot txt11 = new LocNot("b",105.6,10.6,0,0);
		LocNot txt12 = new LocNot("Ask for room reparations",105.6,10.8,0,0);
		//LocNot txt13 = new LocNot("d",105.6,10.5,0,0);
		
		LocNot txt01 = new LocNot("Q",101.5,13.5,0,0);
		LocNot txt02 = new LocNot("W",101.5,11.8,0,0);
		LocNot txt03 = new LocNot("E",101.5,22.0,0,0);
		LocNot txt04 = new LocNot("R",101.5,8.9871,0,0);
		LocNot txt05 = new LocNot("X",100.0,10.5,0,0);
		
		small.insert(10.1, txt);
		small.insert(9.3, txt1);
		small.insert(9.0, txt2);
		small.insert(19.0, txt3);
		
		//small2.insert(10.22, txt10);
		//small2.insert(10.6, txt11);
		small2.insert(10.8, txt12);
		//small2.insert(10.5, txt13);
		
		small3.insert(13.5, txt01);
		small3.insert(11.8, txt02);
		small3.insert(22.0, txt03);
		small3.insert(8.9871, txt04);
		
		//big.insert(100.0, small);
		big.insert(105.6, small2);
		//System.out.println(big.retrieve().retrieve().equals(null));
		//big.insert(101.5, small3);
		//System.out.println(GPS.angle(1000/2));
		//System.out.println(LocNotManager.addNot(big, txt05));
		//System.out.println(LocNotManager.delNot(big, 105.6, 10.5));
		//LocNotManager.load("C:\\Users\\user\\Desktop\\hello.txt");
		LocNotManager.delNots(big, "Ask for room reparations");
		System.out.println("here --> "+big.retrieve().empty());
		/*List<LocNot> l = new LinkedList<LocNot>();
		l = LocNotManager.getAllNots(big);
		l.findFirst();
		while(!l.last()) {
			System.out.println(l.retrieve().toString());
			l.retrieve().perform();
			l.findNext();
		}*/
		//System.out.println(l.retrieve().toString());
		
	/*	l.findFirst();
		while(!l.last()) {
			System.out.println(l.retrieve().toString());
			l.retrieve().perform();
			l.findNext();
		}
		System.out.println(l.retrieve().toString());
	*/
	
		//System.out.println(big.retrieve().retrieve().toString());
		
		//System.out.println(big.retrieve().retrieve().toString());

		//LocNotManager.save("C:\\Users\\user\\Desktop\\hello.txt", big);
		
		BST<Integer , Integer> tree = new BST<Integer , Integer>();
		tree.insert(10, 0);
		tree.insert(45, 0);
		tree.insert(25, 0);
		tree.insert(55, 0);
		tree.insert(12, 0);
		tree.insert(8, 0);
		tree.insert(9, 0);
		tree.insert(5, 0);
		tree.insert(7, 0);
		
		//tree.show();
		
		/*List<Pair<Integer,Integer>> l = new LinkedList<Pair<Integer,Integer>>();
		l = tree.getAll();
		l.findFirst();
		while(!l.last()) {
			System.out.println(l.retrieve().first);
			l.findNext();
		}
		System.out.println(l.retrieve().first);
		//System.out.println(tree.nbKeyComp(10,55));*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
}