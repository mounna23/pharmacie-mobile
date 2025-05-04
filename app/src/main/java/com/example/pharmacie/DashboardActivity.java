package com.example.pharmacie;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmacie.models.Medicament;
import com.example.pharmacie.retrofit.ApiClient;
import com.example.pharmacie.retrofit.MedicamentApi;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Initialisation des statistiques
        setupStatsGrid();

        // Configuration des graphiques
        setupMedicamentsChart();

    }

    private void setupStatsGrid() {
        MedicamentApi api = ApiClient.getClient().create(MedicamentApi.class);

        Call<List<Medicament>> call = api.getAllMedicaments();
        call.enqueue(new Callback<List<Medicament>>() {
            @Override
            public void onResponse(Call<List<Medicament>> call, Response<List<Medicament>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Medicament> medicaments = response.body();
                    int totalMedicaments = medicaments.size();

                    int lowStockCount = 0;
                    for (Medicament med : medicaments) {
                        if (med.getQteStock() < med.getStockMin()) {
                            lowStockCount++;
                        }
                    }

                    List<StatItem> stats = new ArrayList<>();
                    stats.add(new StatItem("Médicaments", String.valueOf(totalMedicaments), R.drawable.ic_medicament, R.color.purple_500));
                    stats.add(new StatItem("Stocks Faibles", String.valueOf(lowStockCount), R.drawable.ic_alerte, R.color.red_500));

                    GridView gridView = findViewById(R.id.gridStats);
                    gridView.setAdapter(new StatsAdapter(DashboardActivity.this, stats));
                }
            }

            @Override
            public void onFailure(Call<List<Medicament>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DashboardActivity.this, "Erreur lors du chargement des statistiques", Toast.LENGTH_SHORT).show();
            }
        });
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




}
