package com.rakuriku.rakuriku.domain.model.compe;
// Generated Jan 16, 2022, 2:40:17 PM by Hibernate Tools 5.3.9.Final


import com.rakuriku.rakuriku.domain.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CompeEntryManagementEntity generated by hbm2java
 */
@Entity
@Table(name="compe_entry_management"
    ,schema="public"
)
public class CompeEntryManagementEntity extends BaseEntity implements java.io.Serializable {


     private String id;
     private CompeEntity compe;
     private EntriesEntity entries;
     private String eventId;
     private String roundId;
     private String adminId;
     private Integer laneNumber;
     private Integer programNumber;
     private Integer applicationRecordInt;
     private String applicationRecordDisplay;
     private Integer resultRecordInt;
     private String resultRecordDisplay;
     private Integer orderOfArrival;
     private String wind;
     private String canGoToNext;

    public CompeEntryManagementEntity() {
    }

	
    public CompeEntryManagementEntity(String id, CompeEntity compe, EntriesEntity entries, String eventId, String roundId, String adminId) {
        this.id = id;
        this.compe = compe;
        this.entries = entries;
        this.eventId = eventId;
        this.roundId = roundId;
        this.adminId = adminId;
    }
    public CompeEntryManagementEntity(String id, CompeEntity compe, EntriesEntity entries, String eventId, String roundId, String adminId, Integer laneNumber, Integer programNumber, Integer applicationRecordInt, String applicationRecordDisplay, Integer resultRecordInt, String resultRecordDisplay, Integer orderOfArrival, String wind, String canGoToNext) {
       this.id = id;
       this.compe = compe;
       this.entries = entries;
       this.eventId = eventId;
       this.roundId = roundId;
       this.adminId = adminId;
       this.laneNumber = laneNumber;
       this.programNumber = programNumber;
       this.applicationRecordInt = applicationRecordInt;
       this.applicationRecordDisplay = applicationRecordDisplay;
       this.resultRecordInt = resultRecordInt;
       this.resultRecordDisplay = resultRecordDisplay;
       this.orderOfArrival = orderOfArrival;
       this.wind = wind;
       this.canGoToNext = canGoToNext;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=36)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="compe_id", nullable=false)
    public CompeEntity getCompe() {
        return this.compe;
    }
    
    public void setCompe(CompeEntity compe) {
        this.compe = compe;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="entry_id", nullable=false)
    public EntriesEntity getEntries() {
        return this.entries;
    }
    
    public void setEntries(EntriesEntity entries) {
        this.entries = entries;
    }

    
    @Column(name="event_id", nullable=false)
    public String getEventId() {
        return this.eventId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    
    @Column(name="round_id", nullable=false)
    public String getRoundId() {
        return this.roundId;
    }
    
    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    
    @Column(name="admin_id", nullable=false)
    public String getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    
    @Column(name="lane_number")
    public Integer getLaneNumber() {
        return this.laneNumber;
    }
    
    public void setLaneNumber(Integer laneNumber) {
        this.laneNumber = laneNumber;
    }

    
    @Column(name="program_number")
    public Integer getProgramNumber() {
        return this.programNumber;
    }
    
    public void setProgramNumber(Integer programNumber) {
        this.programNumber = programNumber;
    }

    
    @Column(name="application_record_int")
    public Integer getApplicationRecordInt() {
        return this.applicationRecordInt;
    }
    
    public void setApplicationRecordInt(Integer applicationRecordInt) {
        this.applicationRecordInt = applicationRecordInt;
    }

    
    @Column(name="application_record_display", length=30)
    public String getApplicationRecordDisplay() {
        return this.applicationRecordDisplay;
    }
    
    public void setApplicationRecordDisplay(String applicationRecordDisplay) {
        this.applicationRecordDisplay = applicationRecordDisplay;
    }

    
    @Column(name="result_record_int")
    public Integer getResultRecordInt() {
        return this.resultRecordInt;
    }
    
    public void setResultRecordInt(Integer resultRecordInt) {
        this.resultRecordInt = resultRecordInt;
    }

    
    @Column(name="result_record_display", length=30)
    public String getResultRecordDisplay() {
        return this.resultRecordDisplay;
    }
    
    public void setResultRecordDisplay(String resultRecordDisplay) {
        this.resultRecordDisplay = resultRecordDisplay;
    }

    
    @Column(name="order_of_arrival")
    public Integer getOrderOfArrival() {
        return this.orderOfArrival;
    }
    
    public void setOrderOfArrival(Integer orderOfArrival) {
        this.orderOfArrival = orderOfArrival;
    }

    
    @Column(name="wind", length=10)
    public String getWind() {
        return this.wind;
    }
    
    public void setWind(String wind) {
        this.wind = wind;
    }

    
    @Column(name="can_go_to_next", length=1)
    public String getCanGoToNext() {
        return this.canGoToNext;
    }
    
    public void setCanGoToNext(String canGoToNext) {
        this.canGoToNext = canGoToNext;
    }




}


