package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Fraction.class)
public abstract class Fraction_ {

	public static volatile SingularAttribute<Fraction, String> fractionName;
	public static volatile SingularAttribute<Fraction, PoliticalParty> politicalParty;
	public static volatile ListAttribute<Fraction, Deputy> deputies;
	public static volatile SingularAttribute<Fraction, Long> id;
	public static volatile ListAttribute<Fraction, Senator> senators;

}

