package me.remainingtoast.toastclient.util;

import net.minecraft.client.Minecraft;

public class TimerUtil {
    private long time;
    private static final Minecraft mc = Minecraft.getMinecraft();

    public TimerUtil(){
        time = -1;
    }

    public boolean passed(double ms){
        return System.currentTimeMillis() - this.time >= ms;
    }

    public void reset()
    {
        this.time = System.currentTimeMillis();
    }

    public void resetTimeSkipTo(long ms)
    {
        this.time = System.currentTimeMillis() + ms;
    }

    public long getTime()
    {
        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

}
