package io.knotx.release.common

import org.junit.jupiter.api.Assertions

fun assertEqualsTrimIdent(expected: String, actual: String) {
    Assertions.assertEquals(expected.trimIndent(), actual.trimIndent())
}