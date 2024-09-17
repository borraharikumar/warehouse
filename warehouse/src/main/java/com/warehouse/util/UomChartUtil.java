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
public class UomChartUtil {
	
	public void createPieChart(String path, List<Object[]> data) throws IOException {
		//1. Create Dataset object for Pie & add data to it
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] x : data) {
			dataset.setValue(String.valueOf(x[0]), Double.valueOf(x[1].toString()));
		}
		//2. Create JFreeChart object with dataset & other details
		JFreeChart chart = ChartFactory.createPieChart("UOM TYPE AND COUNT", dataset);
		//3. Convert JFreeChart object into image
		ChartUtils.saveChartAsJPEG(new File(path+"/pie.jpeg"), chart, 500, 300);
	}
	
	public void createBarChart(String path, List<Object[]> data) throws IOException {
		//1. Create Dataset object for Bar & add data to it
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] y : data) {
							// Value                          Key                  label
			dataset.setValue(Double.valueOf(y[1].toString()), String.valueOf(y[0]), "");
		}
		//2. Create JFreeChart object with dataset & other details
		JFreeChart chart = ChartFactory.createBarChart("UOM TYPE AND COUNT", "UOM TYPE", "COUNT", dataset);
		//3. Convert JFreeChart object into image
		ChartUtils.saveChartAsJPEG(new File(path+"/bar.jpeg"), chart, 500, 300);
	}
	
}
