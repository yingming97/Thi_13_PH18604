package pham.hien.thi_13_ph18604.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pham.hien.thi_13_ph18604.Adapter.ModelAdapter
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.ViewModel.MainViewModel
import pham.hien.thi_13_ph18604.ViewModel.RetrofitViewModel
import pham.hien.thi_13_ph18604.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "YingMing"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RetrofitViewModel

    private lateinit var mModelAdapter: ModelAdapter
    private var mListModel = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[RetrofitViewModel::class.java]

        initListener()
        initObserve()
        initData()
        initRecycleView()
    }

    private fun initObserve() {
        viewModel.mList.observe(this) {
            mModelAdapter.setList(it)
            binding.tvTitle.text = "Tổng bản ghi: ${it.size}"
            Log.d(TAG, "initObserve: ")
        }
    }

    private fun initData() {
        viewModel.getList()
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
        mModelAdapter = ModelAdapter(this) {
            viewModel.delete(it)
            viewModel.getList()
        }
        binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.setHasFixedSize(false)
        binding.rcv.isNestedScrollingEnabled = false
        binding.rcv.adapter = mModelAdapter
        mModelAdapter.setList(mListModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }
}