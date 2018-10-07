package com.scotthensen.fooclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.Visit;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> 
{

}
