package com.example.storybook_theguidingfriend

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.activity_story_book_detail.*
import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class StoryBookDetailActivity : AppCompatActivity() {

    //private lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_book_detail)

        val intent = intent
        val storyTitle = intent.getStringExtra("storyTitle")
        val storyAuthor = intent.getStringExtra("storyAuthor")
        val storyURL = intent.getStringExtra("storyURL")

        //pdfView = findViewById(R.id.pdfView)

        RetrivePDFStream().execute(storyURL)

    }


    internal class RetrivePDFStream : AsyncTask<String, Void, InputStream>() {

        lateinit var pdfView: PDFView

        override fun doInBackground(vararg params: String?): InputStream? {
            var inputStream: InputStream? = null
            try {
                var url = URL(params[0])
                var urlConnection = url.openConnection() as HttpURLConnection
                if (urlConnection.getResponseCode() === 200) {
                    inputStream = BufferedInputStream(urlConnection.getInputStream())
                }
            } catch (e: Exception) {
                return null
            }
            return inputStream!!
        }

        override fun onPostExecute(result: InputStream?) {
            pdfView.fromStream(result).load()
        }
    }
}
