package joao.g.motivationapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import joao.g.motivationapp.R
import joao.g.motivationapp.infra.MotivationConstants
import joao.g.motivationapp.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences : SecurityPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeString("","")


        verifyName()

    }

    private fun verifyName() {
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name !=""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id== R.id.buttonSave){
            handleSave()
        }
    }
    private fun handleSave(){
        val name = editName.text.toString()

        if (name != ""){
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME,name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this,"Enter Your Name",Toast.LENGTH_SHORT).show()
        }
    }


}