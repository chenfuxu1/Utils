package com.copyutils

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * Project: Utils
 * Create By: Chen.F.X
 * DateTime: 2024-09-22 16:00
 *
 * Desc:
 */
/**
 * 对 DataClass 进行深拷贝
 */
fun <T: Any> T.deepCopy(): T {
    // 如果不是数据类，不深拷贝，直接返回
    if (!this::class.isData) {
        return this
    }
    /**
     * this::class.primaryConstructor 获取主构造器
     * primaryConstructor.parameters 是构造器的参数集合
     * this::class.memberProperties 所有的成员参集合
     */
    return this::class.primaryConstructor!!.let { primaryConstructor ->
        primaryConstructor.parameters.map { parameter ->
            // value 就是属性对应的值
            val value = (this::class as KClass<T>).memberProperties.first { it.name == parameter.name}
                .get(this)
            /**
             * parameter.type.classifier 一般就是一个 KClass
             * 判断 parameter 是否是一个数据类，如果是，继续调用它的深拷贝
             */
            if ((parameter.type.classifier as? KClass<*>)?.isData == true) {
                parameter to value?.deepCopy()
            } else {
                // 基本类型直接返回
                parameter to value
            }
        }.toMap() // 将 list<Pair> 转成 map，pair 的 key 是名称，value 是值
            /**
             * callBy(args: Map<KParameter, Any?>): R
             * 返回的就是实例对象
             */
            .let(primaryConstructor::callBy)
    }
}