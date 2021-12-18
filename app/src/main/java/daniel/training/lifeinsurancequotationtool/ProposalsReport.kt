package daniel.training.lifeinsurancequotationtool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.DecimalFormat

class ProposalsReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proposals_report)

        val noteDisp = intent.getStringExtra(EXTRA_NOTE)
        val dobDisp = intent.getStringExtra(EXTRA_DOB)
        val noDisp = intent.getStringExtra(EXTRA_NO)
        val lenDisp = intent.getStringExtra(EXTRA_LEN)
        val durDisp = intent.getStringExtra(EXTRA_DUR)
        val monDisp = intent.getStringExtra(EXTRA_MON)
        val oldDisp = intent.getStringExtra(EXTRA_OLD)

        val premium = monDisp.toDouble()
        val term = lenDisp.toDouble()

        val expensePremium: Double = when (term) {
            8.0 -> 30826.0
            9.0 -> 30344.0
            10.0 -> 30016.0
            11.0 -> 29795.0
            12.0 -> 29648.0
            13.0 -> 29552.0
            14.0 -> 29494.0
            15.0 -> 29463.0
            16.0 -> 29452.0
            17.0 -> 29456.0

            else -> {
                29473.0
            }
        }

        val riskPremium: Double = when (term) {
            8.0 -> 103.813
            9.0 -> 91.061
            10.0 -> 80.888
            11.0 -> 72.604
            12.0 -> 65.735
            13.0 -> 59.953
            14.0 -> 55.032
            15.0 -> 50.798
            16.0 -> 47.122
            17.0 -> 43.906
            else -> {
                41.071
            }
        }

        val sumAssured: Double = (premium - (expensePremium * 1.19630206012632)/12) * ((12 * 1000)/(riskPremium * 1.15550794309791))

        val result = DecimalFormat("#,###")
        val display = result.format(sumAssured)

        val bonuses: Double = sumAssured * Math.pow(1.03,term - 1) + sumAssured * 0.02 * (1 - Math.pow(1.03,term)) / (1-1.03)

        val maturityValue: Double =bonuses
        val output = DecimalFormat("#,###")
        val userOutput = output.format(maturityValue)

        val noteView = findViewById<TextView>(R.id.note).apply {
            text = noteDisp
        }
        val dobView = findViewById<TextView>(R.id.dob).apply {
            text = dobDisp
        }
        val noView = findViewById<TextView>(R.id.number).apply {
            text = noDisp
        }
        val lenView = findViewById<TextView>(R.id.termLen).apply {
            text = lenDisp
        }
        val durView = findViewById<TextView>(R.id.durFreq).apply {
            text = durDisp
        }
        val monView = findViewById<TextView>(R.id.monPremium).apply {
            text = monDisp
        }
        val sumView = findViewById<TextView>(R.id.sumAssured).apply {
            text = display.toString()
        }
        val oldView = findViewById<TextView>(R.id.ageOld).apply {
            text = oldDisp
        }
        val maturityView = findViewById<TextView>(R.id.maturityValue).apply {
            text = userOutput.toString()
        }

    }

}