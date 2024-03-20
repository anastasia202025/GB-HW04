package org.example

data class Person(
    val name: String,
    val phones: List<String>,
    val emails: List<String>
)

val phoneBook: MutableMap<String, Person> = mutableMapOf()