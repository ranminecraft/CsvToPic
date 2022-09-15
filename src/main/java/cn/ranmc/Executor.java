package cn.ranmc;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.IOException;
import java.util.Map;

public class Executor {

    /**
     * 读取数据
     * @param key
     * @param map
     * @param replace
     */
    public static void read(String key, Map<String,Integer> map, String[] replace) {
        for(int i = 0; i < replace.length; i++) {
            if(String.valueOf(i).equals(key)) key = replace[i];
        }
        if(map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    /**
     * 保存饼状图
     * @param name 图表标题
     * @param map 数据
     * @throws IOException
     */
    public static void saveAsPie(String name, Map<String,Integer> map) throws IOException {
        System.out.println("正在生成"+name+"...");
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(String key : map.keySet()) {
            dataset.setValue(key, map.get(key));
            System.out.println(key + map.get(key) + "人");
        }
        ChartPic.create(name, dataset, 960, 640);
    }

    /**
     * 保存柱状图
     * @param name 图表标题
     * @param catgoryAxis 横向文字
     * @param valueAxis 纵向文字
     * @param map 数据
     * @throws IOException
     */
    public static void saveAsBar(String name,String catgoryAxis, String valueAxis, Map<String,Integer> map) throws IOException {
        ChartPic.create(name, catgoryAxis, valueAxis, getBarData(name, map), 960, 640);
    }

    /**
     * 保存大柱状图
     * @param name 图表标题
     * @param catgoryAxis 横向文字
     * @param valueAxis 纵向文字
     * @param map 数据
     * @throws IOException
     */
    public static void saveAsBigBar(String name,String catgoryAxis, String valueAxis, Map<String,Integer> map) throws IOException {
        ChartPic.create(name, catgoryAxis, valueAxis, getBarData(name, map), 1920, 1080);
    }

    /**
     * 获取柱状图数据
     * @param name 图表标题
     * @param map 数据
     * @return
     */
    private static DefaultCategoryDataset getBarData(String name, Map<String, Integer> map) {
        System.out.println("正在生成"+name+"...");
        int error = 0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key : map.keySet()) {
            try {
                String[] keyArr = key.split("‐");
                if(keyArr[0].isEmpty() || keyArr[1].isEmpty()) {
                    error += map.get(key);
                } else {
                    dataset.setValue(map.get(key), keyArr[0], keyArr[1]);
                    System.out.println(key + map.get(key) + "人");
                }
            } catch (Exception e){
                error += map.get(key);
                //e.printStackTrace();
            }
        }
        if(error > 0) System.out.println("已排除"+ error + "条无效数据");
        return dataset;
    }
}
