package com.gse.pdep.qualopoco3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.HistoricoAdapterViewHolder> implements Filterable {
    private ArrayList<String[]> historico_db;
    private ArrayList<String[]> historico_db_full;
    SharedPreferences filtros;



    public HistoricoAdapter(ArrayList<String[]> historico_db) {
        this.historico_db = historico_db;
        historico_db_full = new ArrayList<String[]> (historico_db);

    }


    class HistoricoAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView poco_TextView;
        TextView tipoAmostra_TextView;
        TextView detalheTestemunho_TextView;
        TextView caixa_TextView;
        TextView movimentacao_TextView;
        TextView data_TextView;
        TextView hora_TextView;
        TextView laboratorio_TextView;
        TextView localizacao_TextView;


        public HistoricoAdapterViewHolder(View itemView) {
            super(itemView);

            poco_TextView = (TextView) itemView.findViewById(R.id.tv_poco);
            tipoAmostra_TextView = (TextView) itemView.findViewById(R.id.tv_tipoAmostra);
            detalheTestemunho_TextView = (TextView) itemView.findViewById(R.id.tv_detalheTestemunho);
            caixa_TextView = (TextView) itemView.findViewById(R.id.tv_caixa);
            movimentacao_TextView = (TextView) itemView.findViewById(R.id.tv_movimentacao);
            data_TextView = (TextView) itemView.findViewById(R.id.tv_data);
            hora_TextView = (TextView) itemView.findViewById(R.id.tv_hora);
            laboratorio_TextView = (TextView) itemView.findViewById(R.id.tv_laboratorio);
            localizacao_TextView = (TextView) itemView.findViewById(R.id.tv_localizacao);
            filtros = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        }


    }

    @NonNull
    @Override
    public HistoricoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.historico_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        HistoricoAdapterViewHolder viewHolder = new HistoricoAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoricoAdapterViewHolder holder, int position) {
        String[] item = historico_db.get(position);
        holder.poco_TextView.setText(item[0]);
        holder.tipoAmostra_TextView.setText(item[1]);
        holder.detalheTestemunho_TextView.setText(item[2].substring(0,3));
        holder.caixa_TextView.setText("Cx:"+item[3]);
        holder.data_TextView.setText(item[4]);
        holder.hora_TextView.setText(item[5]);
        holder.movimentacao_TextView.setText(item[6]);
        holder.laboratorio_TextView.setText(item[7]);
        holder.localizacao_TextView.setText(item[8]);
    }

    @Override
    public int getItemCount() {
        if (null == historico_db) return 0;
        return historico_db.size();
    }
    public void setHistorico_db(ArrayList<String[]> listahistorico) {
        historico_db = listahistorico;
    }
    @Override
    public Filter getFilter() {
        return listaHistorico_db_filter;
    }
    private Filter listaHistorico_db_filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String[]> filteredList = new ArrayList<String[]>();
            String filtroPoco = new String();
            String filtroTipoAmostra = new String();
            String filtroTestemunho = new String();
            String filtroCaixa = new String();
            String filtroUsuario = new String();
            String filtroData = new String();

            //adquire valor dos filtros armazenado na sharedPreferences e armazena nas variáveis

            if (filtros!=null){
                filtroPoco = filtros.getString("filtroPoco", null);
                filtroTipoAmostra = filtros.getString("filtroTipoAmostra", null);
                filtroTestemunho = filtros.getString("filtroTestemunho", null);
                filtroCaixa = filtros.getString("filtroCaixa", null);
                filtroUsuario = filtros.getString("filtroUsuario",null);
                filtroData = filtros.getString("filtroData",null);

            }


            //se todos os filtros estiverem nulos, mostrar tudo
            System.out.println("valores antes do fitering: ");
            System.out.println(filtroPoco+" , "+filtroTipoAmostra+" , "+
                    filtroTestemunho+" , "+filtroCaixa+" , "+filtroUsuario+" ,"+filtroData);
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(historico_db_full);


            }else if(filtroPoco== null && filtroTipoAmostra == null && filtroTestemunho==null &&
                    filtroCaixa==null && filtroData==null && filtroUsuario==null){

                System.out.println("todos os filtros são nulos: "+filtroPoco+" , "+filtroTipoAmostra+" , "+filtroTestemunho+" , "+filtroCaixa+" , "+filtroUsuario+" ,"+filtroData);

                filteredList.addAll(historico_db_full);

                //se apenas poço selecionado
            }else if(filtroPoco!= null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario ==null){

                System.out.println("filtro realizado é: "+filtroPoco+" apenas, pois os demais filtros são nulos");

                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco)){
                        filteredList.add(listItem);
                    }
                }

                //se apenas tipoAmostra é selecionado
            }else if(filtroPoco== null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null && filtroData == null && filtroUsuario ==null){
                System.out.println("filtro realizado é: "+filtroTipoAmostra);


                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra)){
                        filteredList.add(listItem);
                    }
                }

                //Se apenas detalheTestemunho é selecionado
            }else if(filtroPoco== null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario ==null){

                System.out.println("filtro realizado é: "+filtroTestemunho);

                    //String filterPattern = constraint.toString().toLowerCase().trim();
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho)){
                        filteredList.add(listItem);
                    }
                }

                //Se apenas tipoCaixa for selecionado
            }else if(filtroPoco== null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){

                System.out.println("filtro realizado é: "+filtroCaixa);

                for (String[] listItem : historico_db_full){
                    if (listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }

                //Se apenas filtro usuário for selecionado
            }else if(filtroPoco== null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se apenas filtroData for selecionado
            }else if (filtroPoco== null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //poco e tipoAmostra selecionado
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario ==null){
                System.out.println("filtros realizados são: "+filtroPoco+" , "+filtroTipoAmostra);
                //String filterPattern = constraint.toString().toLowerCase().trim();

                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) ){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco e filtroTestemunho selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario ==null){
                System.out.println("filtros realizados são: "+filtroPoco+" , "+filtroTestemunho);
                //String filterPattern = constraint.toString().toLowerCase().trim();

                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) ){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco e filtroCaixa selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){
                System.out.println("filtros realizados são: "+filtroPoco+" , "+filtroCaixa);
                //String filterPattern = constraint.toString().toLowerCase().trim();

                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[3].equals(filtroCaixa) ){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco e filtroUsuario selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario != null){
                System.out.println("filtros realizados são: "+filtroPoco+" , "+filtroUsuario);
                //String filterPattern = constraint.toString().toLowerCase().trim();

                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[9].equals(filtroUsuario) ){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco e filtroData selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                System.out.println("filtros realizados são: "+filtroPoco+" , "+filtroData);
                //String filterPattern = constraint.toString().toLowerCase().trim();

                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[4].equals(filtroData) ){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra e filtroTestemunho selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho)){
                        filteredList.add(listItem);
                    }
                }
                //se filtrotipoAmostra e filtroCaixa selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra e filtroUsuario selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTestemunho e filtroCaixa selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho) && listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTestemunho e filtroUsuario selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTestemunho e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroCaixa e Usuario
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[3].equals(filtroCaixa) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroCaixa e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[3].equals(filtroCaixa) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroUsuario e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[9].equals(filtroUsuario) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTipoAmostra e filtroTestemunho selecionados
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[2].equals(filtroTestemunho)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTipoAmostra e filtroCaixa selecionados
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTipoAmostra e filtroUsuario selecionados
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTipoAmostra e filtroData selecionados
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTestemunho e filtroCaixa selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) &&
                            listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTestemunho e filtroUsuario selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) &&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTestemunho e filtroData selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) &&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroCaixa e filtroUsuario selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[3].equals(filtroCaixa) &&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroCaixa e filtroData selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[3].equals(filtroCaixa) &&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroUsuario e filtroData selecionados
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[9].equals(filtroUsuario) &&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra, filtroTestemunho e filtroCaixa selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho)&&
                            listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra, filtroTestemunho e filtroUsuario selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho)&&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra, filtroTestemunho e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho)&&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra, filtroCaixa e filtroUsuario selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[3].equals(filtroCaixa)&&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra, filtroCaixa e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[3].equals(filtroCaixa)&&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTipoAmostra, filtroUsuario e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[9].equals(filtroUsuario)&&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTestemunho, filtroCaixa e filtroUsuario selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho) &&  listItem[3].equals(filtroCaixa) &&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroTestemunho, filtroCaixa e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho) &&  listItem[3].equals(filtroCaixa) &&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroCaixa, filtroUsuario e filtroData selecionados
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[3].equals(filtroCaixa) && listItem[9].equals(filtroUsuario) &&
                            listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //se filtroPoco, filtroTipoAmostra, filtroTestemunho e filtroCaixa selecionados
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[2].equals(filtroTestemunho) && listItem[3].equals(filtroCaixa)){
                        filteredList.add(listItem);
                    }
                }
                // filtroPoco, filtroTipoAmostra, filtroTestemunho e filtroUsuário
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[2].equals(filtroTestemunho) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //filtroPoco, filtroTipoAmostra, filtroTestemunho e filtroData
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[2].equals(filtroTestemunho) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                // filtroPoco, filtroTipoAmostra, filtroCaixa e filtroUsuario
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[3].equals(filtroCaixa) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //filtroPoco, filtroTipoAmostra, filtroCaixa e filtroData
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[3].equals(filtroCaixa) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //filtroPoco, filtroTipoAmostra, filtroUsuario e filtroData
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho == null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[9].equals(filtroUsuario) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //filtroPoco, filtroTestemunho, filtroCaixa e filtroUsuario
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) &&
                            listItem[3].equals(filtroCaixa) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //filtroPoco, filtroTestemunho, filtroCaixa e filtroData
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario == null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) &&
                            listItem[3].equals(filtroCaixa) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //filtroPoco, filtroTestemunho, filtroUsuario e filtroData
            }else if(filtroPoco != null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[2].equals(filtroTestemunho) &&
                            listItem[9].equals(filtroUsuario) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //apenas tipoAmostra, Testemunho, caixa e Usuário
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho) &&
                            listItem[3].equals(filtroCaixa) && listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //apenas tipoAmostra, Testemunho, caixa e Data
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario ==null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho) &&
                            listItem[3].equals(filtroCaixa) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //apenas tipoAmostra, testemunho, usuario, data
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa == null &&
                    filtroData != null && filtroUsuario != null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho) &&
                            listItem[9].equals(filtroUsuario) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //apenas testemunho, caixa, usuario, data
            }else if(filtroPoco == null && filtroTipoAmostra == null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[2].equals(filtroTestemunho) && listItem[3].equals(filtroCaixa) &&
                            listItem[9].equals(filtroUsuario) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
                //poco, tipoAmostra, testemunho, caixa e usuário
            }else if(filtroPoco != null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData == null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[0].equals(filtroPoco) && listItem[1].equals(filtroTipoAmostra) &&
                            listItem[2].equals(filtroTestemunho) && listItem[3].equals(filtroCaixa) &&
                            listItem[9].equals(filtroUsuario)){
                        filteredList.add(listItem);
                    }
                }
                //tipoAmostra, testemunho, caixa, usuario e data selecionados
            }else if(filtroPoco == null && filtroTipoAmostra != null &&
                    filtroTestemunho != null && filtroCaixa != null &&
                    filtroData != null && filtroUsuario !=null){
                for (String[] listItem : historico_db_full){
                    if (listItem[1].equals(filtroTipoAmostra) && listItem[2].equals(filtroTestemunho) &&
                            listItem[3].equals(filtroCaixa) &&
                            listItem[9].equals(filtroUsuario) && listItem[4].equals(filtroData)){
                        filteredList.add(listItem);
                    }
                }
            }
            System.out.println("valores após fitering: ");
            System.out.println(filtroPoco+" , "+filtroTipoAmostra+" , "+
                    filtroTestemunho+" , "+filtroCaixa+" , "+filtroUsuario+" ,"+filtroData);

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraints, FilterResults results) {
            historico_db.clear();
            historico_db.addAll((ArrayList<String[]>) results.values);
            notifyDataSetChanged();
        }
    };
}