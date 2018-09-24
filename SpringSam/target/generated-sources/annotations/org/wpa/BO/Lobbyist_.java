package org.wpa.BO;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Lobbyist.class)
public abstract class Lobbyist_ extends org.wpa.BO.Participation_ {

	public static volatile SingularAttribute<Lobbyist, Committe> committe;
	public static volatile SingularAttribute<Lobbyist, Organization> organization;

}

