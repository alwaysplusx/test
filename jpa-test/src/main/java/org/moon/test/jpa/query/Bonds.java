package org.moon.test.jpa.query;

/**
 * 对应sql<code> key like:keyAlias</code>
 */
class Like extends AbstractBond {
	Like(String key, String type, Object value) {
		super(key, LIKE, type, value);
	}
}

/**
 * 对应sql<code> key not like:keyAlias</code>
 */
class NotLike extends AbstractBond {
	NotLike(String key, String type, Object value) {
		super(key, NOT_LIKE, type, value);
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
}

/**
 * 对应sql<code> key not in:keyAlias</code>
 */
class NotIn extends AbstractBond {
	NotIn(String key, String type, Object value) {
		super(key, NOT_IN, type, value);
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
class NotEqual extends AbstractBond {
	NotEqual(String key, String type, Object value) {
		super(key, NOT_EQAUL, type, value);
	}
}

/**
 * 对应sql<code> key is null</code>
 */
class Null extends AbstractBond {
	Null(String key, String type) {
		super(key, NULL, type, "");
	}
}

/**
 * 对应sql<code> key is not null</code>
 */
class NotNull extends AbstractBond {
	NotNull(String key, String type) {
		super(key, NOT_NULL, type, "");
	}
}
