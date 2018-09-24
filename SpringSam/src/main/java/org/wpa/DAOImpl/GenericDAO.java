package org.wpa.DAOImpl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic DAO for comucation with Data Model. Define basic operation to acces
 * to database. Cominicate via EntityManager.
 *
 * @author Vit Hlavacek <hlava.vit at google.com> & Veronika Maurerova <veronika
 * at maurerova.cz>
 */
@Repository("genericDao")
@Transactional(propagation = Propagation.SUPPORTS)
public class GenericDAO {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    /**
     * Find uniq result in database using WHERE. Find unig result, if not write
     * message to logger.
     *
     * @param <ENTITY>
     * @param clazz
     * @param attrName
     * @param attrVal
     * @return ENTITY entity;
     */
    @Transactional(readOnly = true)
    public <ENTITY> ENTITY findUniqBy(Class<ENTITY> clazz, String attrName, String attrVal) {
        List<ENTITY> list = findBy(clazz, attrName, attrVal);
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.WARNING, "A query with unique attribute returned not unique result!");
            return null;
        }
        return list.get(0);
    }

    /**
     * Find results in database using WHERE. If attrVal is String (not boolean
     * or number) put it into ''.
     *
     * @param <ENTITY>
     * @param clazz
     * @param attrName
     * @param attrVal
     * @return List<ENTITY> of results;
     */
    @Transactional(readOnly = true)
    public <ENTITY> List<ENTITY> findBy(Class<ENTITY> clazz, String attrName, String attrVal) {
        if (!(attrVal.matches("\\d+") || attrVal.equals("true") || attrVal.equals("false"))) {
            attrVal = "'" + attrVal + "'";
        }
        return (List<ENTITY>) em.createQuery("select e from " + clazz.getSimpleName() + " e WHERE e." + attrName + " = " + attrVal).getResultList();
    }

    /**
     * Find results for more than one attribut.
     *
     * @param <ENTITY>
     * @param clazz
     * @param mapAttributs
     * @return
     */
    @Transactional(readOnly = true)
    public <ENTITY> List<ENTITY> findByMultyWhere(Class<ENTITY> clazz, Map<String, String> mapAttributs) {
        String query = "select e from " + clazz.getSimpleName() + " e WHERE ";
        for (Map.Entry<String, String> entry : mapAttributs.entrySet()) {
            String attrName = entry.getKey();
            String attrVal = entry.getValue();
            if (!(attrVal.matches("\\d+") || attrVal.equals("true") || attrVal.equals("false"))) {
                attrVal = "'" + attrVal + "'";
            }
            query += "e." + attrName + " = " + attrVal + " AND ";
        }
        if (!mapAttributs.isEmpty()) {
            query = query.subSequence(0, query.length() - 4).toString();
        }
        return (List<ENTITY>) em.createQuery(query).getResultList();
    }

    /**
     * Get results using Named Query.
     *
     * @param <ENTITY>
     * @param NamedQuery
     * @param clazz
     * @return List<ENTITY> of results;
     */
    @Transactional(readOnly = true)
    public <ENTITY> List<ENTITY> getByNamedQuery(String NamedQuery, Class<ENTITY> clazz) {
        Query query = em.createNamedQuery(NamedQuery, clazz);
        List<ENTITY> results = query.getResultList();
        return results;
    }

    /**
     * Get all results by entity class.
     *
     * @param <ENTITY>
     * @param clazz
     * @return List<ENTITY> of results;
     */
    @Transactional(readOnly = true)
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
        List<ENTITY> list = (List<ENTITY>) em.createQuery("select e from " + clazz.getSimpleName() + " e").getResultList();
        return list;
    }

    /**
     * Get ordering results by entity class.
     *
     * @param <ENTITY>
     * @param clazz
     * @param orderBy
     * @param ascending
     * @return List<ENTITY> of results;
     */
    @Transactional(readOnly = true)
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz, String orderBy, boolean ascending) {
        String inOrder;
        if (ascending) {
            inOrder = "ASC";
        } else {
            inOrder = "DESC";
        }
        List<ENTITY> list = (List<ENTITY>) em.createQuery("select e from " + clazz.getSimpleName() + " e order by " + orderBy + " " + inOrder).getResultList();
        return list;
    }

    /**
     * Find entity by primary key.
     *
     * @param <ENTITY>
     * @param clazz
     * @param key
     * @return ENTITY entity;
     */
    @Transactional(readOnly = true)
    public <ENTITY> ENTITY getByKey(Class<ENTITY> clazz, Object key) {
        if (key == null || clazz == null) {
            return null;
        }
        return em.find(clazz, key);

    }

    /**
     * Remove all records specific entity.
     *
     * @param <ENTITY>
     * @param clazz
     */
    @Transactional(readOnly = false)
    public <ENTITY> void removeAll(Class<ENTITY> clazz) {
        List<ENTITY> entities = this.getAll(clazz);
        for (ENTITY entity : entities) {
            em.remove(entity);
        }
    }

    /**
     * Remove specific record by uniq key.
     *
     * @param <ENTITY>
     * @param id
     * @param clazz
     */
    @Transactional(readOnly = false)
    public <ENTITY> void removeById(long id, Class<ENTITY> clazz) {
        ENTITY e = em.find(clazz, id);
        if (e != null) {
            em.remove(e);
        }
    }

    /**
     * Persist specific entity.
     *
     * @param <ENTITY>
     * @param entity
     * @return entity to be persisted;
     */
    @Transactional(readOnly = false)
    public <ENTITY> ENTITY persistEntity(ENTITY entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Persist all entity in the list. Doesn´t work for entities referencing the
     * same values.
     *
     * @param <ENTITY>
     * @param entity
     */
    @Transactional(readOnly = false)
    public <ENTITY> void persistAll(List<ENTITY> entity) {
        for (ENTITY e : entity) {
            em.persist(e);
        }
    }

    /**
     * Merge specific entity.
     *
     * @param <ENTITY>
     * @param entity
     * @return entity tobe merged;
     */
    @Transactional(readOnly = false)
    public <ENTITY> ENTITY mergeEntity(ENTITY entity) {
        ENTITY ret = em.merge(entity);
        return ret;
    }

    /**
     * Merge all entities in the list.
     *
     * @param <ENTITY>
     * @param entities
     */
    @Transactional(readOnly = false)
    public <ENTITY> void mergeAll(List<ENTITY> entities) {
        for (ENTITY e : entities) {
            em.merge(e);
        }
    }
}
