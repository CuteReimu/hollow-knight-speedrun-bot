package net.cutereimu.hkbot

import net.mamoe.mirai.console.data.*

object HKConfig : AutoSavePluginConfig("HKConfig") {
    @ValueName("enable")
    @ValueDescription("是否启用插件")
    val enable: Boolean by value(true)

    @ValueName("speedrun_push_delay")
    @ValueDescription("speedrun推送间隔")
    val speedrunPushDelay: Long by value(300L)

    @ValueName("speedrun_push_qq_group")
    @ValueDescription("speedrun推送QQ群")
    val speedrunPushQQGroup: LongArray by value(longArrayOf(12345678))

    @ValueName("speedrun_api_key")
    @ValueDescription("speedrun的API Key")
    val speedrunApiKey: String by value("abcdefjhijk")
}