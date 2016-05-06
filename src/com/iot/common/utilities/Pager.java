package com.iot.common.utilities;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午10:38:37
 * @author 
 * 功能说明： 分页工具类
 *
 * 修改历史
 * Revision 
 * Update: ------ empty log ------
 ******************************************************************
 */
public class Pager<T> {
	private static int PAGE_SIZE = 40;
	private static int NUM_BUTTONS = 10;
	private Page<T> page;
	private String url;
	private List<PageItem> pgItemList;
	private List<T> content;

	public Pager(Page<T> page, String url) {
		this.page = page;
		this.url = url;

		if (page != null)
			this.content = page.getContent();
		else {
			this.content = null;
		}

		if ((this.content != null) && (!this.content.isEmpty())) {
			if (totalPages() <= NUM_BUTTONS) {
				this.pgItemList = new ArrayList();

				for (int i = 1; i <= totalPages(); i++) {
					PageItem item = new PageItem(i, Integer.toString(i), pageURLByPage(i));
					if (currentPage() == i) {
						item.set_active(true);
					}
					this.pgItemList.add(item);
				}
			} else {
				int firstSegmentUpperLimit = 8;
				int lastSegmentLowerLimit = totalPages() - 7;

				if (currentPage() <= firstSegmentUpperLimit) {
					this.pgItemList = new ArrayList();

					for (int i = 1; i <= firstSegmentUpperLimit; i++) {
						PageItem item = new PageItem(i, Integer.toString(i), pageURLByPage(i));
						if (currentPage() == i) {
							item.set_active(true);
						}
						this.pgItemList.add(item);
					}
					this.pgItemList.add(new PageItem(9, "...", pageURLByPage(9)));
					this.pgItemList.add(
							new PageItem(totalPages(), Integer.toString(totalPages()), pageURLByPage(totalPages())));
				} else if (currentPage() >= lastSegmentLowerLimit) {
					this.pgItemList = new ArrayList();

					this.pgItemList.add(new PageItem(1, Integer.toString(1), pageURLByPage(1)));
					this.pgItemList.add(new PageItem(totalPages() - 8, "...", pageURLByPage(totalPages() - 8)));
					for (int i = totalPages() - 7; i <= totalPages(); i++) {
						PageItem item = new PageItem(i, Integer.toString(i), pageURLByPage(i));
						if (currentPage() == i) {
							item.set_active(true);
						}
						this.pgItemList.add(item);
					}
				} else {
					this.pgItemList = new ArrayList();

					this.pgItemList.add(new PageItem(1, "1", pageURLByPage(1)));

					this.pgItemList.add(new PageItem(currentPage() - 3, "...", pageURLByPage(currentPage() - 3)));

					for (int i = currentPage() - 2; i <= currentPage() + 3; i++) {
						PageItem item = new PageItem(i, Integer.toString(i), pageURLByPage(i));
						if (currentPage() == i) {
							item.set_active(true);
						}
						this.pgItemList.add(item);
					}

					this.pgItemList.add(new PageItem(currentPage() + 4, "...", pageURLByPage(currentPage() + 4)));

					this.pgItemList.add(
							new PageItem(totalPages(), Integer.toString(totalPages()), pageURLByPage(totalPages())));
				}
			}
		}
	}

	public boolean hasContent() {
		return (this.content != null) && (!this.content.isEmpty());
	}

	public boolean isFirst() {
		return this.page.isFirst();
	}

	public boolean isLast() {
		return this.page.isLast();
	}

	public boolean hasNext() {
		return this.page.hasNext();
	}

	public int totalPages() {
		return this.page.getTotalPages();
	}

	public int currentPage() {
		return this.page.getNumber() + 1;
	}

	public List<T> getContent() {
		return this.content;
	}
	public Integer getTotalElements(){
		return (int) this.page.getTotalElements();
		
	}
	public String firstPageURL() {
		return new StringBuffer(this.url).append("page=").append(0).append("&size=").append(PAGE_SIZE).toString();
	}

	public String lastPageURL() {
		return new StringBuffer(this.url).append("page=").append(totalPages() - 1).append("&size=").append(PAGE_SIZE)
				.toString();
	}

	public String nextPageURL() {
		return new StringBuffer(this.url).append("page=").append(currentPage()).append("&size=").append(PAGE_SIZE)
				.toString();
	}

	public String prevPageURL() {
		return new StringBuffer(this.url).append("page=").append(currentPage() - 2).append("&size=").append(PAGE_SIZE)
				.toString();
	}

	public String currPageURL() {
		return new StringBuffer(this.url).append("page=").append(currentPage() - 1).append("&size=").append(PAGE_SIZE)
				.toString();
	}

	public String pageURLByPage(int pgnum) {
		return new StringBuffer(this.url).append("page=").append(pgnum - 1).append("&size=").append(PAGE_SIZE)
				.toString();
	}

	public String printLeftArrows() {
		if (this.page != null) {
			StringBuffer sb = new StringBuffer("<li");
			if (this.page.isFirst())
				sb.append(" class=\"disabled\"><a>&laquo;</a></li>");
			else {
				sb.append("><a href=\"").append(prevPageURL()).append("\">&laquo;</a></li>");
			}

			return sb.toString();
		}
		return "";
	}

	public String printRightArrows() {
		if (this.page != null) {
			StringBuffer sb = new StringBuffer("<li");
			if (this.page.isLast())
				sb.append(" class=\"disabled\"><a>&raquo;</a></a></li>");
			else {
				sb.append("><a href=\"").append(nextPageURL()).append("\">&raquo;</a></li>");
			}

			return sb.toString();
		}

		return "";
	}

	public String printPageNumbers() {
		if ((this.pgItemList != null) && (!this.pgItemList.isEmpty())) {
			StringBuffer sb = new StringBuffer();
			for (PageItem item : this.pgItemList) {
				sb.append("<li");
				if (item._active)
					sb.append(" class=\"active\"><a>").append(item._display).append("</a></li>");
				else {
					sb.append("><a href=\"").append(item._url).append("\">").append(item._display).append("</a></li>");
				}
			}

			return sb.toString();
		}

		return "";
	}

	public static class PageItem {
		private String _display;
		private int _pageNumber;
		private String _url;
		private boolean _active;

		public PageItem(int pageNumber, String display, String url) {
			this._pageNumber = pageNumber;
			this._display = display;
			this._url = url;
			this._active = false;
		}

		public int get_pageNumber() {
			return this._pageNumber;
		}

		public void set_pageNumber(int _pageNumber) {
			this._pageNumber = _pageNumber;
		}

		public String get_url() {
			return this._url;
		}

		public void set_url(String _url) {
			this._url = _url;
		}

		public boolean is_active() {
			return this._active;
		}

		public void set_active(boolean _active) {
			this._active = _active;
		}
	}
}