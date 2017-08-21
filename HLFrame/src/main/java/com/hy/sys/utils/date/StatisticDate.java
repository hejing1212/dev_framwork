package com.hy.sys.utils.date;

/** 
 * 统计的时间键值对
 * @author He.Xu.Dong 
 * @Date 2016年9月22日 上午11:02:03 
 * @version 1.0 
 */
public class StatisticDate{	
	//标题如2016年 ，如1月 等
	private String title;
	//标识，用于标识唯一的年月日，如年，2016，2017，如月，2017-08 2016-05  如日2017-08-01
	private String identify;
    private String startDate;
    private String endDate;
    public String getStartDate() {
        return startDate;
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


}