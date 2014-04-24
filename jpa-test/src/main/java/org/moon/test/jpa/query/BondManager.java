package org.moon.test.jpa.query;

import static org.moon.test.jpa.query.Bond.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

@SuppressWarnings("unused")
public class BondManager {

	/**
	 * 存储用户所有的SQL条件
	 */
	private List<Bond> bondList = new ArrayList<Bond>();
	/**
	 * 存储用户所有KEY，并记录KEY所用次数
	 */
	private Map<String, Integer> keys = new HashMap<String, Integer>();
	/**
	 * 基础查询语句：select o from Entity o
	 */
	private String baseSQL;
	/**
	 * 表的别名
	 */
	private String tableAlias;
	
	public BondManager(String tableName){
		this.tableAlias = "o";
	}
	
	public BondManager(Class<?> clazz) {
		this.tableAlias = "o"; //generateTableAlias(clazz.getSimpleName());
		Entity entityAnnotation = clazz.getAnnotation(Entity.class);
		if (entityAnnotation == null) {
			baseSQL = "select " + tableAlias + " from " + clazz.getSimpleName() + " " + tableAlias;
		} else {
			String entityName = entityAnnotation.name();
			if ("".equals(entityName)) {
				baseSQL = "select " + tableAlias + " from " + clazz.getSimpleName() + " " + tableAlias;
			} else {
				baseSQL = "select " + tableAlias + " from " + entityName + " " + tableAlias;
			}
		}
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
	 * @param value 键对应的值,in类型的值应为一个List&lt;FieldType&gt;,或者是一个基础数组类型,再或者是单个值
	 * @return this
	 */
	public BondManager andIn(String key, Object value) {
		return bondHandler(new In(key, AND, value));
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key in (value)</code>
	 * @param key 键的名称
	 * @param value 键对应的值,in类型的值应为一个List&lt;FieldType&gt;,或者是一个基础数组类型,再或者是单个值
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

	public Map<Bond, Object> toMap(){
		/*if(!bondsMap.containsKey(BOND_KEYS)){
			bondsMap.put(BOND_KEYS, keys);
		}
		return bondsMap;*/
		return null;
	}
	
	/*private BondManager paramHandler(Bond bond, Object value){
		generateAlias(bond);
		bondsMap.put(bond, value);
		keys.add(bond);
		return this;
	}*/
	
	private BondManager bondHandler(Bond bond){
		return this;
	}
	
	/**
	 * 为Bond的key生成一个别名
	 * @param bond
	 */
	protected void generateAlias(Bond bond) {
		/*String key = bond.getKey();
		int keyTimes = 0;
		if (keyContainer.containsKey(key)) {
			keyTimes = keyContainer.get(key) + 1;
		}
		keyContainer.put(key, keyTimes);
		String alias = key.replace(".", "") + "_";
		if (keyTimes > 0) {
			alias += keyTimes;
		}
		((AbstractBond) bond).setAlias(alias);*/
	}
	
	protected String generateTableAlias(String tableName) {
		return String.valueOf(tableName.charAt(0)).toLowerCase() + tableName.substring(1);
	}
	
	/**
	 * 将用户的请求转化为SQL
	 * @return
	 */
	public String toSQL(){
		String SQL = this.toPreparedSQL();
		/*if(!SQL.equals(baseSQL)){
			Iterator<Bond> it = bondsMap.keySet().iterator();
			while (it.hasNext()) {
				Bond bond = it.next();
				if (SQL.contains(bond.getAlias())) {
					System.out.println(SQL.replace(bond.getAlias(), bulidReplaceSQL(bond.getLogic(), bondsMap.get(bond))));
				}
			}
			return SQL;
		}*/
		return SQL;
	}
	
	private String bulidReplaceSQL(String logic, Object value) {
		if (LIKE.equals(logic) || NOT_LIKE.equals(logic)) {
			return "%" + value + "%";
		}
		if(IN.equals(logic) || NOT_IN.equals(logic)){
			return inReplaceSQL(value);
		}
		if(value instanceof Calendar){
			
		}
		return value + "";
	}
	
	private String inReplaceSQL(Object value) {
		if (value.getClass().isArray()) {

		}
		return null;
	}
	/**
	 * 将用户的请求转化为准备语句
	 * @return
	 */
	public String toPreparedSQL() {
		/*if (!keys.isEmpty()) {
			StringBuffer preparedSQL = new StringBuffer();
			for (Bond bond : keys) {
				preparedSQL.append(buildPreparedSQL(bond));
			}
			return baseSQL + " where " + subPrefix(preparedSQL.toString(), " and ,and ,and, or ,or ,or");
		}*/
		return baseSQL;
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
	
	/**
	 * 创建单条件准备语句
	 * @param bond
	 * @return
	 */
	protected String buildPreparedSQL(Bond bond) {
		if(NULL == bond.getLogic() || NOT_NULL == bond.getLogic()){
			return " " + bond.getType() + " " + tableAlias + "." + bond.getKey() + " is " + bond.getLogic();
		}
		return " " + bond.getType() + " " + tableAlias + "." + bond.getKey() + " " + bond.getLogic() + ":" + bond.getAlias();
	}

	public static void main(String[] args) {
		/*BondManager bondManager = new BondManager(User.class);
		bondManager.andEqual("a", "ABC");
		bondManager.andGreaterThan("b", "ABC");
		bondManager.andIn("c", "ABC");
		bondManager.andLessThan("d", "ABC");
		bondManager.andLike("e", "ABC");
		bondManager.andNotEqual("f", "ABC");
		bondManager.andNotNull("g");
		bondManager.andNull("h");
		bondManager.andNotLike("q", "ABC");
		bondManager.orEqual("i", "ABC");
		bondManager.orGreaterThan("j", "ABC");
		bondManager.orIn("k", "ABC");
		bondManager.orLessThan("l", "ABC");
		bondManager.orLike("m", "ABC");
		bondManager.orNotEqual("n", "ABC");
		bondManager.orNotNull("o");
		bondManager.orNull("p");
		bondManager.orNotLike("r", "ABC");
		System.out.println(bondManager.toSQL());*/
	}
	
}
