package net.fixedbugs.flickrbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.fixedbugs.flickrbrowser.data.PhotoAdapter

class PhotoDetail : AppCompatActivity() {

    private  val photoListViewModel: PhotoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        val photoAuthor: TextView = findViewById(R.id.photo_author)
        val photoImage: ImageView = findViewById(R.id.photo_image)
        val photoTitle: TextView = findViewById(R.id.photo_title)

        Picasso.get().load(intent.getStringExtra("link")).into(photoImage)
        photoAuthor.setText(intent.getStringExtra("author"))
        photoTitle.setText(intent.getStringExtra("title"))


    }
}