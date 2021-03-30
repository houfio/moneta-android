data class Listing (
    val status : Status,
    val data : List<DataSet>
        )

data class Status (
    val timestamp : String,
    val error_code : Int,
    val error_message : String?,
    val elapsed : Int,
    val credit_count : Int,
    val notice : String?,
    val total_count : Int
        )

data class DataSet (
    val id : Int,
    val name : String,
    val symbol : String,
    val slug : String,
    val num_market_pairs : Int,
    val date_added : String,
    val tags : List<List<Int>>?,
    val max_supply : Int?,
    val circulating_supply : Int,
    val total_supply : Int,
    val platform : List<String>?,
    val cmc_rank : Int,
    val last_updated : String,
    val quote : List<String>?
        )
