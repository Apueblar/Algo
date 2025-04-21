package BB;

/**
 * Represents a generic binary heap (min-heap) implementation.
 * @param <T> The type of elements stored in the heap, must be comparable.
 */
public class Heap<T extends Comparable<T>> {
	
	private int size; // The current number of elements in the heap.
	/**
	 * Array to store heap elements. Heap properties are enforced within this array:
	 * - Left child index: (parentIndex * 2) + 1
	 * - Right child index: (parentIndex * 2) + 2
	 * - Parent index: (childIndex - 1) / 2 (integer division)
	 */
	private T[] heap;

	/**
	 * Constructor to initialize the binary heap with a fixed capacity.
	 * @param capacity The maximum number of elements the heap can store.
	 */
	@SuppressWarnings("unchecked")
	public Heap(int capacity) {
		this.heap = (T[]) new Comparable[capacity];
	}

	/**
	 * Constructor to initialize the heap with a fixed capacity and populate it with given elements.
	 * Each element is added following the heap property.
	 * @param capacity The maximum number of elements the heap can store.
	 * @param elements The initial elements to add to the heap.
	 */
	public Heap(int capacity, T[] elements) {
		this(capacity);
		for (T element : elements) { // Add each element to the heap
			insert(element);
		}
	}

	/**
	 * Adds an element to the heap, maintaining the heap property.
	 * @param element The element to add to the heap.
	 * @throws NullPointerException if the element is null.
	 * @throws IllegalStateException if the heap is full.
	 */
	public void insert(T element) {
		if (element == null) {throw new NullPointerException("The element you try to add is null");}
		if (size + 1 > heap.length) {throw new IllegalStateException("The array is full, you cannot add more elements");}
		
		heap[size] = element;
		size++;
		filterUp(size - 1);
	}

	/**
	 * Adjusts the element at the given position upwards to maintain the heap property.
	 * @param position The index of the element to be filtered up.
	 */
	private void filterUp(int position) {
		T children = heap[position];
		int fatherSize = (position - 1) / 2;
		T father = heap[fatherSize];
		if (father.compareTo(children) > 0) { // Father greater than children so change
			heap[position] = father;
			heap[fatherSize] = children;
			filterUp(fatherSize);
		}
	}

	/**
	 * Retrieves and removes the smallest element (root) of the heap.
	 * @return The smallest element in the heap.
	 * @throws IllegalStateException if the heap is empty.
	 */
	public T getMin() {
		if (empty()) {throw new IllegalStateException("The heap has 0 elements so it cannot give the lowest");}
		T min = heap[0];
		size--;
		heap[0] = heap[size];
		filterDown(0);
		return min;
	}

	/**
	 * Adjusts the element at the given position downwards to maintain the heap property.
	 * @param position The index of the element to be filtered down.
	 */
	private void filterDown(int position) {
		T father = heap[position];
		int leftChildrenPos = position * 2 + 1;
		if (leftChildrenPos >= size) {return ;} //Stop
		
		if (leftChildrenPos == size - 1) { // Single child
			if (father.compareTo(heap[size - 1]) > 0) {
				heap[position] = heap[size - 1];
				heap[size - 1] = father;
			}
			return ; // Last iteration for sure
		}
		// Double Child
		int minChildPos = heap[leftChildrenPos].compareTo(heap[leftChildrenPos + 1]) < 0 ? leftChildrenPos : leftChildrenPos + 1;
		
		if (father.compareTo(heap[minChildPos]) > 0) {
			heap[position] = heap[minChildPos];
			heap[minChildPos] = father;
			filterDown(minChildPos);
		}
	}

	/**
	 * Checks if the heap is empty.
	 * @return true if the heap has no elements, false otherwise.
	 */
	public boolean empty() {
		return size == 0;
	}

	/**
	 * Provides a string representation of the heap in array order.
	 * @return A string representation of the heap.
	 */
	@Override
	public String toString() {
		if (empty()) {
			return "[]";
		}
		String represent = "[" + heap[0];
		for (int i = 1; i < size; i++) {
			represent = String.format("%s, %s", represent, heap[i]);
		}
		return represent + "]";
	}

	/**
	 * Retrieves the ascendants of a given element in the heap as a string.
	 * @param element The element whose ascendants are to be retrieved.
	 * @return A string representation of the ascendants.
	 * @throws NullPointerException if the element is null.
	 * @throws IllegalStateException if the element is not found in the heap.
	 */
	public String getAscendants(T element) {
		if (element == null) {throw new NullPointerException("The element you try to get its ascendants is null");}
		int index = -1;
		for (int i = 0; i < size ; i++) {
			if (heap[i].equals(element)) { // We found it
				index = i;
				break;
			}
		}
		if (index == -1) {throw new IllegalStateException("The heap does not have the given element");}
		return getAscendans(index);
	}
	
	/**
	 * Recursively retrieves the ascendants of the element at a given index.
	 * @param index The index of the element in the heap.
	 * @return A string representation of the ascendants.
	 */
	private String getAscendans(int index) {
		if (index == 0) {return heap[0].toString();}
		return heap[index] + " " + getAscendans((index - 1) / 2);
	}
	
	/**
	 * Peak element
	 * @return Best Node
	 */
	public T estimateBest() {
	        if (empty())
	            throw new IllegalStateException("Heap empty");
	        return heap[0];
	 }
	 
	/** extract the best (minimum) element */
    public T extractBestNode() {
        return getMin();
    }
}
