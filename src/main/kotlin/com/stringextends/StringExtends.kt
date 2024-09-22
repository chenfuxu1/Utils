package com.stringextends

import com.logutils.Logit

/**
 * Project: Utils
 * Create By: Chen.F.X
 * DateTime: 2024-09-22 11:04
 *
 * Desc: String 的扩展函数
 */
operator fun String.times(count: Int): String {
    /**
     * 重载 string 的 * 号，返回多个相同的内容
     * separator: 表示字符连接方式
     * transform: ((T) -> CharSequence) T 表示迭代器当前值
     * public fun <T> Iterable<T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String {
     *     return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
     * }
     */
    return (1 .. count).joinToString("") {
        this
    }
}
fun main() {
    Logit.d("aa", "fsd")
}

