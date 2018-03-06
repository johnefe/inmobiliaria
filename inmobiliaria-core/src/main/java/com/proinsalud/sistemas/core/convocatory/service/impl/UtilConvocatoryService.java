package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.ICommentOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.dao.ICommentProductOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.dao.IConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.dao.IConvocatoryProductDao;
import com.proinsalud.sistemas.core.convocatory.dao.IOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.dao.IProductDao;
import com.proinsalud.sistemas.core.convocatory.dao.IProductOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.dao.IProviderDao;
import com.proinsalud.sistemas.core.convocatory.dao.IQuotationConvocatoryProductDao;
import com.proinsalud.sistemas.core.convocatory.dao.IQuotationDao;
import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryTyPeConvocatoryUsersDao;
import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderDao;
import com.proinsalud.sistemas.core.convocatory.dao.IUtilConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.CommentOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.CommentProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.OrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Product;
import com.proinsalud.sistemas.core.convocatory.model.ProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.StepOrder;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTyPeConvocatoryUsers;
import com.proinsalud.sistemas.core.convocatory.service.IUtilConvocatoryService;
import com.proinsalud.sistemas.core.general.dao.IPersonaDao;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.util.enums.StateConvocatoryEnum;

/**
 * 
 * @author Andres Santacruz
 * @datetime 19/12/2017 - 8:47:13 a. m.
 *
 */
@Repository(value = "utilConvocatoryService")
public class UtilConvocatoryService implements IUtilConvocatoryService, Serializable {

	private static final long serialVersionUID = 1198339931674510611L;

	@Autowired(required = true)
	@Qualifier(value = "convocatoryDao")
	private IConvocatoryDao iConvocatoryDao;

	@Autowired(required = true)
	@Qualifier(value = "convocatoryProductDao")
	private IConvocatoryProductDao iConvocatoryProductDao;

	@Autowired(required = true)
	@Qualifier(value = "quotationDao")
	private IQuotationDao iQuotationDao;

	@Autowired(required = true)
	@Qualifier(value = "quotationConvocatoryProductDao")
	private IQuotationConvocatoryProductDao iQuotationConvocatoryProductDao;

	@Autowired(required = true)
	@Qualifier(value = "personaDao")
	private IPersonaDao iPersonaDao;

	@Autowired(required = true)
	@Qualifier(value = "providerDao")
	private IProviderDao iProviderDao;

	@Autowired(required = true)
	@Qualifier(value = "utilConvocatoryDao")
	private IUtilConvocatoryDao iUtilConvocatoryDao;

	@Autowired(required = true)
	@Qualifier(value = "productDao")
	private IProductDao iProductDao;

	@Autowired(required = true)
	@Qualifier(value = "orderBuyDao")
	private IOrderBuyDao iOrderBuyDao;

	@Autowired(required = true)
	@Qualifier(value = "commentOrderBuyDao")
	private ICommentOrderBuyDao iCommentOrderBuyDao;

	@Autowired(required = true)
	@Qualifier(value = "commentProductOrderBuyDao")
	private ICommentProductOrderBuyDao iCommentProductOrderBuyDao;

	@Autowired(required = true)
	@Qualifier(value = "productOrderBuyDao")
	private IProductOrderBuyDao iProductOrderBuyDao;

	@Autowired(required = true)
	@Qualifier(value = "stepOrderDao")
	private IStepOrderDao iStepOrderDao;

	@Autowired(required = true)
	@Qualifier(value = "stepOrderConvocatoryTyPeConvocatoryUsersDao")
	private IStepOrderConvocatoryTyPeConvocatoryUsersDao iStepOrderConvocatoryTyPeConvocatoryUsersDao;
	

	@Transactional
	public void saveConvocatory(Convocatory convocatory, List<ConvocatoryProduct> convocatoryProducts, List<Quotation> quotations, List<Product> products) throws Exception {
		if (convocatory.getId() == null) {
			convocatory.setState(StateConvocatoryEnum.Creada);
			convocatory = iConvocatoryDao.persistEntity(convocatory);
			iProductDao.persistEntity(products);
			iConvocatoryProductDao.persistEntity(convocatoryProducts);
			iQuotationDao.persistEntity(quotations);
		} else {
			List<Quotation> newQ = new ArrayList<>();
			for (Quotation q : quotations) {
				boolean isNew = true;
				for (Quotation q2 : convocatory.getQuotations()) {
					if (q.getId().equals(q2.getId())) {
						convocatory.getQuotations().remove(q2);
						isNew = false;
						break;
					}
				}
				if (isNew) {
					newQ.add(q);
				}
			}

			List<ConvocatoryProduct> deleteCP = convocatory.getConvocatoryProducts();
			List<Product> productsDel = new ArrayList<>();
			deleteCP.forEach(cp -> productsDel.add(cp.getProduct()));
			List<Quotation> deleteQuotation = convocatory.getQuotations();
			convocatory.setConvocatoryProducts(new ArrayList<>());
			convocatory.setQuotations(new ArrayList<>());
			convocatory = iConvocatoryDao.mergeEntity(convocatory);

			iConvocatoryProductDao.deleteEntity(deleteCP);
			iProductDao.deleteEntity(productsDel);

			iProductDao.persistEntity(products);
			iConvocatoryProductDao.persistEntity(convocatoryProducts);

			iQuotationDao.deleteEntity(deleteQuotation);
			iQuotationDao.persistEntity(newQ);

		}
	}

