package com.example.ajeshpai.androidanimatios.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import android.transition.*
import android.view.ViewGroup
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.search_home_view.*
import androidx.core.view.ViewCompat
import androidx.core.app.ActivityOptionsCompat
import android.content.Intent

class SearchViewTransitionActivity : AppCompatActivity() {

    private val view by lazy {
        layoutInflater.inflate(R.layout.search_view,search_view_container,false)
    }

    private val searchview_card by lazy {
        view.findViewById<CardView>(R.id.search_card)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_home_view)

        val transition=TransitionSet()
                .addTransition(Fade()
                        .addTarget(searchview_card))
                .addTransition(TransitionSet()
                .addTransition(ChangeBounds())
                        .addTransition(ChangeTransform())
                .addTarget(home_card)
                .addTarget(searchview_card))


        home_card.setOnClickListener {
            TransitionManager.beginDelayedTransition(search_view_container,transition)
            if (searchview_card.getParent() != null)
                (searchview_card.getParent() as ViewGroup).removeView(searchview_card)
            search_home_container.addView(searchview_card)
            search_home_container.removeView(home_card)
            val intent = Intent(this@SearchViewTransitionActivity, Search_Activity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SearchViewTransitionActivity,
                    searchview_card,
                    ViewCompat.getTransitionName(searchview_card)!!)
            startActivity(intent, options.toBundle())
        }
    }

    override fun onResume() {
        super.onResume()
        if (home_card.getParent() != null)
            (home_card.getParent() as ViewGroup).removeView(home_card)
        search_home_container.removeView(searchview_card)
        search_home_container.addView(home_card)
    }




}
