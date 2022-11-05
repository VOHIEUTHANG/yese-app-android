package com.example.group3_project.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.DiamondGameHistory;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    AnyChartView anyChartView;
    List<DataEntry> seriesData = new ArrayList<>();
    List<DiamondGameHistory> diamondGameHistories;
    ProgressBar progressBar;
    String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        anyChartView = getView().findViewById(R.id.any_chart_view);
        progressBar = getView().findViewById(R.id.progress_bar);

    }

    private void setEvent() {
        anyChartView.setProgressBar(progressBar);
        setUsername();
        initDiamondGameHistoryList();
        setData();
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.title("Biểu đồ thể thiện số kim cương người chơi kiếm được theo thời gian!");
//        cartesian.yAxis(0).title("Number of Diamond");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        Set set = Set.instantiate();
        set.data(seriesData);

        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Diamond");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);

        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }

    public void setUsername() {
        username = Utils.getUsername(requireContext());
    }

    public void setData() {
        int length = diamondGameHistories.size();
        for (int i = 0; i < length; i++) {
            seriesData.add(new ValueDataEntry(diamondGameHistories.get(i).getDay(), diamondGameHistories.get(i).getDiamond()));
        }

        String currentDate = Utils.getCurrentTime();
        if(!diamondGameHistories.get(length-1).getDay().equals(currentDate)){
            seriesData.add(new ValueDataEntry(currentDate, 0 ));
        }

    }

    public void initDiamondGameHistoryList() {
        diamondGameHistories = AppDatabase.getInstance(requireContext()).diamondGameHistoryDao().getAllUserDiamondHistoryOrderByDateASC(username);
//        for (int i = 0; i < diamondGameHistories.size(); i++) {
//            Log.d("Devo", String.valueOf(diamondGameHistories.get(i).getDay()));
//        }
    }

}