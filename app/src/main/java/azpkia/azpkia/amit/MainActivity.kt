package azpkia.azpkia.amit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       signup_go.setOnClickListener{
           startActivity(Intent(this, login::class.java))
       }
        signup_button.setOnClickListener {
            var auth=FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(signup_email.text.toString(),signup_pass.text.toString()).addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "welcom: "+signup_email.text.toString(),Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, homeactivity::class.java))
                }



                    else
                    Toast.makeText(this, task.exception?.message,Toast.LENGTH_LONG).show()
            }

        }

    }
}
