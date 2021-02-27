package com.testapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.testapplication.R;
import com.testapplication.model.BookModel;
import com.testapplication.model.BookResult;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {
    private Context context;
    ArrayList<BookResult> books;
    private  ItemClickListner itemClickListner;


    public BookListAdapter(Context context, ArrayList<BookResult> books) {
        this.context = context;
        this.books = books;

    }


    @NonNull
    @Override
    public BookListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dummy_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.MyViewHolder holder, int position) {

        holder.textViewBookName.setText(this.books.get(position).getBookName());
        holder.textViewBookDesc.setText(this.books.get(position).getBookDesc());
        holder.textViewAuthor.setText(this.books.get(position).getBookAuthor());
        holder.textViewPrice.setText(this.books.get(position).getBookPrice());
        String imageUrl = this.books.get(position).getBookImgUrl();
        Log.e("imageUrl123","imageUrl123 "+imageUrl);

        Picasso.get().load(imageUrl).into(holder.imageView);

    }





    @Override
    public int getItemCount() {

        if (this.books != null)
            return this.books.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewBookName, textViewBookDesc,textViewAuthor,textViewPrice;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewBookName = itemView.findViewById(R.id.textViewBookName);
            textViewBookDesc = itemView.findViewById(R.id.textViewBookDesc);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    public interface ItemClickListner{
        public void onProfileClick(BookModel bookModel);
    }
}
