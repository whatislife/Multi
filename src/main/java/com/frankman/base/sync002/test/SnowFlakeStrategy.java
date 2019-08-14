package com.frankman.base.sync002.test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SnowFlakeStrategy {
	/**
	 * 起始的时间戳
	 */
	private final static long START_STMP = 1480166465631L;

	/**
	 * 每一部分占用的位数
	 */
	private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
	private final static long MACHINE_BIT = 5; // 机器标识占用的位数
	private final static long DATACENTER_BIT = 5;// 数据中心占用的位数

	/**
	 * 每一部分的最大值
	 */
	private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
	private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
	private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

	/**
	 * 每一部分向左的位移
	 */
	private final static long MACHINE_LEFT = SEQUENCE_BIT;
	private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
	private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

	private static long datacenterId = 1; // 数据中心
	private static long machineId = Math.abs(SystemEnvParams.MAC_ADDRESS.hashCode()) % (int) MAX_MACHINE_NUM; // 机器标识
	private long sequence = 0L; // 序列号
	private long lastStmp = -1L;// 上一次时间戳

	
	//static {
	//	new SnowFlakeStrategy(datacenterId, machineId);
	//}

	public SnowFlakeStrategy(long datacenterId, long machineId) {
		if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
			throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
		}
		if (machineId > MAX_MACHINE_NUM || machineId < 0) {
			throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
		}
		SnowFlakeStrategy.datacenterId = datacenterId;
		SnowFlakeStrategy.machineId = machineId;
	}

	public SnowFlakeStrategy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 产生下一个ID
	 *
	 * @return
	 */
	private synchronized long nextId() {
		System.out.println("========"+lastStmp);
		long currStmp = getNewstmp();
		if (currStmp < lastStmp) {
			throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
		}
		if (currStmp == lastStmp) {
			// 相同毫秒内，序列号自增
			sequence = (sequence + 1) & MAX_SEQUENCE;
			// 同一毫秒的序列数已经达到最大
			if (sequence == 0L) {
				currStmp = getNextMill();
			}
		} else {
			// 不同毫秒内，序列号置为0
			sequence = 0L;
		}

		lastStmp = currStmp;
		
		/*try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		

		return (currStmp - START_STMP) << TIMESTMP_LEFT // 时间戳部分
				| datacenterId << DATACENTER_LEFT // 数据中心部分
				| machineId << MACHINE_LEFT // 机器标识部分
				| sequence; // 序列号部分
	}
	private synchronized long nextId2() {
		System.out.println("========"+lastStmp);
		long currStmp = getNewstmp();
		if (currStmp < lastStmp) {
			throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
		}
		if (currStmp == lastStmp) {
			// 相同毫秒内，序列号自增
			sequence = (sequence + 1) & MAX_SEQUENCE;
			// 同一毫秒的序列数已经达到最大
			if (sequence == 0L) {
				currStmp = getNextMill();
			}
		} else {
			// 不同毫秒内，序列号置为0
			sequence = 0L;
		}

		lastStmp = currStmp;

		return (currStmp - START_STMP) << TIMESTMP_LEFT // 时间戳部分
				| datacenterId << DATACENTER_LEFT // 数据中心部分
				| machineId << MACHINE_LEFT // 机器标识部分
				| sequence; // 序列号部分
	}
	private long getNextMill() {
		long mill = getNewstmp();
		while (mill <= lastStmp) {
			mill = getNewstmp();
		}
		return mill;
	}

	private long getNewstmp() {
		return System.currentTimeMillis();
	}

	// @Override
	//public Long getPrimaryId() {
	//	return nextId();
	//}

	// @Override
	public List<Long> getPrimaryIds(int num) {
		return null;
	}

	public static void main(String[] args) {
		Map<Long, Integer> aa = new ConcurrentHashMap<>();
		int abs = Math.abs(SystemEnvParams.MAC_ADDRESS.hashCode());
		int a = abs % 31;
		SnowFlakeStrategy snowFlake = new SnowFlakeStrategy(1, a);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			long nextId = snowFlake.nextId();
			if (aa.containsKey(nextId)) {
				System.out.println(nextId);
			}
			aa.put(nextId, 1);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(snowFlake.machineId);

	}
	
	private static SnowFlakeStrategy snow = new SnowFlakeStrategy(datacenterId, machineId);
	
	public static Long getPrimaryId() {
	    return snow.nextId();
	}
	public static Long getPrimaryId2() {
	    return snow.nextId2();
	}
}
