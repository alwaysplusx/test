package org.moon.test.jpa.query;

import static org.moon.test.jpa.query.AbstractBond.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.h2.engine.User;

public class BondManager {

	/**
	 * 用于存储条件的键值对
	 */
	private Map<Bond, Object> bondsMap = new HashMap<Bond, Object>();
	/**
	 * 为了让生成的sql有顺序,加入该成员变量,生成sql时候遍历keys即是按用户加入条件的先后顺序
	 */
	private List<Bond> keys = new ArrayList<Bond>();
	/**
	 * 所有key都存储在该keyContainer中,通过查找该容器中是否已经存在key判断生产key的别名(alias)
	 * <p>保不齐用户要查询
	 * <p> username = ?1 or username = ?2
	 * <p>所以运用别名来进行该条件的操作
	 * <p>username = alias1 or username = alias2
	 */
	private Map<String, Integer> keyContainer = new HashMap<String, Integer>();

	/**
	 * 待查询的类
	 */
	@SuppressWarnings("unused")
	private Class<?> clazz;
	/**
	 * 基础查询语句：select o from Entity o
	 */
	private String baseSQL;
	
	public BondManager(Class<?> clazz) {
		this.clazz = clazz;
		Entity entityAnnotation = clazz.getAnnotation(Entity.class);
		if (entityAnnotation == null) {
			baseSQL = "select o from " + clazz.getSimpleName() + " o ";
		} else {
			String entityName = entityAnnotation.name();
			if ("".equals(entityName)) {
				baseSQL = "select o from " + clazz.getSimpleName() + " o ";
			} else {
				baseSQL = "select o from " + entityName + " o ";
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
		return paramHandler(new NotEqual(key, AND), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key <> value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orNotEqual(String key, Object value) {
		return paramHandler(new NotEqual(key, OR), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key <> value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager andLike(String key, Object value) {
		return paramHandler(new Like(key, AND), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key <> value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orLike(String key, Object value) {
		return paramHandler(new Like(key, OR), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key = value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager andEqual(String key, Object value) {
		return paramHandler(new Equal(key, AND), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key = value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orEqual(String key, Object value) {
		return paramHandler(new Equal(key, OR), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key > value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager andGreaterThan(String key, Object value) {
		return paramHandler(new GreaterThan(key, AND), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key > value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orGreaterThan(String key, Object value) {
		return paramHandler(new GreaterThan(key, OR), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key < value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager andLessThan(String key, Object value) {
		return paramHandler(new LessThan(key, AND), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key < value</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orLessThan(String key, Object value) {
		return paramHandler(new LessThan(key, OR), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key in (value)</code>
	 * @param key 键的名称
	 * @param value 键对应的值,in类型的值应为一个List&lt;FieldType&gt;,或者是一个基础数组类型,再或者是单个值
	 * @return this
	 */
	public BondManager andIn(String key, Object value) {
		return paramHandler(new In(key, AND), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key in (value)</code>
	 * @param key 键的名称
	 * @param value 键对应的值,in类型的值应为一个List&lt;FieldType&gt;,或者是一个基础数组类型,再或者是单个值
	 * @return this
	 */
	public BondManager orIn(String key, Object value) {
		return paramHandler(new In(key, OR), value);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key is null</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager andNull(String key){
		return paramHandler(new Null(key, AND), null);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key is null</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orNull(String key){
		return paramHandler(new Null(key, OR), null);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>and key is not null</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager andNotNull(String key){
		return paramHandler(new NotNull(key, AND), null);
	}
	/**
	 * <p>对应的SQL为:
	 * <p><code>or key is not null</code>
	 * @param key 键的名称
	 * @param value 键对应的值
	 * @return this
	 */
	public BondManager orNotNull(String key){
		return paramHandler(new NotNull(key, OR), null);
	}

	public Map<Bond, Object> toMap(){
		if(!bondsMap.containsKey(BOND_KEYS)){
			bondsMap.put(BOND_KEYS, keys);
		}
		return bondsMap;
	}
	
	/**
	 * <p>将键值关系做一个对应
	 * <p>并在{@link BondManager#keys}中加入该key
	 * @param bond 键
	 * @param value 值
	 * @return
	 */
	private BondManager paramHandler(Bond bond, Object value){
		generateAlias(bond);
		bondsMap.put(bond, value);
		keys.add(bond);
		return this;
	}
	
	/**
	 * 为Bond的key生成一个别名
	 * @param bond
	 */
	private void generateAlias(Bond bond) {
		String key = bond.getKey();
		int keyTimes = 0;
		if (keyContainer.containsKey(key)) {
			keyTimes = keyContainer.get(key) + 1;
		}
		keyContainer.put(key, keyTimes);
		String alias = key.replace(".", "") + "_";
		if (keyTimes > 0) {
			alias += keyTimes;
		}
		((AbstractBond) bond).setAlias(alias);
	}
	
	/**
	 * 将用户的请求转化为SQL
	 * @return
	 */
	private String toSQL(){
		return null;
	}
	
	/**
	 * 将用户的请求转化为准备语句
	 * @return
	 */
	private String toPreparedSQL(){
		for (Bond bond : keys) {
			buildSQL(bond);
		}
		return null;
	}
	
	private String buildSQL(Bond bond) {
		String SQL = "";
		String type = bond.getType() + " ";
		String key = bond.getKey() + " ";
		String alias = bond.getAlias() + " ";
		String logic = bond.getLogic() + ":";
		System.out.println(type + key + logic + alias);
		if(NULL == logic || NOT_NULL == logic){
			SQL = "";
		}
		return SQL;
	}
	
	private String buildQL() {
		Map<Bond, Object> params = toMap();
		String qlString = "";
		Iterator<Bond> bonds = params.keySet().iterator();
		if (bonds.hasNext()) {
			String condition = ""; 
			if (params.containsKey(BOND_KEYS)) {
				List<Bond> keys = (List<Bond>) params.get(BOND_KEYS);
				for (Bond bond : keys) {
					condition += buildQL(bond);
				}
			} else {
				while(bonds.hasNext()){
					condition += buildQL(bonds.next());
				}
			}
			if(condition.startsWith("and")) {
				qlString += "where " + condition.substring(4);
			}else if(condition.startsWith("or")) {
				qlString += "where " + condition.substring(3);
			}
		}
		return qlString;
	}
	
	private String buildQL(Bond bond) {
		String condition = "and ";
		if (OR.equals(bond.getType()))
			condition = "or ";
		String logic = bond.getLogic();
		String key = bond.getKey();
		String alias = bond.getAlias();
		if (EQUAL.equals(logic)) {
			condition += "o." + key + " =:" + alias + " ";
		} else if (NOT_EQAUL.equals(logic)) {
			condition += "o." + key + " <>:" + alias + " ";
		} else if (IN.equals(logic)) {
			condition += "o." + key + " in:" + alias + " ";
		} else if (LIKE.equals(logic)) {
			condition += "o." + key + " like:" + alias + " ";
		} else if (GREATER_THAN.equals(logic)) {
			condition += "o." + key + " >:" + alias + " ";
		} else if (LESS_THAN.equals(logic)) {
			condition += "o." + key + " <:" + alias + " ";
		} else if (NOT_IN.equals(logic)) {
			condition += "o." + key + " not in:" + alias + " ";
		} else if (NULL.equals(logic)) {
			condition += "o." + key + " is null ";
		} else if (NOT_NULL.equals(logic)) {
			condition += "o." + key + " is not null ";
		} else if (GREATER_EQUAL.equals(logic)) {
			condition += "o." + key + " >=:" + alias + " ";
		} else if (LESS_EQUAL.equals(logic)) {
			condition += "o." + key + " <=:" + alias + " ";
		}
		return condition;
	}

	public static void main(String[] args) {
		BondManager bondManager = new BondManager(User.class);
		System.out.println(bondManager.baseSQL);
		bondManager.andIn("name", "wuxin");
		bondManager.toPreparedSQL();
		/*ArrayList<Long> ids = new ArrayList<Long>();
		ids.add(1l);
		ids.add(2l);
		bondManager.andIn("id", ids);
		//bondManager.buildQL();
		System.out.println(bondManager.buildQL());*/
		//bondManager.buildSQL();
	}
	
}
