package com.example.indmoneyassignment.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indmoneyassignment.ContactListItem
import com.example.indmoneyassignment.R
import com.example.indmoneyassignment.databinding.ActivityMainBinding
import com.example.indmoneyassignment.network.Client
import com.example.indmoneyassignment.network.Resource
import com.example.indmoneyassignment.repo.ContactListRepo
import com.example.indmoneyassignment.ui.viewmodel.ContactListViewModel

const val CONTACT_DATA = "contact_data"

class MainActivity : AppCompatActivity(), ContactListAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val repo by lazy {
        ContactListRepo(Client.api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ContactListViewModel.ContactListViewModelFactory(repo)
        val viewModel = ViewModelProvider(this, factory)[ContactListViewModel::class.java]
        viewModel.contactList.observe(this) {
            when (it) {
                is Resource.Error -> {
                    it.message?.let { message ->
                        binding.loadingPb.visibility = View.GONE
                        binding.errorTv.visibility = View.VISIBLE
                        binding.errorTv.text = message
                    }
                }
                is Resource.Loading -> {
                    binding.loadingPb.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    val adapter =
                        it.data?.let { contactList -> ContactListAdapter(contactList, this) }
                    binding.contactRv.layoutManager = LinearLayoutManager(this)
                    binding.contactRv.adapter = adapter
                    binding.loadingPb.visibility = View.GONE
                }
            }
        }
    }

    override fun onItemClicked(contactItem: ContactListItem, view: View) {
        val intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra(CONTACT_DATA, contactItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            view,
            this.resources.getString(R.string.profileImage)
        )
        startActivity(intent, options.toBundle())
    }
}