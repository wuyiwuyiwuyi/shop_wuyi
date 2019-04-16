package com.qf.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class Goods implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;
    private String gname;
    private BigDecimal gprice;
    private int gsave;
    private String ginfo;
    private  String gimage;
    private int status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime = new Date();
    private int tid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public BigDecimal getGprice() {
        return gprice;
    }

    public void setGprice(BigDecimal gprice) {
        this.gprice = gprice;
    }

    public int getGsave() {
        return gsave;
    }

    public void setGsave(int gsave) {
        this.gsave = gsave;
    }

    public String getGinfo() {
        return ginfo;
    }

    public void setGinfo(String ginfo) {
        this.ginfo = ginfo;
    }

    public String getGimage() {
        return gimage;
    }

    public void setGimage(String gimage) {
        this.gimage = gimage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gsave=" + gsave +
                ", ginfo='" + ginfo + '\'' +
                ", gimage='" + gimage + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", tid=" + tid +
                '}';
    }

    public Goods(int id, String gname, BigDecimal gprice, int gsave, String ginfo, String gimage, int status, Date createtime, int tid) {
        this.id = id;
        this.gname = gname;
        this.gprice = gprice;
        this.gsave = gsave;
        this.ginfo = ginfo;
        this.gimage = gimage;
        this.status = status;
        this.createtime = createtime;
        this.tid = tid;
    }

    public Goods() {

    }
}
