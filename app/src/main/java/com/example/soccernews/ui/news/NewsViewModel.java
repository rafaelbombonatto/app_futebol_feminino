package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.remote.SoccerNewsApi;
import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news= new MutableLiveData<List<News>>();
    private final SoccerNewsApi service;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rafaelbombonatto.github.io/app_futebol_feminino_api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SoccerNewsApi.class);
        this.findNews();

    }

    private void findNews() {
        service.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                } else
                {
                    //TODO tratamento de erros
                }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
//TODO tratamento de erros
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}