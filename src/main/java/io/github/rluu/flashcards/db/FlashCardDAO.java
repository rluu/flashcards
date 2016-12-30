package io.github.rluu.flashcards.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.github.rluu.flashcards.api.FlashCard;

public class FlashCardDAO extends AbstractDAO<FlashCard> {
    private static final Logger log = LoggerFactory.getLogger(FlashCardDAO.class);

    public FlashCardDAO(SessionFactory factory) {
	super(factory);
    }

    @Timed
    @UnitOfWork
    public FlashCard getFlashCardById(Long flashCardId) {
	return get(flashCardId);
    }

    @Timed
    @UnitOfWork
    public FlashCard createFlashCard(FlashCard flashCard) {
	return persist(flashCard);
    }

    @Timed
    @UnitOfWork
    public FlashCard saveOrUpdateFlashCard(FlashCard flashCard) {
	return persist(flashCard);
    }

    @Timed
    @UnitOfWork
    public void deleteFlashCard(FlashCard flashCard) {
	if (flashCard != null && flashCard.getId() != null) {
	    Long flashCardId = flashCard.getId();
	    Session session = currentSession();
	    Query query;
	    int result;

	    query = session.createQuery("delete flshcrd_lst_flshcrd where flshcrd_id = :flashCardId");
	    query.setParameter("flashCardId", flashCardId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table flshcrd_lst_flshcrd for flashCardId: " + flashCardId);
	   
	    query = session.createQuery("delete flshcrd where flshcrd_id = :flashCardId");
	    query.setParameter("flashCardId", flashCardId);
	    result = query.executeUpdate();
	    log.trace("Deleted " + result + " rows from table flshcrd for flashCardId: " + flashCardId);
	}
    }
}
