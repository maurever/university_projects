package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile ListAttribute<Person, Participation> participing;
	public static volatile SingularAttribute<Person, String> firstName;
	public static volatile SingularAttribute<Person, String> lastName;
	public static volatile SingularAttribute<Person, String> salt;
	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SingularAttribute<Person, String> passHash;
	public static volatile SingularAttribute<Person, String> email;

}

