package com.demo.springbootlockdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/09/12  下午 03:14
 * Description:
 */
@RestController
public class Controller {

    private final Lock lock = new ReentrantLock();

    @PostMapping("write")
    public void write() {
        if (lock.tryLock()) {
            try {
                System.out.println("线程：" + Thread.currentThread().getName() + " 已获取到锁");
                int i = 10000;
                while (i > 0) {
                    CacheData.data.put(--i + "", new Date());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("锁未释放，线程" + Thread.currentThread().getName() + "不可访问!");
        }
    }

    @GetMapping("read")
    public Map read() {
        return CacheData.getData();
    }
}
