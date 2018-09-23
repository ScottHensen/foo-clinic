package com.scotthensen.fooclinic.service;

import java.util.Set;

public interface CrudSvc<T, ID> 
{
	Set<T> findAll();
	
	T findById(ID id);
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);
}
