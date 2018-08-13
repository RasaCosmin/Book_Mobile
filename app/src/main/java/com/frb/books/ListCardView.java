package com.frb.books;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frb.books.classes.Book;

import java.util.ArrayList;
import java.util.List;


public class ListCardView extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Book> list = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    public ListCardView()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_card_view,container,false);
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        MainActivity m = (MainActivity) getActivity();
        //list = m.getBookList()
        Book book1 = new Book("abc","defg","gggh");
        Book book2 = new Book("abc2","defg2","gggh2");
        Book book3 = new Book("abc3","defg3","gggh3");

        list.add(book1);
        list.add(book2);
        list.add(book3);

        mAdapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
