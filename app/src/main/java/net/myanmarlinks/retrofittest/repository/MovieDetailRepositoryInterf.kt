package net.myanmarlinks.retrofittest.repository

import net.myanmarlinks.retrofittest.viewmodel.DetailDataCallBack

/**
 * Created by Vincent on 2019-10-21
 */
interface MovieDetailRepositoryInterf {

    fun getDetail(movieId: Int, detailCallBack: DetailDataCallBack)

}