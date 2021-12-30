package com.bookstore.tracker.helper;


import lombok.Data;
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
 * <p>
 * Class adding supplement fields in tables.
 */
@MappedSuperclass
//@EntityListeners({AuditingEntityListener.class})
@EntityListeners({SpringAuditorAware.class})
@Data
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
}
