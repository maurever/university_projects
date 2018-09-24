package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Committe.class)
public abstract class Committe_ {

	public static volatile ListAttribute<Committe, Lobbyist> lobbyists;
	public static volatile ListAttribute<Committe, Management> managements;
	public static volatile ListAttribute<Committe, Deputy> deputies;
	public static volatile SingularAttribute<Committe, String> committeName;
	public static volatile SingularAttribute<Committe, Long> id;
	public static volatile ListAttribute<Committe, Senator> senators;

}

