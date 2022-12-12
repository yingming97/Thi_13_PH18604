package pham.hien.thi_13_ph18604.Utils

import java.text.DecimalFormat

fun moneyFormatter(money: Int): String {
    val formatter = DecimalFormat("###,###,###,###,###")

    return "${formatter.format(money)} đ"

}