/**
 *Book.java
 *Version1.0
 *2015-3-26
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.model;

import java.util.Date;

/**
 * 女装实体<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 
 * -->
 * 
 * @author myan
 * 
 *         since1.0
 */
public class NvZhuang extends Product {
	
	/**
	 * 品牌
	 */
	private String pinPai;
	
	/**
	 * 版型
	 */
	private String banXing;
	
	/**
	 * 款式
	 */
	private String kuanShi;
	
	/**
	 * 图案
	 */
	private String tuAn;
	
	/**
	 * 衣长
	 */
	private String yiChang;
	
	/**
	 * 厚度
	 */
	private String houDu;
	
	/**
	 * 领型
	 */
	private String linXing;

	public String getPinPai() {
		return pinPai;
	}

	public void setPinPai(String pinPai) {
		this.pinPai = pinPai;
	}

	public String getBanXing() {
		return banXing;
	}

	public void setBanXing(String banXing) {
		this.banXing = banXing;
	}

	public String getKuanShi() {
		return kuanShi;
	}

	public void setKuanShi(String kuanShi) {
		this.kuanShi = kuanShi;
	}

	public String getTuAn() {
		return tuAn;
	}

	public void setTuAn(String tuAn) {
		this.tuAn = tuAn;
	}

	public String getYiChang() {
		return yiChang;
	}

	public void setYiChang(String yiChang) {
		this.yiChang = yiChang;
	}

	public String getHouDu() {
		return houDu;
	}

	public void setHouDu(String houDu) {
		this.houDu = houDu;
	}

	public String getLinXing() {
		return linXing;
	}

	public void setLinXing(String linXing) {
		this.linXing = linXing;
	}

	@Override
	public String toString() {
		return "name: " + getName() + "; price :" + getPrice() + "; shopprice: " + getShopPrice()
				+ "; banxing: " + getBanXing() + "; kuanshi: " + getKuanShi()
				+ "; tuan: " + getTuAn() + "; lingxing: " + getLinXing();
	}
	
	

}
