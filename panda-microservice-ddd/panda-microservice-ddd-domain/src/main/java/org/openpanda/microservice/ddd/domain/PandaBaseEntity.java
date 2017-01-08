package org.openpanda.microservice.ddd.domain;

import org.dayatang.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lingen on 2016/12/21.
 */
@MappedSuperclass
public abstract class PandaBaseEntity extends BaseEntity{

    @Id
    @Column(
            name = "uid_"
    )
    private String uid;
    @Version
    @Column(
            name = "version"
    )
    private int version;

    @Column(name = "create_time_")
    private long createTime;

    @Column(name = "update_time_")
    private long updateTime;

    @Column(name = "deleted_")
    private boolean deleted;


    public PandaBaseEntity(){
        this.uid = UUID.randomUUID().toString();
        this.createTime = new Date().getTime();
        this.updateTime = new Date().getTime();
        this.deleted = false;
    }

    @Override
    public Serializable getId() {
        return uid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
