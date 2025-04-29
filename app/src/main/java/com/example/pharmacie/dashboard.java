package com.example.pharmacie;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Initialisation des statistiques
        setupStatsGrid();

        // Configuration des graphiques
        setupMedicamentsChart();
        setupFournisseursChart();
        setupUtilisateursChart();
    }

    private void setupStatsGrid() {
        List<StatItem> stats = new ArrayList<>();

        // Ici, vous devriez remplacer ces valeurs par des données réelles de votre base de données
        stats.add(new StatItem("Médicaments", "256", R.drawable.ic_medicament, R.color.purple_500));
        stats.add(new StatItem("Fournisseurs", "12", R.drawable.profilxml, R.color.teal_700));
        stats.add(new StatItem("Utilisateurs", "8", R.drawable.ic_baseline_person_24, R.color.blue_500));
        stats.add(new StatItem("Commandes", "42", R.drawable.ic_commande, R.color.orange_500));
        stats.add(new StatItem("Stocks Faibles", "15", R.drawable.ic_alerte, R.color.red_500));
        stats.add(new StatItem("Ventes Aujourd'hui", "23", R.drawable.ic_vente, R.color.green_500));

        GridView gridView = findViewById(R.id.gridStats);
        gridView.setAdapter(new StatsAdapter(this, stats));
    }

    private void setupMedicamentsChart() {
        PieChart chart = findViewById(R.id.chartMedicaments);

        // Données exemple - à remplacer par vos données réelles
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(45f, "Antibiotiques"));
        entries.add(new PieEntry(30f, "Antidouleurs"));
        entries.add(new PieEntry(15f, "Vitamines"));
        entries.add(new PieEntry(10f, "Autres"));

        PieDataSet dataSet = new PieDataSet(entries, "Répartition des médicaments");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.BLACK);

        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.setCenterText("Médicaments");
        chart.animateY(1000);
        chart.invalidate();
    }

    private void setupFournisseursChart() {
        BarChart chart = findViewById(R.id.chartFournisseurs);

        // Données exemple - à remplacer par vos données réelles
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 12f));
        entries.add(new BarEntry(1f, 8f));
        entries.add(new BarEntry(2f, 6f));
        entries.add(new BarEntry(3f, 4f));

        BarDataSet dataSet = new BarDataSet(entries, "Médicaments par fournisseur");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(12f);

        BarData data = new BarData(dataSet);
        chart.setData(data);

        // Configuration de l'axe X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"PharmaPlus", "MediCorp", "SantéPro", "BioPharm"}));

        chart.getDescription().setEnabled(false);
        chart.animateY(1000);
        chart.invalidate();
    }

    private void setupUtilisateursChart() {
        LineChart chart = findViewById(R.id.chartUtilisateurs);

        // Données exemple - à remplacer par vos données réelles
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 2f));
        entries.add(new Entry(1f, 4f));
        entries.add(new Entry(2f, 6f));
        entries.add(new Entry(3f, 8f));

        LineDataSet dataSet = new LineDataSet(entries, "Nouveaux utilisateurs (mois)");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);
        dataSet.setLineWidth(2f);

        LineData data = new LineData(dataSet);
        chart.setData(data);

        // Configuration de l'axe X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Jan", "Fév", "Mar", "Avr"}));

        chart.getDescription().setEnabled(false);
        chart.animateY(1000);
        chart.invalidate();
    }
}