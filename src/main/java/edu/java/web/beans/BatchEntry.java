package edu.java.web.beans;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by edwin on 23/01/17.
 */

/*

create table BatchEntry
(
batchEntryId bigint primary key auto_increment,
barcode varchar(50),
itemCount int default 1,
price numeric(7,2) default 0.0,
timestamp bigint default 0,
batchId bigint
) auto_increment=1000000;

 */


@Entity
@Table(name="BatchEntry")
public class BatchEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batchEntryId;

    @Column(name="barcode")
    private String barcode;

    @Column(name="itemCount")
    private int    itemCount;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="timestamp")
    private long timestamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="batchId")
    private Batch batch;

    public Long getBatchEntryId() {
        return batchEntryId;
    }

    public void setBatchEntryId(Long batchEntryId) {
        this.batchEntryId = batchEntryId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
