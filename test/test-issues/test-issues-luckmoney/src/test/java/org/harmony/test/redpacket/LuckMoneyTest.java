package org.harmony.test.redpacket;

import org.harmony.test.redpacket.LuckMoneyService.LuckMoney;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class LuckMoneyTest {

    @Test
    public void test() {
        LuckMoney lm = new LuckMoneyService.LuckMoney(0l, 500.0, 20);
        while (lm.hasNext()) {
            System.out.print(lm.nextRandomMoney());
            if (lm.hasNext()) {
                System.out.print("\t");
            }
        }
    }

}
