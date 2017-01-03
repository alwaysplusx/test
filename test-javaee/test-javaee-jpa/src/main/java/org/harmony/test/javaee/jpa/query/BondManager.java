package org.harmony.test.javaee.jpa.query;

import static org.harmony.test.javaee.jpa.query.Bond.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.harmony.test.javaee.jpa.persistence.User;
import org.harmony.test.javaee.jpa.util.JPAAnnotationUtil;

public class BondManager {

    private List<BondListener> bondListeners = new ArrayList<BondListener>();
    /**
     * 存储用户所有的SQL条件
     */
    private List<Bond> bondList = new ArrayList<Bond>();
    /**
     * 保存bond的键的别名与值
     */
    private Map<String, Object> KVMap = new HashMap<String, Object>();
    /**
     * 存储用户所有KEY，并记录KEY所用次数
     */
    private Map<String, Integer> keys = new HashMap<String, Integer>();
    /**
     * 表的别名
     */
    private String tableAlias = "o";
    private String tableName;
    private String entityName;
    
    public BondManager(Class<?> clazz) {
        this.entityName = JPAAnnotationUtil.getEntityName(clazz);
        this.tableName = JPAAnnotationUtil.getTableName(clazz);
        this.addListener(new LogicBondListener());
        this.addListener(new ValueBondListener());
    }
        
    /**
     * <p>对应的SQL为:
     * <p><code>and key <> value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andNotEqual(String key, Object value) {
        return bondHandler(new NotEqual(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key <> value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orNotEqual(String key, Object value) {
        return bondHandler(new NotEqual(key, OR, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key <> value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andLike(String key, Object value) {
        return bondHandler(new Like(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key <> value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orLike(String key, Object value) {
        return bondHandler(new Like(key, OR, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key <> value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andNotLike(String key, Object value) {
        return bondHandler(new NotLike(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key <> value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orNotLike(String key, Object value) {
        return bondHandler(new NotLike(key, OR, value));
    }    
    /**
     * <p>对应的SQL为:
     * <p><code>and key = value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andEqual(String key, Object value) {
        return bondHandler(new Equal(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key = value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orEqual(String key, Object value) {
        return bondHandler(new Equal(key, OR, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key > value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andGreaterThan(String key, Object value) {
        return bondHandler(new GreaterThan(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key > value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orGreaterThan(String key, Object value) {
        return bondHandler(new GreaterThan(key, OR, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key < value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andLessThan(String key, Object value) {
        return bondHandler(new LessThan(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key < value</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orLessThan(String key, Object value) {
        return bondHandler(new LessThan(key, OR, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key in (value)</code>
     * @param key 键的名称
     * @param value 键对应的值,in类型的值应是一个基础数组类型,再或者是单个值
     * @return this
     */
    public BondManager andIn(String key, Object value) {
        return bondHandler(new In(key, AND, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key in (value)</code>
     * @param key 键的名称
     * @param value 键对应的值,in类型的值应是一个基础数组类型,再或者是单个值
     * @return this
     */
    public BondManager orIn(String key, Object value) {
        return bondHandler(new In(key, OR, value));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key is null</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andNull(String key){
        return bondHandler(new Null(key, AND));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key is null</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orNull(String key){
        return bondHandler(new Null(key, OR));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>and key is not null</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager andNotNull(String key){
        return bondHandler(new NotNull(key, AND));
    }
    /**
     * <p>对应的SQL为:
     * <p><code>or key is not null</code>
     * @param key 键的名称
     * @param value 键对应的值
     * @return this
     */
    public BondManager orNotNull(String key){
        return bondHandler(new NotNull(key, OR));
    }

    public Map<String, Object> toMap() {
        return KVMap;
    }
    
    private BondManager bondHandler(Bond bond){
        generateKeyAlias(bond);
        bondList.add(bond);
        notifyListenerBondAdded(bond);
        KVMap.put(bond.getAlias(), bond.getValue());
        return this;
    }
    
    /**
     * <p>为Bond的key生成一个别名
     * <p>如果一个语句中会使用同一个key做多次判断，确保每次的别名都不相同
     * @param bond
     */
    protected void generateKeyAlias(Bond bond) {
        String key = bond.getKey();
        int keyTimes = 0;
        if (keys.containsKey(key)) {
            keyTimes = keys.get(key) + 1;
        }
        keys.put(key, keyTimes);
        String alias = formatKey(bond.getKey()) + keyTimes;
        ((AbstractBond) bond).alias = alias;
    }
    
    private String formatKey(String key) {
        String[] split = key.split("\\.");
        String alias = split[0];
        for (int i = 1; i < split.length; i++) {
            alias += String.valueOf(split[i].charAt(0)).toUpperCase() + split[i].substring(1);
        }
        return alias + "_";
    }
    
    /**
     * 将用户的请求转化为SQL
     * @return
     */
    public String toSQL() {
        StringBuffer sb = new StringBuffer();
        if (!bondList.isEmpty()) {
            for (Bond bond : bondList) {
                sb.append(bond.toSQL(this.tableAlias));
            }
        }
        return buildQL(sb.toString(), false);
    }
    
    public String toXQL() {
        StringBuffer sb = new StringBuffer();
        if (!bondList.isEmpty()) {
            for (Bond bond : bondList) {
                sb.append(bond.toXQL(this.tableAlias));
            }
        }
        return buildQL(sb.toString(), true);
    }
    
    protected String buildQL(String sql, boolean XQL) {
        String baseQL;
        if (XQL) {
            baseQL = "select " + this.tableAlias + " from " + this.entityName + " " + tableAlias;
        } else {
            baseQL = "select " + this.tableAlias + ".* from " + this.tableName + " " + tableAlias;
        }
        return sql == null || sql.length() == 0 ? baseQL : baseQL + " where " + subPrefix(sql, " and , or "); 
    }
    
    private String subPrefix(String pattern, String prefixs) {
        String[] split = prefixs.split(",");
        for (String prefix : split) {
            if(pattern.startsWith(prefix)){
                return pattern.substring(prefix.length());
            }
        }
        return pattern;
    }
    
    private void notifyListenerBondAdded(Bond bond){
        for(BondListener listener: bondListeners){
            listener.bondAdded(bond);
        }
    }
    
    public boolean addListener(BondListener listener){
        return bondListeners.add(listener);
    }
    
    public static void main(String[] args) {
        BondManager bm = new BondManager(User.class);
        System.out.println(bm.formatKey("username"));
        /*bm.andLike("username", "wuxin");
        bm.orIn("userId", new Integer[]{1,2,3});
        bm.andEqual("birthday", new Date());
        System.out.println(bm.toSQL());
        System.out.println(bm.toXQL());*/
        
    }
    
}
