package com.example.myhouse.util

inline fun <K, V> Map<K, List<V>>.mapNestedList(transform: (V) -> V): Map<K, List<V>> {
    return mapValues { (_, values) -> values.map(transform) }
}
