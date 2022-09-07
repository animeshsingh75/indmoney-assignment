package com.example.indmoneyassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indmoneyassignment.ContactList
import com.example.indmoneyassignment.ContactListItem
import com.example.indmoneyassignment.R
import com.example.indmoneyassignment.databinding.ItemContactListBinding
import com.squareup.picasso.Picasso

class ContactListAdapter(
    private val data: ContactList,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val binding =
            ItemContactListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ContactListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.bind(data[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClicked(contactItem: ContactListItem,view:View)
    }

    class ContactListViewHolder(
        private val binding: ItemContactListBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactListItem, clickListener: OnItemClickListener) {

            Picasso.get().load(contact.image)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(binding.ivProfileImage)

            binding.tvTitle.text = contact.title
            binding.tvSubtitle.text = contact.subtitle
            itemView.setOnClickListener {
                clickListener.onItemClicked(contact,binding.ivProfileImage)
            }
        }
    }
}