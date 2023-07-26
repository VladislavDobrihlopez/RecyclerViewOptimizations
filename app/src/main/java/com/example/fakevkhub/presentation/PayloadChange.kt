package com.example.fakevkhub.presentation

data class PayloadChange<out T>(val first: T, val second: T)

fun <K> createTwoEndPayload(changes: List<Any>): PayloadChange<K> {
    if (changes.isEmpty()) {
        throw IllegalStateException("There are less than 1 elements")
    }
    val first = changes.first() as K
    val last = changes.last() as K

    return PayloadChange(first = first, second = last)
}
