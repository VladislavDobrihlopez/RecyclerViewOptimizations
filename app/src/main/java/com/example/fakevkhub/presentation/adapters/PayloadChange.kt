package com.example.fakevkhub.presentation.adapters

class PayloadChange<out T> private constructor(val first: T, val second: T) {
    companion object {
        fun <K> createTwoEndPayload(changes: List<Any>): PayloadChange<K> {
            if (changes.isEmpty()) {
                throw IllegalStateException("There are less than 1 element")
            }
            val first = changes.first() as K
            val last = changes.last() as K

            return PayloadChange(first = first, second = last)
        }
    }
}


