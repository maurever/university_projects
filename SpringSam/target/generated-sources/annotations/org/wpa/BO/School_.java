package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(School.class)
public abstract class School_ {

	public static volatile SingularAttribute<School, String> city;
	public static volatile SingularAttribute<School, String> street;
	public static volatile SingularAttribute<School, String> streetCode;
	public static volatile SingularAttribute<School, Integer> postalCode;
	public static volatile SingularAttribute<School, Long> id;
	public static volatile ListAttribute<School, Participation> paticipations;
	public static volatile SingularAttribute<School, String> schoolName;

}

