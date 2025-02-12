/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow.lite.examples.detection.env

import android.annotation.SuppressLint
import android.util.Log
import java.util.*

/** Wrapper for the platform log function, allows convenient message prefixing and log disabling.  */
class Logger{
    companion object {
        private const val DEFAULT_TAG = "tensorflow"
        private const val DEFAULT_MIN_LOG_LEVEL = Log.DEBUG

        // Classes to be ignored when examining the stack trace
        private var IGNORED_CLASS_NAMES: MutableSet<String>// We're only interested in the simple name of the class, not the complete package.// Get the current callstack so we can pull the class of the caller off of it.

        /**
         * Return caller's simple name.
         *
         *
         * Android getStackTrace() returns an array that looks like this: stackTrace[0]:
         * dalvik.system.VMStack stackTrace[1]: java.lang.Thread stackTrace[2]:
         * com.google.android.apps.unveil.env.UnveilLogger stackTrace[3]:
         * com.google.android.apps.unveil.BaseApplication
         *
         *
         * This function returns the simple version of the first non-filtered name.
         *
         * @return caller's simple name
         */
        private val callerSimpleName: String
            private get() {
                // Get the current callstack so we can pull the class of the caller off of it.
                val stackTrace = Thread.currentThread().stackTrace
                for (elem in stackTrace) {
                    val className = elem.className
                    if (!IGNORED_CLASS_NAMES!!.contains(className)) {
                        // We're only interested in the simple name of the class, not the complete package.
                        val classParts = className.split("\\.".toRegex()).toTypedArray()
                        return classParts[classParts.size - 1]
                    }
                }
                return Logger::class.java.simpleName
            }

        init {
            IGNORED_CLASS_NAMES = HashSet(3)
            IGNORED_CLASS_NAMES.add("dalvik.system.VMStack")
            IGNORED_CLASS_NAMES.add("java.lang.Thread")
            IGNORED_CLASS_NAMES.add(Logger::class.java.canonicalName)
        }
    }

    private val tag: String
    private val messagePrefix: String
    private var minLogLevel = DEFAULT_MIN_LOG_LEVEL

    /**
     * Creates a Logger using the class name as the message prefix.
     *
     * @param clazz the simple name of this class is used as the message prefix.
     */
    constructor(): this(DEFAULT_TAG, null){}

    constructor(tag: String, messagePrefix: String?){
        this.tag = tag
        val prefix = messagePrefix ?: callerSimpleName
        this.messagePrefix = if (prefix.isNotEmpty()) "$prefix: " else prefix
    }

    constructor(clazz: Class<*>) : this(clazz.simpleName) {}

    /**
     * Creates a Logger using the specified message prefix.
     *
     * @param messagePrefix is prepended to the text of every message.
     */
    constructor(messagePrefix: String?) : this(DEFAULT_TAG, messagePrefix) {}

    /** Creates a Logger using the caller's class name as the message prefix.  */
    constructor(minLogLevel: Int) : this(DEFAULT_TAG, null) {
        this.minLogLevel = minLogLevel
    }

    fun setMinLogLevel(minLogLevel: Int) {
        this.minLogLevel = minLogLevel
    }

    fun isLoggable(logLevel: Int): Boolean {
        return logLevel >= minLogLevel || Log.isLoggable(tag, logLevel)
    }

    private fun toMessage(format: String, vararg args: Any): String {
        return messagePrefix + if (args.size > 0) String.format(format, *args) else format
    }

    @SuppressLint("LogTagMismatch")
    fun v(format: String, vararg args: Any) {
        if (isLoggable(Log.VERBOSE)) {
            Log.v(tag, toMessage(format, *args))
        }
    }

    @SuppressLint("LogTagMismatch")
    fun v(t: Throwable?, format: String, vararg args: Any) {
        if (isLoggable(Log.VERBOSE)) {
            Log.v(tag, toMessage(format, *args), t)
        }
    }

    @SuppressLint("LogTagMismatch")
    fun d(format: String, vararg args: Any) {
        if (isLoggable(Log.DEBUG)) {
            Log.d(tag, toMessage(format, *args))
        }
    }

    @SuppressLint("LogTagMismatch")
    fun d(t: Throwable?, format: String, vararg args: Any) {
        if (isLoggable(Log.DEBUG)) {
            Log.d(tag, toMessage(format, *args), t)
        }
    }

    @SuppressLint("LogTagMismatch")
    fun i(format: String, vararg args: Any) {
        if (isLoggable(Log.INFO)) {
            Log.i(tag, toMessage(format, *args))
        }
    }

    @SuppressLint("LogTagMismatch")
    fun i(t: Throwable?, format: String, vararg args: Any) {
        if (isLoggable(Log.INFO)) {
            Log.i(tag, toMessage(format, *args), t)
        }
    }

    @SuppressLint("LogTagMismatch")
    fun w(format: String, vararg args: Any) {
        if (isLoggable(Log.WARN)) {
            Log.w(tag, toMessage(format, *args))
        }
    }

    @SuppressLint("LogTagMismatch")
    fun w(t: Throwable?, format: String, vararg args: Any) {
        if (isLoggable(Log.WARN)) {
            Log.w(tag, toMessage(format, *args), t)
        }
    }

    @SuppressLint("LogTagMismatch")
    fun e(format: String, vararg args: Any) {
        if (isLoggable(Log.ERROR)) {
            Log.e(tag, toMessage(format, *args))
        }
    }

    @SuppressLint("LogTagMismatch")
    fun e(t: Throwable?, format: String, vararg args: Any) {
        if (isLoggable(Log.ERROR)) {
            Log.e(tag, toMessage(format, *args), t)
        }
    }
    /**
     * Creates a Logger with a custom tag and a custom message prefix. If the message prefix is set to
     *
     * <pre>null</pre>
     *
     * , the caller's class name is used as the prefix.
     *
     * @param tag identifies the source of a log message.
     * @param messagePrefix prepended to every message if non-null. If null, the name of the caller is
     * being used
     */
    /** Creates a Logger using the caller's class name as the message prefix.  */

}