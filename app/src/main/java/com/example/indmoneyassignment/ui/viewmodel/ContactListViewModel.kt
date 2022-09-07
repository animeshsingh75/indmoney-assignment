package com.example.indmoneyassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.indmoneyassignment.ContactList
import com.example.indmoneyassignment.network.Resource
import com.example.indmoneyassignment.repo.ContactListRepo
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val repo: ContactListRepo,
) : ViewModel() {

    private val _contactList = MutableLiveData<Resource<ContactList>>()
    val contactList: LiveData<Resource<ContactList>> = _contactList

    init {
        getCardList()
    }

    private fun getCardList() = viewModelScope.launch {
        _contactList.postValue(Resource.Loading())
        _contactList.postValue(repo.getContactList())
    }


    class ContactListViewModelFactory(private val repo: ContactListRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ContactListViewModel(repo) as T
        }
    }
}