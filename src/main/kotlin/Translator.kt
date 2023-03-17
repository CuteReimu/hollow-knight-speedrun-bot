package net.cutereimu.hkbot

import java.io.BufferedReader
import java.io.InputStreamReader

object Translator {
    private val translateDict = Trie<String>()

    private val regexpSpace = Regex("""(?<![()\[\]{}%'"A-Za-z-]) (?![()\[\]{}%'"A-Za-z-])""")

    init {
        javaClass.getResourceAsStream("/translate.tsv")!!.use {
            BufferedReader(InputStreamReader(it)).use { reader ->
                var line: String
                while (true) {
                    line = reader.readLine() ?: break
                    if (line.isNotEmpty()) {
                        val arr = line.split("\t")
                        val key = arr[0]
                        val value = if (arr.size > 1) arr[1] else ""
                        if (!translateDict.putIfAbsent(key, value))
                            throw Exception("出现重复数据：${line}")
                    }
                }
                mapOf(
                    "beat the WR" to "打破了世界纪录：",
                    "in Hollow Knight Category Extensions -" to "",
                    "King's Pass: Level" to "国王山道",
                    "in Hollow Knight -" to "",
                    "The new WR is" to "新的世界纪录是",
                    "Their time is" to "时间是",
                    "Its time is" to "时间是",
                    "The time is" to "时间是",
                    "His time is" to "时间是",
                    "Her time is" to "时间是",
                    "got a new top 3 PB" to "获得了前三：",
                    "Pantheon of the Master: Level" to "大师万神殿",
                    "Pantheon of the Artist: Level" to "艺术家万神殿",
                    "Pantheon of the Sage: Level" to "贤者万神殿",
                    "Pantheon of the Knight: Level" to "骑士万神殿",
                    "Pantheon of Hallownest: Level" to "圣巢万神殿",
                    "White Palace: Level" to "白色宫殿",
                    "Path of Pain: Level" to "苦痛之路",
                    "Trial of the Warrior: Level" to "勇士的试炼",
                    "Trial of the Conqueror: Level" to "征服者的试炼",
                    "Trial of the Fool: Level" to "愚人的试炼",
                    "NMG." to "无主要邪道.",
                    "- NMG" to "- 无主要邪道",
                    "- NMG." to "- 无主要邪道.",
                    "Console Runs" to "主机速通",
                    "Any Bindings" to "任意锁",
                ).forEach { (key, value) ->
                    if (!translateDict.putIfAbsent(key, value))
                        throw Exception("出现重复数据：${key}")
                }
            }
        }
    }

    fun translate(s: String) = regexpSpace.replace(translateDict.replaceAll(s), "")

    private class Trie<T : Any> {
        fun putIfAbsent(key: String, value: T): Boolean {
            var node = root
            for (c in key.lowercase())
                node = node.child.getOrPut(c) { TrieNode() }
            if (node.value != null) return false
            node.value = value
            return true
        }

        private fun getLongest(s: String): Pair<String, T>? {
            var node = root
            var node2: TrieNode<T>? = null
            var key = ""
            var key2 = ""
            for ((idx, c) in s.lowercase().withIndex()) {
                val n = node.child[c]
                if (n != null) {
                    key += c
                    node = n
                    if (node.value != null && (idx + 1 >= s.length || s[idx + 1] in symbols)) {
                        node2 = node
                        key2 = key
                    }
                    continue
                }
                break
            }
            if (node2 == null) return null
            return Pair(key2, node2.value!!)
        }

        fun replaceAll(str: String): String {
            var s = str
            var s2 = ""
            while (s.isNotEmpty()) {
                if (!(s2.isEmpty() || s2.last() in symbols)) {
                    s2 += s.first()
                    s = s.substring(1)
                    continue
                }
                val kv = getLongest(s)
                if (kv != null) {
                    s2 += kv.second
                    s = s.substring(kv.first.length)
                } else {
                    s2 += s[0]
                    s = s.substring(1)
                }
            }
            return s2
        }

        private val root = TrieNode<T>()

        private class TrieNode<T : Any> {
            var value: T? = null
            val child = HashMap<Char, TrieNode<T>>()
        }

        companion object {
            private val symbols = setOf(' ', '(', ')', '[', ']', '{', '}', '%', '\'', '"', '.', ',')
        }
    }
}