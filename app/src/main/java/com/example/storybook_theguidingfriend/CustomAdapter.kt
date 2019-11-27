package com.example.storybook_theguidingfriend

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val storyBooksList: ArrayList<StoryBooksDataClass>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        context = view.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storyBooksList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(storyBooksList[position])

        // adding on click listener to go on details activity with intent
        holder.itemView.setOnClickListener {
            val intent = Intent(context, StoryBookDetailActivity::class.java)
            intent.putExtra("storyTitle", storyBooksList[position].storyTitle)
            intent.putExtra("storyAuthor", storyBooksList[position].storyAuthorName)
            intent.putExtra("storyURL", storyBooksList[position].storyURL)
            context!!.startActivity(intent)

        }
    }

    //the class is holing the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(storyBook: StoryBooksDataClass) {
            val textViewStorySno  = itemView.findViewById(R.id.storySNOTextView) as TextView
            val textViewAuthorName = itemView.findViewById(R.id.authorNameTextView) as TextView
            val textViewStoryTitle  = itemView.findViewById(R.id.storyTitleTextView) as TextView

            textViewStorySno.text = storyBook.sno.toString()
            textViewAuthorName.text = storyBook.storyAuthorName
            textViewStoryTitle.text = storyBook.storyTitle
        }
    }
}