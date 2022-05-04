package cl.desafiolatm.challengeandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatm.challengeandroid.R
import cl.desafiolatm.challengeandroid.databinding.FragmentDetailBinding
import cl.desafiolatm.challengeandroid.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val viewModel by activityViewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        viewModel.listMovies.observe(viewLifecycleOwner, Observer {
            viewModel.searchMovie(id)
        })

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            if (it != null){
                with(binding){
                    Picasso.get().load("http://image.tmdb.org/t/p/w500/${it.poster_path}")
                        .into(ivPictureDetail)
                    tvTitleDetail.text = it.title
                    tvOverview.text = it.overview
                    tvRuntime.text = it.runtime.toString()
                    tvGenre.text = it.genre
                    tvLanguage.text = it.language
                    tvPopularity.text = it.popularity.toString()
                    tvRelease.text = it.release_date
                    tvCountry.text = it.country
                }
            }
        })



        return binding.root
    }
}