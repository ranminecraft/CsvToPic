package cn.ranmc;

import com.csvreader.CsvReader;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {

    private double primarySchool = 0;
    private double middleSchool = 0;
    private double highSchool = 0;
    //学习时间
    private double primarySchoolTime = 0;
    private double middleSchoolTime = 0;
    private double highSchoolTime = 0;
    private int primarySchoolTimeError = 0;
    private int middleSchoolTimeError = 0;
    private int highSchoolTimeError = 0;
    private Map<String, Integer> onlineTime = new HashMap<>();//线上课时
    private Map<String, Integer> emotion = new HashMap<>();//学习状态
    private Map<String, Integer> accompany = new HashMap<>();//亲人陪伴
    private Map<String, Integer> awner = new HashMap<>();//互动频率
    private Map<String, Integer> mode = new HashMap<>();//学习模式
    private Map<String, Integer> device = new HashMap<>();//使用设备
    private Map<String, Integer> area = new HashMap<>();//所在地区
    private Map<String, Integer> action = new HashMap<>();//使用功能
    private Map<String, Integer> style = new HashMap<>();//组织形式
    private Map<String, Integer> content = new HashMap<>();//课堂内容
    private Map<String, Integer> way = new HashMap<>();//解决方法
    private Map<String, Integer> problem = new HashMap<>();//遇到问题
    private Map<String, Integer> ability = new HashMap<>();//培育能力
    private Map<String, Integer> good = new HashMap<>();//线上优势
    private Map<String, Integer> bad = new HashMap<>();//线上不足
    private Map<String, Integer> hope = new HashMap<>();//期待改进
    private Map<String, Integer> move = new HashMap<>();//学习行为
    private Map<String, Integer> happy = new HashMap<>();//满意度

    public Reader(File file) throws IOException {
        System.out.println("正在读取中...");
        List<String[]> list = new ArrayList<>();
        CsvReader reader = new CsvReader(file.getPath(), ',', Charset.forName("GBK"));
        while (reader.readRecord()) {
            list.add(reader.getValues());
        }
        reader.close();

        System.out.println("正在分析数据...");
        for (int row = 0; row < list.size(); row++) {

            //分析数据
            if (row > 0) {
                //年级及学习时间
                int grade = Integer.parseInt(list.get(row)[2]);
                //小学
                if(grade <= 6) {
                    primarySchool++;
                    try {
                        primarySchoolTime += Double.parseDouble(list.get(row)[23]);
                    } catch (NumberFormatException e) {
                        primarySchoolTimeError++;
                    }
                }
                //初中
                if(grade >= 7 && grade <= 9) {
                    middleSchool++;
                    try {
                        middleSchoolTime += Double.parseDouble(list.get(row)[23]);
                    } catch (NumberFormatException e) {
                        middleSchoolTimeError++;
                    }
                }
                //高中
                if(grade >= 10 && grade <= 12) {
                    highSchool++;
                    try {
                        highSchoolTime += Double.parseDouble(list.get(row)[23]);
                    } catch (NumberFormatException e) {
                        highSchoolTimeError++;
                    }
                }
                //线上课时
                Executor.read(list.get(row)[22], onlineTime, new String[]{"0", "20分钟以内", "20-30分钟", "30-40分钟", "45分钟以上"});
                //学习状态
                Executor.read(list.get(row)[24], emotion, new String[]{"0", "能专心学习", "在监督下能专心学习", "有时能专心学习", "基本不能专心学习", "不适应线上学习"});
                //亲人陪伴
                Executor.read(list.get(row)[25], accompany, new String[]{"0", "完全不需要", "有时需要", "完全需要"});
                //互动频率
                Executor.read(list.get(row)[45], awner, new String[]{"0", "不回答问题", "偶尔回答问题", "大多数情况下能参与回答问题", "回答问题", "课堂没有提问环节，没机会回答"});
                //喜欢的学习模式
                Executor.read(list.get(row)[91], mode, new String[]{"0", "以前的实体课堂模式", "实体课堂学习模式，线上学习资源作为补充", "线上线下混合式学习模式", "完全线上的学习模式"});
                //喜欢的学习模式
                Executor.read(list.get(row)[91], mode, new String[]{"0", "以前的实体课堂模式", "实体课堂学习模式，线上学习资源作为补充", "线上线下混合式学习模式", "完全线上的学习模式"});
                //使用设备
                Executor.read(list.get(row)[3], device, new String[]{"不能‐电视", "能‐电视"});
                Executor.read(list.get(row)[4], device, new String[]{"不能‐电脑", "能‐电脑"});
                Executor.read(list.get(row)[5], device, new String[]{"不能‐平板", "能‐平板"});
                Executor.read(list.get(row)[6], device, new String[]{"不能‐手机", "能‐手机"});
                Executor.read(list.get(row)[7], device, new String[]{"不能‐音频", "能‐音频"});
                Executor.read(list.get(row)[8], device, new String[]{"不能‐纸质", "能‐纸质"});
                //使用功能
                Executor.read(list.get(row)[9], action, new String[]{"未使用‐回看课程视频", "使用过‐回看课程视频"});
                Executor.read(list.get(row)[10], action, new String[]{"未使用‐作业提交", "使用过‐作业提交"});
                Executor.read(list.get(row)[11], action, new String[]{"未使用‐随堂测试", "使用过‐随堂测试"});
                Executor.read(list.get(row)[12], action, new String[]{"未使用‐视频会议", "使用过‐视频会议"});
                Executor.read(list.get(row)[13], action, new String[]{"未使用‐作业批改反馈", "使用过‐作业批改反馈"});
                Executor.read(list.get(row)[14], action, new String[]{"未使用‐课堂发言", "使用过‐课堂发言"});
                Executor.read(list.get(row)[15], action, new String[]{"未使用‐班级通知", "使用过‐班级通知"});
                Executor.read(list.get(row)[16], action, new String[]{"未使用‐班级圈", "使用过‐班级圈"});
                Executor.read(list.get(row)[17], action, new String[]{"未使用‐优秀作业查看", "使用过‐优秀作业查看"});
                Executor.read(list.get(row)[18], action, new String[]{"未使用‐线上学科竞赛", "使用过‐线上学科竞赛"});
                Executor.read(list.get(row)[19], action, new String[]{"未使用‐屏幕共享", "使用过‐屏幕共享"});
                Executor.read(list.get(row)[20], action, new String[]{"未使用‐弹幕", "使用过‐弹幕"});
                Executor.read(list.get(row)[21], action, new String[]{"未使用‐讨论", "使用过‐讨论"});
                //组织形式
                Executor.read(list.get(row)[26], style, new String[]{"不喜欢‐直播", "喜欢‐直播"});
                Executor.read(list.get(row)[27], style, new String[]{"不喜欢‐录播", "喜欢‐录播"});
                Executor.read(list.get(row)[28], style, new String[]{"不喜欢‐资源包", "喜欢‐资源包"});
                Executor.read(list.get(row)[29], style, new String[]{"不喜欢‐电视课堂", "喜欢‐电视课堂"});
                Executor.read(list.get(row)[30], style, new String[]{"不喜欢‐直播+录播", "喜欢‐直播+录播"});
                Executor.read(list.get(row)[31], style, new String[]{"不喜欢‐直播+资源包", "喜欢‐直播+资源包"});
                Executor.read(list.get(row)[32], style, new String[]{"不喜欢‐录播+资源包", "喜欢‐录播+资源包"});
                Executor.read(list.get(row)[33], style, new String[]{"不喜欢‐直播+录播+资源包", "喜欢‐直播+录播+资源包"});
                Executor.read(list.get(row)[34], style, new String[]{"不喜欢‐录播+资源包+线上辅导", "喜欢‐录播+资源包+线上辅导"});
                //课程内容
                Executor.read(list.get(row)[35], content, new String[]{"未学习‐学科课程新课", "学习过‐学科课程新课"});
                Executor.read(list.get(row)[36], content, new String[]{"未学习‐学科课程复习", "学习过‐学科课程复习"});
                Executor.read(list.get(row)[37], content, new String[]{"未学习‐音美体劳教育", "学习过‐音美体劳教育"});
                Executor.read(list.get(row)[38], content, new String[]{"未学习‐专题教育(课外知识)", "学习过‐专题教育(课外知识)"});
                //解决方法
                Executor.read(list.get(row)[39], way, new String[]{"未使用‐查阅资料自主解决", "使用过‐查阅资料自主解决"});
                Executor.read(list.get(row)[40], way, new String[]{"未使用‐学习平台视频回放", "使用过‐学习平台视频回放"});
                Executor.read(list.get(row)[42], way, new String[]{"未使用‐教师线上答疑", "使用过‐教师线上答疑"});
                Executor.read(list.get(row)[42], way, new String[]{"未使用‐社交平台咨询教师", "使用过‐社交平台咨询教师"});
                Executor.read(list.get(row)[43], way, new String[]{"未使用‐同学互相交流", "使用过‐同学互相交流"});
                Executor.read(list.get(row)[44], way, new String[]{"未使用‐暂时放下以后解决", "使用过‐暂时放下以后解决"});
                //遇到问题
                Executor.read(list.get(row)[46], problem, new String[]{"未遇到‐容易拥堵卡顿", "遇到过‐容易拥堵卡顿"});
                Executor.read(list.get(row)[47], problem, new String[]{"未遇到‐线上体验不好", "遇到过‐线上体验不好"});
                Executor.read(list.get(row)[48], problem, new String[]{"未遇到‐与教师沟通不顺畅", "遇到过‐与教师沟通不顺畅"});
                Executor.read(list.get(row)[49], problem, new String[]{"未遇到‐课后作业不合理", "遇到过‐课后作业不合理"});
                Executor.read(list.get(row)[50], problem, new String[]{"未遇到‐课程资源质量欠佳", "遇到过‐课程资源质量欠佳"});
                Executor.read(list.get(row)[51], problem, new String[]{"未遇到‐眼睛疲劳", "遇到过‐眼睛疲劳"});
                Executor.read(list.get(row)[52], problem, new String[]{"未遇到‐软件平台过多容易混淆", "遇到过‐软件平台过多容易混淆"});
                Executor.read(list.get(row)[53], problem, new String[]{"未遇到‐环境干扰因素多", "遇到过‐环境干扰因素多"});
                //培育能力
                Executor.read(list.get(row)[59], ability, new String[]{"未培养‐自主学习", "培养了‐自主学习"});
                Executor.read(list.get(row)[60], ability, new String[]{"未培养‐自控", "培养了‐自控"});
                Executor.read(list.get(row)[61], ability, new String[]{"未培养‐数字化资源利用", "培养了‐数字化资源利用"});
                Executor.read(list.get(row)[62], ability, new String[]{"未培养‐表达沟通", "培养了‐表达沟通"});
                Executor.read(list.get(row)[63], ability, new String[]{"未培养‐生活实践", "培养了‐生活实践"});
                Executor.read(list.get(row)[64], ability, new String[]{"未培养‐其他", "培养了‐其他"});
                //线上优势
                Executor.read(list.get(row)[72], good, new String[]{"不存在‐能听到名师优质课", "存在‐能听到名师优质课"});
                Executor.read(list.get(row)[73], good, new String[]{"不存在‐方便回看复习理解", "存在‐方便回看复习理解"});
                Executor.read(list.get(row)[74], good, new String[]{"不存在‐学习效果更好", "存在‐学习效果更好"});
                Executor.read(list.get(row)[75], good, new String[]{"不存在‐减轻了学习负担", "存在‐减轻了学习负担"});
                Executor.read(list.get(row)[76], good, new String[]{"不存在‐自觉性自主性增强", "存在‐自觉性自主性增强"});
                Executor.read(list.get(row)[77], good, new String[]{"不存在‐可以随时随地学习", "存在‐可以随时随地学习"});
                Executor.read(list.get(row)[78], good, new String[]{"不存在‐其他)", "存在‐其他)"});
                //线上不足
                Executor.read(list.get(row)[79], bad, new String[]{"不存在‐不如课堂教学效果好", "存在‐不如课堂教学效果好"});
                Executor.read(list.get(row)[80], bad, new String[]{"不存在‐视频课程质量参差", "存在‐视频课程质量参差"});
                Executor.read(list.get(row)[81], bad, new String[]{"不存在‐学习负担加重", "存在‐学习负担加重"});
                Executor.read(list.get(row)[82], bad, new String[]{"不存在‐缺乏师生互动", "存在‐缺乏师生互动"});
                Executor.read(list.get(row)[83], bad, new String[]{"不存在‐其他", "存在‐其他"});
                //期待改进
                Executor.read(list.get(row)[84], hope, new String[]{"不期待‐老师实时视音频方式的互动答疑", "期待‐老师实时视音频方式的互动答疑"});
                Executor.read(list.get(row)[85], hope, new String[]{"不期待‐线上小组讨论和合作", "期待‐线上小组讨论和合作"});
                Executor.read(list.get(row)[86], hope, new String[]{"不期待‐增加专题教育的内容和课时", "期待‐增加专题教育的内容和课时"});
                Executor.read(list.get(row)[87], hope, new String[]{"不期待‐线上随堂测试或考试", "期待‐线上随堂测试或考试"});
                Executor.read(list.get(row)[88], hope, new String[]{"不期待‐智能推荐学习资源", "期待‐智能推荐学习资源"});
                Executor.read(list.get(row)[89], hope, new String[]{"不期待‐学习状态智能监测并反馈提醒", "期待‐学习状态智能监测并反馈提醒"});
                Executor.read(list.get(row)[90], hope, new String[]{"不期待‐其他", "期待‐其他"});
                //学习行为
                Executor.read(list.get(row)[54], move, new String[]{"0","否‐回看课程视频", "是‐回看课程视频"});
                Executor.read(list.get(row)[55], move, new String[]{"0","否‐认真学习老师提供课程资料", "是‐认真学习老师提供课程资料"});
                Executor.read(list.get(row)[56], move, new String[]{"0","否‐开展居家自觉自学自修活动", "是‐开展居家自觉自学自修活动"});
                Executor.read(list.get(row)[57], move, new String[]{"0","否‐遇到问题积极向老师提问", "是‐遇到问题积极向老师提问"});
                Executor.read(list.get(row)[58], move, new String[]{"0","否‐线上作业质量能达到线下效果", "是‐线上作业质量能达到线下效果"});
                //满意度
                Executor.read(list.get(row)[65], happy, new String[]{"0","非常满意‐线上课堂直播", "满意‐线上课堂直播", "一般‐线上课堂直播", "不满意‐线上课堂直播"});
                Executor.read(list.get(row)[66], happy, new String[]{"0","非常满意‐线上课堂录播", "满意‐线上课堂录播", "一般‐线上课堂录播", "不满意‐线上课堂录播"});
                Executor.read(list.get(row)[67], happy, new String[]{"0","非常满意‐教师线上教学态度", "满意‐教师线上教学态度", "一般‐教师线上教学态度", "不满意‐教师线上教学态度"});
                Executor.read(list.get(row)[68], happy, new String[]{"0","非常满意‐教师线上教学水平", "满意‐教师线上教学水平", "一般‐教师线上教学水平", "不满意‐教师线上教学水平"});
                Executor.read(list.get(row)[69], happy, new String[]{"0","非常满意‐线上学习资源内容", "满意‐线上学习资源内容", "一般‐线上学习资源内容", "不满意‐线上学习资源内容"});
                Executor.read(list.get(row)[70], happy, new String[]{"0","非常满意‐线上学习平台", "满意‐线上学习平台", "一般‐线上学习平台", "不满意‐线上学习平台"});
                Executor.read(list.get(row)[71], happy, new String[]{"0","非常满意‐总体", "满意‐总体", "一般‐总体", "不满意‐总体"});
                //所在地区
                Executor.read(list.get(row)[1], area, new String[]{"0"});
            }

        }


        System.out.println("开始生成图片...");

        //年级
        System.out.println("正在生成年级统计图...");
        System.out.println("小学" + String.format("%.0f",primarySchool) + "人");
        System.out.println("初中" + String.format("%.0f",middleSchool) + "人");
        System.out.println("高中" + String.format("%.0f",highSchool) + "人");
        DefaultPieDataset gradePieDataset = new DefaultPieDataset();

        gradePieDataset.setValue("小学", primarySchool);
        gradePieDataset.setValue("初中", middleSchool);
        gradePieDataset.setValue("高中", highSchool);
        ChartPic.create("年级统计图", gradePieDataset, 960, 640);

        //学习时间
        DefaultCategoryDataset studyTimeDataset = new DefaultCategoryDataset();
        System.out.println("正在生成学习时间统计图...");
        System.out.println("小学平均学习时间" + String.format("%.5f",primarySchoolTime/(primarySchool-primarySchoolTimeError)) + "小时,已排除"+ primarySchoolTimeError + "条无效数据");
        System.out.println("初中平均学习时间" + String.format("%.5f",middleSchoolTime/(middleSchool-middleSchoolTimeError)) + "小时,已排除"+ middleSchoolTimeError + "条无效数据");
        System.out.println("高中平均学习时间" + String.format("%.5f",highSchoolTime/(highSchool-highSchoolTimeError)) + "小时,已排除"+ highSchoolTimeError + "条无效数据");

        studyTimeDataset.setValue(primarySchoolTime/primarySchool, "小学", "小学");
        studyTimeDataset.setValue(middleSchoolTime/middleSchool, "初中", "初中");
        studyTimeDataset.setValue(highSchoolTime/highSchool, "高中", "高中");
        ChartPic.create("学习时间统计图", "每日平均学习时间", "小时", studyTimeDataset, 960, 640);

        Executor.saveAsPie("线上课时统计图", onlineTime);//线上课时
        Executor.saveAsPie("学习状态统计图", emotion);//学习状态
        Executor.saveAsPie("亲人陪伴统计图", accompany);//亲人陪伴
        Executor.saveAsPie("互动频率统计图", awner);//互动频率
        Executor.saveAsPie("喜欢的学习模式统计图", mode);//学习模式
        Executor.saveAsBar("使用设备统计图", "设备", "人数", device);//使用设备
        Executor.saveAsBar("所在地区统计图", "地区", "人数", area);//所在地区
        Executor.saveAsBigBar("使用功能统计图", "功能", "人数", action);//使用功能
        Executor.saveAsBigBar("组合形式统计图", "组合", "人数", style);//组合形式
        Executor.saveAsBar("课程内容统计图", "内容", "人数", content);//课程内容
        Executor.saveAsBigBar("未掌握知识解决方法统计图", "方法", "人数", way);//解决方法
        Executor.saveAsBigBar("线上学习遇到的问题统计图", "问题", "人数", problem);//遇到问题
        Executor.saveAsBigBar("培育能力统计图", "能力", "人数", ability);//培育能力
        Executor.saveAsBigBar("线上优势统计图", "优势", "人数", good);//线上优势
        Executor.saveAsBar("线上不足统计图", "不足", "人数", bad);//线上不足
        Executor.saveAsBigBar("期待改进方向统计图", "方面", "人数", hope);//期待改进
        Executor.saveAsBigBar("学习行为统计图", "行为", "人数", move);//学习行为
        Executor.saveAsBigBar("满意度统计图", "满意度", "人数", happy);//满意度

    }
}
