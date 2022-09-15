package cn.ranmc;

public class Timing {
    private long startTime;
    public Timing() {}

    /**
     * 开始计时
     */
    public void start() {
        startTime = System.currentTimeMillis();
        System.out.println("开始运行...");
    }

    /**
     * 结束计时
     */
    public void end() {
        long finishTime = System.currentTimeMillis();
        long timeElapsed = finishTime - startTime;
        System.out.println("所有数据都已解析完成，耗时" + String.format("%.2f",(double)timeElapsed/1000) + "秒");
    }

}
