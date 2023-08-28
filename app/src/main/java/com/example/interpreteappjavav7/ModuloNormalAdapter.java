package com.example.interpreteappjavav7;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ModuloNormalAdapter extends FirebaseRecyclerAdapter<ModuloNormalModel,ModuloNormalAdapter.myViewHolder> {

    public ModuloNormalAdapter(@NonNull FirebaseRecyclerOptions<ModuloNormalModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModuloNormalModel model) {
        holder.tituloN.setText(model.getTituloNormal());
        holder.descN.setText(model.getDescripcionNormal());
        holder.autorN.setText(model.getUsuario());


        Glide.with(holder.imageView.getContext())
                .load(model.getImgSubir())
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imageView);

        //setOnClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),ModuloNormalVerItemActivity.class);
                //enviamos los datos a la nueva activity desde el modelo de la activity
                intent.putExtra("TituloNormal",model.getTituloNormal());
                intent.putExtra("DescripcionNormal",model.getDescripcionNormal());
                intent.putExtra("TituloSordo",model.getTituloSordo());
                intent.putExtra("DescripcionSordo",model.getDescripcionSordo());
                intent.putExtra("Usuario",model.getUsuario());
                intent.putExtra("Fecha",model.getFecha());
                intent.putExtra("Hora",model.getHora());
                intent.putExtra("Categoria",model.getCategoria());
                intent.putExtra("ImgSubir",model.getImgSubir());
                holder.imageView.getContext().startActivity(intent);


            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modulo_normal_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tituloN, descN, autorN;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgid);
            tituloN = itemView.findViewById(R.id.tituloNid);
            descN = itemView.findViewById(R.id.descNid);
            autorN = itemView.findViewById(R.id.autorNid);
        }
    }
}
