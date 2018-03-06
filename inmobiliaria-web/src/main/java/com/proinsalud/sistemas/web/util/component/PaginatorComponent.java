package com.proinsalud.sistemas.web.util.component;

import java.util.List;

/**
 * @author Mauricio Pinchao
 * @datetime 26/12/2017 - 5:02:37 p. m.
 */
public class PaginatorComponent {
	
	private static final int DEFAULT_RECORDS_NUMBER = 6;
	private static final int DEFAULT_PAGE_INDEX = 1;

	private int records;
	private int recordsTotal;
	private int pageIndex;
	private int pages;
	private boolean hasPrev;
	private boolean hasNext = true;
	
	private List<?> origModel;
	private List<?> model;

	public PaginatorComponent(List<?> model) {
		this.origModel = model;
		this.records = DEFAULT_RECORDS_NUMBER;
		this.pageIndex = DEFAULT_PAGE_INDEX;
		this.recordsTotal = model.size();
		if (records > 0) {
			pages = records <= 0 ? 1 : recordsTotal / records;
			if (recordsTotal % records > 0) {
				pages++;
			}
			if (pages == 0) {
				pages = 1;
			}
		} else {
			records = 1;
			pages = 1;
		}
		updateModel();
	}

	public void updateModel() {
		int fromIndex = getFirst();
		int toIndex = getFirst() + records;
		if (toIndex > this.recordsTotal) {
			toIndex = this.recordsTotal;
		}
		this.model = origModel.subList(fromIndex, toIndex);
	}

	public void next() {
		if (this.pageIndex < pages) {
			this.pageIndex++;
			hasNext = true;
			hasPrev = true;
		} 
		if(this.pageIndex == pages) {
			hasNext = false;
			hasPrev = true;
		}
		updateModel();
	}

	public void prev() {
		if (this.pageIndex > 1) {
			this.pageIndex--;
			hasPrev = true;
			hasNext = true;
		} 
		if(this.pageIndex == 1) {
			hasPrev = false;
			hasNext = true;
		}
		updateModel();
	}

	public int getRecords() {
		return records;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPages() {
		return pages;
	}

	public int getFirst() {
		return (pageIndex * records) - records;
	}

	public List<?> getModel() {
		return model;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}

	public boolean isHasNext() {
		return hasNext;
	}
}
