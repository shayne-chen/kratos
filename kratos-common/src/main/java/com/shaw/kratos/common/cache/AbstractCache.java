package com.shaw.kratos.common.cache;

/**
 * @author shaw
 * @date 2021/6/16
 */
public abstract class AbstractCache<K, V> {

    abstract void put(K key, V value);

    abstract V get(K key);
}
