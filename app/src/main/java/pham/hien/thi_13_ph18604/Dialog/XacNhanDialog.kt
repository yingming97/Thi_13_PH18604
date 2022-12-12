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
import pham.hien.thi_13_ph18604.R

class XacNhanDialog(
    context: Context,
    private val linkAnh: String,
    private val title: String,
    private val content: String, private val dongY: (() -> Unit)
) :
    Dialog(context),
    View.OnClickListener {

    private lateinit var tvTitle: TextView
    private lateinit var tvContent: TextView
    private lateinit var tvXoa: TextView
    private lateinit var tvHuy: TextView
    private lateinit var imvClose: ImageView
    private lateinit var imv_book: ImageView
    private val mContext = context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_xac_nhan)
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
        tvTitle = findViewById(R.id.tv_title)
        tvContent = findViewById(R.id.tv_content)
        tvXoa = findViewById(R.id.tv_xoa)
        tvHuy = findViewById(R.id.tv_huy)
        imvClose = findViewById(R.id.imv_close)
        imv_book = findViewById(R.id.imv_book)

        imvClose.setOnClickListener(this)
        tvXoa.setOnClickListener(this)
        tvHuy.setOnClickListener(this)

        tvTitle.text = title
        tvContent.text = content
        Glide.with(mContext).load(linkAnh).placeholder(R.drawable.img_default).into(imv_book)
    }

    override fun onClick(v: View?) {
        when (v) {
            imvClose, tvHuy -> {
                dismiss()
            }
            tvXoa -> {
                dongY()
                dismiss()
            }
        }
    }
}

