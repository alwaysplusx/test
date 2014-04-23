package org.moon.test.jpa.query;

public abstract class AbstractBond implements Bond {

	private String key;
	private String logic;
	private String type;
	private String alias;
	
	AbstractBond(String key, String logic, String type) {
		this.key = key;
		this.logic = logic;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public String getLogic() {
		return logic;
	}

	public String getType() {
		return type;
	}

	public String getAlias() {
		return alias;
	}

	protected void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [key=" + key + ", logic=" + logic + ", type=" + type + ", alias=" + alias + "]";
	}

	/**
	 * 对应于Bond的Type：and
	 */
	public static final String AND = "and";
	/**
	 * 对应于Bond的Type：or
	 */
	public static final String OR = "or";

	/**
	 * sql中逻辑条件：等于
	 */
	public static final String EQUAL = "=";
	/**
	 * sql中逻辑条件：不等于
	 */
	public static final String NOT_EQAUL = "<>";
	/**
	 * sql中逻辑条件：like
	 */
	public static final String LIKE = "like";
	/**
	 * sql中逻辑条件：not like
	 */
	public static final String NOT_LIKE = "not like";
	/**
	 * sql中逻辑条件：大于
	 */
	public static final String GREATER_THAN = ">";
	/**
	 * sql中逻辑条件：小于
	 */
	public static final String LESS_THAN = "<";
	/**
	 * sql中逻辑条件：in
	 */
	public static final String IN = "in";
	/**
	 * sql中逻辑条件：not in
	 */
	public static final String NOT_IN = "not in";
	/**
	 * sql中逻辑条件：空
	 */
	public static final String NULL = "null";
	/**
	 * sql中逻辑条件：不为空
	 */
	public static final String NOT_NULL = "not null";
	/**
	 * sql中逻辑条件：大于等于
	 */
	public static final String GREATER_EQUAL = ">=";
	/**
	 * sql中逻辑条件：小于等于
	 */
	public static final String LESS_EQUAL = "<=";
	/**
	 * 代表一个BondManager中的所有key，用于map中
	 * {@link AbstractBond#BOND_KEYS}
	 */
	public static final String ALL_KEYS = "org.moon.test.jpa.query.BondManager.ALL_KEYS";
	/**
	 * 该值用于指定所有keys的List
	 */
	public static final Bond BOND_KEYS = new Bond(){
		
		@Override
		public String getKey() {
			return ALL_KEYS;
		}

		@Override
		public String getType() {
			return null;
		}

		@Override
		public String getLogic() {
			return null;
		}

		@Override
		public String getAlias() {
			return ALL_KEYS;
		}
		
		public int hashCode(){
			return ALL_KEYS.hashCode();
		}
		
		public boolean equals(Object obj){
			return obj == this;
		}
	};	

}

/**
 * 对应sql<code> key like:keyAlias</code>
 */
class Like extends AbstractBond {
	Like(String key, String type) {
		super(key, LIKE, type);
	}
}
/**
 * 对应sql<code> key not like:keyAlias</code>
 */
class NotLike extends AbstractBond {
	NotLike(String key, String type){
		super(key, NOT_LIKE, type);
	}
}
/**
 * 对应sql<code> key >:keyAlias</code>
 */
class GreaterThan extends AbstractBond {
	GreaterThan(String key, String type) {
		super(key, GREATER_THAN, type);
	}
}
/**
 * 对应sql<code> key >=:keyAlias</code>
 */
class GreaterEqual extends AbstractBond {
	GreaterEqual(String key, String type) {
		super(key, GREATER_EQUAL, type);
	}
}
/**
 * 对应sql<code> key <:keyAlias</code>
 */
class LessThan extends AbstractBond {
	LessThan(String key, String type) {
		super(key, LESS_THAN, type);
	}
}
/**
 * 对应sql<code> key <=:keyAlias</code>
 */
class LessEqual extends AbstractBond {
	LessEqual(String key, String type) {
		super(key, LESS_EQUAL, type);
	}
}
/**
 * 对应sql<code> key in:keyAlias</code>
 */
class In extends AbstractBond {
	In(String key, String type) {
		super(key, IN, type);
	}
}
/**
 * 对应sql<code> key not in:keyAlias</code>
 */
class NotIn extends AbstractBond {
	NotIn(String key, String type){
		super(key, NOT_IN, type);
	}
}
/**
 * 对应sql<code> key =:keyAlias</code>
 */
class Equal extends AbstractBond {
	public Equal(String key, String type) {
		super(key, EQUAL, type);
	}
}
/**
 * 对应sql<code> key <>:keyAlias</code>
 */
class NotEqual extends AbstractBond {
	NotEqual(String key, String type) {
		super(key, NOT_EQAUL, type);
	}
}
/**
 * 对应sql<code> key is null</code>
 */
class Null extends AbstractBond {
	Null(String key, String type) {
		super(key, NULL, type);
	}
}
/**
 * 对应sql<code> key is not null</code>
 */
class NotNull extends AbstractBond {
	NotNull(String key, String type) {
		super(key, NOT_NULL, type);
	}
}



