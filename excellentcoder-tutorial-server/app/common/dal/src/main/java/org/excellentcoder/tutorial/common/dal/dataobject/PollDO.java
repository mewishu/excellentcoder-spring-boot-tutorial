package org.excellentcoder.tutorial.common.dal.dataobject;

import java.util.Date;

public class PollDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.question
     *
     * @mbg.generated
     */
    private String question;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.gmt_expiration
     *
     * @mbg.generated
     */
    private Date gmtExpiration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.created_by
     *
     * @mbg.generated
     */
    private Long createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column polls.updated_by
     *
     * @mbg.generated
     */
    private Long updatedBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.id
     *
     * @return the value of polls.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.id
     *
     * @param id the value for polls.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.question
     *
     * @return the value of polls.question
     *
     * @mbg.generated
     */
    public String getQuestion() {
        return question;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.question
     *
     * @param question the value for polls.question
     *
     * @mbg.generated
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.gmt_expiration
     *
     * @return the value of polls.gmt_expiration
     *
     * @mbg.generated
     */
    public Date getGmtExpiration() {
        return gmtExpiration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.gmt_expiration
     *
     * @param gmtExpiration the value for polls.gmt_expiration
     *
     * @mbg.generated
     */
    public void setGmtExpiration(Date gmtExpiration) {
        this.gmtExpiration = gmtExpiration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.gmt_create
     *
     * @return the value of polls.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.gmt_create
     *
     * @param gmtCreate the value for polls.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.gmt_modified
     *
     * @return the value of polls.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.gmt_modified
     *
     * @param gmtModified the value for polls.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.created_by
     *
     * @return the value of polls.created_by
     *
     * @mbg.generated
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.created_by
     *
     * @param createdBy the value for polls.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column polls.updated_by
     *
     * @return the value of polls.updated_by
     *
     * @mbg.generated
     */
    public Long getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column polls.updated_by
     *
     * @param updatedBy the value for polls.updated_by
     *
     * @mbg.generated
     */
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table polls
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PollDO other = (PollDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestion() == null ? other.getQuestion() == null : this.getQuestion().equals(other.getQuestion()))
            && (this.getGmtExpiration() == null ? other.getGmtExpiration() == null : this.getGmtExpiration().equals(other.getGmtExpiration()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table polls
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestion() == null) ? 0 : getQuestion().hashCode());
        result = prime * result + ((getGmtExpiration() == null) ? 0 : getGmtExpiration().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table polls
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", question=").append(question);
        sb.append(", gmtExpiration=").append(gmtExpiration);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append("]");
        return sb.toString();
    }
}