package cn.edu.sdust.database;

public class Hash {
    private int capacity;			//数组容量
    private Entry[] entries = {};	//键值对数组

    public Hash(int capacity) {
        this.capacity = capacity;
        entries = new Entry[this.capacity];
    }

    private int getHashKey(String key) {
    	return key.hashCode() % capacity;
    }

    //存放数据
    public void put(String key, String value) {
    	Entry curEntry = new Entry(key, value);
        int curhash = getHashKey(key);
        if(entries[curhash] == null) {//未有冲突键
            entries[curhash] = curEntry;
        }
        else {//找到冲突的哈希值，存放单链表中
            Entry temp = entries[curhash];
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = curEntry;
        }
    }

    //获取数据
    public String get(String key) {
    	int hash = getHashKey(key);
        if(entries[hash] != null) {
            Entry temp = entries[hash];
            while(temp.next != null) {
                temp = temp.next;
                if(temp.key.equals(key)) {
                	break;
                }
            }
            return temp.value;
        }
        return null;
    }

    private class Entry {
        String key;
        String value;
        Entry next;
        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
