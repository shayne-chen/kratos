package com.shaw.kratos.common.cache;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author chenxiao
 * @date 2021/6/21 7:10 下午
 */
public class LFUCache<K, V> extends AbstractCache<K, V> {

    private int cap;
    private int size = 0;

    private Map<K, DLinkNode<K, V>> map = new HashMap<>();

    private int minCount = Integer.MAX_VALUE;
    private Map<Integer, LinkedHashSet<DLinkNode<K, V>>> countMap = new HashMap<>();

    public LFUCache(int cap) {
        this.cap = cap;
    }

    @Override
    void put(K key, V value) {
        if (size >= cap) {
            LinkedHashSet<DLinkNode<K, V>> linkedHashSet = countMap.get(minCount);
            DLinkNode<K, V> needRemoveNode = linkedHashSet.iterator().next();
            map.remove(needRemoveNode.k);
            linkedHashSet.remove(needRemoveNode);
            size--;
            if (CollectionUtils.isEmpty(linkedHashSet)) {
                countMap.remove(minCount);
                minCount++;
            } else {
                countMap.put(minCount, linkedHashSet);
            }
        }
        DLinkNode<K, V> node = map.get(key);
        if (null == node) {
            DLinkNode<K, V> newNode = new DLinkNode<>(key, value);
            map.put(key, newNode);
            addNodeToHashSet(newNode);
            minCount = Math.min(minCount, newNode.count);
            size++;
        } else {
            LinkedHashSet<DLinkNode<K, V>> linkedHashSet = countMap.getOrDefault(node.count, new LinkedHashSet<>());
            linkedHashSet.remove(node);
            if (CollectionUtils.isEmpty(linkedHashSet)) {
                countMap.remove(node.count);
                minCount = node.count + 1;
            } else {
                countMap.put(node.count, linkedHashSet);
            }
            node.count++;
            node.v = value;
            map.put(key, node);
            addNodeToHashSet(node);
        }

    }

    @Override
    V get(K key) {
        DLinkNode<K, V> node = map.get(key);
        if (null == node) {
            return null;
        }
        LinkedHashSet<DLinkNode<K, V>> linkedHashSet = countMap.get(node.count);
        linkedHashSet.remove(node);
        if (CollectionUtils.isEmpty(linkedHashSet)) {
            countMap.remove(node.count);
            minCount = node.count + 1;
        } else {
            countMap.put(node.count, linkedHashSet);
        }
        node.count++;
        addNodeToHashSet(node);
        return node.v;
    }

    private void addNodeToHashSet(DLinkNode<K, V> node) {
        LinkedHashSet<DLinkNode<K, V>> linkedHashSet = countMap.getOrDefault(node.count, new LinkedHashSet<>());
        linkedHashSet.add(node);
        countMap.put(node.count, linkedHashSet);
    }

    static class DLinkNode<K, V> {
        K k;
        V v;
        int count = 1;
        DLinkNode(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, v);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DLinkNode)) {
                return false;
            }
            return this.hashCode() == obj.hashCode();
        }
    }
}
