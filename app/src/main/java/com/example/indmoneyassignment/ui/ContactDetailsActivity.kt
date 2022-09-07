package com.example.indmoneyassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.indmoneyassignment.ContactListItem
import com.example.indmoneyassignment.R
import com.example.indmoneyassignment.databinding.ActivityContactDetailsBinding
import com.squareup.picasso.Picasso

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contact = intent.getSerializableExtra(CONTACT_DATA) as ContactListItem
        Picasso.get().load(contact.image)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(binding.ivProfileImage)

        binding.tvDescription.text = contact.description
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}