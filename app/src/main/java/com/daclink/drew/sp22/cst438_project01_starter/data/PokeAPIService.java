package com.daclink.drew.sp22.cst438_project01_starter.data;

import com.daclink.drew.sp22.cst438_project01_starter.model.PokeDex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeAPIService {

    @GET("pokemon")
    Call<PokeDex> getPokemonList(@Query("limit") int limit,@Query("offset") int offset);
}
