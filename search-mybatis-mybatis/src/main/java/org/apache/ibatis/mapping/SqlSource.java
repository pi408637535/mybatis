/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.mapping;

/**
 * Represents the content of a mapped statement read from an XML file or an annotation.
 * It creates the SQL that will be passed to the database out of the input parameter received from the user.
 *  负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回
 *
 * DynamicSqlSource:处理动态sql。
 * RawSqlSource：处理静态sql，其内部装饰StaticSqlSource。
 * StaticSqlSource：处理静态sql，无论是静态sql，还是动态sql，最终的处理结果，都是静态sql。
 * ProviderSqlSource:处理注解Annotation形式的sql。
 * DynamicSqlSource和StaticSqlSource的最大区别在于：StaticSqlSource的String sql，可以直接获取使用，
 *   而DynamicSqlSource的String sql需要逐一根据条件解析并拼接出最终的sql，方能使用。
 *
 *
 * DynamicSqlSource中的SqlNode rootSqlNode属性，通常都是MixedSqlNode对象
 * 完全是静态sql时，可能是一个StaticTextSqlNode），而MixedSqlNode对象又保存了所有的List<SqlNode>集合，
 * 这也是通过一个rootSqlNode，就能找到所有SqlNode的深层原因。
 *
 * 组合模式与DynamicSqlSource 关系？
 */
public interface SqlSource {

  BoundSql getBoundSql(Object parameterObject);

}
