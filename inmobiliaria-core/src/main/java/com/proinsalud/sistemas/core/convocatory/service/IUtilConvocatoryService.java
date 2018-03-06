package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.OrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Product;
import com.proinsalud.sistemas.core.convocatory.model.ProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.security.model.Users;

public interface IUtilConvocatoryService {

	public void saveConvocatory(Convocatory convocatory, List<ConvocatoryProduct> convocatoryProducts, List<Quotation> quotations , List<Product> products) throws Exception;
	
	public void deleteConvocatory(Convocatory convocatory) throws Exception;
	
	public void saveProvider(Provider provider) throws Exception;
	
	public void persistStateTypeConvocatory(String type) throws Exception;
	
	public OrderBuy saveOrderBuy(OrderBuy entity, Users user, List<ProductOrderBuy> lstProductOrderBuy) throws Exception;

}
