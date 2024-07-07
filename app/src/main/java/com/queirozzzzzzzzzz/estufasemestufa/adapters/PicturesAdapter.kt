package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.MilissecondsToDateConverter
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture

class PicturesAdapter(private val pictures: List<Picture>) :
    RecyclerView.Adapter<PicturesAdapter.PictureViewHolder>() {
    class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val createdAtView: TextView = itemView.findViewById(R.id.created_at)
        val pictureView: ImageView = itemView.findViewById(R.id.picture)
        val deleteButton: TextView = itemView.findViewById(R.id.delete_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PictureViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.picture_item, parent, false)
        return PictureViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: PictureViewHolder,
        position: Int,
    ) {
        val picture = pictures[position]
        holder.createdAtView.text =
            MilissecondsToDateConverter.fromMilissecondsToDate(picture.createdAt).toString()
        Glide.with(holder.itemView.context)
            .load(picture.path)
            .override(350, 350)
            .centerCrop()
            .into(holder.pictureView)
        holder.pictureView.setOnClickListener {
            showFullResolutionImage(holder.itemView.context, picture.path.toString())
        }
        holder.deleteButton.tag = picture.id
    }

    override fun getItemCount(): Int = pictures.size

    private fun showFullResolutionImage(
        context: Context,
        imagePath: String,
    ) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.full_screen_image, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.full_screen_image_view)

        Glide.with(context)
            .load(imagePath)
            .into(imageView)

        MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .show()
    }
}
