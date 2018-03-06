package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IUsersDao;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.OptionAction;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.model.UserOptionAction;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;
import com.proinsalud.sistemas.core.util.comparators.OptionComparator;

/**
 * 
 * @author Andres Santacruz
 * @datetime 22/12/2017 - 3:01:00 p. m.
 *
 */
@Repository(value = "userDao")
public class UsersDao extends GenericDao<Long, Users> implements IUsersDao, Serializable {

	private static final long serialVersionUID = -7310296740975868889L;

	public Users persistEntity(Users entity) {
		return super.persist(entity);
	}

	public Users mergeEntity(Users entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Users entity) {
		super.delete(entity);
	}

	public List<Users> findAllEntity() {
		return super.findAll();
	}

	public Users findEntityById(Long id) {
		return super.findById(id);
	}

	public Users findEntityByUsername(String username) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<Users> users = executeNamedQuery("Users.findEntityByUsername", params);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public Users findUserCompletely(String username) {
		long start = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder();
		builder.append("---------BEGIN: " + start);
		Users user = findEntityByUsername(username);
		if (user != null) {
			List<Option> options = new ArrayList<>();
			user.getUserOptions().forEach(uo-> options.add(uo.getOption()));

			List<Option> organized = organizeOptions(options, null, user.getUserOptions());

			GeneralComparator.organized(OptionComparator.byName, organized, "getOptions");
			
			List<UserOption> userOptions = new ArrayList<>();
			organized.forEach(o -> userOptions.add(new UserOption(o, null)));

			user.setUserOptions(userOptions);
			long fin = System.currentTimeMillis();
			builder.append("---------FINISH: " + fin);
			builder.append("---------TIME ORGANIZED OPTIONS " + (fin - start));
			System.out.println(builder.toString());
			printOptionsRecursive(organized, "");
			return user;
		}
		return null;
	}

	@Override
	public List<Users> findUsersProfileTemplate() {
		List<Users> users = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		users = executeNamedQuery("Users.findUsersProfileTemplate", params);
		return users;
	}

	public void printOptionsRecursive(List<Option> options, String tab) {
		for (Option option : options) {
			System.out.println(tab + option.getLevel().getLevelPos() + ". " + option.getName());
			if (!option.getOptionActions().isEmpty()) {
				for (OptionAction oa : option.getOptionActions()) {
					System.out.println(tab + "\t-" + oa.getAction().getAction());
				}
			}
			printOptionsRecursive(option.getOptions(), tab + "\t");
		}
	}

	public List<Option> organizeOptions(List<Option> options, Option father, List<UserOption> userOptions) {
		List<Option> organized = new ArrayList<>();
		for (Option opt : options) {
			if (opt.getOptionFather() == null && father == null) {
				opt.setOptions(organizeOptions(options, opt, userOptions));
				opt.setOptionActions(getOptionsAction(userOptions, opt));
				organized.add(opt);
			} else if (father != null && opt.getOptionFather() != null && opt.getOptionFather().getId() == father.getId()) {
				opt.setOptions(organizeOptions(options, opt, userOptions));
				opt.setOptionActions(getOptionsAction(userOptions, opt));
				organized.add(opt);
			}
		}
		return organized;
	}

	public List<OptionAction> getOptionsAction(List<UserOption> userOptions, Option option) {
		List<OptionAction> optionActions = new ArrayList<>();
		for (UserOption userOption : userOptions) {
			if (userOption.getOption() == option) {
				for (UserOptionAction uoa : userOption.getUserOptionActions()) {
					optionActions.add(uoa.getOptionAction());
				}
				userOptions.remove(userOption);
				return optionActions;
			}
		}
		return optionActions;
	}

}
