package com.proinsalud.sistemas.core.util.comparators;

import java.util.Comparator;

import com.proinsalud.sistemas.core.general.model.Person;

/**
 * @author Andres Santacruz
 * @datetime 7/02/2018 - 4:18:06 p. m.
 *
 */
public class PersonComparator {

	public static Comparator<Person> byLastName = (pa, pb) -> pa.getLastName().compareTo(pb.getLastName());

	public static Comparator<Person> byLastSecondName = byLastName.thenComparing((pa, pb) -> pa.getSecondLastName().compareTo(pb.getSecondLastName()));

	public static Comparator<Person> byNameCompleted = (pa, pb) -> pa.getNameCompleted().compareTo(pb.getNameCompleted());

	public static Comparator<Person> byIdentificaction = (pa, pb) -> pa.getIdentification().compareTo(pb.getIdentification());
}
