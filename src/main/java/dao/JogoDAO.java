package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpaUtil.JPAUtil;
import entity.Jogo;

public class JogoDAO {

	public static List<Jogo> listAll() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select jogo from Jogo jogo");
		List<Jogo> jogos = q.getResultList();
		em.close();
		
		return jogos;
	}
		
	public static void save(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void update(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Jogo jogoToUpdate = em.find(Jogo.class, jogo.getId());
		
		jogoToUpdate.setDataCadastro(jogo.getDataCadastro());
		jogoToUpdate.setNumeroSorteado(jogo.getNumeroSorteado());
		jogoToUpdate.setV1(jogo.getV1());
		jogoToUpdate.setV2(jogo.getV2());
		jogoToUpdate.setV3(jogo.getV3());
		jogoToUpdate.setV4(jogo.getV4());
		jogoToUpdate.setV5(jogo.getV5());
		
		em.merge(jogoToUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void delete(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jogo = em.find(Jogo.class, jogo.getId());
		em.remove(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Integer findMaxValuePerGame() {
        EntityManager em = JPAUtil.criarEntityManager();
        Query query = em.createNamedQuery("Jogo.findMaxValuePerGame");
        Integer maxValue = (Integer) query.getSingleResult();
        em.close();
        return maxValue;
    }
	
}
