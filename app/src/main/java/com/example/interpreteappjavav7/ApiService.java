package com.example.interpreteappjavav7;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("predict")
    Call<AddCustomerRes>addCustomer(@Part MultipartBody.Part file,
                                    @Part("n_top")RequestBody n_top);
}
