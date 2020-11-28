package br.com.hefestos.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor


class LoginActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val biometricButton = findViewById<ImageButton>(R.id.biometricImgBt)

        //init biometric
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    //auth error, stop tasks that requires auth
                    //authStatusTv.text = "Authentication Error: $errString"
                    Toast.makeText(
                        applicationContext,
                        "Authentication Error: $errString",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    //auth succeed, do tasks that requires auth
                    //authStatusTv.text = "Auth succeed...!"
                    Toast.makeText(applicationContext, "Auth succeed...!", Toast.LENGTH_SHORT)
                        .show()

                    val intent = Intent(applicationContext, MenuActivity::class.java)
                    startActivity(intent)

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    //auth failed, stop tasks that requires auth
                    //authStatusTv.text = "Auth failed...!"
                    Toast.makeText(applicationContext, "Auth failed...!", Toast.LENGTH_SHORT).show()
                }
            })

        //set properties like title and description on auth dialog
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticação Biometrica")
            .setSubtitle("Login usando impressão digital")
            .setNegativeButtonText("Voltar")
            .build()


        // Register the onClick listener with the implementation above
        biometricButton.setOnClickListener {
            //show auth dialog
            biometricPrompt.authenticate(promptInfo)

        }
    }


}