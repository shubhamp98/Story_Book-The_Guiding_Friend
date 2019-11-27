package com.example.storybook_theguidingfriend

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.LinearLayout
import com.github.barteksc.pdfviewer.PDFView
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import kotlinx.android.synthetic.main.activity_story_book_detail.*
import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class StoryBookDetailActivity : AppCompatActivity(), DownloadFile.Listener {

    lateinit var root: LinearLayout
    private lateinit var remotePDFViewPager: RemotePDFViewPager
    private lateinit var adapter: PDFPagerAdapter

    //private lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_book_detail)

        root = findViewById(R.id.remote_pdf_root)


        val intent = intent
        val storyTitle = intent.getStringExtra("storyTitle")
        val storyAuthor = intent.getStringExtra("storyAuthor")
        val storyURL = intent.getStringExtra("storyURL")

        //pdfView = findViewById(R.id.pdfView)

        //RetrivePDFStream().execute(storyURL)

        //pdfView.fromAsset("test.pdf").load()

        remotePDFViewPager = RemotePDFViewPager(applicationContext, storyURL, this)

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

    override fun onSuccess(url: String?, destinationPath: String?) {
        adapter = PDFPagerAdapter(this, "AdobeXMLFormsSamples.pdf")
        remotePDFViewPager.setAdapter(adapter)
        setContentView(remotePDFViewPager)

        root.removeAllViewsInLayout()
    }

    override fun onFailure(e: Exception?) {
        Log.e("PDF Error", "Something went wrong!"+e)
    }

    override fun onProgressUpdate(progress: Int, total: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.close()
    }
}
