package org.moon.test.jpa.query;

import static org.moon.test.jpa.query.AbstractBond.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * @param putKeys
	 * @return
	 */
	public Map<Bond, Object> toMap(boolean putKeys){
		if(putKeys){
			bondsMap.put(BOND_KEYS, keys);
		}
		return bondsMap;
	}
	
	public Map<Bond, Object> toMap(){
		return toMap(true);
	}
	
	private BondManager paramHandler(Bond bond, Object value){
		generateAlias(bond);
		bondsMap.put(bond, value);
		keys.add(bond);
		return this;
	}
	
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
	
}
