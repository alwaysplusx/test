### [Java Persistence API](https://github.com/superwuxin/tommy-test/tree/master/src/main/java/org/moon/tomee/jpa#java-persistence-api)

依赖`BondManager`实现参数化查询
<ul>
<li>使用BondManager中andEqual orEqual等方法来实现参数的添加
<li>添加完后使用toSQL将传入的参数转化为SQL语句
<li>使用toXQL转化为JPQL/HQL类似的语句
<li>toMap方法将返回JPQL/HQL中对应代填参数的键值对
</ul>
