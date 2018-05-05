package org.harmony.test.redpacket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wuxii@foxmail.com
 */
public class LuckMoneyService {

    private static final AtomicLong ID = new AtomicLong();
    // can use redis
    private Map<Long, LuckMoney> packets = new HashMap<>();
    private Map<Long, List<LuckGuy>> picks = new HashMap<>();

    /**
     * 发布红包
     * 
     * @param money
     *            发布的总金额
     * @param slice
     *            份数
     * @return id
     */
    public Long publish(double money, int slice) {
        LuckMoney packet = new LuckMoney(ID.getAndIncrement(), money, slice);
        packets.put(packet.getId(), packet);
        return packet.getId();
    }

    /**
     * 抢红包
     * 
     * @param redpacketId
     *            抢的是哪个红包Id
     * @param name
     *            抢红包的人
     * @return 抢红包的结果
     */
    public LuckGuy rush(Long luckMoneyId, String name) {
        LuckMoney packet = getLuckMoney(luckMoneyId);
        if (packet == null) {
            throw new IllegalStateException("packet not exists");
        }
        if (!packet.hasNext()) {
            return null;
        }

        List<LuckGuy> luckGuys = getLuckGuys(luckMoneyId);
        LuckGuy luckGuy = null;
        synchronized (luckGuys) {
            // same user check
            Optional<LuckGuy> o = luckGuys.stream().filter(e -> e.getName().equals(name)).findFirst();
            if (o.isPresent()) {
                return o.get();
            }
            if (packet.hasNext()) {
                luckGuy = new LuckGuy(luckMoneyId, name, packet.nextRandomMoney());
                luckGuys.add(luckGuy);
            }
        }
        return luckGuy;
    }

    public List<LuckGuy> getAllLuckGuys(Long id) {
        return picks.get(id);
    }

    private List<LuckGuy> getLuckGuys(Long id) {
        List<LuckGuy> luckGuys = picks.get(id);
        if (luckGuys == null) {
            synchronized (picks) {
                if (luckGuys == null) {
                    luckGuys = new CopyOnWriteArrayList<>();
                    picks.put(id, luckGuys);
                }
            }
        }
        return luckGuys;
    }

    private LuckMoney getLuckMoney(Long luckMoneyId) {
        LuckMoney luckMoney = packets.get(luckMoneyId);
        return luckMoney;
    }

    public static final class LuckMoney {

        private Long id;
        private double money;
        private int slice;
        private int remainSlice;
        private double remainMoney;

        public LuckMoney(Long id, double money, int slice) {
            this.id = id;
            this.money = money;
            this.slice = slice;
            this.remainSlice = slice;
            this.remainMoney = money;
        }

        public double nextRandomMoney() {
            if (remainSlice == 0) {
                return 0;
            }
            double randomMoney = 0;
            if (remainSlice == 1) {
                // TAKE ALL
                randomMoney = (double) Math.round(remainMoney * 100) / 100;
            } else {
                Random r = new Random();
                double min = 0.01; //
                double max = remainMoney / remainSlice * 2;

                randomMoney = r.nextDouble() * max;
                randomMoney = randomMoney <= min ? 0.01 : randomMoney;
                randomMoney = Math.floor(randomMoney * 100) / 100;
            }

            remainSlice--;
            remainMoney -= randomMoney;
            return randomMoney;
        }

        public boolean hasNext() {
            return remainSlice > 0;
        }

        public int getRemind() {
            return remainSlice;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getSlice() {
            return slice;
        }

        public void setSlice(int slice) {
            this.slice = slice;
        }

    }

    public static class LuckGuy {

        private Long redpacketId;
        private String name;
        private double money;

        private LuckGuy(Long redpacketId, String name, double money) {
            this.redpacketId = redpacketId;
            this.name = name;
            this.money = money;
        }

        public Long getRedpacketId() {
            return redpacketId;
        }

        public void setRedpacketId(Long redpacketId) {
            this.redpacketId = redpacketId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((redpacketId == null) ? 0 : redpacketId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            LuckGuy other = (LuckGuy) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (redpacketId == null) {
                if (other.redpacketId != null)
                    return false;
            } else if (!redpacketId.equals(other.redpacketId))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "LuckGuy {redpacketId:" + redpacketId + ", name:" + name + ", money:" + money + "}";
        }

    }

}
