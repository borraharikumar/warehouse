package com.warehouse.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class OrderMethodChartUtils {

	public void generatePieChart(String path, List<Object[]> data) throws IOException {
		//Create dataset
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] x : data) {
			dataset.setValue(String.valueOf(x[0]), Double.valueOf(x[1].toString()));
		}
		//Create JFreeChart object
		JFreeChart chart = ChartFactory.createPieChart("ORDER MODE AND COUNT", dataset);
		//Convert JFreeChart object into image
		ChartUtils.saveChartAsJPEG(new File(path+"/pie.jpeg"), chart, 500, 300);
	}
	
	public void generateBarChart(String path, List<Object[]> data) throws IOException {
		//Create dataset
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] y : data) {
			dataset.setValue(Double.valueOf(y[1].toString()), String.valueOf(y[0]), "");
		}
		//Create JFreeChart object
		JFreeChart chart = ChartFactory.createBarChart("ORDER MODE AND COUNT", "MODE", "COUNT", dataset);
		//Convert JFreeChart object into image
		ChartUtils.saveChartAsJPEG(new File(path+"/bar.jpeg"), chart, 500, 300);
	}
	
}
