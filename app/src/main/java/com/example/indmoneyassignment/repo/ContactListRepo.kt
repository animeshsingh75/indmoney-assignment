package com.example.indmoneyassignment.repo

import com.example.indmoneyassignment.ContactList
import com.example.indmoneyassignment.network.ApiService
import com.example.indmoneyassignment.network.Resource

class ContactListRepo(private val apiService: ApiService) : BaseRepo() {

    suspend fun getContactList(): Resource<ContactList> {
        return safeApiCall { apiService.getContactList() }
    }
}