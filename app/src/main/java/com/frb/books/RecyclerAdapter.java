package com.frb.books;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.frb.books.classes.Book;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private List<Book> bookList = new ArrayList<>();

    public RecyclerAdapter(List<Book> bookList)
    {
        this.bookList = bookList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleText.setText("Title:   "+bookList.get(position).getTitle());
        holder.authorText.setText("Author:   "+bookList.get(position).getAuthor());
        holder.publisherText.setText("Publisher:   "+bookList.get(position).getPublisher());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
    public void addInList(Book book)
    {
        bookList.add(book);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public TextView titleText;
        public TextView authorText;
        public TextView publisherText;
        public Button updateButton;
        public Button deleteButton;


        public ViewHolder(View itemView)
        {
            super(itemView);
            titleText =(TextView)itemView.findViewById(R.id.title_id);
            authorText =(TextView)itemView.findViewById(R.id.author_id);
            publisherText =(TextView)itemView.findViewById(R.id.publisher_id);

        }
    }
}
