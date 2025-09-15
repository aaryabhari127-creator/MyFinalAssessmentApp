package com.example.myassssmentapplication.dashboard

import java.io.Serializable

data class Entity(
    val title: String,
    val subtitle: String?,
    val ownerKeypass: String,
    val description: String?,
    val extra: Map<String, String>
) : Serializable
