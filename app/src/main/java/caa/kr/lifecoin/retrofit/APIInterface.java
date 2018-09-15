package caa.kr.lifecoin.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("blocks")
    Call<List<BlockResource>> getBlocks();
}