package com.example.library

/** constants */
object Constants {
    /** library description */
    val DESCRIPTION: String = listOf(
        "name=${BuildConfig.LIBRARY_PACKAGE_NAME}",
        "type=${BuildConfig.BUILD_TYPE}",
        "debuggable=${BuildConfig.DEBUG}",
        "patched"
    ).joinToString(separator = ", ")
}
