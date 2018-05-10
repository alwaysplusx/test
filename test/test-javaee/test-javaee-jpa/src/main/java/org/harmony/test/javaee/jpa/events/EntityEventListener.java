package org.harmony.test.javaee.jpa.events;

import javax.persistence.PrePersist;

/**
 * @author wuxii@foxmail.com
 */
public class EntityEventListener {

    @PrePersist
    void prePersist(EventEntity entity) {
        System.out.println("EntityEventListener " + entity.getName());
    }

}
