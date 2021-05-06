package com.example.mycinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycinema.adapter.ReviewListAdapter
import com.example.mycinema.adapter.VideoListAdapter
import com.example.mycinema.databinding.FragmentThirdBinding
import com.example.mycinema.model.Movie
import com.example.mycinema.network.DataFetchStatus
import com.example.mycinema.viewmodel.ThirdViewModel
import com.example.mycinema.viewmodel.ThirdViewModelFactory

class ThirdFragment: Fragment() {

    private lateinit var viewModel: ThirdViewModel
    private lateinit var viewModelFactory: ThirdViewModelFactory

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToMovieListFragment())
        }*/
        _binding = FragmentThirdBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        viewModelFactory = ThirdViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ThirdViewModel::class.java)

        val reviewListAdapter = ReviewListAdapter()
        binding.reviewListRv.adapter = reviewListAdapter

        val videoListAdapter = VideoListAdapter()
        binding.videoListRv.adapter = videoListAdapter


        val movieId = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie.id.toInt()

        viewModel.getReviews(movieId)

        viewModel.getVideos(movieId)

        viewModel.reviewList.observe(viewLifecycleOwner, { reviewList ->
            reviewList?.let{
                reviewListAdapter.submitList(reviewList)
            }

        })

        viewModel.videoList.observe(viewLifecycleOwner, { videoList ->
            videoList?.let{
                videoListAdapter.submitList(videoList)
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

        return binding.root


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_third, container, false)
    }

    /*fun onBackPressed(){
        findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToMovieListFragment())
    }*/





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.back_to_movie_detail).setOnClickListener {
            movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
            findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToMovieListFragment())
        }
    }


}