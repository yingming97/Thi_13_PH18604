package pham.hien.thi_13_ph18604.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pham.hien.thi_13_ph18604.Dialog.XacNhanDialog
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.R
import java.util.*

class ModelAdapter(val context: Context) :
    RecyclerView.Adapter<ModelAdapter.ViewHolder>() {

    private lateinit var mListMoto: List<Model>

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Model>) {
        mListMoto = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moto = mListMoto[position]
//        holder.tv_name.text = moto.name
//        holder.tv_gia.text = "Giá : ${moneyFormatter(moto.price)}"
//        holder.tv_color.text = "Màu : ${moto.color}"
//        Glide.with(context)
//            .load(moto.image)
//            .placeholder(R.drawable.img_default)
//            .into(holder.imv)

    }

    override fun getItemCount(): Int {
        return mListMoto.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imv: ImageView
        val tv_name: TextView
        val tv_gia: TextView
        val tv_color: TextView
        val tv_create_at: TextView
        val item_moto: RelativeLayout

        init {
            imv = itemView.findViewById(R.id.imv)
            tv_name = itemView.findViewById(R.id.tv_name)
            tv_gia = itemView.findViewById(R.id.tv_gia)
            tv_create_at = itemView.findViewById(R.id.tv_create_at)
            tv_color = itemView.findViewById(R.id.tv_color)
            item_moto = itemView.findViewById(R.id.item_moto)
        }
    }
    private fun showPopupMenu(button: View, m: Model) {
        val popupWindow: PopupWindow
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView: View = inflater.inflate(R.layout.layout_menu, null)
        val tvChiTiet = rootView.findViewById(R.id.tv_chi_tiet) as TextView
        val tvEdit = rootView.findViewById(R.id.tv_edit) as TextView
        val tvDelete = rootView.findViewById(R.id.tv_delete) as TextView
        popupWindow = PopupWindow(
            rootView, 300, RelativeLayout.LayoutParams.WRAP_CONTENT, true)
        popupWindow.showAsDropDown(button)
        tvChiTiet.setOnClickListener {
//            val intent = Intent(context, BookDetailActivity::class.java)
//            intent.putExtra("book", b)
//            context.startActivity(intent)
            popupWindow.dismiss()
        }
        tvEdit.setOnClickListener {
//            val intent = Intent(context, BookEditActivity::class.java)
//            intent.putExtra("book", b)
//            context.startActivity(intent)
            popupWindow.dismiss()
        }
        tvDelete.setOnClickListener {
//            XacNhanDialog(context, m.linkAnh,
//                "Bạn muốn xóa \"${b.tenSach}\"",
//                "Dữ liệu đã xóa không thể khôi phục",
//                dongY = {
//                    deleteBook(b)
//                    popupWindow.dismiss()
//                },
//                huy = {
//                    popupWindow.dismiss()
//                }).show()
        }


    }
}