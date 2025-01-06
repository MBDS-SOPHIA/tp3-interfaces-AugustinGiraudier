package com.augustingiraudier.unice.tp3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDices() }
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDices() {
        // Create new Dice object with 6 sides and roll it twice
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceRoll2 = dice.roll()

        // Update the screen with the dice roll
        val resultTextView: TextView = findViewById(R.id.textView1)
        val resultTextView2: TextView = findViewById(R.id.textView2)
        resultTextView.text = diceRoll.toString()
        resultTextView2.text = diceRoll2.toString()

        // update result message
        val messageTextView: TextView = findViewById(R.id.message)
        messageTextView.text = if (diceRoll == diceRoll2) getString(R.string.winMessage) else getString(R.string.looseMessage)
    }
}


class Dice(val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}