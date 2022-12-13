package pham.hien.thi_13_ph18604.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Model : Serializable{

    @SerializedName("createdAt")
    @Expose
    var createdAt: Date? = Date()

    @SerializedName("name")
    @Expose
    var name: String = ""

    @SerializedName("cpu")
    @Expose
    var cpu: String = ""

    @SerializedName("ram")
    @Expose
    var ram: String = ""

    @SerializedName("hdd_ssd")
    @Expose
    var hdd_ssd: String = ""

    @SerializedName("price")
    @Expose
    var price: Int = 0

    @SerializedName("image")
    @Expose
    var image: String =
        "https://firebasestorage.googleapis.com/v0/b/networkinglab18604.appspot.com/o/img_default.png?alt=media&token=866f8d93-b35f-4e16-bb75-65792db6d773"

    @SerializedName("id")
    @Expose
    var id: String? = ""

    constructor(
        name: String,
        cpu: String,
        ram: String,
        hdd_ssd: String,
        price: Int,
        image: String,
    ) {
        this.name = name
        this.cpu = cpu
        this.ram = ram
        this.hdd_ssd = hdd_ssd
        this.price = price
        this.image = image
    }

    override fun toString(): String {
        return "Model(createdAt=$createdAt, name='$name', cpu='$cpu', ram='$ram', hdd_ssd='$hdd_ssd', price=$price, image='$image', id=$id)"
    }

}