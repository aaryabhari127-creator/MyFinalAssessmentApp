package com.example.myassssmentapplication.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService
) {
    fun login(req: LoginRequest) = api.login(req)
    fun dashboard(keypass: String) = api.getDashboard(keypass)
}
