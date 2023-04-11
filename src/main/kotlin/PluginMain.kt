package net.cutereimu.hkbot

import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.Duration
import java.util.*

internal object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "net.cutereimu.hkbot",
        name = "Hollow Knight Speedrun Bot",
        version = "1.0.1"
    )
) {
    override fun onEnable() {
        HKConfig.reload()
        HKData.reload()
        if (!HKConfig.enable || HKConfig.speedrunPushQQGroup.isEmpty())
            return
        val re = Regex("<.*?>")
        Timer().schedule(object : TimerTask() {
            override fun run() {
                launch {
                    try {
                        val result1 = query().mapNotNull {
                            if (HKData.pushedMessages.findLast { e -> e == it.id } != null) return@mapNotNull null
                            HKData.pushedMessages += it.id
                            val s = re.replace(it.text, "")
                            if ("beat the WR" in s || "got a new top 3 PB" in s) Translator.translate(s) else null
                        }
                        if (HKData.pushedMessages.size > 100)
                            HKData.pushedMessages = HKData.pushedMessages.takeLast(100)
                        Bot.instances.firstOrNull()?.run {
                            HKConfig.speedrunPushQQGroup.forEach { groupId ->
                                try {
                                    val group = bot.getGroup(groupId) ?: return@forEach
                                    val result2 = HKData.unsend[groupId]?.plus(result1) ?: result1
                                    if (result2.isEmpty()) {
                                        HKData.unsend = HKData.unsend.minus(groupId)
                                        return@forEach
                                    }
                                    HKData.unsend = HKData.unsend.plus(groupId to result2)
                                    group.sendMessage(result2.joinToString("\r\n"))
                                    HKData.unsend = HKData.unsend.minus(groupId)
                                } catch (e: Exception) {
                                    logger.error("send group message failed: ", e)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        logger.error(e)
                    }
                }
            }
        }, 10000, HKConfig.speedrunPushDelay * 1000)
    }


    private const val ua =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36 Edg/97.0.1072.69"
    private val client = OkHttpClient().newBuilder().connectTimeout(Duration.ofMillis(20000)).build()
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        isLenient = true
        allowStructuredMapKeys = true
    }

    private fun query(): Array<SpeedrunData> {
        val request = Request.Builder().url("https://www.speedrun.com/api/v1/notifications")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("user-agent", ua)
            .header("Accept", "application/json")
            .header("X-API-Key", HKConfig.speedrunApiKey)
            .get().build()
        val resp = client.newCall(request).execute()
        if (resp.code != 200)
            throw Exception("请求错误，错误码：${resp.code}，返回内容：${resp.message}")
        val body: String = resp.body!!.string()
        return json.decodeFromJsonElement(
            json.serializersModule.serializer(),
            json.parseToJsonElement(body).jsonObject["data"]!!
        )
    }

    @Serializable
    private data class SpeedrunData(
        val id: String,
        val text: String
    )
}
