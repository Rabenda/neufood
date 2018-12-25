package io.github.rabenda.neufood.server


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.rabenda.neufood.bean.*
import java.net.URL

object Server {
    val BaseUrl = "http://172.24.10.175:8080/foodService/"
    fun login(username: String, userpass: String) = Gson().fromJson(
            URL(BaseUrl + "userLogin.do?username=${username}&userpass=${userpass}").readText(),
            LoginBean::class.java
    )

    fun register(username: String, userpass: String, mobilenum: String, addrmess: String, comment: String) =
            Gson().fromJson(
                    URL(BaseUrl + "userRegister.do?username=${username}&userpass=${userpass}&mobilenum=${mobilenum}&addrmess=${addrmess}&comment=${comment}").readText(),
                    RegisterBean::class.java
            )

    fun getAllShops() = Gson().fromJson<List<StoreBean>>(
            URL(BaseUrl + "getAllShops.do").readText(),
            object : TypeToken<List<StoreBean>>() {}.type
    )

    fun getFoodByShop(shop_id: String) = Gson().fromJson<List<FoodBean>>(
            URL(BaseUrl + "getFoodByShop.do?shop_id=${shop_id}").readText(),
            object : TypeToken<List<FoodBean>>() {}.type
    )

    fun collectShop(user_id: String, shop_id: String) = Gson().fromJson(
            URL(BaseUrl + "userCollectShop.do?user_id=${user_id}&shop_id=$shop_id").readText(),
            RegisterBean::class.java
    )

    fun collectFood(user_id: String, food_id: String) = Gson().fromJson(
            URL(BaseUrl + "userCollectShop.do?user_id=${user_id}&food_id=$food_id").readText(),
            RegisterBean::class.java
    )

    fun isCollected(user_id: String, shop_food_id: String, flag: String) = Gson().fromJson(
            URL(BaseUrl + "isCollected.do?user_id=${user_id}&shop_food_id=${shop_food_id}&flag=${flag}").readText(),
            CollectedBean::class.java
    )

    fun getAllUsrCollection(user_id: String, flag: String) =
            Gson().fromJson<List<CollectListBean>>(
                    URL(BaseUrl + "getAllUserCollection.do?user_id=${user_id}&flag=${flag}").readText(),
                    object : TypeToken<List<CollectListBean>>() {}.type
            )

    fun getAllCommentsByFood(food_id: String) = Gson().fromJson<List<CommentBean>>(
            URL(BaseUrl + "getAllCommentsByFood.do?food_id=${food_id}").readText(),
            object : TypeToken<List<CommentBean>>() {}.type
    )

    fun getFoodById(food_id: String) =
            Gson().fromJson(URL(BaseUrl + "getFoodById.do?food_id=${food_id}").readText(), FoodBean::class.java)

    fun insertOrder(user_id: String, food_id: String, num: Int, sum: Double, address: String) =
            Gson().fromJson(
                    URL(BaseUrl + "insertOrder.do?user_id=${user_id}&food_id=${food_id}&num=${num}&sum=${sum}&suggesttime=now&address=${address}").readText(),
                    RegisterBean::class.java
            )

    fun search(content: String) =
            Gson().fromJson<List<FoodBean>>(
                    URL(BaseUrl + "getFoodBySearch.do?search=$content").readText(),
                    object : TypeToken<List<FoodBean>>() {}.type
            )

    fun addCart(user_id: String, food_id: String, num: Int) = Gson().fromJson(
            URL(BaseUrl + "addCart.do?user_id=$user_id&food_id=$food_id&num=$num").readText(),
            RegisterBean::class.java
    )

    fun insertComment(item_id: String, content: String) = Gson().fromJson(
            URL(BaseUrl + "insertComment.do?item_id=$item_id&content=$content").readText(),
            RegisterBean::class.java
    )

    fun updateComment(item_id: String, content: String) = Gson().fromJson(
            URL(BaseUrl + "updateComment.do?item_id=$item_id&content=$content").readText(),
            RegisterBean::class.java
    )

    fun deleteComment(item_id: String) = Gson().fromJson(
            URL(BaseUrl + "deleteComment.do?item_id=$item_id").readText(),
            RegisterBean::class.java
    )

    fun updateUserById(
            usr_id: String,
            username: String,
            userpass: String,
            mobilenum: String,
            addrmess: String
    ) =
            Gson().fromJson(
                    URL("updateUserById.do?user_id=$usr_id&username=${username}&userpass=${userpass}&mobilenum=${mobilenum}&addrmess=${addrmess}").readText(),
                    RegisterBean::class.java
            )

    fun getUserById(usr_id: String) =
            Gson().fromJson(URL(BaseUrl + "getUserById.do?user_id=$usr_id").readText(), UsrBean::class.java)

    fun getAllCommentsByUser(usr_id: String) =
            Gson().fromJson<List<CommentBean>>(
                    URL(BaseUrl + "getAllCommentsByUser.do?user_id=$usr_id").readText(),
                    object : TypeToken<List<CommentBean>>() {}.type
            )

    fun getAllOrdersByUser(usr_id: String) =
            Gson().fromJson<List<CommentBean>>(
                    URL(BaseUrl + "getAllOrdersByUser.do?user_id=$usr_id").readText(),
                    object : TypeToken<List<CommentBean>>() {}.type
            )

    fun deleteCartItem(item_id: String) =
            Gson().fromJson(URL(BaseUrl + "deleteCartItem.do?item_id=$item_id").readText(), RegisterBean::class.java)

    fun getMyCartByUser(usr_id: String) =
            Gson().fromJson<List<CarListBean>>(
                    URL(BaseUrl + "getMyCartByUser.do?user_id=$usr_id").readText(),
                    object : TypeToken<List<CarListBean>>() {}.type
            )

    fun insertOrderByCart(usr_id: String, addr: String, sum: Double, items: String) =
            Gson().fromJson(URL(BaseUrl + "insertOrder2.do?user_id=$usr_id&address=$addr&sum=$sum&suggesttime=now&items=$items").readText(), RegisterBean::class.java)
}
