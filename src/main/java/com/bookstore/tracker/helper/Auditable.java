package com.bookstore.tracker.helper;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@MappedSuperclass
//@EntityListeners({AuditingEntityListener.class})
@EntityListeners({SpringAuditorAware.class})
public abstract class Auditable<U> {

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected Date createDate;

    @LastModifiedBy
    protected U modifiedBy;

    @LastModifiedDate
    protected Date modifiedDate;

    @Version
    protected long version;

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public U getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(U modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
