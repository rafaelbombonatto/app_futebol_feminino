package com.example.soccernews.ui.news;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.soccernews.data.local.AppDatabase;
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

    public enum State {
        DOING,
        DONE,
        ERROR
    }

    private final MutableLiveData<List<News>> news= new MutableLiveData<>();
    private final MutableLiveData<State> state= new MutableLiveData<>();
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
        state.setValue(State.DOING);
        service.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else
                {
                    state.setValue(State.ERROR);
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
    public LiveData<State> getState() {
        return state;
    }
}