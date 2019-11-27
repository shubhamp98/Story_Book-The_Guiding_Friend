package com.example.storybook_theguidingfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDatabase: FirebaseDatabase

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    var storyDetails = ArrayList<StoryBooksDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase = FirebaseDatabase.getInstance()

        getStoryDetailsFromFirebaseDB()

        //getting recyclerview from xml
        mRecyclerView = findViewById(R.id.bookListRecyclerView)
        //adding a layoutmanager
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager

        //storyDetails.add(StoryBooksDataClass(1, "Test", "Shubham", "test"))

        //println("After FB Data:" + storyDetails)
        mAdapter = CustomAdapter(storyDetails)
        mRecyclerView!!.adapter = mAdapter
    }

    private fun getStoryDetailsFromFirebaseDB() {
        val getDataReference = mDatabase.getReference("Story")
        getDataReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.w("Get Data Error:", "getDataReference:onCancelled")
                Toast.makeText(applicationContext, "Action Cancelled", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                var sno = 0
                for (data in p0.children) {
                    val hashMap = data.value as HashMap<String, Any>

                    if (hashMap.size > 0) {
                        //println(hashMap.size)

                        //println("HashMap" + hashMap)
                        sno++

                        val storyTitleFromFB = hashMap["Story Title"].toString()
                        val storyAuthorFromFB = hashMap["Author"].toString()
                        val storyURLFromFB = hashMap["URL"].toString()

                        /*====================
                        val storyData = StoryBooksDataClass() // StoryBooksDataClass is a class used to bind the data
                        storyData.sno = sno
                        storyData.storyTitle = storyTitleFromFB
                        storyData.storyAuthorName = storyAuthorFromFB
                        storyData.storyURL = storyURLFromFB

                        storyDetails.add(storyData)

                         */

                        storyDetails.add(StoryBooksDataClass(sno, storyTitleFromFB, storyAuthorFromFB, storyURLFromFB))

                        //println("In FB Data:" + storyDetails)

                        lastStoryTextView.text = "Last story: " + storyTitleFromFB

                    }
                }
                mAdapter!!.notifyDataSetChanged() // it is used to indicate that some new data add/changed
            }
        })
    }
}
