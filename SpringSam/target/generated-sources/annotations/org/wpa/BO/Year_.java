package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Year.class)
public abstract class Year_ {

	public static volatile ListAttribute<Year, Participation> participing;
	public static volatile SingularAttribute<Year, Integer> yearInt;
	public static volatile SingularAttribute<Year, Boolean> opened;
	public static volatile ListAttribute<Year, Management> managing;

}

