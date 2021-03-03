package com.example.prans.news.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.prans.news.R;
import com.example.prans.news.activity.WebViewActivity;
import com.example.prans.news.adapter.NewsAdapter;
import com.example.prans.news.adapter.RecyclerTouchListener;
import com.example.prans.news.model.News;
import com.example.prans.news.model.NewsResource;
import com.example.prans.news.util.ApiClient;
import com.example.prans.news.util.ApiResponse;
import com.example.prans.news.util.Internet;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.prans.news.model.Contants.API_KEY;
import static com.example.prans.news.model.Contants.CATEGORY_ENTERTAINMENT;
import static com.example.prans.news.model.Contants.COUNTRY;

public class EntertainmentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<News> newsArrayList = new ArrayList<>();
    private NewsAdapter mAdapter;
    private RecyclerView recyclerView;
    private RelativeLayout errorLayout;

    public EntertainmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initViews();

        return view;
    }

    private void initViews() {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorGreen,
                R.color.colorBlue,
                R.color.colorOrange);
        loadJSON();
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                News news = newsArrayList.get(position);
                Intent title_Intent = new Intent(getActivity(), WebViewActivity.class);
                title_Intent.putExtra("url", news.getUrl());
                startActivity(title_Intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void loadJSON() {
        swipeRefreshLayout.setRefreshing(true);
        errorLayout = view.findViewById(R.id.errorLayout);

        if (Internet.checkConnection(getContext())) {
            ApiResponse request = ApiClient.getApiService();

            if (errorLayout.getVisibility() == View.VISIBLE){
                errorLayout.setVisibility(View.GONE);
            }
            Call<NewsResource> call = request.getCategoryOfHeadlines(COUNTRY, CATEGORY_ENTERTAINMENT, API_KEY);
            call.enqueue(new Callback<NewsResource>() {

                @Override
                public void onResponse(Call<NewsResource> call, Response<NewsResource> response) {

                    if (response.isSuccessful() && response.body().getArticles() != null) {
                        swipeRefreshLayout.setRefreshing(false);
                        if (!newsArrayList.isEmpty()) {
                            newsArrayList.clear();
                        }

                        newsArrayList = response.body().getArticles();
                        mAdapter = new NewsAdapter(newsArrayList);
                        recyclerView.setAdapter(mAdapter);
                    }
                }

                @Override
                public void onFailure(Call<NewsResource> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            showErrorMessage();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadJSON();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void showErrorMessage(){
        if (errorLayout.getVisibility() == View.GONE){
            errorLayout.setVisibility(View.VISIBLE);
        }
    }

}