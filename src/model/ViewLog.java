// ViewLog.java (in model package)
package model;

import java.util.Date;

public class ViewLog {
    private int logId;
    private String tableName;
    private int recordId;
    private String actionType;
    private String actionDescription;
    private String oldValues;
    private String newValues;
    private String performedBy;
    private Date performedAt;

    public ViewLog() {}

    // Getters and setters
    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }
    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getActionDescription() { return actionDescription; }
    public void setActionDescription(String actionDescription) { this.actionDescription = actionDescription; }
    public String getOldValues() { return oldValues; }
    public void setOldValues(String oldValues) { this.oldValues = oldValues; }
    public String getNewValues() { return newValues; }
    public void setNewValues(String newValues) { this.newValues = newValues; }
    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
    public Date getPerformedAt() { return performedAt; }
    public void setPerformedAt(Date performedAt) { this.performedAt = performedAt; }
}