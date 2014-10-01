package com.likya.commons.utils;

import java.util.ArrayList;

public class LimitedArrayList<E> extends ArrayList<E> {

	public LimitedArrayList() {
		super();
	}

	public LimitedArrayList(int maxLength) {
		super();
		setMaxLength(maxLength);
	}

	private static final long serialVersionUID = 238155686014345308L;

	private int maxLength = -1;

	@Override
	public boolean add(E e) {
		if (maxLength > 0 && this.size() == maxLength) {
			super.remove(0);
		}
		return super.add(e);
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		if (maxLength < 1) {
			throw new IllegalArgumentException("Illegal Capacity: " + maxLength);
		}
		this.maxLength = maxLength;
	}

}
