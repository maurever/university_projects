package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PoliticalParty.class)
public abstract class PoliticalParty_ {

	public static volatile ListAttribute<PoliticalParty, Management> managements;
	public static volatile SingularAttribute<PoliticalParty, Long> id;
	public static volatile ListAttribute<PoliticalParty, Fraction> fractions;
	public static volatile SingularAttribute<PoliticalParty, String> politicalartyName;

}

