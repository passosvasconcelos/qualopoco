package com.gse.pdep.qualopoco3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class ListaCaixasLabAdapter extends RecyclerView.Adapter<ListaCaixasLabAdapter.ListaCaixasLabAdapterViewHolder> implements Filterable {
    private ArrayList<String[]> listaCaixasLab_db;
    private ArrayList<String[]> listaCaixasLab_db_full;


    public ListaCaixasLabAdapter(ArrayList<String[]> listaCaixasLab_db) {
        listaCaixasLab_db = listaCaixasLab_db;
        listaCaixasLab_db_full = new ArrayList<String[]> (listaCaixasLab_db);
    }



    class ListaCaixasLabAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView poco_TextView;
        TextView tipoAmostra_TextView;
        TextView detalheTestemunho_TextView;
        TextView caixa_TextView;
        TextView data_TextView;
        TextView hora_TextView;
        TextView laboratorio_TextView;
        TextView localizacao_TextView;


        public ListaCaixasLabAdapterViewHolder(View itemView) {
            super(itemView);

            poco_TextView = (TextView) itemView.findViewById(R.id.tv_poco);
            tipoAmostra_TextView = (TextView) itemView.findViewById(R.id.tv_tipoAmostra);
            detalheTestemunho_TextView = (TextView) itemView.findViewById(R.id.tv_detalheTestemunho);
            caixa_TextView = (TextView) itemView.findViewById(R.id.tv_caixa);
            data_TextView = (TextView) itemView.findViewById(R.id.tv_data);
            hora_TextView = (TextView) itemView.findViewById(R.id.tv_hora);
            laboratorio_TextView = (TextView) itemView.findViewById(R.id.tv_laboratorio);
            localizacao_TextView = (TextView) itemView.findViewById(R.id.tv_localizacao);
        }


    }

    @NonNull
    @Override
    public ListaCaixasLabAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.listacaixaslab_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ListaCaixasLabAdapterViewHolder viewHolder = new ListaCaixasLabAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListaCaixasLabAdapterViewHolder holder, int position) {
        String[] item = listaCaixasLab_db.get(position);
        holder.poco_TextView.setText(item[0]);
        holder.tipoAmostra_TextView.setText(item[1]);
        holder.detalheTestemunho_TextView.setText(item[2]);
        holder.caixa_TextView.setText("Cx:"+item[3]);
        holder.data_TextView.setText(item[4]);
        holder.hora_TextView.setText(item[5]);
        holder.localizacao_TextView.setText(item[6]);
    }

    @Override
    public int getItemCount() {
        if (null == listaCaixasLab_db) return 0;
        return listaCaixasLab_db.size();
    }

    public void setListaCaixasLab_db(ArrayList<String[]> listaCaixasLab) {
        listaCaixasLab_db = listaCaixasLab;

    }

    public ArrayList<String[]> getListaCaixasLab_db() {
        return listaCaixasLab_db;
    }

    @Override
    public Filter getFilter() {
        return listaCaixasLab_db_filter;
    }
    private Filter listaCaixasLab_db_filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String[]> filteredList = new ArrayList<String[]>();
            if (constraint == null || constraint.length() == 0){

                filteredList.addAll(listaCaixasLab_db_full);


            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String[] listItem : listaCaixasLab_db_full){
                    if (listItem[0].toLowerCase().contains(filterPattern)){
                        filteredList.add(listItem);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraints, FilterResults results) {
            listaCaixasLab_db.clear();
            listaCaixasLab_db.addAll((ArrayList<String[]>) results.values);
            notifyDataSetChanged();
        }
    };

}