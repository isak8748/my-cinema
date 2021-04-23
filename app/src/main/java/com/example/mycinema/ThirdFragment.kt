package com.example.mycinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mycinema.model.Movie

class ThirdFragment: Fragment() {
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToMovieListFragment())
        }*/
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
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