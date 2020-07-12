package lmu.cmsi281.assignments;

import java.util.ArrayList;

class HeapNodeInt {
	private int data;
	private int index;
	
	public HeapNodeInt(int element, int n) {
		data = element;
		index = n;
	}
	
	public int getData() {
		return data;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setData(int element) {
		data = element;
	}
	
	public void setIndex(int n) {
		index = n;
	}
}

public class MaxHeapInt {
	private ArrayList<HeapNodeInt> heap;
	
	public MaxHeapInt() {
		heap = new ArrayList<HeapNodeInt>();
	}
	
	public HeapNodeInt getRoot() {
		if (heap.isEmpty()){
			return null;
		}
		return heap.get(0);

	}
	
	public HeapNodeInt getLeft(HeapNodeInt node) {
		int leftIndex = 2 * node.getIndex() + 1;
		if (leftIndex < heap.size()){
			return heap.get(leftIndex);
		}
		return null;

	}
	
	public HeapNodeInt getRight(HeapNodeInt node) {
		int rightIndex = 2 * node.getIndex() + 2;
		if (rightIndex < heap.size()){
			return heap.get(rightIndex);
		}
		return null;
	}
	
	public HeapNodeInt getParent(HeapNodeInt node) {
		//(i-1)/2 with integer division
		int parentIndex = Math.floorDiv(node.getIndex() - 1, 2);
		if (parentIndex > -1){
			return heap.get(parentIndex);
		}
		return null;
		
	}
	
	public void addNode(int element) {
		//add to last spot, if child is greater then parent then swap
		
		HeapNodeInt addedNode = new HeapNodeInt(element, heap.size());
		heap.add(addedNode);
		HeapNodeInt parentNode = getParent(addedNode);
		
		while(parentNode != null && parentNode.getData() < element) {
				swap(parentNode, addedNode);
				parentNode = getParent(addedNode);
		}	
	}		
	
	public void deleteNode(int element) {
		// TODO: deletes the specified element from the heap while maintaining
		// the heap data structure constraints
		//move last node to deleted spot
		// check size and then swap if necessary
		
		//what if there are two nodes of the same element?
		//Is element the index of the Node you are deleting?
		//DO WE NEED TO update the size of the heap as we go (in the add/delete methods?)
		
		HeapNodeInt deletedNode = null;
		HeapNodeInt lastNode = heap.get(heap.size()-1);
		
		
		for (int i = 0; i < heap.size()-1; i++) {
			if(heap.get(i).getData() == element) {
				deletedNode = heap.get(i);
				break;		
			} 
		}
		
		swap(deletedNode, lastNode);
		heap.remove(deletedNode.getIndex());
		
		
		HeapNodeInt leftChild = getLeft(lastNode);
		HeapNodeInt rightChild = getRight(lastNode);
		HeapNodeInt maxChild = getLeft(lastNode);
		
//		while(leftChild != null) {
//			if(rightChild != null) {
//				HeapNodeInt maxChild = (leftChild.getData() >= rightChild.getData()) ? leftChild : rightChild;
//			} else {
//				HeapNodeInt maxChild = leftChild;
//			}
//			if (lastNode.getData() < maxChild.getData()) {
//				swap(lastNode, maxChild);
//			}
//			else {
//				break;
//			}
//		}
		
		
		while (maxChild != null) {
			leftChild = getLeft(lastNode);
			rightChild = getRight(lastNode);
			
			//if two child
			if(leftChild != null && rightChild != null) {
				maxChild = (leftChild.getData() >= rightChild.getData()) ? leftChild : rightChild;
			}
			
			//if one child
			else if(leftChild != null) {
				maxChild = leftChild;	
			} 
			
			//if no child
			else if(leftChild == null) {
				maxChild = null;
			}
			
			if(maxChild != null && maxChild.getData() > lastNode.getData()) {
				swap(maxChild, lastNode);
			}
			
		}
		
	}
	
	void swap(HeapNodeInt nodeA, HeapNodeInt nodeB) {
		int tempIndex = nodeA.getIndex();
		nodeA.setIndex(nodeB.getIndex());
		nodeB.setIndex(tempIndex);
		heap.set(nodeA.getIndex(), nodeA);
		heap.set(nodeB.getIndex(), nodeB);

	}
	
	public ArrayList<Integer> toArray() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int index = 0; index < heap.size(); index++) {
			arr.add(heap.get(index).getData());
		}
		return arr;
	}
	
	public static void main(String[] args) {
		MaxHeapInt maxHeap = new MaxHeapInt();
		maxHeap.addNode(35);
		maxHeap.addNode(33);
		maxHeap.addNode(42);
		maxHeap.addNode(10);
		maxHeap.addNode(14);
		maxHeap.addNode(19);
		maxHeap.addNode(27);
		maxHeap.addNode(44);
		maxHeap.addNode(26);
		maxHeap.addNode(31);
		System.out.println(maxHeap.toArray().toString());
		maxHeap.deleteNode(44);
		System.out.println(maxHeap.toArray().toString());
		maxHeap.deleteNode(31);
		System.out.println(maxHeap.toArray().toString());
	}
}
