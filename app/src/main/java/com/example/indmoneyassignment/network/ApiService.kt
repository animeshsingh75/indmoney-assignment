package com.example.indmoneyassignment.network

import com.example.indmoneyassignment.ContactList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("1017068603929018368")
    suspend fun getContactList(): Response<ContactList>

}