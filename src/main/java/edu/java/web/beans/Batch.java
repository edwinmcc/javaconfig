package edu.java.web.beans;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 23/01/17.
 */


 /*
create table Batch
(
batchId bigint primary key auto_increment,
batchName varchar(100) not null,
batchSize int default 0,
noOfScans int default 0,
noOfEntries int default 0,
scanStartDate bigint default 0,
scanEndDate  bigint default 0,
userId       bigint
) auto_increment=1000000;

*/

@Entity
@Table(name="Batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batchId;

    @Column(name="batchName")
    private String batchName;

    @Column(name="batchSize")
    private int    batchSize=0;

    @Column(name="noOfScans")
    private int    noOfScans=0;

    @Column(name="noOfEntries")
    private int    noOfEntries=0;

    @Column(name="scanStartDate")
    private long   scanStartDate=0;

    @Column(name="scanEndDate")
    private long   scanEndDate=0;

    @OneToOne(targetEntity = edu.java.web.beans.User.class,fetch = FetchType.LAZY )
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "batch", fetch = FetchType.LAZY)
    private List<BatchEntry> batchEntries=new ArrayList<BatchEntry>();

    public List<BatchEntry> getBatchEntries() {
        return batchEntries;
    }

    public void setBatchEntries(List<BatchEntry> batchEntries) {
        this.batchEntries = batchEntries;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getNoOfScans() {
        return noOfScans;
    }

    public void setNoOfScans(int noOfScans) {
        this.noOfScans = noOfScans;
    }

    public int getNoOfEntries() {
        return noOfEntries;
    }

    public void setNoOfEntries(int noOfEntries) {
        this.noOfEntries = noOfEntries;
    }

    public long getScanStartDate() {
        return scanStartDate;
    }

    public void setScanStartDate(long scanStartDate) {
        this.scanStartDate = scanStartDate;
    }

    public long getScanEndDate() {
        return scanEndDate;
    }

    public void setScanEndDate(long scanEndDate) {
        this.scanEndDate = scanEndDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

