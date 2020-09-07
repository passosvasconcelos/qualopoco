package com.gse.pdep.qualopoco3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class GraficosFragment extends Fragment {
    private BarChart lotacao_acervo_barchart;
    private LineChart movimentacao_linechart;
    private String laboratorio;
    private LineData dados_linechart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_graficos, container, false);
        lotacao_acervo_barchart = (BarChart) view.findViewById(R.id.barchart_lotacao_acervo);
        lotacao_acervo_barchart.setMaxVisibleValueCount(40);

        movimentacao_linechart = (LineChart) view.findViewById(R.id.linechart_movimentacao);


        //cria dados para o gráfico de barras...
        ArrayList<BarEntry> barEntries_laboratorios = new ArrayList<>();
        //ArrayList<BarEntry> datesBarEntries = new ArrayList<>();

        /*Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
        laboratorio = "I33";
        intentEnviadora.putExtra("laboratorio", laboratorio);*/
        //float espacoUsado = new float[];

        //barEntries_laboratorios.add(new BarEntry(0, new float[]{espacoUsado, espacoLivre}));
        barEntries_laboratorios.add(new BarEntry(500, 0)); //I33
        barEntries_laboratorios.add(new BarEntry(21,1)); //I27
        barEntries_laboratorios.add(new BarEntry(108, 2)); //AlaE
        barEntries_laboratorios.add(new BarEntry(0, 3)); //predio20
        barEntries_laboratorios.add(new BarEntry(0, 4)); //litoteca
        barEntries_laboratorios.add(new BarEntry(0, 5)); //parque
        BarDataSet barDataSet = new BarDataSet(barEntries_laboratorios, "Laboratorios");

        /*datesBarEntries.add(new BarEntry(20f,8));
        datesBarEntries.add(new BarEntry(30f,12));
        datesBarEntries.add(new BarEntry(10f,10));*/
        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("I33");
        theDates.add("I27");
        theDates.add("Ala E");
        theDates.add("Prédio 20");
        theDates.add("Litoteca");
        theDates.add("Parque");

        //BarDataSet datesDataSet = new BarDataSet(datesBarEntries, "test");

        BarData theData = new BarData(theDates, barDataSet);
        lotacao_acervo_barchart.setData(theData);

        //cria dados para o gráfico de linhas
        ArrayList<Entry> values1 = new ArrayList<>();
        /*for (int i = 0; i < 12; i++) {
            values1.add(new Entry(i, i));
        }*/

        values1.add(new Entry(0,1, 10));
        values1.add(new Entry(4, 2,20));
        values1.add(new Entry(8, 3,30));

        LineDataSet d1 = new LineDataSet(values1, "Entrada ");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(true);

        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            values2.add(new Entry(i, i-1));
        }

        LineDataSet d2 = new LineDataSet(values2, "Saída");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(true);

        /*ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);*/

        dados_linechart = new LineData();
        dados_linechart.addDataSet(d1);
        //dados_linechart.addDataSet(d2);
        movimentacao_linechart.setData(dados_linechart);
        return view;
    }
    public static GraficosFragment newInstance() {
        return new GraficosFragment();
    }

    /*private LineData generateDataLine(int cnt) {

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(values1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values2.add(new Entry(i, values1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(values2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);

        return new LineData(sets);
    }*/
}
