package cl.desafiolatm.challengeandroid.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatm.challengeandroid.R
import cl.desafiolatm.challengeandroid.adapter.MovieAdapter
import cl.desafiolatm.challengeandroid.databinding.FragmentListBinding
import cl.desafiolatm.challengeandroid.model.movielist.MovieList
import cl.desafiolatm.challengeandroid.viewmodel.MovieViewModel

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private val viewModel by activityViewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)
        val adapter = MovieAdapter()
        val manager =  LinearLayoutManager(requireContext())

        adapter.setMiOnClickListener(object : MovieAdapter.miOnClickListener{
            override fun onClickListener(movie: MovieList) {
                viewModel.getMovieDetail(movie.id)
                Navigation.findNavController(requireView()).navigate(R.id.action_listFragment_to_detailFragment)
            }
        })

        with(binding){
            rvList.adapter = adapter
            rvList.layoutManager = manager
        }

        viewModel.listMoviesPopular.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        viewModel.filter.observe(viewLifecycleOwner, Observer {
            adapter.filterList(viewModel.listMoviesPopular.value!!.filter {
                m -> m.originalTitle!!.uppercase().contains(it.uppercase())
            })
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}