package swipe.card.recycler.view

import androidx.room.*
import com.google.gson.annotations.SerializedName

data class ResultResponse (
    @SerializedName("results") val results : List<Results>,
    @SerializedName("info") val info : Info
)

@Entity(tableName = "items")
data class Results (
    @SerializedName("gender") val gender : String,
    @Embedded(prefix = "name_")
    @SerializedName("name") val name : Name,
    @Embedded(prefix = "location_")
    @SerializedName("location") val location : Location,
    @SerializedName("email") val email : String,
    @Embedded(prefix = "login_")
    @SerializedName("login") val login : Login,
    @Embedded(prefix = "dob_")
    @SerializedName("dob") val dob : Dob,
    @Embedded(prefix = "registered_")
    @SerializedName("registered") val registered : Registered,
    @SerializedName("phone") val phone : String,
    @SerializedName("cell") val cell : String,
    @Embedded(prefix = "id_")
    @SerializedName("id") val id : Id,
    @Embedded(prefix = "picture_")
    @SerializedName("picture") val picture : Picture,
    @SerializedName("nat") val nat : String,
    @PrimaryKey(autoGenerate = true) val profileId: Int = 0,
    @ColumnInfo(name = "userFirstName") var firstName : String?,
    @ColumnInfo(name = "userLastName") var lastName : String?,
    @ColumnInfo(name = "userAge") var age : Int,
    @ColumnInfo(name = "userCity") var city : String?,
    @ColumnInfo(name = "userStatus") val status : String?,
    @Ignore
    @ColumnInfo(name = "userId") var uuid : String
){
    constructor(gender: String, name: Name, location: Location, email: String, login: Login, dob: Dob, registered: Registered, phone: String, cell: String, id: Id, picture: Picture, nat: String, profileId: Int, firstName: String?, lastName: String?, age: Int, city: String?, status: String?) :
        this(gender, name, location, email, login, dob, registered, phone, cell, id, picture, nat, profileId, firstName, lastName, age, city, status, "") {
    }
}

data class Coordinates (
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)

data class Dob (
    @SerializedName("date") val date : String,
    @SerializedName("age") val age : Int
)

data class Id (
    @SerializedName("name") val name : String,
    @SerializedName("value") val value : String
)

data class Info (
    @SerializedName("seed") val seed : String,
    @SerializedName("results") val results : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("version") val version : Double
)

data class Location (
    @Embedded(prefix = "street_")
    @SerializedName("street") val street : Street,
    @SerializedName("city") val city : String,
    @SerializedName("state") val state : String,
    @SerializedName("country") val country : String,
    @SerializedName("postcode") val postcode : Int,
    @Embedded(prefix = "coordinates_")
    @SerializedName("coordinates") val coordinates : Coordinates,
    @Embedded(prefix = "timezone_")
    @SerializedName("timezone") val timezone : Timezone
)

data class Login (
    @SerializedName("uuid") val uuid : String,
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String,
    @SerializedName("salt") val salt : String,
    @SerializedName("md5") val md5 : String,
    @SerializedName("sha1") val sha1 : String,
    @SerializedName("sha256") val sha256 : String
)

data class Name (
    @SerializedName("title") val title : String,
    @SerializedName("first") val first : String,
    @SerializedName("last") val last : String
)

data class Picture (
    @SerializedName("large") val large : String,
    @SerializedName("medium") val medium : String,
    @SerializedName("thumbnail") val thumbnail : String
)

data class Registered (
    @SerializedName("date") val date : String,
    @SerializedName("age") val age : Int
)

data class Street (
    @SerializedName("number") val number : Int,
    @SerializedName("name") val name : String
)

data class Timezone (
    @SerializedName("offset") val offset : String,
    @SerializedName("description") val description : String
)