package com.nf98.moviecatalogue.app.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nf98.moviecatalogue.R
import com.nf98.moviecatalogue.app.ui.ListFragment

class MainPagerAdapter(private val context: Context?, fm: FragmentManager, private val type: Int)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        const val TYPE_MOVIE = 0
        const val TYPE_TV = 1
        const val TYPE_DISCOVER = 2
    }

    @StringRes
    private var TITLES = intArrayOf()

    init {
        when(type) {
            TYPE_MOVIE -> TITLES = intArrayOf(R.string.popular, R.string.top_rated, R.string.upcoming, R.string.now_playing)
            TYPE_TV -> TITLES = intArrayOf(R.string.popular, R.string.top_rated, R.string.on_tv, R.string.airing_today)
            TYPE_DISCOVER -> TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
        }
    }

    override fun getItem(position: Int): Fragment {
        return when(type) {
            TYPE_MOVIE -> ListFragment.newInstance(position)
            TYPE_TV -> ListFragment.newInstance(position + 4)
            TYPE_DISCOVER -> ListFragment.newInstance(position + 8)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(TITLES[position])
    }

    override fun getCount(): Int {
        return TITLES.size
    }

}