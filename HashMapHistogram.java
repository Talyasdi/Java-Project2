package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{
	private HashMap<T, Integer> lstOfElements;

	public HashMapHistogram (){
		this.lstOfElements = new HashMap<T, Integer>();
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<>(lstOfElements);
	}

	@Override
	public void addItem(T item) {
		if (this.lstOfElements.get(item) != null){
			this.lstOfElements.put(item, this.lstOfElements.get(item) +1);
		}
		else {
			this.lstOfElements.put(item, 1);
		}
		
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if (this.lstOfElements.get(item) != null){
			this.lstOfElements.put(item, this.lstOfElements.get(item) -1);
			if (this.lstOfElements.get(item) == 0){
				this.lstOfElements.remove(item);
			}
		}
		else {
			throw new IllegalItemException();
		}
		
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if (k<1){
			throw new IllegalKValueException(k);
		}
		else if(this.lstOfElements.get(item) != null) {
			this.lstOfElements.put(item, this.lstOfElements.get(item) + k);
		}
		else {
			this.lstOfElements.put(item, k);
		}
		
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		if (this.lstOfElements.get(item) == null){
			throw new IllegalItemException();
		}
		else if(this.lstOfElements.get(item) < k || k<1){
			throw new IllegalKValueException(k);
		}
		else {
			this.lstOfElements.put(item, this.lstOfElements.get(item) - k);
			if (this.lstOfElements.get(item) == 0){
				this.lstOfElements.remove(item);
			}
		}
		
	}

	@Override
	public int getCountForItem(T item) {
		if (this.lstOfElements.get(item) == null){
			return 0;
		}
		else {
			return this.lstOfElements.get(item);
		}
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T element : items){
			this.addItem(element);
		}
		
	}

	@Override
	public void clear() {
		this.lstOfElements.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return this.lstOfElements.keySet();
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		for ( T element :anotherHistogram){
			if (this.lstOfElements.get(element) == null) {
				this.lstOfElements.put(element, anotherHistogram.getCountForItem(element));
			}
			else {
				this.lstOfElements.put(element, this.lstOfElements.get(element) +anotherHistogram.getCountForItem(element));
			}
		}
	}

	
}
