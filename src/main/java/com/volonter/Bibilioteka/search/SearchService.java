package com.volonter.Bibilioteka.search;

import com.volonter.Bibilioteka.entities.Knjiga;
import com.volonter.Bibilioteka.repositories.KnjigaRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class SearchService {
    private List<Knjiga> knjige;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private KnjigaRepo knjigaRepo;

    public List<Knjiga> knjigePoKriterijumu(Map<String,Object> fieldCriteria) {
        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Knjiga> query = builder.createQuery(Knjiga.class);

        Root<Knjiga> knjiga = query.from(Knjiga.class);

        fieldCriteria.forEach((fieldName, fieldValue) -> predicates.add((Predicate) builder.equal(knjiga.get(fieldName), fieldValue)));
        query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
