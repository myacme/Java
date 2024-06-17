package easyExcle;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/6/4 下午2:41
 */
// 设置表头行高度
@HeadRowHeight(35)
// 设置内容行高度
@ContentRowHeight(18)
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,
		borderLeft = BorderStyleEnum.THIN,
		borderRight = BorderStyleEnum.THIN,
		borderTop = BorderStyleEnum.THIN,
		borderBottom = BorderStyleEnum.THIN)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,
		borderLeft = BorderStyleEnum.THIN,
		borderRight = BorderStyleEnum.THIN,
		borderTop = BorderStyleEnum.THIN,
		borderBottom = BorderStyleEnum.THIN)
public class ProgressOverview {

	public static void main(String[] args) {
		List<ProgressOverview> list = new ArrayList<>();
		EasyExcel.write("", ProgressOverview.class).sheet("sheet").doWrite(list);
	}

	@ExcelProperty(value = {"单位", "单位"}, index = 0)
	private String dw;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"需求任务", "项目数 "}, index = 1)
	private int xq;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"委托设计", "项目数"}, index = 2)
	private int wt;
	@ColumnWidth(13)
	@ExcelProperty(value = {"委托设计", "工作进度"}, index = 3)
	private String wtjd;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"设计作业启动", "项目数"}, index = 4)
	private int sjqd;
	@ColumnWidth(13)
	@ExcelProperty(value = {"设计作业启动", "工作进度"}, index = 5)
	private String sjqdjd;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"勘察完成", "项目数"}, index = 6)
	private int kc;
	@ColumnWidth(13)
	@ExcelProperty(value = {"勘察完成", "工作进度"}, index = 7)
	private String kcjd;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"设计完成", "项目数"}, index = 8)
	private int sj;
	@ColumnWidth(13)
	@ExcelProperty(value = {"设计完成", "工作进度"}, index = 9)
	private String sjjd;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"技经完成", "项目数"}, index = 10)
	private int jj;
	@ColumnWidth(17)
	@NumberFormat("0.00_ ")
	@ExcelProperty(value = {"技经完成", "金额（万元）"}, index = 11)
	private double jjje;
	@ColumnWidth(13)
	@ExcelProperty(value = {"技经完成", "工作进度"}, index = 12)
	private String jjjd;
	@ColumnWidth(12)
	@NumberFormat("0_ ")
	@ExcelProperty(value = {"成果提交", "项目数"}, index = 13)
	private int ps;
	@ColumnWidth(17)
	@NumberFormat("0.00_ ")
	@ExcelProperty(value = {"成果提交", "金额（万元）"}, index = 14)
	private double psje;
	@ColumnWidth(13)
	@ExcelProperty(value = {"评审完成", "工作进度"}, index = 15)
	private String psjd;


	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public int getXq() {
		return xq;
	}

	public void setXq(int xq) {
		this.xq = xq;
	}

	public int getWt() {
		return wt;
	}

	public void setWt(int wt) {
		this.wt = wt;
	}

	public String getWtjd() {
		return wtjd;
	}

	public void setWtjd(String wtjd) {
		this.wtjd = wtjd;
	}

	public int getSjqd() {
		return sjqd;
	}

	public void setSjqd(int sjqd) {
		this.sjqd = sjqd;
	}

	public String getSjqdjd() {
		return sjqdjd;
	}

	public void setSjqdjd(String sjqdjd) {
		this.sjqdjd = sjqdjd;
	}

	public int getKc() {
		return kc;
	}

	public void setKc(int kc) {
		this.kc = kc;
	}

	public String getKcjd() {
		return kcjd;
	}

	public void setKcjd(String kcjd) {
		this.kcjd = kcjd;
	}

	public int getSj() {
		return sj;
	}

	public void setSj(int sj) {
		this.sj = sj;
	}

	public String getSjjd() {
		return sjjd;
	}

	public void setSjjd(String sjjd) {
		this.sjjd = sjjd;
	}

	public int getJj() {
		return jj;
	}

	public void setJj(int jj) {
		this.jj = jj;
	}

	public double getJjje() {
		return jjje;
	}

	public void setJjje(double jjje) {
		this.jjje = jjje;
	}

	public String getJjjd() {
		return jjjd;
	}

	public void setJjjd(String jjjd) {
		this.jjjd = jjjd;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public double getPsje() {
		return psje;
	}

	public void setPsje(double psje) {
		this.psje = psje;
	}

	public String getPsjd() {
		return psjd;
	}

	public void setPsjd(String psjd) {
		this.psjd = psjd;
	}
}
