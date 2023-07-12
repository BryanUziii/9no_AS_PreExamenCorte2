package com.example.a9no_as_preexamencorte2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class usuarioAdapter extends RecyclerView.Adapter<usuarioAdapter.UsuarioViewHolder>{
    private List<Usuario> listaUsuarios;
    private Context context;

    public usuarioAdapter(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alumno, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.bind(usuario);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private TextView textViewCorreo;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            //
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewCorreo = itemView.findViewById(R.id.textViewCorreo);
        }

        public void bind(Usuario alumno) {

            textViewNombre.setText(alumno.getUserName());
            textViewCorreo.setText(alumno.getCorreo());

        }
    }
}
