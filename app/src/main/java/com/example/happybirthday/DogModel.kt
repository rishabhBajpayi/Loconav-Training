package com.example.happybirthday

import java.io.Serializable

data class DogModel(val imageResId: Int,
                    val name: String,
                    val description: String,
                    val url: String,
                    var text: String = "") : Serializable
