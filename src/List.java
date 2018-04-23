public class List<T>{
    private int size;
    private Node<T> node;

    public List(){
        node = null;
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void add(T val){
        size++;
        if(node == null){
            node = new Node<T>(val);
            return;
        }
        Node<T> now = node;
        while(now.getNext() != null){
            now = now.getNext();
        }
        now.setNext(new Node<T>(val));
    }

    public void addFirst(T val){
        Node<T> now = node;
        node = new Node<T>(val);
        node.setNext(now);
        size++;
    }
    public void removeIdx(int idx){
        Node<T> prev = null;
        Node<T> now = head;

        int i = 0;
        while (i < idx){
            prev = now;
            now = now.getNext();
            i++;
        }

        if (now != null){
            if (prev != null){
                prev.setNext(now.getNext());
            }else{
                head = now.getNext();
            }
            now.setNext(null);
            return true;
        }else return false;

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
}
