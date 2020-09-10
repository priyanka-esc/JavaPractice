class LRUCache {
        HashMap<Integer, Integer> map ;
        Deque<Integer> list ;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap(capacity);
        list = new LinkedList();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Integer value = map.get(key);
        if( value != null)
        {
            list.remove(value);
            list.addFirst(value);
            return map.get(key);
        }
        else return -1;
    }
    
    public void put(int key, int value) {
        if(map.get(key) == null){
            map.put(key, value);
            if(list.size() == capacity){
                Integer removed = list.removeLast();
                map.remove(removed);
            }
            list.addFirst(value);
        }
        else
        {
            list.remove(value);
            list.addFirst(value);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
////////////////////////////////////////////////////////////////////////////////////////////////// design data structure for LRU
class Entry {
	int value;
	int key;
	Entry left;
	Entry right;
}
public class LRUCache {

	HashMap<Integer, Entry> hashmap;
	Entry start, end;
	int LRU_SIZE = 4; // Here i am setting 4 to test the LRU cache
						// implementation, it can make be dynamic
	public LRUCache() {
		hashmap = new HashMap<Integer, Entry>();
	}

	public int getEntry(int key) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the
		{
			Entry entry = hashmap.get(key);
			removeNode(entry);
			addAtTop(entry);
			return entry.value;
		}
		return -1;
	}

	public void putEntry(int key, int value) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the value and move it to top
		{
			Entry entry = hashmap.get(key);
			entry.value = value;
			removeNode(entry);
			addAtTop(entry);
		} else {
			Entry newnode = new Entry();
			newnode.left = null;
			newnode.right = null;
			newnode.value = value;
			newnode.key = key;
			if (hashmap.size() > LRU_SIZE) // We have reached maxium size so need to make room for new element.
			{
				hashmap.remove(end.key);
				removeNode(end);				
				addAtTop(newnode);

			} else {
				addAtTop(newnode);
			}

			hashmap.put(key, newnode);
		}
	}
	public void addAtTop(Entry node) {
		node.right = start;
		node.left = null;
		if (start != null)
			start.left = node;
		start = node;
		if (end == null)
			end = start;
	}

	public void removeNode(Entry node) {

		if (node.left != null) {
			node.left.right = node.right;
		} else {
			start = node.right;
		}

		if (node.right != null) {
			node.right.left = node.left;
		} else {
			end = node.left;
		}
	}
