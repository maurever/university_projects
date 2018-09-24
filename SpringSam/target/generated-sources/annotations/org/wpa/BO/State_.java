package org.wpa.BO;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(State.class)
public abstract class State_ {

	public static volatile SingularAttribute<State, String> stateName;
	public static volatile ListAttribute<State, Management> managements;
	public static volatile ListAttribute<State, Deputy> deputies;
	public static volatile SingularAttribute<State, Long> id;
	public static volatile ListAttribute<State, Senator> senators;

}

