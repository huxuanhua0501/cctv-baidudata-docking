package com.busap.jpa;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.internal.util.StringHelper;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class SpringNamingStrategy extends ImprovedNamingStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9169691959086448981L;

	@Override
	public String foreignKeyColumnName(String propertyName, String propertyEntityName,
			String propertyTableName, String referencedColumnName) {
		String name = propertyTableName;
		if (propertyName != null) {
			name = StringHelper.unqualify(propertyName);
		}
		Assert.state(StringUtils.hasLength(name),
				"Unable to generate foreignKeyColumnName");
		return columnName(name) + "_" + referencedColumnName;
	}

}
