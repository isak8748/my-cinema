package com.example.mycinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mycinema.database.MovieDatabase
import com.example.mycinema.database.MovieDatabaseDao
import com.example.mycinema.database.MovieDetails
import com.example.mycinema.databinding.FragmentMovieDetailBinding
import com.example.mycinema.model.Movie
import com.example.mycinema.model.MovieDetail
import com.example.mycinema.utils.Constants
import com.example.mycinema.viewmodel.MovieDetailViewModel
import com.example.mycinema.viewmodel.MovieDetailViewModelFactory
import com.example.mycinema.viewmodel.MovieListViewModel
import com.example.mycinema.viewmodel.MovieListViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var  viewModelFactory: MovieDetailViewModelFactory

    private lateinit var movieDatabaseDao: MovieDatabaseDao


    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie
    private lateinit var movieDetail: MovieDetail
    private val movieDetails = MovieDetails()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_movie_detail, container, false)
        _binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie

        val application = requireNotNull(this.activity).application
        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao

        viewModelFactory = MovieDetailViewModelFactory(movieDatabaseDao, application, movie)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        binding.movie = movie
        binding.viewmodel = viewModel
        /*for(md in movieDetails.list){
            if(md.id == 5L/*movie.id*/){
                movieDetail = md
            }
        }
        binding.movieDetail = movieDetail
        var genres = ""
        for(genre in movieDetail.genres){
            genres += genre
            genres += " "
        }
        binding.genresString = genres
        binding.imdbUrl = Constants.IMDB_URL*/
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToMovieList.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment())
        }
        binding.toThirdFragment.setOnClickListener {
            movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToThirdFragment(movie))
        }
    }
}