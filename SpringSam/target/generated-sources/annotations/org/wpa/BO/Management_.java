package org.wpa.BO;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Management.class)
public abstract class Management_ {

	public static volatile SingularAttribute<Management, Committe> committe;
	public static volatile SingularAttribute<Management, Manager> manager;
	public static volatile SingularAttribute<Management, PoliticalParty> politicalParty;
	public static volatile SingularAttribute<Management, Rank> rank;
	public static volatile SingularAttribute<Management, State> state;
	public static volatile SingularAttribute<Management, Year> yearObj;

}

