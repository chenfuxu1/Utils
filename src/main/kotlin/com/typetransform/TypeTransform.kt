package com.typetransform

/**
 * Project: Utils
 * Create By: Chen.F.X
 * DateTime: 2024-09-22 12:07
 *
 * Desc: 类型转换
 */
fun <T> Any.safeAs(): T? {
    return this as? T
}