package org.moon.test.jpa.query;

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
	public String getType();

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

}