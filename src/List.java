public class List<T>{
	public List(){
		node = null;
		size = 0;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public void add(T val){
		if(node == null){
			node = new Node<T>(val);
		}
		Node<T> now = node;
		while(now.getNext() != null){
			now = now.getNext();
		}
		now.setNext(new Node<T>(val));
		size++;
	}
	public void addFirst(T val){
		Node<T> now = node;
		node = new Node<T>(val);
		node.setNext(now);
		size++;
	}
	public void removeIdx(int idx){
		if(idx == 0){
			delFirst();
			return;
		}
		Node<T> now = node;
		while(idx>1){
			now = now.getNext();
		}
		now.setNext(now.getNext().getNext());
		size--;
	}
	public void remove(T item){
		if(item.equals(node.getValue())){
			delFirst();
			return;
		}
		Node<T> now = node;
		while(now.getNext()!=null && !item.equals(now.getNext().getValue())){
			now = now.getNext();
		}
		if(now.getNext() == null)
			return;
		now.setNext(now.getNext().getNext());
	}
	public T delFirst(){
		Node<T> now = node;
		node = node.getNext();
		size--;
		return now.getValue();
	}
	public T delLast(){
		Node<T> now = node;
		if(node.getNext() == null){
			node = null;
			size--;
			return now.getValue();
		}
		while(now.getNext().getNext()!=null){
			now = now.getNext();
		}
		Node<T> temp = now.getNext();
		now.setNext(null);
		size--;
		return temp.getValue();
	}
	public int getSize(){
		return size;
	}
	public int cariIndeksTerdekat(Posisi x){
		//error
		double min = 999999999;
		int indeksmin = -1;
		Node<T> now = node;
		for(int idx=0;now != null;idx++){
			if(min > x.hitungjarak(now.getValue())){
				indeksmin = idx;
				min = x.hitungjarak(now.getValue());
			}
			now = now.getNext();
		}
		return indeksmin;
	}
	public int find(T val){
		Node<T> now = node;
		int idx = 0;
		while(now!=null && !val.equals(now.getValue())){
			now = now.getNext();
			idx++;
		}
		if(node == null)
			return -1;
		return idx;
	}
	public T getIdx(int idx){
		Node<T> now = node;
		while(idx>0){
			now = now.getNext();
			idx--;
		}
		return now.getValue();
	}
	private int size;
	private Node<T> node;
}