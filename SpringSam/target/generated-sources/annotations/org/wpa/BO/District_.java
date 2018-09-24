package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(District.class)
public abstract class District_ {

	public static volatile SingularAttribute<District, String> districtName;
	public static volatile SingularAttribute<District, Long> id;
	public static volatile ListAttribute<District, Senator> senators;

}

