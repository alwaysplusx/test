package org.moon.test.jpa.query;

/**
 * 对应sql<code> key like:keyAlias</code>
 */
class Like extends AbstractBond {

	Like(String key, String type, Object value) {
		super(key, LIKE, type, value);
	}

	@Override
	public String toSQL(String tableAlias) {
		return " " + this.link + " " + this.key + " " + this.logic + " '" + this.sqlValue + "'";
	}
}

/**
 * 对应sql<code> key not like:keyAlias</code>
 */
class NotLike extends Like {

	NotLike(String key, String type, Object value) {
		super(key, type, value);
		this.logic = NOT_LIKE;
	}
}

/**
 * 对应sql<code> key >:keyAlias</code>
 */
class GreaterThan extends AbstractBond {
	GreaterThan(String key, String type, Object value) {
		super(key, GREATER_THAN, type, value);
	}
}

/**
 * 对应sql<code> key >=:keyAlias</code>
 */
class GreaterEqual extends AbstractBond {
	GreaterEqual(String key, String type, Object value) {
		super(key, GREATER_EQUAL, type, value);
	}
}

/**
 * 对应sql<code> key <:keyAlias</code>
 */
class LessThan extends AbstractBond {
	LessThan(String key, String type, Object value) {
		super(key, LESS_THAN, type, value);
	}
}

/**
 * 对应sql<code> key <=:keyAlias</code>
 */
class LessEqual extends AbstractBond {
	LessEqual(String key, String type, Object value) {
		super(key, LESS_EQUAL, type, value);
	}
}

/**
 * 对应sql<code> key in:keyAlias</code>
 */
class In extends AbstractBond {
	
	In(String key, String type, Object value) {
		super(key, IN, type, value);
	}

	@Override
	public String toSQL(String tableAlias) {
		return " " + this.link + " " + tableAlias + "." + this.key + " " + this.logic + " (" + this.sqlValue + ")";
	}
}

/**
 * 对应sql<code> key not in:keyAlias</code>
 */
class NotIn extends In {
	NotIn(String key, String type, Object value) {
		super(key, type, value);
		this.logic = NOT_IN;
	}
}

/**
 * 对应sql<code> key =:keyAlias</code>
 */
class Equal extends AbstractBond {
	public Equal(String key, String type, Object value) {
		super(key, EQUAL, type, value);
	}
}

/**
 * 对应sql<code> key <>:keyAlias</code>
 */
class NotEqual extends Equal {
	NotEqual(String key, String type, Object value) {
		super(key, type, value);
		this.logic = NOT_EQAUL;
	}
}

/**
 * 对应sql<code> key is null</code>
 */
class Null extends AbstractBond {
	Null(String key, String type) {
		super(key, NULL, type, null);
	}

	@Override
	public String toSQL(String tableAlias) {
		return " " + this.link + " " + tableAlias + "." + this.key + " is " + this.logic;
	}
}

/**
 * 对应sql<code> key is not null</code>
 */
class NotNull extends Null {
	NotNull(String key, String type) {
		super(key, type);
		this.logic = NOT_NULL;
	}
}
