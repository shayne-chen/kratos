package com.shaw.kratos.common.cache;

/**
 * @author chenxiao
 * @date 2021/6/16 3:17 下午
 */
public abstract class AbstractCache<K, V> {

    abstract void put(K key, V value);

    abstract V get(K key);
}
