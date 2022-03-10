package android.example.kitchenunitconverter

import android.annotation.SuppressLint
import android.example.kitchenunitconverter.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.convertButton.setOnClickListener { convert() }

        binding.quantityToConvertEdit.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun convert() {
        val stringInTextField = binding.quantityToConvertEdit.text.toString()
        val quantity = stringInTextField.toDoubleOrNull()
        if (quantity == null) {
            binding.conversionResult.text = ""
            return
        }

        val ingredientWeightInGrams = when (binding.ingredientOptions.checkedRadioButtonId) {
            R.id.water -> 236.59
            R.id.milk -> 243.69
            R.id.oil -> 208.20
            R.id.flour -> 125.16
            else -> 165.61
        }

        val chosenUnit = binding.unitOptions.checkedRadioButtonId
        val conversionResult = when (chosenUnit) {
            R.id.cups_to_grams -> quantity * ingredientWeightInGrams
            else -> quantity / ingredientWeightInGrams
        }
        val roundedResult = "%.1f".format(conversionResult)

        if (chosenUnit == R.id.cups_to_grams) {
            binding.conversionResult.text = "$roundedResult grams"
        } else {
            binding.conversionResult.text = "$roundedResult cups"
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}