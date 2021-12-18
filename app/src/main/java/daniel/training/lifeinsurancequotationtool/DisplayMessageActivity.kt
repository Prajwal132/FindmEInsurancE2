package daniel.training.lifeinsurancequotationtool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

const val EXTRA_NOTE = "daniel.lifeInsuranceQuotation.NOTE"
const val EXTRA_DOB = "daniel.lifeInsuranceQuotation.DOB"
const val EXTRA_NO = "daniel.lifeInsuranceQuotation.NO"
const val EXTRA_LEN = "daniel.lifeInsuranceQuotation.LEN"
const val EXTRA_DUR = "daniel.lifeInsuranceQuotation.DUR"
const val EXTRA_MON = "daniel.lifeInsuranceQuotation.MON"
const val EXTRA_OLD = "daniel.lifeInsuranceQuotation.OLD"

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val date = intent.getStringExtra(EXTRA_DATE)
        val phone = intent.getStringExtra(EXTRA_PHONE)
        val term = intent.getStringExtra(EXTRA_TERM)
        val frequency = intent.getStringExtra(EXTRA_FREQUENCY)
        val premium = intent.getStringExtra(EXTRA_PREMIUM)
        val age = intent.getStringExtra(EXTRA_AGE)

        // Capture the layouts text view and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }
        val dateView = findViewById<TextView>(R.id.textView3).apply {
            text = date
        }
        val phoneView = findViewById<TextView>(R.id.textView4).apply {
            text = phone
        }
        val termView = findViewById<TextView>(R.id.textView5).apply {
            text = term
        }
        val frequencyView = findViewById<TextView>(R.id.textView6).apply {
            text = frequency
        }
        val premiumView = findViewById<TextView>(R.id.textView8).apply {
            text = premium
        }
        val ageView = findViewById<TextView>(R.id.ageMessage).apply {
            text = age
        }

    }
    fun calculateSum(view: View) {
        val messageView = findViewById<TextView>(R.id.textView)
        val dateView = findViewById<TextView>(R.id.textView3)
        val phoneView = findViewById<TextView>(R.id.textView4)
        val termView = findViewById<TextView>(R.id.textView5)
        val frequencyView = findViewById<TextView>(R.id.textView6)
        val premiumView = findViewById<TextView>(R.id.textView8)
        val ageView = findViewById<TextView>(R.id.ageMessage)
        val note = messageView.text.toString()
        val dob = dateView.text.toString()
        val no = phoneView.text.toString()
        val len = termView.text.toString()
        val dur = frequencyView.text.toString()
        val mon = premiumView.text.toString()
        val old = ageView.text.toString()

        val intent = Intent(this, ProposalsReport::class.java ).apply {
            putExtra(EXTRA_NOTE, note);putExtra(EXTRA_DOB, dob);putExtra(EXTRA_NO, no);putExtra(EXTRA_LEN, len);
            putExtra(EXTRA_DUR, dur);putExtra(EXTRA_MON, mon);putExtra(EXTRA_OLD, old);
        }
        startActivity(intent)
    }
}