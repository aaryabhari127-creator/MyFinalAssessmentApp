package com.example.myassssmentapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassssmentapplication.data.LoginRequest
import com.example.myassssmentapplication.data.LoginResponse
import com.example.myassssmentapplication.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _loginResult = MutableLiveData<String?>()
    val loginResult: LiveData<String?> get() = _loginResult

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun login(username: String, password: String) {
        repo.login(LoginRequest(username, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, resp: Response<LoginResponse>) {
                    if (resp.isSuccessful) {
                        _loginResult.value = resp.body()?.keypass
                    } else {
                        _error.value = "Login failed. Check credentials."
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _error.value = t.message ?: "Network error"
                }
            })
    }
}
