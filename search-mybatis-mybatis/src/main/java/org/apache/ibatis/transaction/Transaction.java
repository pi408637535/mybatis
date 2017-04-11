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
package org.apache.ibatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Wraps a database connection.
 * Handles the connection lifecycle that comprises: its creation, preparation, commit/rollback and close. 
 *
 * @author Clinton Begin
 *
 * 一说到事务，人们可能又会想起create、begin、commit、rollback、close、suspend。
 * 可实际上，只有commit、rollback是实际存在的，剩下的create、begin、close、suspend都是虚幻的，是业务层或数据库底层应用语意，而非JDBC事务的真实命令。
 *
 * create（事务创建）：不存在。
 * begin（事务开始）：姑且认为存在于DB的命令行中，比如Mysql的start transaction命令，以及其他数据库中的begin transaction命令。JDBC中不存在。
 * close（事务关闭）：不存在。应用程序接口中的close()方法，是为了把connection放回数据库连接池中，供下一次使用，与事务毫无关系。
 * suspend（事务挂起）：不存在。Spring中事务挂起的含义是，需要新事务时，将现有的connection1保存起来（它还有尚未提交的事务），然后创建connection2，connection2提交、回滚、关闭完毕后，再把connection1取出来，完成提交、回滚、关闭等动作，保存connection1的动作称之为事务挂起。在JDBC中，是根本不存在事务挂起的说法的，也不存在这样的接口方法。
 * 记住事务的三个真实存在的方法，不要被各种事务状态名词所迷惑，它们分别是：conn.setAutoCommit()、conn.commit()、conn.rollback()。
 */
public interface Transaction {

  /**
   *   
   * String resource = "mybatis-config.xml";  
	InputStream inputStream = Resources.getResourceAsStream(resource);  
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);  
	SqlSession sqlSession = sqlSessionFactory.openSession();  
	sqlSession.selectList("SELECT * FROM STUDENTS");
   * 只有当第5句sqlSession.selectList("SELECT * FROM STUDENTS")，
   * 才会触发MyBatis在底层执行下面这个方法来创建java.sql.Connection对象
   * Retrieve inner database connection
   * @return DataBase connection
   * @throws SQLException
   */
  Connection getConnection() throws SQLException;

  /**
   * Commit inner database connection.
   * @throws SQLException
   */
  void commit() throws SQLException;

  /**
   * Rollback inner database connection.
   * @throws SQLException
   */
  void rollback() throws SQLException;

  /**
   * Close inner database connection.
   * @throws SQLException
   */
  void close() throws SQLException;

}
