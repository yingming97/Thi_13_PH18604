package pham.hien.thi_13_ph18604.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.R
import pham.hien.thi_13_ph18604.Utils.ImagesUtils
import pham.hien.thi_13_ph18604.Utils.gone
import pham.hien.thi_13_ph18604.Utils.visible
import pham.hien.thi_13_ph18604.ViewModel.RetrofitViewModel
import pham.hien.thi_13_ph18604.databinding.ActivityEditModelBinding

class EditModelActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditModelBinding

    private lateinit var viewModel: RetrofitViewModel
    private lateinit var mModel: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mModel = intent.getSerializableExtra("model") as Model
        viewModel = ViewModelProvider(this)[RetrofitViewModel::class.java]

        binding.imvChange.setOnClickListener(this)
        binding.imvLaptop.setOnClickListener(this)
        binding.btnAddNew.setOnClickListener(this)

        initData()
    }

    private fun initData() {

        Glide.with(this)
            .load(mModel.image)
            .placeholder(R.drawable.img_default)
            .into(binding.imvLaptop)
        binding.edName.setText(mModel.name)
        binding.edCpu.setText(mModel.cpu)
        binding.edHhdSsd.setText(mModel.hdd_ssd)
        binding.edRam.setText(mModel.ram)
        binding.edPrice.setText(mModel.price.toString())

    }

    override fun onClick(v: View) {
        when (v) {
            binding.imvLaptop, binding.imvChange -> {
                ImagesUtils().checkPermissionChonAnh(this, binding.imvLaptop)
            }
            binding.btnAddNew -> {
                binding.pgLoading.visible()
                validate {
                    if (it) {
                        ImagesUtils().uploadImage(binding.imvLaptop,
                            "Laptop", mModel.id!!) { img ->
                            val model =
                                Model(binding.edName.text.toString(),
                                    binding.edCpu.text.toString(),
                                    binding.edRam.text.toString(),
                                    binding.edHhdSsd.text.toString(),
                                    binding.edPrice.text.toString().toInt(),
                                    img
                                )
                            viewModel.add(model) {
                                Toast.makeText(this, "Update th??nh c??ng", Toast.LENGTH_LONG).show()
                                binding.pgLoading.gone()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validate(checkDone: (Boolean) -> Unit) {
        var strError = ""
        if (binding.edName.text.toString().trim().isEmpty()) {
            strError += "Ch??a nh???p t??n"
        } else if (binding.edCpu.text.toString().trim().isEmpty()) {
            strError += "Nh???p CPU"
        } else if (binding.edRam.text.toString().trim().isEmpty()) {
            strError += "Nh???p Ram"
        } else if (binding.edHhdSsd.text.toString().trim().isEmpty()) {
            strError += "Nh???p lo???i"
        } else if (binding.edPrice.text.toString().toInt() <= 0) {
            strError += "Gi?? l???n h??n 0"
        }
        if (strError.isEmpty()) {
            checkDone(true)
        } else {
            checkDone(false)
            Toast.makeText(this, strError, Toast.LENGTH_LONG).show()
        }
    }
}