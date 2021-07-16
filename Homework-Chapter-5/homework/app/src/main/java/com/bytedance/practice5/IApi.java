package com.bytedance.practice5;


import com.bytedance.practice5.model.Message;
import com.bytedance.practice5.model.UploadResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IApi {

    //TODO 4
    // 补全所有注解
    @Multipart
    @POST("messages")
    Call<UploadResponse> submitMessage(@Query("student_id") String studentId,
                                      @Query("extra_value") String extraValue,
                                      @Body MultipartBody.Part from,
                                      @Body MultipartBody.Part to,
                                      @Body MultipartBody.Part content,
                                      @Body MultipartBody.Part image,
                                      @Header("token")String token);
}
