package com.example.storybook_theguidingfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.bookListRecyclerView) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        //crating an arraylist to store users using the data class user
        val story = ArrayList<StoryBooksDataClass>()

        //adding some dummy data to the list
        story.add(StoryBooksDataClass(1, "Belal Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(2, "Ramiz Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(3, "Faiz Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(4, "Yashar Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(5, "Belal Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(6, "Ramiz Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(7, "Faiz Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(8, "Yashar Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(9, "Belal Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(10, "Ramiz Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(11, "Faiz Khan", "Ranchi Jharkhand"))
        story.add(StoryBooksDataClass(12, "Yashar Khan", "Ranchi Jharkhand"))

        //creating our adapter
        val adapter = CustomAdapter(story)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }
}
