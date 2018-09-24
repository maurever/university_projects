package org.wpa.BO;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Senator.class)
public abstract class Senator_ extends org.wpa.BO.Participation_ {

	public static volatile SingularAttribute<Senator, Committe> committe;
	public static volatile SingularAttribute<Senator, District> district;
	public static volatile SingularAttribute<Senator, State> state;
	public static volatile SingularAttribute<Senator, Fraction> fraction;

}

