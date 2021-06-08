package com.android.dubizzle

import org.junit.Test

class DateFormatTest {
    @Test
    fun `test that formatDate function work as expected`() {
        val dateString = "2019-02-24 04:04:17.566515"
        assert(com.android.dubizzle.mvi.util.DateUtil.timeSince(dateString) == "24 Feb 2019")
    }

    @Test
    fun `test that formatDate should return not available when as string is empty`() {
        val dateString = ""
        assert(com.android.dubizzle.mvi.util.DateUtil.timeSince(dateString) == "n/a")
    }
}