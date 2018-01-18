package com.example.hello.shoppingcart2.model;

import java.util.List;

/**
 * 购物车的bean
 */

public class CartBean {
	private String msg;
	private String code;
	private List<DataBean> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		//添加一boolean，记录选中状态
		private boolean parentIsSelected;

		public DataBean(boolean parentIsSelected, String sellerName, String sellerid, List<ChildBean> list) {
			this.parentIsSelected = parentIsSelected;
			this.sellerName = sellerName;
			this.sellerid = sellerid;
			this.list = list;
		}

		public boolean isParentIsSelected() {
			return parentIsSelected;
		}

		public void setParentIsSelected(boolean parentIsSelected) {
			this.parentIsSelected = parentIsSelected;
		}

		private String sellerName;
		private String sellerid;
		private List<ChildBean> list;

		public String getSellerName() {
			return sellerName;
		}

		public void setSellerName(String sellerName) {
			this.sellerName = sellerName;
		}

		public String getSellerid() {
			return sellerid;
		}

		public void setSellerid(String sellerid) {
			this.sellerid = sellerid;
		}

		public List<ChildBean> getList() {
			return list;
		}

		public void setList(List<ChildBean> list) {
			this.list = list;
		}
	}

	public class ChildBean {

		private double bargainPrice;
		private String createtime;
		private String detailUrl;
		private String images;
		private double num;
		private double pid;
		private double price;
		private double pscid;
		private double selected;
		private double sellerid;
		private String subhead;
		private String title;
		private boolean childIsSelected;

		public ChildBean(double bargainPrice, String createtime, String detailUrl, String images, double num, double pid, double price, double pscid, double selected, double sellerid, String subhead, String title, boolean childIsSelected) {
			this.bargainPrice = bargainPrice;
			this.createtime = createtime;
			this.detailUrl = detailUrl;
			this.images = images;
			this.num = num;
			this.pid = pid;
			this.price = price;
			this.pscid = pscid;
			this.selected = selected;
			this.sellerid = sellerid;
			this.subhead = subhead;
			this.title = title;
			this.childIsSelected = childIsSelected;
		}

		public boolean isChildIsSelected() {
			return childIsSelected;
		}

		public void setChildIsSelected(boolean childIsSelected) {
			this.childIsSelected = childIsSelected;
		}

		public double getBargainPrice() {
			return bargainPrice;
		}

		public void setBargainPrice(double bargainPrice) {
			this.bargainPrice = bargainPrice;
		}

		public String getCreatetime() {
			return createtime;
		}

		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}

		public String getDetailUrl() {
			return detailUrl;
		}

		public void setDetailUrl(String detailUrl) {
			this.detailUrl = detailUrl;
		}

		public String getImages() {
			return images;
		}

		public void setImages(String images) {
			this.images = images;
		}

		public double getNum() {
			return num;
		}

		public void setNum(double num) {
			this.num = num;
		}

		public double getPid() {
			return pid;
		}

		public void setPid(double pid) {
			this.pid = pid;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getPscid() {
			return pscid;
		}

		public void setPscid(double pscid) {
			this.pscid = pscid;
		}

		public double getSelected() {
			return selected;
		}

		public void setSelected(double selected) {
			this.selected = selected;
		}

		public double getSellerid() {
			return sellerid;
		}

		public void setSellerid(double sellerid) {
			this.sellerid = sellerid;
		}

		public String getSubhead() {
			return subhead;
		}

		public void setSubhead(String subhead) {
			this.subhead = subhead;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
}
