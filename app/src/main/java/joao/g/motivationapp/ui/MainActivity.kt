package joao.g.motivationapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import joao.g.motivationapp.R
import joao.g.motivationapp.infra.MotivationConstants
import joao.g.motivationapp.infra.SecurityPreferences
import joao.g.motivationapp.repository.Mock
import joao.g.motivationapp.repository.Phrase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhaseFilter : Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "Hello, $name!"

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }


        imageAll.setColorFilter(resources.getColor(R.color.purple_200))
        handleNewPhrase()


        ButttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)


    }



    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.ButttonNewPhrase) {
            handleNewPhrase()
        } else if (id == R.id.imageAll) {
            handleFilter(1)
        } else if (id == R.id.imageHappy) {
            handleFilter(2)
        } else if (id == R.id.imageMorning) {
            handleFilter(3)
        }
    }

    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(mPhaseFilter)
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.purple_200))
                mPhaseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.purple_200))
                mPhaseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.purple_200))
                mPhaseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }
}