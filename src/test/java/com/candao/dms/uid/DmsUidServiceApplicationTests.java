package com.candao.dms.uid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DmsUidServiceApplication.class)
public class DmsUidServiceApplicationTests {

	 	private static final int SIZE = 7000000; // 700w
	    private static final boolean VERBOSE = false;
	    private static final int THREADS = Runtime.getRuntime().availableProcessors() << 1;

	    @Resource(name="cachedUidGenerator")
	    private UidGenerator uidGenerator;

	    /**
	     * Test for serially generate
	     * 
	     * @throws IOException
	     */
	    //@Test
	    public void testSerialGenerate() throws IOException {
	        // Generate UID serially
	        Set<Long> uidSet = new HashSet<>(SIZE);
	        for (int i = 0; i < SIZE; i++) {
	            doGenerate(uidSet, i);
	        }

	        // Check UIDs are all unique
	        checkUniqueID(uidSet);
	    }
	    
	    /**
	     * 批量生产多个uid
	     */
	    //@Test
	    public void testSerialGenerate1(){
	    	 // Generate UID serially
	        Set<Long> uidSet = new HashSet<Long>(100);
	        
	        //生产100个uid
	        for (int i = 0; i < 100; i++) {
	            doGenerate(uidSet, i);
	        }
	        
	        for(Long uid : uidSet){
	        	System.out.println("uid:" + uid);
	        }
	    }
	    
	    /**
	     * 生产单个uid
	     */
	    @Test
	    public void testSerialGenerate2() {
	        // Generate UID
	        long uid = uidGenerator.getUID();

	        System.out.println(uid);
	        System.out.println(uidGenerator.parseUID(uid));
	    }

	    /**
	     * Test for parallel generate
	     * 
	     * @throws InterruptedException
	     * @throws IOException
	     */
	   // @Test
	    public void testParallelGenerate() throws InterruptedException, IOException {
	        AtomicInteger control = new AtomicInteger(-1);
	        Set<Long> uidSet = new ConcurrentSkipListSet<Long>();

	        // Initialize threads
	        List<Thread> threadList = new ArrayList<Thread>(THREADS);
	        for (int i = 0; i < THREADS; i++) {
	            Thread thread = new Thread(() -> workerRun(uidSet, control));
	            thread.setName("UID-generator-" + i);

	            threadList.add(thread);
	            thread.start();
	        }

	        // Wait for worker done
	        for (Thread thread : threadList) {
	            thread.join();
	        }

	        // Check generate 700w times
	        Assert.assertEquals(SIZE, control.get());

	        // Check UIDs are all unique
	        checkUniqueID(uidSet);
	    }

	    /**
	     * Woker run
	     */
	    private void workerRun(Set<Long> uidSet, AtomicInteger control) {
	        for (;;) {
	            int myPosition = control.updateAndGet(old -> (old == SIZE ? SIZE : old + 1));
	            if (myPosition == SIZE) {
	                return;
	            }

	            doGenerate(uidSet, myPosition);
	        }
	    }

	    /**
	     * Do generating
	     */
	    private void doGenerate(Set<Long> uidSet, int index) {
	        long uid = uidGenerator.getUID();
	        String parsedInfo = uidGenerator.parseUID(uid);
	        boolean existed = !uidSet.add(uid);
	        if (existed) {
	            System.out.println("Found duplicate UID " + uid);
	        }

	        // Check UID is positive, and can be parsed
	        Assert.assertTrue(uid > 0L);
	        Assert.assertTrue(StringUtils.isNotBlank(parsedInfo));

	        if (VERBOSE) {
	            System.out.println(Thread.currentThread().getName() + " No." + index + " >>> " + parsedInfo);
	        }
	    }

	    /**
	     * Check UIDs are all unique
	     */
	    private void checkUniqueID(Set<Long> uidSet) throws IOException {
	        System.out.println(uidSet.size());
	        Assert.assertEquals(SIZE, uidSet.size());
	    }
}
