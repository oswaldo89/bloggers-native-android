package com.app.oswaldo.bloggers.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.oswaldo.bloggers.R

class FavoritesFragment : Fragment() {


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        return root
    }
}