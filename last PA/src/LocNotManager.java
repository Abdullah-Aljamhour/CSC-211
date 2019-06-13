import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class LocNotManager {
	// Load notifications from file. Assume format is correct. The notifications are
	// indexed by latitude then by longitude.
	public static Map<Double, Map<Double, LocNot>> load(String fileName) {
		Map<Double, Map<Double, LocNot>> list;
		try {
			FileReader filereader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(filereader);
			String lines;
			while((lines = br.readLine())!=null) {
				System.out.println(lines.substring(0, 8));
			}
			
	}
		catch(Exception e) {
			return null;
		}
		return null;	
	}

	// Save notifications to file.
	public static void save(String fileName, Map<Double, Map<Double, LocNot>> nots) {
		List<Pair<Double, Map<Double, LocNot>>> ALL = new LinkedList<Pair<Double, Map<Double, LocNot>>>();
		List<Pair<Double, LocNot>> list = new LinkedList<Pair<Double, LocNot>>();
		ALL = nots.getAll();
		ALL.findFirst();
		try {
		File file = new File(fileName);
		BufferedWriter  out = new BufferedWriter (new FileWriter(file));
		while(!ALL.last()) {
			//System.out.println("error?");
			list = ALL.retrieve().second.getAll();
			list.findFirst();
		while(!list.last()) {
		out.write(list.retrieve().second.toString());
		out.newLine();
		list.findNext();
		}
		if(!(list.retrieve().equals(null))) {
			out.write(list.retrieve().second.toString());
			out.newLine();
		}
		ALL.findNext();
		}
		if(!(ALL.retrieve().equals(null))){
			System.out.println("error?");
			list = ALL.retrieve().second.getAll();
			list.findFirst();
		while(!list.last()) {
		out.write(list.retrieve().second.toString());
		out.newLine();
		list.findNext();
		}
		if(!(list.retrieve().equals(null))) {
			out.write(list.retrieve().second.toString());
			out.newLine();
		}
		}
		out.close();
		}
		
		catch(Exception e) {
			
		}
		
	}

	// Return all notifications sorted first by latitude then by longitude.
	public static List<LocNot> getAllNots(Map<Double, Map<Double, LocNot>> nots) {
		List<Pair<Double, Map<Double, LocNot>>> bigboy = new LinkedList<Pair<Double, Map<Double, LocNot>>>();
		List<Pair<Double, LocNot>> small = new LinkedList<Pair<Double, LocNot>>();
		List<LocNot> list = new LinkedList<LocNot>();
		//List<LocNot> list2 = new LinkedList<LocNot>();
		bigboy = nots.getAll();
		bigboy.findFirst();
		while(!bigboy.last()) {
			if(!bigboy.retrieve().second.empty()) {
			small = bigboy.retrieve().second.getAll();
			small.findFirst();
				while(!small.last()) {
					list.insert(small.retrieve().second);
					small.findNext();
						}
			if(!(small.retrieve().equals(null))) {
				list.insert(small.retrieve().second);
			}
			}
			bigboy.findNext();
		}
		if(!(bigboy.retrieve().equals(null))){
			small = bigboy.retrieve().second.getAll();
			small.findFirst();
		while(!small.last()) {
			list.insert(small.retrieve().second);
			small.findNext();
		}
		if(!(small.retrieve().equals(null))) {
			list.insert(small.retrieve().second);
		}
		}
		
		return list;
	}

	// Add a notification. Returns true if insert took place, false otherwise.
	public static boolean addNot(Map<Double, Map<Double, LocNot>> nots, LocNot not) {
		Map<Double, LocNot> data = new BST<Double, LocNot> ();
		data.insert(not.getLng(), not);
		if(!nots.find(not.getLat())) {
			//System.out.println("bzinga");
		return nots.insert(not.getLat(), data);
		}
		else {
			nots.find(not.getLat());
			return nots.retrieve().insert(not.getLng(), not);
		}
	}

	// Delete the notification at (lat, lng). Returns true if delete took place, false otherwise.
	public static boolean delNot(Map<Double, Map<Double, LocNot>> nots, double lat, double lng) {
		if(nots.find(lat)) {
			if(nots.retrieve().find(lng)) {
			nots.retrieve().remove(lng);
			return true;
			}
		}
		return false;
	}

	// Return the list of notifications within a square of side dst (in meters) centered at the position (lat, lng) 
	//(it does not matter if the notification is active or not). Do not call Map.getAll().
	public static List<LocNot> getNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		double k = GPS.angle(dst/2);
		 List<LocNot> re = new  LinkedList<LocNot>();
		 List<Pair<Double, LocNot>> temp = new LinkedList<Pair<Double, LocNot>>();
		 List<Pair<Double, Map<Double, LocNot>>> l = nots.getRange(lat-k, (lat+k));
		 l.findFirst();
		 if(l.empty()) {
			return re;
		 }
		 while(!l.last()) {
			// System.out.println(l.retrieve().first);
			 temp= l.retrieve().second.getRange(lng-k, lng+k);
			 temp.findFirst();
			 if(!temp.empty()) {
			 while(!temp.last()) {
				 re.insert(temp.retrieve().second);
				 temp.findNext();
			 }
			 if(!temp.retrieve().equals(null)) {
			 re.insert(temp.retrieve().second);
			 }
			 }
			 l.findNext();
		 }
		 if(!(l.retrieve().equals(null))) {
			 temp= l.retrieve().second.getRange(lng-k, (lng+k));
			 temp.findFirst();
			 while(!temp.last()) {
				 re.insert(temp.retrieve().second);
				 temp.findNext();
			 }
			 if(!temp.retrieve().equals(null)) {
			 re.insert(temp.retrieve().second);
			 }
		 }
		
		return re;
	}

	// Return the list of active notifications within a square of side dst (in meters) centered at the position (lat, lng). Do not call Map.getAll().
	public static List<LocNot> getActiveNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		List<LocNot> m1 = new LinkedList<LocNot>();
		List<LocNot> m2 = new LinkedList<LocNot>();
		m1 = getNotsAt(nots,lat,lng,dst);
		if(m1.empty()) {
			return m1;
		}
		m1.findFirst();
		while(!m1.last()) {
			if(m1.retrieve().isActive()) {
				m2.insert(m1.retrieve());
			}
			m1.findNext();
		}
		if(!(m1.retrieve().equals(null))) {
			if(m1.retrieve().isActive()) {
				m2.insert(m1.retrieve());
			}
		}
		return m2;
	}

	// Perform task of any active notification within a square of side dst (in meters) centered at the position (lat, lng) (call method perform). Do not call Map.getAll().
	public static void perform(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		List<LocNot> m1 = new LinkedList<LocNot>();
		m1=getActiveNotsAt(nots,lat,lng,dst);
		if(!m1.empty()) {
		m1.findFirst();
		while(!m1.last()) {
			m1.retrieve().perform();
			m1.findNext();
		}
		m1.retrieve().perform();
		}
	}

	// Return a map that maps every word to the list of notifications in which it appears. The list must have no duplicates.
	public static Map<String, List<LocNot>> index(Map<Double, Map<Double, LocNot>> nots) {
		return null;
	}

	// Delete all notifications containing the word w.
	public static void delNots(Map<Double, Map<Double, LocNot>> nots, String w) {
		List<LocNot> list = new LinkedList<LocNot>();
		list = getAllNots(nots);
		list.findFirst();
		while(!list.last()){
			if(list.retrieve().getText().contains(w)) {
				delNot(nots,list.retrieve().getLat(),list.retrieve().getLng());
			}
			list.findNext();
		}
		if(list.retrieve().getText().equals(w)) {
			delNot(nots,list.retrieve().getLat(),list.retrieve().getLng());
		}
	}

	// Print a list of notifications in the same format used in file.
	public static void print(List<LocNot> l) {
		System.out.println("-------------------------------------------------------------------------------------");
		if (!l.empty()) {
			l.findFirst();
			while (!l.last()) {
				System.out.println(l.retrieve());
				l.findNext();
			}
			System.out.println(l.retrieve());
		} else {
			System.out.println("Empty");
		}
		System.out.println("------------------");
	}

	// Print an index.
	public static void print(Map<String, List<LocNot>> ind) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		List<Pair<String, List<LocNot>>> l = ind.getAll();
		if (!l.empty()) {
			l.findFirst();
			while (!l.last()) {
				System.out.println(l.retrieve().first);
				print(l.retrieve().second);
				l.findNext();
			}
			System.out.println(l.retrieve().first);
			print(l.retrieve().second);
		} else {
			System.out.println("Empty");
		}
		System.out.println("++++++++++++++++++");
	}

}
