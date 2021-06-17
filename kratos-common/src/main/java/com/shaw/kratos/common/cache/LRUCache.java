package com.shaw.kratos.common.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxiao
 * @date 2021/6/16 3:18 下午
 */
public class LRUCache<K, V> extends AbstractCache<K, V> {

    private int cap;
    private int size = 0;
    private DLinkNode dummyHead;
    private DLinkNode dummyTail;

    private Map<K, DLinkNode<K, V>> map = new HashMap<>();

    public LRUCache(int cap) {
        this.cap = cap;
        dummyHead = new DLinkNode<>(-1, -1);
        dummyTail = new DLinkNode<>(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    @Override
    public void put(K key, V value) {
        if (size >= cap) {
            DLinkNode needRemoveNode = dummyHead.next;
            removeNode(needRemoveNode);
            map.remove(needRemoveNode.k);
            size--;
        }
        DLinkNode node = map.get(key);
        DLinkNode<K, V> addNode = new DLinkNode<>(key, value);
        if (null == node) {
            map.put(key, addNode);
            addHead(addNode);
            size++;
        } else {
            removeNode(node);
            addHead(addNode);
            map.put(key, addNode);
        }
    }

    @Override
    public V get(K key) {
        DLinkNode<K, V> node = map.get(key);
        if (null == node) {
            return null;
        }
        removeNode(node);
        addHead(node);
        return node.v;
    }

    private void addHead(DLinkNode node) {
        DLinkNode tmp = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
        node.next = tmp;
        tmp.prev = node;
    }

    private void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class DLinkNode<K, V> {
        K k;
        V v;
        DLinkNode prev;
        DLinkNode next;
        DLinkNode(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
}
