package pham.hien.thi_13_ph18604.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pham.hien.thi_13_ph18604.databinding.ActivityAddModelBinding

class AddModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddModelBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}