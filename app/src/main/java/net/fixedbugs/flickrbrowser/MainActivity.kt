package net.fixedbugs.flickrbrowser

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
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


    private  val photoListViewModel: PhotoListViewModel by viewModels()
    private val photoAdapter = net.fixedbugs.flickrbrowser.data.PhotoAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        loadPhoto("loading")

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                photoListViewModel.getPhotos(query.toString())
                loadPhoto("refresh")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

    private fun loadPhoto(method : String){

        photoListViewModel.photos.observe(this){
           if (method=="loading") {
               val photoRecyclerView: RecyclerView = findViewById(R.id.recycler_view)
               val photoListAdapter = PhotoAdapter(it)
               photoRecyclerView.adapter=photoListAdapter
               photoRecyclerView.layoutManager =  GridLayoutManager(applicationContext, 1)
               Log.d("qwe","load...")
           }
            else{
                Log.d("qwe","refreshing...")
                photoAdapter.loadNewPhotos(it)
           }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}