package daniel.training.lifeinsurancequotationtool

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*

const val EXTRA_MESSAGE = "daniel.lifeInsuranceQuotation.quote.MESSAGE"
const val EXTRA_DATE = "daniel.lifeInsuranceQuotation.quote.DATE"
const val EXTRA_PHONE = "daniel.lifeInsuranceQuotation.quote.PHONE"
const val EXTRA_TERM = "daniel.lifeInsuranceQUotation.quote.TERM"
const val EXTRA_FREQUENCY = "daniel.lifeInsuranceQuotation.quote.FREQUENCY"
const val EXTRA_PREMIUM = "daniel.lifeInsuranceQuotation.quote.PREMIUM"
const val EXTRA_AGE = "daniel.lifeInsuranceQuotation.quote.AGE"
class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.editTerm)
        val polTerm = resources.getStringArray(R.array.term_array)
        val frequencyTerm = resources.getStringArray(R.array.premium_frequency)
        val spinnerFreq = findViewById<Spinner>(R.id.payFrequency)

        // Create an arrray adapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(this,R.array.term_array,android.R.layout.simple_spinner_item)
            .also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter. setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // apply the adapter to the spinner
                spinner.adapter = adapter
            }

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,frequencyTerm)

        spinnerFreq.adapter = arrayAdapter
        editTerm.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerResult.text = polTerm[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spinnerFreq.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                displayFreq.text = frequencyTerm[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        

    }

    fun showDatePickerDialog(v: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        val currentDate = DateFormat.getDateInstance().format(c.time)

        val toDisplay = findViewById<TextView>(R.id.display_date)
        toDisplay.text = currentDate

        val today = Calendar.getInstance()
        val Yod: Int = today.get(Calendar.YEAR)
        val Yob: Int = c.get(Calendar.YEAR)
        val age: Int = Yod - Yob

        val displayAge = findViewById<TextView>(R.id.ageResult)
        displayAge.text = age.toString()
    }
    /** called when the user taps the get Started Button */
    fun getStarted (view: View) {
        // Do something in response to button
        val editText = findViewById<EditText>(R.id.editName)
        val editDate = findViewById<TextView>(R.id.display_date)
        val editAge = findViewById<TextView>(R.id.ageResult)
        val editNumber = findViewById<TextView>(R.id.editTextNumber)
        val selectedTerm = findViewById<TextView>(R.id.spinnerResult)
        val frequencyDisp = findViewById<TextView>(R.id.displayFreq)
        val editPremium = findViewById<EditText>(R.id.premium)
        val message = editText.text.toString()
        val dateOfBirth = editDate.text.toString()
        val ageEntered = editAge.text.toString()
        val phoneNumber = editNumber.text.toString()
        val termDisplay = selectedTerm.text.toString()
        val freqDisp = frequencyDisp.text.toString()
        val prem = editPremium.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message);putExtra(EXTRA_DATE, dateOfBirth);putExtra(EXTRA_PHONE, phoneNumber);
            putExtra(EXTRA_TERM, termDisplay);putExtra(EXTRA_FREQUENCY, freqDisp);putExtra(
            EXTRA_PREMIUM, prem);putExtra(EXTRA_AGE, ageEntered)
        }
        val age: Int = ageEntered.toInt()
        val premium: Int = prem.toInt()

        if (age <18) {
            Toast.makeText(applicationContext, "Age Must be above 18 years", Toast.LENGTH_LONG).show()
        }

        else if (premium < 5000 || premium == null) {
            Toast.makeText(applicationContext, "Minimum Premium is 5000", Toast.LENGTH_LONG).show()
        }

        else if (message == null) {
            Toast.makeText(applicationContext, "Please Enter Name", Toast.LENGTH_LONG).show()
        }
        else if (freqDisp != "Monthly") {
            Toast.makeText(applicationContext, "Quotation for the payment frequency is not available", Toast.LENGTH_LONG).show()
        }
        else if (age > 65) {
            Toast.makeText(applicationContext,"Maximum age is 65", Toast.LENGTH_LONG).show()
        }
        else {
            startActivity(intent)
        }
    }
}