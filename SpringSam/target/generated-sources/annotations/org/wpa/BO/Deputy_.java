package org.wpa.BO;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Deputy.class)
public abstract class Deputy_ extends org.wpa.BO.Participation_ {

	public static volatile SingularAttribute<Deputy, Committe> committe;
	public static volatile SingularAttribute<Deputy, State> state;
	public static volatile SingularAttribute<Deputy, Fraction> fraction;

}

