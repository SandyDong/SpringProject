package com.dong.quartz;

import com.dong.service.OrderInfoQueryService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

/**
 * 创建一个定时任务定时获取数据task
 *
 * 其本质就是在job方法中注入Schedular的对象，从Schedular中获取Trigger（触发器），然后修改触发器的条件，重新启动。
 *
 * 前文中的方法，其实已经涉及到了循环调用。
 *
 * Job类 => Job => Trigger => Schedular =>Job类。
 *
 * 这样是很容易发生问题的，也确实在实际项目中发生了问题，特别是当Schedular中有多个trigger的时候，注入实际的Job工作类时候报错了，产生的原因应该就是因为循环调用的问题。
 *
 * 方法2：
 *
 * 既然我们已经通过在Srping的Bean的XML文件中配置了SchedulerFactory产生的Bean，那么完全可以在实际的Job中通过@Resource或者@Autowired注入，再仔细一思考，仍然不对啊，这个和方法一的循环调用一样，会出现问题（实际也是）。
 *
 * 此时处理办法是：懒加载Schdular的类，用到的时候才加载。
 */
public class GetDataEveryMinuteJob {

    private static final Logger logger = LoggerFactory.getLogger(GetDataEveryMinuteJob.class);

    @Autowired
    private OrderInfoQueryService orderInfoQueryService;

    public void printDataInfo(){
        logger.info("定时获取的数据===>{}",orderInfoQueryService.getOrderInfo());
//        System.out.println("定时获取的数据是:===========>"+System.currentTimeMillis());

    }

    /**
     * 动态修改定时器
     *
     */
    @Resource(name = "schedulerFactoryBean")
    @Lazy
    private Scheduler scheduler;

   /* public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }*/

   /* public void restJob(long time) {
        TriggerKey triggerKey=new TriggerKey("dayTrigger", Scheduler.DEFAULT_GROUP);
        SimpleTriggerImpl simpleTrigger=null;
        try {
            simpleTrigger= (SimpleTriggerImpl) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        simpleTrigger.setRepeatInterval(time);

        try {
            scheduler.rescheduleJob(triggerKey,simpleTrigger);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    /**
     * 重置定时任务
     * @Title: restJob
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param  @param time
     * @param  @throws Exception
     * @return void    返回类型
     * @throws
     */
    public void restJob(long time) throws Exception {
        TriggerKey triggerKey = new TriggerKey("dayTrigger", Scheduler.DEFAULT_GROUP);
        SimpleTriggerImpl simpleTrigger = (SimpleTriggerImpl) scheduler.getTrigger(triggerKey);
        simpleTrigger.setRepeatInterval(time);
        scheduler.rescheduleJob(triggerKey, simpleTrigger);
    }
}
