package com.tecsup.integrador.api;

import com.tecsup.integrador.model.EmpresaApi;
import com.tecsup.integrador.model.UserApi;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import java.util.List;
public interface ApiService {


    String API_BASE_URL = "https://empresas-app-mrpapita.c9users.io/";

    @GET("api/v1/loginUsers")
    Call<List<UserApi>> getUsers();

    @GET("api/v1/empresas")
    Call<List<EmpresaApi>> getProductos();

    @FormUrlEncoded
    @POST("api/v1/login")
    Call<UserApi> loginUser
            (@Field("username")String username,
             @Field("password") String password
            );
    @FormUrlEncoded
    @POST("api/v1/register")
    Call<ResponseMessage> createUsuario(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("email") String email);






}