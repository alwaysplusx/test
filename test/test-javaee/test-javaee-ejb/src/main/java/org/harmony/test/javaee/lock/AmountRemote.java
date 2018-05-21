package org.harmony.test.javaee.lock;

import java.math.BigDecimal;

import javax.ejb.Remote;

import org.harmony.test.javaee.jpa.Amount;

/**
 * @author wuxii@foxmail.com
 */
@Remote
public interface AmountRemote {

	Amount save(Amount amount);

	Amount increment(Long id, BigDecimal money);

}
