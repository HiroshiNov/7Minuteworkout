package jp.hiroshi.nov.m.a7minuteworkout

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {
    @Test
    fun defaultExerciseList_returnsFullList() {
        val exerciseList = Constants.defaultExerciseList()
        assertEquals(12, exerciseList.size)
        assertEquals("JumpingJacks", exerciseList[0].getName())
    }
}
