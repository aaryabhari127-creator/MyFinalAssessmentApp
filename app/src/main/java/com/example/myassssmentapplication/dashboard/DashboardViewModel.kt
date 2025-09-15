package com.example.myassssmentapplication.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassssmentapplication.data.DashboardEnvelope
import com.example.myassssmentapplication.data.Repository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _items = MutableLiveData<List<Entity>>()
    val items: LiveData<List<Entity>> get() = _items

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun load(keypass: String) {
        repo.dashboard(keypass).enqueue(object : Callback<DashboardEnvelope> {
            override fun onResponse(call: Call<DashboardEnvelope>, resp: Response<DashboardEnvelope>) {
                if (!resp.isSuccessful || resp.body() == null) {
                    _error.value = "Failed to load entities"
                    return
                }
                val list = resp.body()!!.entities.map { obj ->
                    toEntity(obj, keypass)
                }
                _items.value = list
            }

            override fun onFailure(call: Call<DashboardEnvelope>, t: Throwable) {
                _error.value = t.message ?: "Network error"
            }
        })
    }

    private fun toEntity(obj: JsonObject, keypass: String): Entity {
        val description = if (obj.has("description")) obj.get("description").asString else null
        val map = mutableMapOf<String, String>()

        for ((k, v) in obj.entrySet()) {
            if (k != "description" && v.isJsonPrimitive) {
                map[k] = v.asString
            }
        }

        val title = map.values.firstOrNull() ?: "(entity)"
        val subtitle = map.values.drop(1).firstOrNull()

        return Entity(
            title = title,
            subtitle = subtitle,
            ownerKeypass = keypass,
            description = description,
            extra = map
        )
    }
}
