package com.example.mycinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mycinema.adapter.MovieListAdapter
import com.example.mycinema.adapter.MovieListClickListener
import com.example.mycinema.database.*
import com.example.mycinema.databinding.FragmentMovieListBinding
import com.example.mycinema.databinding.MovieListItemBinding
import com.example.mycinema.network.DataFetchStatus
import com.example.mycinema.viewmodel.MovieListViewModel
import com.example.mycinema.viewmodel.MovieListViewModelFactory
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var  viewModelFactory: MovieListViewModelFactory

    private lateinit var movieDatabaseDao: MovieDatabaseDao
    private lateinit var cachedDatabaseDao: CachedDatabaseDao

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private var lastSelectedMenuOption = R.id.action_load_popular_movies

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        _binding = FragmentMovieListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao
        cachedDatabaseDao = CachedDatabase.getInstance(application).cachedDatabaseDao
        viewModelFactory = MovieListViewModelFactory(movieDatabaseDao, cachedDatabaseDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        val movieListAdapter = MovieListAdapter(
            MovieListClickListener { movie ->
                viewModel.onMovieListItemClicked(movie)
            }
        )
        binding.movieListRv.adapter = movieListAdapter

        binding.movieListRv.layoutManager = GridLayoutManager(this.context, 3)

        viewModel.movieList.observe(viewLifecycleOwner, { movieList ->
            movieList?.let{
                movieListAdapter.submitList(movieList)
            }
        })

        viewModel.favList.observe(viewLifecycleOwner, { favList ->
            favList?.let{
                movieListAdapter.submitList(favList)
            }
        })

        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner, {movie ->
            movie?.let {
                this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie))
                viewModel.onMovieDetailNavigated()
            }
        })


        viewModel.dataFetchStatus.observe(viewLifecycleOwner, { status ->
            status?.let {
                when(status) {
                    DataFetchStatus.LOADING -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.loading_animation)
                    }
                    DataFetchStatus.ERROR -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                    }
                    DataFetchStatus.DONE -> {
                        binding.statusImage.visibility = View.GONE
                    }
                }
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_load_popular_movies -> {
                lastSelectedMenuOption = R.id.action_load_popular_movies
                viewModel.getPopularMovies()
            }
            R.id.action_load_top_rated_movies -> {
                lastSelectedMenuOption = R.id.action_load_top_rated_movies
                viewModel.getTopRatedMovies()
            }
            R.id.action_load_saved_movies -> {
                lastSelectedMenuOption = R.id.action_load_saved_movies
                viewModel.getSavedMovies()

            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onResume(){
        super.onResume()
        when(lastSelectedMenuOption){
            R.id.action_load_saved_movies -> {
                viewModel.getSavedMovies()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val movies = Movies()

        val movieList = view.findViewById<LinearLayout>(R.id.movie_list_ll)

        val movieItem = movieList.findViewById<View>(R.id.movie_1)
        val movieTitle = movieItem.findViewById<TextView>(R.id.movie_title)
        val moviePoster = movieItem.findViewById<ImageView>(R.id.movie_poster)

        movieTitle.text = movies.list[0].title
        Glide
                .with(this)
                .load(Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movies.list[0].posterPath)
                .into(moviePoster)


         */
        /*view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }
}