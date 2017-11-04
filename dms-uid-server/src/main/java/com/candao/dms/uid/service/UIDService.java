package com.candao.dms.uid.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.candao.dms.uid.UidGenerator;
import com.candao.dms.uid.contants.ConstContants;
import com.candao.dms.uid.enums.ServiceCodeEnum;

/**
 * @author jeromeLiu
 */
@Service
public class UIDService {

	@Resource(name = "cachedUidGenerator")
	private UidGenerator uidGenerator;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 从缓存获取uid
	 * 
	 * @param serverPrefix ==> ConstContants 的配置描述
	 *        业务前缀
	 * @desc 业务前缀_业务ID_uid
	 */
	public String getUidWithCache(String serverPrefix) {
		ServiceCodeEnum codeEnum = ServiceCodeEnum.getServiceByPrefix(serverPrefix);
		// 从redis ==> uid
		String uid = redisTemplate.opsForList().leftPop(ConstContants.UID_GENERATOR_LIST);

		// 断言缓存数据为空
		if (StringUtils.isEmpty(uid) || StringUtils.isBlank(uid)) {
			// 防雪崩
			if (redisTemplate.opsForValue().setIfAbsent(ConstContants.FLAG_BIT, ConstContants.FLAG_BIT_VALUE)) {
				//进行去重处理
				Set<String> uidValueSet = new HashSet<String>(ConstContants.STEP_SIZE);
				for (int i = 0; i < ConstContants.STEP_SIZE; i++) {
					StringBuffer uid_value = new StringBuffer();
					uid_value.append(codeEnum.getServicePrefix()).append("_").append(codeEnum.getServiceCode())
							.append("_").append(uidGenerator.getUID());
					uidValueSet.add(uid_value.toString());
				}
				// 存放 uid list
				redisTemplate.opsForList().rightPushAll(ConstContants.UID_GENERATOR_LIST, uidValueSet);
				//设置10分钟过期时间
				redisTemplate.expire(ConstContants.UID_GENERATOR_LIST, ConstContants.UID_GENERATOR_LIST_EXPIRE, TimeUnit.SECONDS);
				//清除分布式flag位
				redisTemplate.delete(ConstContants.FLAG_BIT);
			}
			uid = redisTemplate.opsForList().leftPop(ConstContants.UID_GENERATOR_LIST);
		}
		return uid;
	}

	/**
	 * 获取uid
	 * 
	 * @param serverPrefix
	 *            业务前缀
	 * @param serverId
	 *            业务ID
	 * @desc 业务前缀_业务ID_uid
	 */
	public String getUid(String serverPrefix) {
		ServiceCodeEnum codeEnum = ServiceCodeEnum.getServiceByPrefix(serverPrefix);
		StringBuffer uid_value = new StringBuffer();
		uid_value.append(codeEnum.getServicePrefix()).append("_").append(codeEnum.getServiceCode()).append("_")
				.append(uidGenerator.getUID());

		return uid_value.toString();
	}

}
