package org.wpa.BO;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Journalist.class)
public abstract class Journalist_ extends org.wpa.BO.Participation_ {

	public static volatile SingularAttribute<Journalist, Organization> organization;

}

