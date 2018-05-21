package org.harmony.test.javaee.lock;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.harmony.test.javaee.jpa.Amount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
@Stateless
public class AmountService implements AmountRemote {

	private static final Logger log = LoggerFactory.getLogger(AmountService.class);

	@PersistenceContext(unitName = "harmony")
	private EntityManager entityManager;

	@Override
	public Amount increment(Long id, BigDecimal money) {
		Amount amount = entityManager.find(Amount.class, id);
		// 悲观锁
		entityManager.lock(amount, LockModeType.PESSIMISTIC_WRITE);
		log.info("lock entity {}", amount);

		BigDecimal newAmount = amount.getAmount().add(money);
		amount.setAmount(newAmount);

		log.info("increment amount to {}, increment is ", newAmount, money);

		entityManager.persist(amount);
		entityManager.flush();

		return amount;
	}

	@Override
	public Amount save(Amount amount) {
		entityManager.persist(amount);
		return amount;
	}

}
