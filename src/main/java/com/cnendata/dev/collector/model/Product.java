/**
 *Product.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息，所有小得商品品类都必须继承该类<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-3-17,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
public class Product implements Serializable {
	private Long id;
	private String name;// 书名
	private String descript;// 简介
	private String url;
	private Date updateTime;// 采集更新日期
	private Float price;// 定价
	private Float shopPrice;// 商城价
	private Integer rank;// 排行榜
	private Integer commentCount;// 评论数
	private String img;// 图片路径

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Float shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
