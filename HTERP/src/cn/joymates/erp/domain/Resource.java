package cn.joymates.erp.domain;

import java.util.List;
import java.util.Objects;

import cn.joymates.erp.domain.base.BaseResource;

public class Resource extends BaseResource implements Comparable<Resource> {
private List<Resource> childrenResouce;
	
	public List<Resource> getChildrenResouce() {
		return childrenResouce;
	}

	public void setChildrenResouce(List<Resource> childrenResouce) {
		this.childrenResouce = childrenResouce;
	}

	@Override
	public int compareTo(Resource o) {
		String id1 = this.getResourceId();
		String id2 = o.getResourceId();
		return id1.compareTo(id2);
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this.getResourceId(), ((Resource)obj).getResourceId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getResourceId());
	}
}
