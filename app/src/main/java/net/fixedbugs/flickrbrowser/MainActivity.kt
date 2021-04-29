package net.fixedbugs.flickrbrowser

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.fixedbugs.flickrbrowser.data.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        val photoRecyclerView: RecyclerView = findViewById(R.id.recycler_view)

        CoroutineScope(Dispatchers.Main).launch {

           val response = FlickerApi.getClient()
                .create(FlickrApiService::class.java)
                .listPhoto("cars","json","1")

            if (response.isSuccessful) {

                val photoList= ArrayList<Photo>()
                response.body()!!.items?.map { photoList.add(Photo(it.title,it.media)) }
                val photoListAdapter = PhotoAdapter(photoList)
                photoRecyclerView.adapter=photoListAdapter
                photoRecyclerView.layoutManager =  GridLayoutManager(applicationContext, 1)

            } else {
                Toast.makeText(applicationContext,"Hatalı işlem", Toast.LENGTH_LONG).show()
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}