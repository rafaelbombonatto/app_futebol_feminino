package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;


public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<List<News>>();

        //TODO Remover MOCK
        List<News> news = new ArrayList();
        news.add(new News("Baleia baleia Baleia", "Baleia compra arma e atira em companheira, que a acusou de 'baleiofobia'"));
        news.add(new News("Atenção", "Obrigado pela atenção"));
        news.add(new News("Carro descontrolado perde o controle", "BOOm CRASH POIM"));

        this.news.setValue(news);
    }
    public LiveData<List<News>> getNews() {
        return news;
    }
}