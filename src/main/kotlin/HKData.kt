package net.cutereimu.hkbot

import net.mamoe.mirai.console.data.*

object HKData : AutoSavePluginData("HKData") {
    @ValueName("pushedMessages")
    var pushedMessages: List<String> by value(listOf())

    @ValueName("unsend")
    var unsend: Map<Long, List<String>> by value(mapOf())
}