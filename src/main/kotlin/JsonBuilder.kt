package org.example

import org.jetbrains.kotlin.js.parser.sourcemaps.JsonObject

class JsonBuilder {
    val json = JsonObject()

    class JsonObject {
        operator fun set(name: String, value: JsonObject) {

        }

        fun put(json: JsonObject) {

        }

    }

    fun array(name: String, build: JsonBuilder.() -> Unit) {
        val arrayBuilder = JsonBuilder()
        arrayBuilder.build()
        json[name] = arrayBuilder.json
    }

    fun obj(build: JsonBuilder.() -> Unit) {
        val objBuilder = JsonBuilder()
        objBuilder.build()
        json.put(objBuilder.json)
    }

    infix fun String.to(value: Any) {
        json[this] = value as JsonObject
    }
}

fun buildJson(build: JsonBuilder.() -> Unit): JsonBuilder.JsonObject {
    val builder = JsonBuilder()
    builder.build()
    return builder.json
}