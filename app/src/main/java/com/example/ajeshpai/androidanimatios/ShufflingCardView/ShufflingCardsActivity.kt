package com.example.ajeshpai.androidanimatios.ShufflingCardView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.activity_shuffling_cards.*

class ShufflingCardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuffling_cards)
        val cardDesign = arrayListOf(
                CardDesign.PLUS_HOT_CORAL,
                CardDesign.PLUS_LAGOON_BLUE,
                CardDesign.PLUS_MIDNIGHT_SKY
        )
        card_view.setCards(cardDesign)
        card_view.moveToFront(CardDesign.PLUS_MIDNIGHT_SKY)

        button9.setOnClickListener { card_view.moveToFront(CardDesign.PLUS_HOT_CORAL) }
        button10.setOnClickListener { card_view.moveToFront(CardDesign.PLUS_LAGOON_BLUE) }
        button11.setOnClickListener { card_view.moveToFront(CardDesign.PLUS_MIDNIGHT_SKY) }


    }
}
