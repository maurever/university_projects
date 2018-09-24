package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Manager.class)
public abstract class Manager_ extends org.wpa.BO.Person_ {

	public static volatile ListAttribute<Manager, Competency> competencies;
	public static volatile ListAttribute<Manager, Management> managing;

}

