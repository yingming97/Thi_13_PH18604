package pham.hien.thi_13_ph18604.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pham.hien.thi_13_ph18604.Adapter.ModelAdapter
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.ViewModel.MainViewModel
import pham.hien.thi_13_ph18604.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var mModelAdapter: ModelAdapter
    private var mListModel = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initListener()
        initData()
        initObserve()
        initRecycleView()
    }

    private fun initObserve() {

    }

    private fun initData() {

    }

    private fun initListener() {
        binding.imvAdd.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            binding.imvAdd -> {
                startActivity(Intent(this, AddModelActivity::class.java))
            }
        }
    }

    private fun initRecycleView() {
        mModelAdapter = ModelAdapter(this)
        binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.setHasFixedSize(false)
        binding.rcv.isNestedScrollingEnabled = false
        binding.rcv.adapter = mModelAdapter
        mModelAdapter.setList(mListModel)
    }
}