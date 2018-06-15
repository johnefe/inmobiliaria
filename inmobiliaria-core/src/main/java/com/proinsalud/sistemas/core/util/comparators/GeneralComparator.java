package com.proinsalud.sistemas.core.util.comparators;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import com.proinsalud.sistemas.core.convocatory.model.CommentOrderBuy;
//import com.proinsalud.sistemas.core.convocatory.model.CommentProductOrderBuy;
//import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;
//import com.proinsalud.sistemas.core.convocatory.model.StepOrder;
//import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTypeConvocatory;
//import com.proinsalud.sistemas.core.documentary_management.model.FileLocation;
//import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.human_talent.model.Area;
import com.proinsalud.sistemas.core.security.model.UserHistorial;

/**
 * @author Andres Santacruz
 * @datetime 7/02/2018 - 5:22:58 p. m.
 *
 */
public class GeneralComparator {

	

	/**
	 * Metodo encargado para organizar las listas que son recursivas (padre - hijo)
	 * @author Andres Santacruz
	 * @datetime 16/02/2018 - 7:39:00 a. m.
	 *
	 * @param comparator
	 * @param lst
	 * @param nameMethod
	 */
	@SuppressWarnings("unchecked")
	public static void organized(@SuppressWarnings("rawtypes") Comparator comparator, List<? extends Object> lst, String nameMethod) {
		try {
			Collections.sort(lst, comparator);
			for (Object obj : lst) {
				Method method = obj.getClass().getMethod(nameMethod);
				List<? extends Object> childs = (List<? extends Object>) method.invoke(obj);
				if (!lst.isEmpty()) {
					organized(comparator, childs, nameMethod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//public static Comparator<FileLocation> FileLocationCompareByName = (a, b) -> a.getNameFileLocation().compareTo(b.getNameFileLocation());

	//public static Comparator<Trd> TrdCompareByName = (a, b) -> a.getTrdItem().getName().compareTo(b.getTrdItem().getName());

	public static Comparator<Area> AreaCompareByName = (a, b) -> a.getName().compareTo(b.getName());
	
	public static Comparator<UserHistorial> UserHistorialCompareByDateAccessDesc = (a, b) -> b.getDateAccess().compareTo(a.getDateAccess());
	
	//public static Comparator<CommentOrderBuy> CommentsOrderBuyByRegisteredDesc = (a, b) -> b.getRegistered().compareTo(a.getRegistered());
	
	//public static Comparator<CommentProductOrderBuy> CommentsProductOrderBuyByRegisteredDesc = (a, b) -> b.getRegistered().compareTo(a.getRegistered());
	
	//public static Comparator<QuotationConvocatoryProduct> QuotationConvocatoryProductByPrice = (pa, pb) -> (pa.getPrice().compareTo( pb.getPrice()));
	
	//public static Comparator<StepOrder> StepOrderByDateRegisteredDesc = (a, b) -> (b.getRegistered().compareTo( a.getRegistered()));
	
	//public static Comparator<StepOrderConvocatoryTypeConvocatory> StepOrderConvocatoryTypeConvocatoryByPosition =  (a, b) -> (a.getPosition().compareTo( b.getPosition()));;
}
