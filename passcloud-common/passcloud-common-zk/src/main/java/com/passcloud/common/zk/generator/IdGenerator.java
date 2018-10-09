package com.passcloud.common.zk.generator;

/**
 * The interface Id generator.
 *
 * @author liyuzhang
 */
public interface IdGenerator {

	/**
	 * 生成下一个ID
	 *
	 * @return the string
	 */
	Long nextId();
}
