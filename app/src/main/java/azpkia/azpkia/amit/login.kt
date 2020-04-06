package azpkia.azpkia.amit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

textView.setOnClickListener {
    startActivity(Intent(this,MainActivity::class.java))
}
        login_button.setOnClickListener {
            var auth=FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(login_email.text.toString(),login_pass.text.toString()).addOnCompleteListener {
                task ->
                if (task.isSuccessful)
                    startActivity(Intent(this,homeactivity::class.java))
                else
                    Toast.makeText(this, task.exception?.message,Toast.LENGTH_LONG).show()
            }

        }


    }
}
