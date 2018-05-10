package org.harmony.test.javaee.jpa.query;

public interface Bond {
    /**
     * 字段的名称
     * @return
     */
    public String getKey();

    /**
     * 对应sql中的 and or
     * @return
     */
    public String getLink();

    /**
     * 对应sql中的逻辑如：等于、大于、小于等
     * <p>field [logic] value <code>
     * <p>e.g.:
     * <p>&nbsp;&nbsp;username = 'test'
     * <p>&nbsp;&nbsp;age < 10    
     * </code>
     * @return
     */
    public String getLogic();
    
    /**
     * 根据key生成的一个别名
     * @return
     */
    public String getAlias();
    /**
     * 值
     * @return
     */
    public Object getValue();
    /**
     * @return
     */
    public String toSQL(String tableAlias);
    /**
     * @return
     */
    public String toXQL(String tableAlias);
    
    //###### default SQL type ######
    
    /**
     * 对应于Bond的Link：and
     */
    public static final String AND = "and";
    /**
     * 对应于Bond的Link：or
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
}