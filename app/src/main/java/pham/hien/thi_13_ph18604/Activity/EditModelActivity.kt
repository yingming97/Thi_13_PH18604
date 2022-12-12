package pham.hien.thi_13_ph18604.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pham.hien.thi_13_ph18604.databinding.ActivityEditModelBinding

class EditModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditModelBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}