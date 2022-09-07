package com.example.indmoneyassignment

import java.io.Serializable

class ContactList : ArrayList<ContactListItem>()

data class ContactListItem(
    val description: String?,
    val image: String?,
    val subtitle: String?,
    val title: String?
):Serializable