	@Transactional
	public void deleteConvocatory(Convocatory convocatory) throws Exception {
		List<QuotationConvocatoryProduct> deleteQCP = new ArrayList<>();
		for (Quotation q : convocatory.getQuotations()) {
			deleteQCP.addAll(q.getQuotationConvocatoryProducts());
		}
		List<Product> products = new ArrayList<>();
		convocatory.getConvocatoryProducts().forEach(cp -> products.add(cp.getProduct()));

		iQuotationConvocatoryProductDao.deleteEntity(deleteQCP);
		iConvocatoryProductDao.deleteEntity(convocatory.getConvocatoryProducts());
		iProductDao.deleteEntity(products);
		iQuotationDao.deleteEntity(convocatory.getQuotations());
		iConvocatoryDao.deleteEntity(convocatory);
	}

	@Transactional
	public void saveProvider(Provider provider) throws Exception {
		if (provider.getId() == null) {
			Person person = new Person();
			person.setFirstName(provider.getName());
			person.setLastName(provider.getName());
			person.setAddress(provider.getAddress());
			person.setEmail(provider.getEmail());
			person.setTelefono(provider.getPhone());
			person.setIdentification(provider.getNit());
			iPersonaDao.persistEntity(person);
			provider.setPerson(person);
			provider = iProviderDao.persistEntity(provider);
		} else {
			Person person = provider.getPerson();
			person.setFirstName(provider.getName());
			person.setAddress(provider.getAddress());
			person.setEmail(provider.getEmail());
			person.setTelefono(provider.getPhone());
			person.setIdentification(provider.getNit());
			iPersonaDao.mergeEntity(person);
			provider = iProviderDao.persistEntity(provider);
		}

	}

	@Transactional(propagation = Propagation.NEVER)
	public void persistStateTypeConvocatory(String type) throws Exception {
		iUtilConvocatoryDao.persistStateTypeConvocatory(type);
	}

	@Transactional
	public OrderBuy saveOrderBuy(OrderBuy entity, Users user, List<ProductOrderBuy> lstProductoPersist) throws Exception {
		CommentOrderBuy commentOrderBuy = null;
		if (entity.getComment() != null && !entity.getComment().isEmpty()) {
			commentOrderBuy = new CommentOrderBuy(entity.getComment(), user, entity);
		}
		if (entity.getId() == null) {
			entity = iOrderBuyDao.persistEntity(entity);
			
			StepOrder next = new StepOrder();
			next.setUser(user);
			next.setOrderBuy(entity);
			next.setAprobed(false);
			next.setComment("SE REGISTRO LA ORDEN DE COMPRA");
			List<StepOrderConvocatoryTyPeConvocatoryUsers> lstSOCTC = iStepOrderConvocatoryTyPeConvocatoryUsersDao.findAllEntityByIdUser(user.getId());
			for (StepOrderConvocatoryTyPeConvocatoryUsers s: lstSOCTC) {
				if(s.getStepOCTC().getPosition() == 1 ) {
					next.setStepOCTC(s.getStepOCTC());
					break;
				}
			}
			iStepOrderDao.persistEntity(next);

			if (commentOrderBuy != null) {
				iCommentOrderBuyDao.persistEntity(commentOrderBuy);
			}
			iProductOrderBuyDao.persistEntity(lstProductoPersist);
			List<CommentProductOrderBuy> commentsProduct = new ArrayList<>();
			for (ProductOrderBuy pob : lstProductoPersist) {
				if (pob.getComment() != null && !pob.getComment().isEmpty()) {
					commentsProduct.add(new CommentProductOrderBuy(pob.getComment(), pob, user));
				}
			}
			iCommentProductOrderBuyDao.persistEntity(commentsProduct);
		} else {
			List<ProductOrderBuy> lstProductoDelete = new ArrayList<>();
			List<ProductOrderBuy> lstSaved = new ArrayList<>(entity.getProductsOrder());
			List<CommentProductOrderBuy> lstCommentsProductPersist = new ArrayList<>();

			entity.setProductsOrder(new ArrayList<>());
			entity = iOrderBuyDao.mergeEntity(entity);
			if (commentOrderBuy != null) {
				iCommentOrderBuyDao.persistEntity(commentOrderBuy);
			}

			for (ProductOrderBuy p2 : lstSaved) {
				boolean delete = true;
				for (ProductOrderBuy p : lstProductoPersist) {
					if (p.getId() != null) {
						if (p2.getId().equals(p.getId())) {
							if (p.getComment() != null && !p.getComment().isEmpty()) {
								CommentProductOrderBuy c = new CommentProductOrderBuy(p.getComment(), p2, user);
								lstCommentsProductPersist.add(c);
							}
							lstProductoPersist.remove(p);
							delete = false;
							break;
						}
					}
				}
				if (delete) {
					lstProductoDelete.add(p2);
				}
			}

			if (!lstProductoPersist.isEmpty()) {
				iProductOrderBuyDao.persistEntity(lstProductoPersist);
				for (ProductOrderBuy p : lstProductoPersist) {
					if (p.getComment() != null && !p.getComment().isEmpty()) {
						{
							CommentProductOrderBuy c = new CommentProductOrderBuy(p.getComment(), p, user);
							lstCommentsProductPersist.add(c);
						}
					}
				}
			}
			iProductOrderBuyDao.deleteEntity(lstProductoDelete);
			iCommentProductOrderBuyDao.persistEntity(lstCommentsProductPersist);
		}
		return entity;
	}

}
