package net.fixedbugs.flickrbrowser.data

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.fixedbugs.flickrbrowser.PhotoDetail
import net.fixedbugs.flickrbrowser.PhotoListViewModel
import net.fixedbugs.flickrbrowser.R



class PhotoViewHolder (view: View) : RecyclerView.ViewHolder(view){

 var photoImageView : ImageView = view.findViewById(R.id.photo_image_view)
 var photoTitle: TextView = view.findViewById(R.id.image_title)
}
class PhotoAdapter(var photolist : List<Photo>) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_photo_item,parent,false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo= photolist[position]
        Picasso.get().load(photo.media.m).into(holder.photoImageView)
        holder.photoTitle.text=photo.title
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,PhotoDetail::class.java)
            intent.putExtra("author",photolist[position].author)
            intent.putExtra("link",photolist[position].media.m)
            intent.putExtra("title",photolist[position].title)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return photolist.size
    }
}