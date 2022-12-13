package pham.hien.thi_13_ph18604.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.R
import pham.hien.thi_13_ph18604.Utils.moneyFormatter

class DetailDialog(
    context: Context,
    model: Model,
) :
    Dialog(context),
    View.OnClickListener {

    private lateinit var tv_name: TextView
    private lateinit var tv_price: TextView
    private lateinit var tv_ram: TextView
    private lateinit var tv_hhd: TextView
    private lateinit var tv_cpu: TextView
    private lateinit var imvClose: ImageView
    private lateinit var imv_laptop: ImageView
    private val mContext = context
    private val mModel = model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_detail)
        window!!.decorView.setBackgroundResource(R.color.transparent)
        val wlp = window!!.attributes
        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window!!.attributes = wlp
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setCancelable(true)
        initView()
    }

    private fun initView() {
        tv_name = findViewById(R.id.tv_name)
        tv_price = findViewById(R.id.tv_price)
        tv_ram = findViewById(R.id.tv_ram)
        tv_hhd = findViewById(R.id.tv_hhd)
        imvClose = findViewById(R.id.imv_close)
        tv_cpu = findViewById(R.id.tv_cpu)
        imv_laptop = findViewById(R.id.imv_laptop)

        imvClose.setOnClickListener(this)
        tv_name.text ="Name: "+ mModel.name
        tv_price.text = "Price: " +moneyFormatter(mModel.price)
        tv_ram.text = "Ram: "+ mModel.ram
        tv_hhd.text ="HHD/SSD: "+ mModel.hdd_ssd
        tv_cpu.text ="CPU:" + mModel.cpu
        Glide.with(mContext).load(mModel.image).placeholder(R.drawable.img_default).into(imv_laptop)
    }

    override fun onClick(v: View?) {
        when (v) {
            imvClose -> {
                dismiss()
            }
        }
    }
}

