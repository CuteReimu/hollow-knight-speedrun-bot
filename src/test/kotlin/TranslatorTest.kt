package net.cutereimu.hkbot

import org.junit.Assert
import org.junit.Test

class TranslatorTest {
    @Test
    fun testWR() {
        val s =
            "AAAA beat the WR in Hollow Knight - Pantheon of Hallownest: Level - All Bindings (1.4.3.2+). The new WR is 35m 21s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("AAAA 打破了世界纪录：圣巢万神殿 - 四锁 (1.4.3.2+).新的世界纪录是35m 21s.", s2)
    }

    @Test
    fun testWR2() {
        val s =
            "CCCC beat the WR in Hollow Knight Category Extensions - King's Pass: Level - Slower. The new WR is 0m 55s 900ms."
        val s2 = Translator.translate(s)
        Assert.assertEquals("CCCC 打破了世界纪录：国王山道 - Slower.新的世界纪录是0m 55s 900ms.", s2)
    }

    @Test
    fun testWR3() {
        val s =
            "AAAA beat the WR in Hollow Knight Category Extensions - Save Myla - 1.4.3.2+ NMG. The new WR is 34m 57s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("AAAA 打破了世界纪录：拯救米拉 - 1.4.3.2+无主要邪道.新的世界纪录是34m 57s.", s2)
    }

    @Test
    fun testWR4() {
        val s = "VVVV beat the WR in Hollow Knight - 112% APB - No Major Glitches. The new WR is 3h 09m 43s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("VVVV 打破了世界纪录：112% 全万神殿BOSS - 无主要邪道.新的世界纪录是3h 09m 43s.", s2)
    }

    @Test
    fun testWR5() {
        val s =
            "Gusten13 beat the WR in Hollow Knight - All Achievements - No Major Glitches. The new WR is 6h 38m 52s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("Gusten13打破了世界纪录：全成就 - 无主要邪道.新的世界纪录是6h 38m 52s.", s2)
    }

    @Test
    fun testWR6() {
        val s = "Deites beat the WR in Hollow Knight - Console Runs - Any%, PS5/XSeries. The new WR is 59m 45s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("Deites 打破了世界纪录：主机速通 - Any%, PS5/XSeries.新的世界纪录是59m 45s.", s2)
    }

    @Test
    fun testWR7() {
        val s =
            "YouYu beat the WR in Hollow Knight - Pantheon of the Artist: Level - Any Bindings. The new WR is 3m 04s 360ms."
        val s2 = Translator.translate(s)
        Assert.assertEquals("YouYu 打破了世界纪录：艺术家万神殿 - 任意锁.新的世界纪录是3m 04s 360ms.", s2)
    }

    @Test
    fun testWR8() {
        val s = "Skitter_ beat the WR in Hollow Knight - Console Runs - 112% APB, Switch. The new WR is 4h 23m 21s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("Skitter_打破了世界纪录：主机速通 - 112% 全万神殿BOSS, Switch.新的世界纪录是4h 23m 21s.", s2)
    }

    @Test
    fun testWR9() {
        val s = "fatmoose beat the WR in Hollow Knight - Trial of the Warrior: Level. The new WR is 1m 55s 720ms."
        val s2 = Translator.translate(s)
        Assert.assertEquals("fatmoose 打破了世界纪录：勇士的试炼.新的世界纪录是1m 55s 720ms.", s2)
    }

    @Test
    fun testTop3() {
        val s =
            "BBBB got a new top 3 PB in Hollow Knight Category Extensions - 0 Geo - All Glitches. Their time is 15m 31s."
        val s2 = Translator.translate(s)
        Assert.assertEquals("BBBB 获得了前三：0吉欧 - 允许所有邪道.时间是15m 31s.", s2)
    }

}