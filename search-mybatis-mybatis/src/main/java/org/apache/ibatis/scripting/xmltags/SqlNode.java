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
package org.apache.ibatis.scripting.xmltags;

/**
 * @author Clinton Begin
 *
 * xml中每个标签，eg：update，trim，if，bind，where等标签
 *
 * SqlNode 采用组合模式来组件各个子标签
 *
 * SqlNode由NodeHandler创建出来
 */
public interface SqlNode {
  /**
   * 该方法的含义为，将sql的处理结果，append到DynamicContext context对象中，
   * DynamicContext可以理解为StringBuilder对象的功能，它的作用就是计算sql片段并append到一起，形成最终的sql。
   *
    对该方法的理解非常重要，只有理解了这个方法，才能真正明白一个完整sql是如何组装出来的。
   *
   *
   * 下面的伪代码，展示了SqlNode.apply(DynamicContext)方法设计的核心原理。

   StringBuilder sb = new StringBuilder();
      IfSqlNode.apply(StringBuilder sb) {
      sb.append("select ");
      }
      SetSqlNode.apply(StringBuilder sb) {
      sb.append("* from ss ");
   }
   sb.toString();


   * @param context
   * @return
   */
  boolean apply(DynamicContext context);
}
