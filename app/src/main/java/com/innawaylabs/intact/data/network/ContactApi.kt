package com.innawaylabs.intact.data.network

import com.innawaylabs.intact.data.network.model.ApiContact
import retrofit2.http.GET

interface ContactApi {
    @GET("contacts")
    suspend fun getContacts(): List<ApiContact>
}