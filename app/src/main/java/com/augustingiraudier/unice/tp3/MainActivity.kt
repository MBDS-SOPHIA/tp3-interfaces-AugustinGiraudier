package com.augustingiraudier.unice.tp3

import android.animation.ObjectAnimator
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

        val inputNumber: EditText = findViewById(R.id.editTextNumber)
        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(inputIsValid(s)){
                    rollDices()
                }
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

        val isWon : Boolean = (diceRoll + diceRoll2).toString().equals(inputNumber.text.toString())
        messageTextView.text = if (isWon) getString(R.string.winMessage) else getString(R.string.looseMessage)
        if(isWon){
            shakeTextView(resultTextView)
            shakeTextView(resultTextView2)
        }

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

    fun shakeTextView(textView: TextView) {
        // Animation de translation horizontale pour simuler un tremblement
        val animator = ObjectAnimator.ofFloat(textView, "translationX", 0f, 10f, -10f, 10f, -10f, 5f, -5f, 0f)
        animator.duration = 500 // Durée en ms
        animator.start()
    }
}


class Dice(val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}