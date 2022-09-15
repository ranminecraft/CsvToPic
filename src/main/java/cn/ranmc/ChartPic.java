package cn.ranmc;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChartPic {
    static Font font = new Font("黑体", Font.PLAIN, 15);

    /**
     * 生成饼状图
     * @param title 图表标题
     * @param pieDataset 数据
     * @throws IOException
     */
    public static void create(String title, DefaultPieDataset pieDataset, int width, int height) throws IOException {
        JFreeChart chart = ChartFactory.createPieChart(title, pieDataset, false, false, true);
        chart.getTitle().setFont(font);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(font);
        plot.setStartAngle(3.14f / 2f);
        plot.setForegroundAlpha(0.7f);
        plot.setBackgroundAlpha(0.0f);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {1}占{2}"));
        File path = new File(new File("").getCanonicalPath()+"/"+title+".jpg");
        ChartUtilities.saveChartAsJPEG(path, chart, width, height);
        System.out.println("饼状图已保存至"+path);
        System.out.println("-----------------");
    }

    /**
     * 生成柱状图
     * @param title 图表标题
     * @param catgoryAxis 横向文字
     * @param valueAxis 纵向文字
     * @param categoryDataset 数据
     * @throws IOException
     */
    public static void create(String title, String catgoryAxis, String valueAxis, DefaultCategoryDataset categoryDataset, int width, int height) throws IOException {
        JFreeChart chart = ChartFactory.createBarChart3D(title,catgoryAxis,valueAxis,categoryDataset,PlotOrientation.VERTICAL,true,false,false);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(font);
        domainAxis.setTickLabelFont(font);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(font);
        chart.getTitle().setFont(font);
        chart.getLegend().setItemFont(font);
        File path = new File(new File("").getCanonicalPath()+"/"+title+".jpg");
        ChartUtilities.saveChartAsJPEG(path, chart, width, height);
        System.out.println("柱状图已保存至"+path);
        System.out.println("-----------------");
    }
}
