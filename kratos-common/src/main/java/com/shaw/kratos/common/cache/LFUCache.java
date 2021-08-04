package com.shaw.kratos.common.cache;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author shaw
 * @date 2021/6/21
 */
public class LFUCache<K, V> extends AbstractCache<K, V> {

    /** 缓存容量 */
    private int cap;

    /** 当前缓存大小 */
    private int size = 0;

    /** key<=>数据的映射关系 */
    private Map<K, DLinkNode<K, V>> map = new HashMap<>();

    /** 记录最小使用次数 */
    private int minCount = Integer.MAX_VALUE;

    /** 使用次数<=>数据的映射关系 */
    private Map<Integer, LinkedHashSet<DLinkNode<K, V>>> countMap = new HashMap<>();

    public LFUCache(int cap) {
        this.cap = cap;
    }

    @Override
    void put(K key, V value) {
        if (size >= cap) {
            /** 缓存已满，删除使用次数最小的元素 */
            LinkedHashSet<DLinkNode<K, V>> linkedHashSet = countMap.get(minCount);
            DLinkNode<K, V> needRemoveNode = linkedHashSet.iterator().next();
            map.remove(needRemoveNode.k);
            linkedHashSet.remove(needRemoveNode);
            size--;
            if (CollectionUtils.isEmpty(linkedHashSet)) {
                /** 如果使用次数最小的集合为空，直接删除该集合，并且最小次数+1 */
                countMap.remove(minCount);
                minCount++;
            } else {
                countMap.put(minCount, linkedHashSet);
            }
        }
        DLinkNode<K, V> node = map.get(key);
        if (null == node) {
            // 新增节点
            DLinkNode<K, V> newNode = new DLinkNode<>(key, value);
            map.put(key, newNode);
            addNodeToHashSet(newNode);
            minCount = Math.min(minCount, newNode.count);
            size++;
        } else {
            // 更新已有节点，次数-集合关系表中，删除已有节点，该节点次数+1，放入新的集合中
            LinkedHashSet<DLinkNode<K, V>> linkedHashSet = countMap.getOrDefault(node.count, new LinkedHashSet<>());
            linkedHashSet.remove(node);
            if (CollectionUtils.isEmpty(linkedHashSet)) {
                countMap.remove(node.count);
                minCount = node.count == minCount ? node.count + 1: minCount;
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
            minCount = node.count == minCount ? node.count + 1 : minCount;
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

    private static class DLinkNode<K, V> {
        K k;
        V v;
        int count = 1; // 使用次数
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
