
import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("age")
    var age: Int,
    @SerializedName("country")
    var country: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("favorite")
    var favorite: FavoriteX,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("profession")
    var profession: String
)