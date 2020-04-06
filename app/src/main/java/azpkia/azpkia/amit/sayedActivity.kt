package azpkia.azpkia.amit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sayed.*

class sayedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sayed)

        web_bt.setOnClickListener{
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.90way.com/"))
            startActivity(intent)
        }

        call_bt.setOnClickListener{
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("tel:$01062255778"))
            startActivity(intent)

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.list_menu)
        {
            startActivity(Intent(this,findActivity::class.java))

        }
        if (item.itemId==R.id.signout_menu){
            var auth = FirebaseAuth.getInstance()

            auth.signOut()
            Toast.makeText(this,"sign out successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, login::class.java))

        }
        if (item.itemId==R.id.me_menu)
        {
            startActivity(Intent(this,sayedActivity::class.java))
        }
        if (item.itemId==R.id.add_menu){
            startActivity(Intent(this,homeactivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
