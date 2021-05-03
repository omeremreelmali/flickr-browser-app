package net.fixedbugs.flickrbrowser

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.fixedbugs.flickrbrowser.data.FlickerApi
import net.fixedbugs.flickrbrowser.data.FlickrApiService
import net.fixedbugs.flickrbrowser.data.Photo
import net.fixedbugs.flickrbrowser.data.PhotoAdapter

class PhotoListViewModel : ViewModel() {

    private val _photos = MutableLiveData<MutableList<Photo>>()
    val photos : LiveData<MutableList<Photo>> = _photos


    init{
        _photos.value= ArrayList<Photo>()
        getPhotos("programming")
    }

    fun getPhotos(searckey : String){
        _photos.value= ArrayList<Photo>()

        viewModelScope.launch {
            val response = FlickerApi.getClient()
                    .create(FlickrApiService::class.java)
                    .listPhoto(searckey,"json","1")

            if (response.isSuccessful) {
                response.body()!!.items?.map {
                    _photos.value?.add(Photo(it.title,it.media,it.author))
                    _photos.value=photos.value
                }
            }
        }
    }



}