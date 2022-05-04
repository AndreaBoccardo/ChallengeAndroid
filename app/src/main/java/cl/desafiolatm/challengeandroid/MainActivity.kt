package cl.desafiolatm.challengeandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import cl.desafiolatm.challengeandroid.databinding.ActivityMainBinding
import cl.desafiolatm.challengeandroid.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMoviesPopular()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu,menu)
        val searchMenu = menu?.findItem(R.id.app_bar_search)
        val searchView = searchMenu?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filter.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isEmpty())
                {
                    viewModel.filter.value = ""
                }
                return false
            }

        })
        return false
    }
}