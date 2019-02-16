package net.hdhuu.datasource.mapper

import java.util.*

abstract class BaseMapper<T1, T2> {

    abstract fun mapToEntity(o2: T2): T1

    abstract fun mapFromEntity(o1: T1): T2

    fun mapToEntity(o2List: List<T2>?): List<T1> {
        val o1List = ArrayList<T1>()
        if (o2List != null) {
            var o1: T1
            for (o2 in o2List) {
                o1 = mapToEntity(o2)
                o1List.add(o1)
            }
        }
        return o1List
    }

    fun mapFromEntity(o1List: List<T1>?): List<T2> {
        val o2List = ArrayList<T2>()
        if (o1List != null) {
            var o2: T2
            for (o1 in o1List) {
                o2 = mapFromEntity(o1)
                o2List.add(o2)
            }
        }
        return o2List
    }
}
