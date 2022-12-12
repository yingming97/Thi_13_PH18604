package pham.hien.thi_13_ph18604.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pham.hien.thi_13_ph18604.databinding.ActivityDetailModelBinding

class DetailModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}