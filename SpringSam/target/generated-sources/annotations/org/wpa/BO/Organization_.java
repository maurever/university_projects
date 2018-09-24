package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Organization.class)
public abstract class Organization_ {

	public static volatile SingularAttribute<Organization, String> organizationName;
	public static volatile ListAttribute<Organization, Lobbyist> lobbyists;
	public static volatile SingularAttribute<Organization, String> description;
	public static volatile SingularAttribute<Organization, Long> id;
	public static volatile SingularAttribute<Organization, Integer> type;
	public static volatile ListAttribute<Organization, Journalist> journalists;

}

