package com.augustingiraudier.unice.tp3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
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

        val inputNumber: EditText = findViewById(R.id.editTextNumber)
        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                rollButton.isEnabled = inputIsValid(s);
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
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
        val inputNumber: EditText = findViewById(R.id.editTextNumber)
        val messageTextView: TextView = findViewById(R.id.message)

        println(inputNumber.text.length)
        println((diceRoll + diceRoll2).toString().length)


        messageTextView.text = if ((diceRoll + diceRoll2).toString().equals(inputNumber.text.toString())) getString(R.string.winMessage) else getString(R.string.looseMessage)
    }

    private fun inputIsValid(s :Editable?): Boolean {
        val inputText = s.toString()

        // Vérification que le texte n'est pas vide
        if (inputText.isEmpty()) {
            println("Le champ est vide")
            return false
        }

        // Vérification que le texte est un entier
        val number = inputText.toIntOrNull()
        if (number == null) {
            println("Le texte n'est pas un entier valide")
            return false
        }

        // Vérification que le nombre est entre 0 et 12 inclus
        if (number !in 2..12) {
            println("Le nombre $number est invalide")
            return false
        }

        return true
    }
}


class Dice(val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}