package com.example.myassssmentapplication.data

import com.google.gson.JsonObject

data class DashboardEnvelope(
    val entities: List<JsonObject>,
    val entityTotal: Int
)
