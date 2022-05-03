package cl.desafiolatm.challengeandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatm.challengeandroid.R
import cl.desafiolatm.challengeandroid.databinding.ItemLayoutBinding
import cl.desafiolatm.challengeandroid.model.movielist.MovieList
import com.squareup.picasso.Picasso

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.CustomViewHolder>() {

    var list: List<MovieList> = ArrayList()
    lateinit var listener: miOnClickListener

    class CustomViewHolder(itemView: View, var listener: miOnClickListener): RecyclerView.ViewHolder(itemView){

        private val binding = ItemLayoutBinding.bind(itemView)

        fun bindData(movie: MovieList){
            with(binding){
                Picasso.get().load("http://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .resize(250, 250).into(ivPictureItem)
                tvTitleItem.text = movie.originalTitle
                itemView.setOnClickListener {
                    listener.onClickListener(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return CustomViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: List<MovieList>){
        this.list = list
        notifyDataSetChanged()
    }

    interface miOnClickListener{
        fun onClickListener(movie: MovieList)
    }

    fun setMiOnClickListener(listener: miOnClickListener){
        this.listener = listener
    }

    fun filterList(list: List<MovieList>){
        this.list = list
        notifyDataSetChanged()
    }
}