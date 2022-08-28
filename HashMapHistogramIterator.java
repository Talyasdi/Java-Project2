package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
	
	private int sizeOfList;
	private int currIndex;
	private List<Map.Entry<T,Integer>> lst;

	public HashMapHistogramIterator(HashMap<T, Integer> histolst){
		Set<Map.Entry<T, Integer>> st = histolst.entrySet();
		this.lst = new ArrayList<>(st);
		Collections.sort(lst, new CompatreMyList());
		sizeOfList = lst.size();
		currIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return currIndex < sizeOfList;
	}

	@Override
	public T next() {
		currIndex += 1;
		return lst.get(currIndex-1).getKey();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}


	public class CompatreMyList implements Comparator<Map.Entry<T, Integer>>{

		public int compare (Map.Entry<T,Integer> e1, Map.Entry<T, Integer> e2){
			int res = Integer.compare(e2.getValue(), e1.getValue());
			if (res == 0){
				return e1.getKey().compareTo(e2.getKey());
			}
			else {
				return res;
			}
		}

	}
}
