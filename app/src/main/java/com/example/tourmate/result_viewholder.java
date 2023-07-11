package com.example.tourmate;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class result_viewholder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView t1, t2;
    public result_viewholder(@NonNull View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img1);
        t1 = (TextView) itemView.findViewById(R.id.tv1);
        t2 = (TextView) itemView.findViewById(R.id.tv2);

    }
}
