package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Rank.class)
public abstract class Rank_ {

	public static volatile SingularAttribute<Rank, String> rankName;
	public static volatile ListAttribute<Rank, Management> managements;
	public static volatile SingularAttribute<Rank, Long> id;

}

