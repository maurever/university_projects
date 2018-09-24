package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Competency.class)
public abstract class Competency_ {

	public static volatile SingularAttribute<Competency, String> name;
	public static volatile SingularAttribute<Competency, String> description;
	public static volatile SingularAttribute<Competency, Long> id;
	public static volatile ListAttribute<Competency, Manager> managers;

}

