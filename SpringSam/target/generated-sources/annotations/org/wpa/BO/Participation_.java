package org.wpa.BO;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Participation.class)
public abstract class Participation_ {

	public static volatile SingularAttribute<Participation, School> school;
	public static volatile SingularAttribute<Participation, Boolean> accepted;
	public static volatile SingularAttribute<Participation, Year> yearObj;
	public static volatile SingularAttribute<Participation, Person> participant;

}

