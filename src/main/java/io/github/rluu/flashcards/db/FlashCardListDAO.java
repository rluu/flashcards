package io.github.rluu.flashcards.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.github.rluu.flashcards.api.FlashCardList;

public class FlashCardListDAO extends AbstractDAO<FlashCardList> {
    private static final Logger log = LoggerFactory.getLogger(FlashCardListDAO.class);

    public FlashCardListDAO(SessionFactory factory) {
	super(factory);
    }

    @Timed
    @UnitOfWork
    public FlashCardList getFlashCardListById(Long flashCardListId) {
	return get(flashCardListId);
    }

    @Timed
    @UnitOfWork
    public FlashCardList createFlashCardList(FlashCardList flashCardList) {
	return persist(flashCardList);
    }

    @Timed
    @UnitOfWork
    public FlashCardList saveOrUpdateFlashCardList(FlashCardList flashCardList) {
	return persist(flashCardList);
    }

    @Timed
    @UnitOfWork
    public void deleteFlashCardList(FlashCardList flashCardList) {
	if (flashCardList != null && flashCardList.getId() != null) {
	    Long flashCardListId = flashCardList.getId();
	    Session session = currentSession();
	    Query query;
	    int result;

	    query = session.createQuery("delete flshcrd where flshcrd_id in (select flshcrd_id from flshcrd_lst_flshcrd where flshcrd_lst_id = :flashCardListId)");
	    query.setParameter("flashCardListId", flashCardListId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table flshcrd for flashCardListId: " + flashCardListId);
	   
	    query = session.createQuery("delete usr_flshcrd_lst where flshcrd_lst_id = :flashCardListId");
	    query.setParameter("flashCardListId", flashCardListId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table usr_flshcrd_lst for flashCardListId: " + flashCardListId);
	    
	    query = session.createQuery("delete flshcrd_lst where flshcrd_lst_id = :flashCardListId");
	    query.setParameter("flashCardListId", flashCardListId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table flshcrd_lst for flashCardListId: " + flashCardListId);
	}
    }
}
