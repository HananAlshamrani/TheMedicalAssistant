package com.example.themedicalassistant.Model

data class Diseases (
    var name: String,
    var Symptoms :List<String> = listOf("","","","",""),
    var image : String

):java.io.Serializable