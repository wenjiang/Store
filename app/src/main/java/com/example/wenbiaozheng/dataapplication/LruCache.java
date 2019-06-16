package com.example.wenbiaozheng.dataapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LruCache {
    private Node head;
    private Node tail;
    private ArrayMap<String, Node> map = null;
    int cap = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LruCache(int capacity) {
        this.cap = capacity;
        this.map = new ArrayMap<>();
    }

    public Object get(String key) {
        if (map.get(key) == null) {
            return -1;
        }

        //move to tail
        Node t = map.get(key);

        removeNode(t);
        offerNode(t);

        return t.value;
    }

    public List<Object> getAll() {
        List<Object> dataList = new ArrayList<>();
        for (String key : map.keySet()) {
            dataList.add(map.get(key).value);
        }

        return dataList;
    }

    public void clear() {
        map.clear();
    }

    public void put(String key, Object value) {
        if (map.containsKey(key)) {
            Node t = map.get(key);
            t.value = value;

            //move to tail
            removeNode(t);
            offerNode(t);
        } else {
            if (map.size() >= cap) {
                //delete head
                map.remove(head.key);
                removeNode(head);
            }

            //add to tail
            Node node = new Node(key, value);
            offerNode(node);
            map.put(key, node);
        }
    }

    private void removeNode(Node n) {
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.prev = n.prev;
        } else {
            tail = n.prev;
        }
    }

    private void offerNode(Node n) {
        if (tail != null) {
            tail.next = n;
        }

        n.prev = tail;
        n.next = null;
        tail = n;

        if (head == null) {
            head = tail;
        }
    }

    class Node {
        String key;
        Object value;
        Node prev;
        Node next;

        public Node(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
