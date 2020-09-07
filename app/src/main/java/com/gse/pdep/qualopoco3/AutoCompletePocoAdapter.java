package com.gse.pdep.qualopoco3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AutoCompletePocoAdapter extends ArrayAdapter<String> {
    private List<String> listaPocosFull;

    public AutoCompletePocoAdapter(@NonNull Context context, @NonNull ArrayList<String> listaPocos) {
        super(context, 0, listaPocos);
        listaPocosFull = new ArrayList<String>(listaPocos);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return pocosFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.poco_autocomplete_row, parent, false);
        }

        TextView textViewPoco = convertView.findViewById(R.id.text_view_poco);

        String poco = getItem(position);

        if (poco != null){
            textViewPoco.setText(poco);
        }
        return convertView;
    }

    private Filter pocosFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            ArrayList<String> suggestions = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                suggestions.addAll(listaPocosFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (String poco : listaPocosFull) {
                    if (poco.toLowerCase().contains(filterPattern)) {
                        suggestions.add(poco);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            clear();
            addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((String) resultValue);
        }
    };
}
